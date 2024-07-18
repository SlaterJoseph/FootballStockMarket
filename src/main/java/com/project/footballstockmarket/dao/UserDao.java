package com.project.footballstockmarket.dao;

import com.project.footballstockmarket.mappers.UserRowMapper;
import com.project.footballstockmarket.utils.SqlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);
    private final JdbcTemplate jdbcTemplate;
    private final SqlUtils sqlUtils;
    private final Map<String, String> sqlMap;
    private final UserRowMapper userRowMapper;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate, UserRowMapper userRowMapper, SqlUtils sqlUtils){
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
        this.sqlUtils = sqlUtils;
        this.sqlMap = sqlUtils.getUserMap();
    }


}
