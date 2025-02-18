package com.self.thoughtApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.self.thoughtApp.entity.thoughtEntry;

public interface thoughtEntryRepository extends MongoRepository<thoughtEntry, ObjectId> {

}
