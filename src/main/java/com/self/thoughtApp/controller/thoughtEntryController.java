package com.self.thoughtApp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.self.thoughtApp.entity.thoughtEntry;
import com.self.thoughtApp.entity.User;
import com.self.thoughtApp.service.thoughtEntryService;
import com.self.thoughtApp.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/thought")
@Tag(name = "thought APIs")
public class thoughtEntryController {

    @Autowired
    private thoughtEntryService thoughtEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Get all thought entries of a user")
    public ResponseEntity<?> getAllthoughtEntriesOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<thoughtEntry> all = user.getThoughtEntries();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<thoughtEntry> createEntry(@RequestBody thoughtEntry myEntry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            thoughtEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getthoughtEntryById(@PathVariable String myId) {
        ObjectId objectId = new ObjectId(myId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<thoughtEntry> collect = user.getThoughtEntries().stream().filter(x -> x.getId().equals(objectId)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<thoughtEntry> thoughtEntry = thoughtEntryService.findById(objectId);
            if (thoughtEntry.isPresent()) {
                return new ResponseEntity<>(thoughtEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deletethoughtEntryById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        boolean removed = thoughtEntryService.deleteById(myId, username);
        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<?> updatethoughtById(@PathVariable ObjectId myId, @RequestBody thoughtEntry newEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<thoughtEntry> collect = user.getThoughtEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<thoughtEntry> thoughtEntry = thoughtEntryService.findById(myId);
            if (thoughtEntry.isPresent()) {
                thoughtEntry old = thoughtEntry.get();
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
                thoughtEntryService.saveEntry(old);
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}