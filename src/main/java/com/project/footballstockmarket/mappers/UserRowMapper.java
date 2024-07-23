package com.project.footballstockmarket.mappers;

import com.project.footballstockmarket.models.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        user.setId(rs.getInt("id"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));

        String firstName = rs.getString("first_name");
        user.setFirstName(firstName != null ? firstName : "");

        String lastName = rs.getString("last_name");
        user.setLastName(lastName != null ? lastName : "");

        String userName = rs.getString("user_name");
        user.setUserName(userName != null ? userName : "");

        String photoUrl = rs.getString("photo_url");
        user.setPhotoUrl(photoUrl != null ? photoUrl : "");

        String leagueString = rs.getString("leagues");
        if (leagueString != null && !leagueString.isEmpty()){
            List<Integer> leagues = Arrays.stream(leagueString.split(","))
                                          .map(Integer::parseInt)
                                          .toList();
            user.setLeagues(leagues);
        } else {
            user.setLeagues(null);
        }

        return user;
    }
}
