package com.rk.couchbase.sample.springbootcouchbase.repository;

import com.rk.couchbase.sample.springbootcouchbase.entity.User;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "user", viewName = "all")
public interface UserRepository extends CrudRepository<User, String> {

    public Optional<User> findByUserId(String userId);

    public Optional<User> findByFirstName(String firstName);

}
