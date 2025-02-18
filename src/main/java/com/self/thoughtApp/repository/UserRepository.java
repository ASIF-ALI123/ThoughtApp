package com.self.thoughtApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.self.thoughtApp.entity.thoughtEntry;
import com.self.thoughtApp.entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String username);

    void deleteByUserName(String username);
}
