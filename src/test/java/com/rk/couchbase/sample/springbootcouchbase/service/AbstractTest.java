package com.rk.couchbase.sample.springbootcouchbase.service;

import com.rk.couchbase.sample.springbootcouchbase.entity.Address;
import com.rk.couchbase.sample.springbootcouchbase.entity.User;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = AbstractTest.CouchbaseTestConfig.class)
@SpringBootTest()
public abstract class AbstractTest {

    /*
    public static final String clusterUser = "Administrator";
    public static final String clusterPassword = "password";

    @ClassRule
    public static CouchbaseContainer couchbaseContainer = new CouchbaseContainer()
            .withFTS(true)
            .withIndex(true)
            .withQuery(true)
            .withClusterUsername(clusterUser)
            .withClusterPassword(clusterPassword)
            .withNewBucket(DefaultBucketSettings.builder()
                    .name("userbucket")
                    .password("password")
                    .enableFlush(true)
                    .type(BucketType.COUCHBASE)
                    .quota(200)
                    .build());

    @Configuration
    static class CouchbaseTestConfig implements CouchbaseConfigurer {

        @PostConstruct
        public void init() throws Exception {
            waitForContainer();
        }

        public void waitForContainer(){
            CouchbaseWaitStrategy s = new CouchbaseWaitStrategy();
            s.withBasicCredentials(clusterUser, clusterPassword);
            s.waitUntilReady(couchbaseContainer);
        }

        @Override
        public CouchbaseEnvironment couchbaseEnvironment() throws Exception {
            return couchbaseContainer.getCouchbaseEnvironment();
        }

        @Override
        public Cluster couchbaseCluster() throws Exception {
            return couchbaseContainer.getCouchbaseCluster();
        }

        @Override
        public ClusterInfo couchbaseClusterInfo() throws Exception {
            Cluster cc = couchbaseCluster();
            ClusterManager manager = cc.clusterManager(clusterUser, clusterPassword);
            return manager.info();
        }

        @Override
        public Bucket couchbaseClient() throws Exception {
            return couchbaseContainer.getCouchbaseCluster().openBucket("userbucket");
        }
    }
    */

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        getUserService().deleteAllUsers();
    }

    protected abstract UserService getUserService();

    @Test
    public void test(){
        String id = "rick";
        User user = new User();
        user.setId(id);
        user.setUserId(user.getId());
        user.setFirstName("Rick");
        user.setLastName("jobs");
        user.setEmail("rick.jobs@mail.com");

        //add 2 addresses
        Address address = new Address();
        address.setAddressLineOne("add one");
        user.addAddress(address);
        address = new Address();
        address.setAddressLineOne("add two");
        user.addAddress(address);

        //save the user
        User userActual = getUserService().saveUser(user);
        Assert.assertNotNull(userActual);

        Optional<User> userSaved = getUserService().findUserByUserId(user.getUserId());
        Assert.assertTrue(userSaved.isPresent());
        Assert.assertEquals(2, userSaved.get().getAddresses().size());

        //update just the first name
        /*
        Map<String, String> attributes = new HashMap<>();
        attributes.put("firstName", "test first name");
        userService.updateAttributes(user.getId(), attributes);

        //read the user
        User userWithUpdateFirstName = userService.findUserByUserId(user.getUserId());

        Assert.assertEquals("test first name", userWithUpdateFirstName.getFirstName());
        */

        //update the user
        user.setFirstName("test first name 2");

        getUserService().updateUser(user);

        //find the user with the updated firstname
        Optional<User> userWithUpdatedFirstName = getUserService().findUserByFirstName(user.getFirstName());
        Assert.assertTrue(userWithUpdatedFirstName.isPresent());

        Iterable<User> users = getUserService().findAllUsers();

        Assert.assertEquals(1, users.spliterator().getExactSizeIfKnown());


        getUserService().deleteUser(user.getId());

        users = getUserService().findAllUsers();

        Assert.assertEquals(0, users.spliterator().getExactSizeIfKnown());

    }

}
