package com.self.thoughtApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.self.thoughtApp.entity.ConfigthoughtAppEntity;
import com.self.thoughtApp.entity.User;

public interface ConfigthoughtAppRepository extends MongoRepository<ConfigthoughtAppEntity, ObjectId> {

}
