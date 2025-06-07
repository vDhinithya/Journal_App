/*
 in this, going to create user logging functionality
 if there are many users, and they will have multiple journal entries and each journal entry will be associated with a specific user
 so we want to show the entries of a particular user

 */
package com.dhinithya.journalApp.entity;
import lombok.*;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Document(collection = "users")// this annotation will say spring that this entity is mapped with a mongodb collection
@Data

public class User {
    @Id
    private ObjectId id;

    @Indexed(unique = true) // because username must be unique/ cannot be same || since we have used Indexed, it will not create auto index here so we will use in (spring.data.mongodb.auto-index-creation=true) app-prop
    @NonNull
    private String userName;

    @NonNull
    private String password;

    private String email;
    private boolean sentimentAnalysis;

    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();
    private List<String> roles;

}