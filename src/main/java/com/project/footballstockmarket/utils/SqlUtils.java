package com.project.footballstockmarket.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SqlUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(SqlUtils.class);
    private final Map<String, String> userMap = new HashMap<>();

    public SqlUtils(){
        generateUserSql();
    }

    public Map<String, String> getUserMap() {
        return userMap;
    }

    /*
    A method which generates all sql statements for the user request
    Returns void
     */
    private void generateUserSql(){

    }
}
