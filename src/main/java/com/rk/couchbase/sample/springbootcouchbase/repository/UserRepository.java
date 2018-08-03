package com.rk.couchbase.sample.springbootcouchbase.repository;

import com.rk.couchbase.sample.springbootcouchbase.entity.User;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.repository.CrudRepository;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "user")
public interface UserRepository extends CrudRepository<User, String> {

    public User findByUserId(String userId);
}
