package com.rk.couchbase.sample.springbootcouchbase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class UserServiceN1QLImplTest extends AbstractTest{

    @Autowired
    @Qualifier("userServiceN1QLImpl")
    private UserService userService;


    @Override
    protected UserService getUserService() {
        return userService;
    }
}