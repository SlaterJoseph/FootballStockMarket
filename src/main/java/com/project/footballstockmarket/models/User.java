package com.project.footballstockmarket.models;

import lombok.Data;

import java.util.List;

@Data
public class User {
    int id;
    String firstName;
    String lastName;
    String userName;
    String email;
    String password;
    String photoUrl;
    List<Integer> leagues;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User [id: ");
        sb.append(id);
        sb.append(", firstName: ");
        sb.append(firstName);
        sb.append(", lastName: ");
        sb.append(lastName);
        sb.append(", userName: ");
        sb.append(userName);
        sb.append(", email: ");
        sb.append(email);
        sb.append(", password: ");
        sb.append(password);
        sb.append(", photoUrl: ");
        sb.append(photoUrl);
        sb.append(", leagues: {");

        for (int league : leagues) {
            sb.append(league);
            sb.append(",");
        }

        sb.delete(sb.length() - 1, sb.length());
        sb.append("}");

        return sb.toString();
    }
}
