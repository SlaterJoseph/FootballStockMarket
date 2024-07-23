package com.project.footballstockmarket.dao;

import com.project.footballstockmarket.mappers.UserRowMapper;
import com.project.footballstockmarket.utils.SqlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
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

    /*
    Creates the new user
    Param: email - The users email
    Param: encodedPassword - The users encoded password
    Returns the response entity based on result
    */
    public ResponseEntity<String> createUser(String email, String encodedPassword) {
        LOGGER.debug("Adding user {} to db", email);
        ResponseEntity<String> response;

        try {
            jdbcTemplate.update(sqlMap.get("NEW_USER"),
                    new Object[]{email, encodedPassword},
                    new int[]{Types.VARCHAR, Types.VARCHAR});
            response = new ResponseEntity<>("User created successfully", HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            // Error duplicate email
            LOGGER.error("Error creating User: {}", e.getMessage());
            response = new ResponseEntity<>("Email already in use", HttpStatus.CONFLICT);

        } catch (DataAccessException e) {
            // Error connecting to db
            LOGGER.error("Error creating user: {}", e.getMessage());
            response = new ResponseEntity<>("Database access error", HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return response;
    }
}
