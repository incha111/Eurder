package com.eurder.eurder.service.security;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SecurityRepository {
    private final Map<String,User> securityMap;

    public SecurityRepository() {
        this.securityMap = new HashMap<>();
        securityMap.put("admin@eurder.com",new User("Vincent","Lesage","admin@eurder.com","123",Role.ADMIN));

    }
    public void addUser(User user){
        securityMap.put(user.getEmail(),user);
    }

    public User getUser(String email){
        return securityMap.get(email);
    }

}
