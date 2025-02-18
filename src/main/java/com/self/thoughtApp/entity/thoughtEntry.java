package com.self.thoughtApp.entity;

import lombok.*;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.self.thoughtApp.enums.Sentiment;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "thought_entries")
@Data
@NoArgsConstructor
public class thoughtEntry {
    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;
    private Sentiment sentiment;
}
