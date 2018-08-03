package com.rk.couchbase.sample.springbootcouchbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

@SpringBootApplication
@EnableCouchbaseRepositories
public class SpringbootCouchbaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCouchbaseApplication.class, args);
	}
}
