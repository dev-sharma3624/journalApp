package com.example.learningSpringboot.journalApp.repository;

import com.example.learningSpringboot.journalApp.entity.JournalEntry;
import com.example.learningSpringboot.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName (String username);
}
