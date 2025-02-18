package com.self.thoughtApp.service;

import lombok.extern.slf4j.Slf4j;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.self.thoughtApp.entity.thoughtEntry;
import com.self.thoughtApp.entity.User;
import com.self.thoughtApp.repository.thoughtEntryRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class thoughtEntryService {

    @Autowired
    private thoughtEntryRepository thoughtEntryRepository;

    @Autowired
    private UserService userService;



    @Transactional
    public void saveEntry(thoughtEntry thoughtEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            thoughtEntry.setDate(LocalDateTime.now());
            thoughtEntry saved = thoughtEntryRepository.save(thoughtEntry);
            user.getThoughtEntries().add(saved);
            userService.saveUser(user);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while saving the entry.", e);
        }
    }

    public void saveEntry(thoughtEntry thoughtEntry) {
        thoughtEntryRepository.save(thoughtEntry);
    }

    public List<thoughtEntry> getAll() {
        return thoughtEntryRepository.findAll();
    }

    public Optional<thoughtEntry> findById(ObjectId id) {
        return thoughtEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getThoughtEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                thoughtEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            log.error("Error ",e);
            throw new RuntimeException("An error occurred while deleting the entry.", e);
        }
        return removed;
    }

}