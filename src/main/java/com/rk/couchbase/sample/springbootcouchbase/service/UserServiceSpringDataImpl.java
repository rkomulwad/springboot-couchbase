package com.rk.couchbase.sample.springbootcouchbase.service;

import com.rk.couchbase.sample.springbootcouchbase.entity.User;
import com.rk.couchbase.sample.springbootcouchbase.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@Qualifier("userServiceSpringDataImpl")
public class UserServiceSpringDataImpl extends AbstractUserService {
    @Autowired
    @Qualifier("userRepository")
    private UserRepository repository;

    @Override
    public void updateAttributes(String userId, Map<String, String> attributeUpdates) {
        Optional<User> userOp = findUserByUserId(userId);
        userOp.ifPresent(user -> {
            if(attributeUpdates.containsKey("firstName")) {
                user.setFirstName(attributeUpdates.get("firstName"));
            }
            updateUser(user);
        });
    }


    @Override
    public UserRepository getRepository() {
        return repository;
    }
}
