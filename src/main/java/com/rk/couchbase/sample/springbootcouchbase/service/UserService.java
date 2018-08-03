package com.rk.couchbase.sample.springbootcouchbase.service;

import com.rk.couchbase.sample.springbootcouchbase.entity.User;

import java.util.Map;

public interface UserService {
    public User saveUser(User user);

    public User findUserByUserId(String userId);

    public Iterable<User> findAllUsers();

    public User updateUser(User user);

    public void deleteUser(String id);

    public User updateAttributes(String userId, Map<String, String> attributeUpdates);
}
