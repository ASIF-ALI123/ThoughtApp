package com.self.thoughtApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "config_thought_app")
@Data
@NoArgsConstructor
public class ConfigthoughtAppEntity {

    private String key;
    private String value;

}
