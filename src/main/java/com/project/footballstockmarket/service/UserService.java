package com.project.footballstockmarket.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService() {
    }
}
