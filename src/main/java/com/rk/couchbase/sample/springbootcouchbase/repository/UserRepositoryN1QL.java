package com.rk.couchbase.sample.springbootcouchbase.repository;

public interface UserRepositoryN1QL extends UserRepository{

    void updateFirstName(String userId, String firstName);
}
