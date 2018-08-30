package com.jarvis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jarvis.resources.User;
import com.jarvis.service.NewsFeedManager;

/**
 * @author ravik
 * @@since 29/08/18 2:56 PM
 */

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    NewsFeedManager newsFeedManager;

    @RequestMapping(method = RequestMethod.POST)
    public User createUser(@RequestBody User usr) {
        return newsFeedManager.createUser(usr);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public User getTestuser() {
        User usr = new User();
        usr.setId(1345);
        usr.setName("Test Name");
        usr.setAge(34);
        return usr;
    }
}
