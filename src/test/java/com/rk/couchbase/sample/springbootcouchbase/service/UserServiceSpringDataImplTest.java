package com.rk.couchbase.sample.springbootcouchbase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class UserServiceSpringDataImplTest extends AbstractTest{

    @Autowired
    @Qualifier("userServiceSpringDataImpl")
    private UserService userService;


    @Override
    protected UserService getUserService() {
        return userService;
    }
}