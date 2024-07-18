package com.project.footballstockmarket.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Configuration
public class PropertiesConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesConfig.class);

    // Gets properties (Only used until I figure out how to work Paramstore with Springboot)
    public String getFileData(String query){
        LOGGER.debug("Searching for {}", query);

        String result = null;

        String filePath = "env.properties";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineChuncks = line.split("=");
                if(lineChuncks[0].equals(query)){
                    result = lineChuncks[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.debug("Result: {}", result);

        return result;
    }
}
