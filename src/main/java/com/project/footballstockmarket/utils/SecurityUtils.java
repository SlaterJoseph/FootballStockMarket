package com.project.footballstockmarket.utils;

import com.project.footballstockmarket.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger((SecurityUtils.class));
    BCryptPasswordEncoder encoder;

    public SecurityUtils(){
        encoder = new BCryptPasswordEncoder(16);
    }

    /*
    A method for encoding the password for database storage
    Params: Password - The password fo the account
    Returns the encoded password
    */
    public String encodePassword(String password){
        LOGGER.debug("Encoding password for storage");
        return encoder.encode(password);
    }

    /*
    A method which checks of the if the inputted password and encoded password match
    Param: inputPassword - The password inputted from the user
    Param: encoded Password - The password stored in the database
    Returns if boolean of the values matching
    */
    public boolean checkPassword(String inputPassword, String encodedPassword){
        LOGGER.debug("Checking if the passwords match");
        return encoder.matches(inputPassword, encodedPassword);
    }
}
