package com.jarvis.dao.neo4j;

/**
 * @author ravik
 * @@since 29/08/18 2:45 PM
 */
public interface CQLQueries {

    String CREATE_USER_NODE = "CREATE (us:User {Name: {name},Age:{age}}) RETURN ID(us)";

}
