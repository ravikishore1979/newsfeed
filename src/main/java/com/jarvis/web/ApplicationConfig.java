package com.jarvis.web;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.jarvis.dao.NewsFeedDAO;

/**
 * @author ravik
 * @@since 29/08/18 4:35 PM
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "com.jarvis.controllers",
        "com.jarvis.dao",
        "com.jarvis.service"})
@PropertySource({"classpath:application.properties"})
public class ApplicationConfig {

    @Autowired
    ApplicationContext context;

    @Value("${neo4j.graphdb.uri}")
    private String uri;

    @Value("${neo4j.graphdb.user}")
    private String usr;

    @Value("${neo4j.graphdb.pwd}")
    private String pwd;

    @Value("${daolayer.bean}")
    private String daoLayer;

    @Bean
    public Driver getNeo4jDriver() {
        return GraphDatabase.driver(this.uri, AuthTokens.basic(this.usr, this.pwd));
    }

    @Bean
    public NewsFeedDAO getDaoLayer(@Value("${daolayer.bean}") String daoName) {
        return (NewsFeedDAO) context.getBean(daoName);
    }
}
