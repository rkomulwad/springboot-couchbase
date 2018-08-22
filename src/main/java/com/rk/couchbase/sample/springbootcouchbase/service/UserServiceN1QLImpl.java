package com.rk.couchbase.sample.springbootcouchbase.service;

import com.rk.couchbase.sample.springbootcouchbase.repository.UserRepository;
import com.rk.couchbase.sample.springbootcouchbase.repository.UserRepositoryN1QL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Qualifier("userServiceN1QLImpl")
public class UserServiceN1QLImpl extends AbstractUserService {

    @Autowired
    @Qualifier("userRepositoryN1QL")
    private UserRepositoryN1QL userRepository;

    @Override
    public void updateAttributes(String userId, Map<String, String> attributeUpdates) {
        if(attributeUpdates.containsKey("firstName")) {
            userRepository.updateFirstName(userId, attributeUpdates.get("firstName"));
        }
    }

    @Override
    public UserRepository getRepository() {
        return userRepository;
    }
}
