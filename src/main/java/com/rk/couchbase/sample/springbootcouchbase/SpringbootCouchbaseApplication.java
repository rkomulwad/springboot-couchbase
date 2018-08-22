package com.rk.couchbase.sample.springbootcouchbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import java.util.Properties;

@SpringBootApplication
@EnableCouchbaseRepositories
public class SpringbootCouchbaseApplication {

	public static void main(String[] args) {
		Properties systemProperties = System.getProperties();
		systemProperties.put("net.spy.log.LoggerImpl", "net.spy.memcached.compat.log.SLF4JLogger");
		System.setProperties(systemProperties);

		SpringApplication.run(SpringbootCouchbaseApplication.class, args);
	}
}
