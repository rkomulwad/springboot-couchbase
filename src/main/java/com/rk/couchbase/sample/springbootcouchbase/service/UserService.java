package com.rk.couchbase.sample.springbootcouchbase.service;

import com.rk.couchbase.sample.springbootcouchbase.entity.User;

import java.util.Map;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    Optional<User> findUserByUserId(String userId);

    Optional<User> findUserByFirstName(String firstName);

    Iterable<User> findAllUsers();

    User updateUser(User user);

    void deleteUser(String id);

    void updateAttributes(String userId, Map<String, String> attributeUpdates);

    void deleteAllUsers();
}
