package com.dhinithya.journalApp.Repository;

import com.dhinithya.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserForSA() {
        // to get used from db wholes email is registered and opted for sentiment analysis using CRITERIA
        // criteria and query goes hand in hand
        Query query = new Query();
        //query.addCriteria(Criteria.where("userName").is("KNOX"));
        // query to check query.addCriteria(Criteria.where("field").ne("value")); ne for not equal, lt for less than, lte for less than equals to gt, gte

//        query.addCriteria(Criteria.where("email").exists(true));
//        query.addCriteria(Criteria.where("email").ne(null).ne(""));
        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$")); // with using this we can check the regular email expression
        query.addCriteria(Criteria.where("sentimentAnalysis").exists(true));

        // for using "and" / "or" operator we can use this as

//        Criteria criteria = new Criteria();
//        query.addCriteria(criteria.orOperator(
//                Criteria.where("email").exists(true),
//                Criteria.where("sentimentAnalysis").exists(true)
//        ));
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }
}
