package com.rk.couchbase.sample.springbootcouchbase.service;

import com.rk.couchbase.sample.springbootcouchbase.entity.User;
import com.rk.couchbase.sample.springbootcouchbase.repository.UserRepository;

import java.util.Optional;

public abstract class AbstractUserService implements UserService{

    public abstract UserRepository getRepository();

    @Override
    public User saveUser(User user) {
        return getRepository().save(user);
    }

    @Override
    public Optional<User> findUserByUserId(String userId) {
        return getRepository().findByUserId(userId);
    }

    @Override
    public Optional<User> findUserByFirstName(String firstName) {
        return getRepository().findByFirstName(firstName);
    }

    public Iterable<User> findAllUsers() {
        return getRepository().findAll();
    }

    @Override
    public User updateUser(User user) {
        return getRepository().save(user);
    }

    @Override
    public void deleteUser(String id) {
        getRepository().deleteById(id);
    }

    @Override
    public void deleteAllUsers() {
        getRepository().deleteAll();
    }
}
