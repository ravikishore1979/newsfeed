package com.jarvis.dao.neo4j;

import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;
import org.neo4j.driver.v1.Values;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jarvis.dao.NewsFeedDAO;
import com.jarvis.resources.User;

/**
 * @author ravik
 * @@since 29/08/18 2:26 PM
 */

@Repository(value = "neo4jHandler")
public class Neo4jHandler implements NewsFeedDAO {

    private Driver neoDriver;

    @Override
    public User addUserToGraph(User usr) {
        try (Session session = this.neoDriver.session()) {
            int userId = session.writeTransaction(new TransactionWork<Integer>() {
                @Override
                public Integer execute(Transaction transaction) {
                    return createUser(transaction, usr);
                }
            });
            usr.setId(userId);
        }
        return usr;
    }

    @Autowired
    public void setNeoDriver(Driver neoDriver) {
        this.neoDriver = neoDriver;
    }

    private int createUser(Transaction tx, User usr) {
        StatementResult rs = tx.run(CQLQueries.CREATE_USER_NODE, Values.parameters("name", usr.getName(), "age", usr.getAge()));
        return rs.single().get(0).asInt();
    }
}
