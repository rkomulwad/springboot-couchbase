package com.rk.couchbase.sample.springbootcouchbase.service;

import com.rk.couchbase.sample.springbootcouchbase.entity.User;
import com.rk.couchbase.sample.springbootcouchbase.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }

    @Override
    public User findUserByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    public Iterable<User> findAllUsers() {
        return repository.findAll();
    }

    @Override
    public User updateUser(User user) {
        return repository.save(user);
    }

    @Override
    public void deleteUser(String id) {
        repository.delete(findUserByUserId(id));
    }

    @Override
    public User updateAttributes(String userId, Map<String, String> attributeUpdates) {
        User user = findUserByUserId(userId);
        if(attributeUpdates.containsKey("firstName"))
            user.setFirstName(attributeUpdates.get("firstName"));
        else if(attributeUpdates.containsKey("lastName"))
            user.setFirstName(attributeUpdates.get("lastName"));
        else if(attributeUpdates.containsKey("email"))
            user.setFirstName(attributeUpdates.get("email"));

        return updateUser(user);
    }
}
