package com.jarvis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jarvis.dao.NewsFeedDAO;
import com.jarvis.resources.User;

/**
 * @author ravik
 * @@since 29/08/18 4:51 PM
 */
@Service
public class NewsFeedManagerImpl implements NewsFeedManager {

    @Autowired
    @Qualifier("getDaoLayer")
    private NewsFeedDAO newsFeedDAO;

    @Override
    public User createUser(User usr) {
        return this.newsFeedDAO.addUserToGraph(usr);
    }
}
