package com.project.footballstockmarket.mappers;

import com.project.footballstockmarket.models.User;
import org.springframework.stereotype.Component;

import javax.swing.tree.RowMapper;

@Component
public class UserRowMapper implements RowMapper<User> {
    User user = new User();

}
