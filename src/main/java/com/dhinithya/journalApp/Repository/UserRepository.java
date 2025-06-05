package com.dhinithya.journalApp.Repository;

import com.dhinithya.journalApp.entity.JournalEntry;
import com.dhinithya.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> { // mongorepository is an interface given by spring mongodb which is useful in CRUD operations
    User findByuserName(String username);

    void deleteByUserName(String name);

//    void deleteByUserName(String userName);
}
