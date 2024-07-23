package com.project.footballstockmarket.service;

import com.project.footballstockmarket.dao.UserDao;
import com.project.footballstockmarket.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final SecurityUtils securityUtils;
    private final UserDao userDao;

    @Autowired
    public UserService(SecurityUtils securityUtils, UserDao userDao) {
        this.securityUtils = securityUtils;
        this.userDao = userDao;
    }

    /*
    A method which creates the basis of an account
    Param: accountPayload - The map payload from creating the account
    Returns the response created based on the success of account creation
    */
    public ResponseEntity<String> createAccount(Map<String, String> accountPayload){
        LOGGER.debug("Creating account for user {}", accountPayload.get("email"));

        String encodedPassword = securityUtils.encodePassword(accountPayload.get("password"));
        ResponseEntity<String> response = userDao.createUser(accountPayload.get("email"), encodedPassword);
        return response;
    }

    /*
    A method which updates account info
    Params: accountId - The accounts ID
    Params: updatePayload - The payload of fields to update
    Returns the response created based on success of account update
    */
    public ResponseEntity<String> updateAccount(long accountId, Map<String, String> updatePayload) {
        LOGGER.debug("Updating account {}", accountId);
        return null;
    }
}
