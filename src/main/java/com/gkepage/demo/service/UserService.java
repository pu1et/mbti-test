package com.gkepage.demo.service;

import java.util.List;

import javax.annotation.PostConstruct;

import com.gkepage.demo.db.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/*

@Service
public class UserService {
    
    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    Datastore datastore;

    private KeyFactory userKeyFactory;

    @PostConstruct
    public void initalizeKeyFactories(){
        log.info("Initializing key factories");
        userKeyFactory = datastore.newKeyFactory();
    }

    public Entity createUser(User user){
        return datastore.put(createUserEntity(user));
    }

    /*한번에 저장
    public Batch.Response createUser(BatchUser users){
        Batch batch = datastore.newBatch();
        for(User user: users.getUsers()){
            batch.put(createUserEntity(user));
        }
        return batch.submit();
    }
    

    public Entity createUserEntity(User user){
        Key key = userKeyFactory.newKey(user.getId());
        return Entity.newBuilder(key)
                    .set("from", user.getFrom())
                    .set("stars", user.getStars())
                    .set("to", user.getTo())
                    .build();
    }
    
    //public List<User> findByFrom(String from){
    //}

    //public List<User> findByTo(String to){
//}

    
    @Async
    public void updateUser(String id, User user){
        //
    }

    @Async
    public void deleteUser(String id){
         //
    }


}
*/