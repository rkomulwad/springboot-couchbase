package com.rk.couchbase.sample.springbootcouchbase.controller;

import com.rk.couchbase.sample.springbootcouchbase.entity.User;
import com.rk.couchbase.sample.springbootcouchbase.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    @Qualifier("userServiceSpringDataImpl")
    private UserService service;

    @GetMapping("/{userId}")
    public ResponseEntity<User> read(@PathVariable("userId") String userId){
        return service.findUserByUserId(userId)
                .map(user -> ResponseEntity.ok(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> readAll(){
        return ResponseEntity.ok(service.findAllUsers());
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.ok(service.saveUser(user));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> update(@PathVariable("userId") String userId, @RequestBody User user) {
        return ResponseEntity.ok(service.saveUser(user));
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<User> updateFirstName(@PathVariable("userId") String userId, @RequestBody Map<String, String> attributeUpdates) {
        service.updateAttributes(userId, attributeUpdates);
        return ResponseEntity.ok(service.findUserByUserId(userId).get());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable("userId") String userId) {
        service.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

}
