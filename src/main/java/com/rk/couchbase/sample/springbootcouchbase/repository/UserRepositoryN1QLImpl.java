package com.rk.couchbase.sample.springbootcouchbase.repository;

import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.couchbase.client.java.query.ParameterizedN1qlQuery;
import com.couchbase.client.java.query.SimpleN1qlQuery;
import com.couchbase.client.java.query.consistency.ScanConsistency;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rk.couchbase.sample.springbootcouchbase.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryN1QLImpl implements UserRepositoryN1QL{

    private Logger LOG = LoggerFactory.getLogger(UserRepositoryN1QLImpl.class);

    @Autowired
    private CouchbaseTemplate couchbaseTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void updateFirstName(String userId, String firstName) {
        String paStatement = "UPDATE `userbucket` USE KEYS $1 SET firstName=$2";

        N1qlQuery query = N1qlQuery.parameterized(paStatement, JsonArray.from(userId, firstName));
        LOG.info("executing query -- "+query.n1ql());
        couchbaseTemplate.getCouchbaseBucket().query(query);
    }


    @Override
    public User save(User user) {
        String queryStr = "upsert into `userbucket` (KEY,VALUE) VALUES ($1, {'userId': $2, 'firstName': $3, 'lastName': $4, 'email': $5})";
        JsonArray parameters = JsonArray.create()
                .add(user.getId())
                .add(user.getUserId())
                .add(user.getFirstName())
                .add(user.getLastName())
                .add(user.getEmail());

        ParameterizedN1qlQuery query = ParameterizedN1qlQuery.parameterized(queryStr, parameters);
        LOG.info("executing query -- "+query.n1ql());
        N1qlQueryResult queryResult = couchbaseTemplate.getCouchbaseBucket().query(query);

        return user;
    }

    private List<User> extractResult(N1qlQueryResult result) throws IOException {
        if (!result.finalSuccess()) {
            throw new DataRetrievalFailureException("Query error: " + result.errors());
        }
        final List<User> content = new ArrayList<User>();
        for (N1qlQueryRow row : result) {
            content.add(mapper.readValue(row.value().toString(), User.class));
        }
        return content;
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        String queryStr = "SELECT `userbucket`.* " +
                "FROM `userbucket` AS users " +
                "WHERE META(users).id = $1";
        ParameterizedN1qlQuery query = ParameterizedN1qlQuery.parameterized(queryStr, JsonArray.create().add(userId));
        query.params().consistency(ScanConsistency.STATEMENT_PLUS);
        LOG.info("executing query -- "+query.n1ql());
        N1qlQueryResult queryResult = couchbaseTemplate.getCouchbaseBucket().query(query);
        try {
            List<User> users = extractResult(queryResult);
            if(!users.isEmpty())
                return Optional.of(users.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Iterable<User> findAll() {
        String queryStr = "SELECT META(users).id, userId, firstName, lastName, email " +
                "FROM `userbucket` AS users ";
        SimpleN1qlQuery query = ParameterizedN1qlQuery.simple(queryStr);
        LOG.info("executing query -- "+query.n1ql());
        N1qlQueryResult queryResult = couchbaseTemplate.getCouchbaseBucket().query(query);
        try {
            return extractResult(queryResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteById(String id) {
        String queryStr = "DELETE " +
                "FROM `userbucket` AS users " +
                "WHERE META(users).id = $1";
        ParameterizedN1qlQuery query = ParameterizedN1qlQuery.parameterized(queryStr, JsonArray.create().add(id));
        LOG.info("executing query -- "+query.n1ql());
        N1qlQueryResult queryResult = couchbaseTemplate.getCouchbaseBucket().query(query);
    }

    @Override
    public void deleteAll() {
        String queryStr = "DELETE " +
                "FROM `userbucket` AS users ";
        SimpleN1qlQuery query = ParameterizedN1qlQuery.simple(queryStr);
        LOG.info("executing query -- "+query.n1ql());
        N1qlQueryResult queryResult = couchbaseTemplate.getCouchbaseBucket().query(query);
    }

    @Override
    public Optional<User> findByFirstName(String firstName) {
        String queryStr = "SELECT META(users).id, userId, firstName, lastName, email " +
                "FROM `userbucket` AS users " +
                "WHERE firstName = $1";
        ParameterizedN1qlQuery query = ParameterizedN1qlQuery.parameterized(queryStr, JsonArray.create().add(firstName));
        LOG.info("executing query -- "+query.n1ql());
        N1qlQueryResult queryResult = couchbaseTemplate.getCouchbaseBucket().query(query);
        try {
            List<User> users = extractResult(queryResult);
            if(!users.isEmpty())
                return Optional.of(users.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
        //TBD
        return null;
    }

    @Override
    public Optional<User> findById(String s) {
        //TBD
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        //TBD
        return false;
    }

    @Override
    public Iterable<User> findAllById(Iterable<String> strings) {
        //TBD
        return null;
    }

    @Override
    public long count() {
        //TBD
        return 0;
    }

    @Override
    public void delete(User entity) {
        //TBD
    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {
        //TBD
    }
}
