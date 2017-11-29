package com.salesforce.iblockedu.IBlockedU.controllers;

import com.salesforce.iblockedu.IBlockedU.dal.UsersDal;
import com.salesforce.iblockedu.IBlockedU.model.User;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by doron.levi on 27/11/2017.
 */

@RestController
@RequestMapping("/iblockedu/api")
public class IBlockedUController {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Autowired
    private DataSource dataSource;

    @RequestMapping(value = "/iAmBlocking", method = RequestMethod.GET)
    public String iAmBlocking(@RequestParam String email, @RequestParam String licensePlate) {
        return email + " is Blocking car with license plate " + licensePlate;
    }

    @RequestMapping(value = "/whoBlocks", method = RequestMethod.GET)
    public String whosBlocking(@RequestParam String email) {
        return "Mock Car is blocking " + email;
    }

    @RequestMapping(value = "/goingHome", method = RequestMethod.GET)
    public String goingHome(@RequestParam String email) {
        return email + " Is going home. Mock Car is now unblocked";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signIn(@RequestParam String email) {

        UsersDal usersDal = new UsersDal(dataSource);

        User user = usersDal.getUserByEmail(email);

        if (user.isActive())
            return user.getName();
        else
            return "Guest";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users() {

        return "{\"users\":[{\"id\":\"1\", \"name\":\"dor\", \"phone\":\"1234\"}]}";
    }

    @RequestMapping("/dbinit")
    String dbinit() {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS USERS (ID serial primary key, EMAIL text not null unique, NAME text not null, PHONE_NUMBER text not null, IMAGE_LOCATION text, ACTIVE boolean)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS CARS (ID serial primary key, COLOR text, MODEL text, OWNER_ID integer not null REFERENCES USERS (ID), LICENSE_PLATE text not null UNIQUE)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS BLOCKS (ID serial primary key, BLOCKER_ID integer not null REFERENCES CARS (ID), BLOCKED_ID integer not null REFERENCES CARS (ID), BLOCKING_DATE text not null, BLOCKER_EXIT integer not null, BLOCKED_EXIT integer not null)");
            insert_users(stmt);
            insert_cars(stmt);
            return "db created";
        } catch (Exception e) {
            return "error " + e.getMessage();
        }
    }

    String insert_users(Statement stmt) {
        try {
            stmt.executeUpdate("INSERT INTO USERS (EMAIL, NAME, PHONE_NUMBER, IMAGE_LOCATION, ACTIVE) VALUES ('mmatalon@salesforce.com', 'Morin Matalon', '9720542038545', '', True)");
            stmt.executeUpdate("INSERT INTO USERS (EMAIL, NAME, PHONE_NUMBER, IMAGE_LOCATION, ACTIVE) VALUES ('ndayan@salesforce.com', 'Nir Dayan', '9720524664030', '', True)");
            stmt.executeUpdate("INSERT INTO USERS (EMAIL, NAME, PHONE_NUMBER, IMAGE_LOCATION, ACTIVE) VALUES ('orosenfeld@salesforce.com', 'Odelia Rosenfeld', '9720544484004', '', True)");
            stmt.executeUpdate("INSERT INTO USERS (EMAIL, NAME, PHONE_NUMBER, IMAGE_LOCATION, ACTIVE) VALUES ('dsoltz@salesforce.com', 'Doron Soltz', '9720549222613', '', True)");
            stmt.executeUpdate("INSERT INTO USERS (EMAIL, NAME, PHONE_NUMBER, IMAGE_LOCATION, ACTIVE) VALUES ('ychrysler@salesforce.com', 'Yossie Chrysler', '9720542154201', '', True)");
            stmt.executeUpdate("INSERT INTO USERS (EMAIL, NAME, PHONE_NUMBER, IMAGE_LOCATION, ACTIVE) VALUES ('ademidov@salesforce.com', 'Alexey Demidov', '9720542881189', '', True)");
            stmt.executeUpdate("INSERT INTO USERS (EMAIL, NAME, PHONE_NUMBER, IMAGE_LOCATION, ACTIVE) VALUES ('icohen@salesforce.com', 'Idan Cohen', '9720507867939', '', True)");
            stmt.executeUpdate("INSERT INTO USERS (EMAIL, NAME, PHONE_NUMBER, IMAGE_LOCATION, ACTIVE) VALUES ('yzecharya@salesforce.com', 'Yaniv Zecharya', '9720524824984', '', True)");
            stmt.executeUpdate("INSERT INTO USERS (EMAIL, NAME, PHONE_NUMBER, IMAGE_LOCATION, ACTIVE) VALUES ('dkolog@salesforce.com', 'Dor Kolog', '9720548141148', '', True)");
            stmt.executeUpdate("INSERT INTO USERS (EMAIL, NAME, PHONE_NUMBER, IMAGE_LOCATION, ACTIVE) VALUES ('kperets@salesforce.com', 'Koby Perets', '9720525852731', '', True)");
            stmt.executeUpdate("INSERT INTO USERS (EMAIL, NAME, PHONE_NUMBER, IMAGE_LOCATION, ACTIVE) VALUES ('doron.levi@salesforce.com', 'Doron Levi', '9720544663319', '', True)");
            stmt.executeUpdate("INSERT INTO USERS (EMAIL, NAME, PHONE_NUMBER, IMAGE_LOCATION, ACTIVE) VALUES ('aelimelech@salesforce.com', 'Adam Elimelech', '9720547499905', '', True)");
            stmt.executeUpdate("INSERT INTO USERS (EMAIL, NAME, PHONE_NUMBER, IMAGE_LOCATION, ACTIVE) VALUES ('peisendorf@salesforce.com', 'Pavel Eisendorf', '9720532830655', '', True)");
        } catch (Exception e) {
            return "error " + e.getMessage();
        }
        return "";
    }

    String insert_cars(Statement stmt) {
        try {
            stmt.executeUpdate("INSERT INTO CARS (COLOR, MODEL, OWNER_ID, LICENSE_PLATE) VALUES ('red', 'Alfa Romeo 4C Coupe', 1, '5826560')");
            stmt.executeUpdate("INSERT INTO CARS (COLOR, MODEL, OWNER_ID, LICENSE_PLATE) VALUES ('white', 'Aston Martin DB11', 2, '6169679')");
            stmt.executeUpdate("INSERT INTO CARS (COLOR, MODEL, OWNER_ID, LICENSE_PLATE) VALUES ('blue', 'Audi A5 Coupe', 3, '2248870')");
            stmt.executeUpdate("INSERT INTO CARS (COLOR, MODEL, OWNER_ID, LICENSE_PLATE) VALUES ('gray', 'Audi RS 5 Coupe', 4, '8674514')");
            stmt.executeUpdate("INSERT INTO CARS (COLOR, MODEL, OWNER_ID, LICENSE_PLATE) VALUES ('white', 'BMW 3 Series Coupe', 5, '5243971')");
            stmt.executeUpdate("INSERT INTO CARS (COLOR, MODEL, OWNER_ID, LICENSE_PLATE) VALUES ('gray', 'BMW 3 Series Coupe', 6, '2810776')");
            stmt.executeUpdate("INSERT INTO CARS (COLOR, MODEL, OWNER_ID, LICENSE_PLATE) VALUES ('red', 'BMW 4 Series Coupe', 7, '1176671')");
            stmt.executeUpdate("INSERT INTO CARS (COLOR, MODEL, OWNER_ID, LICENSE_PLATE) VALUES ('yellow', 'BMW M4 Coupe', 8, '1851475')");
            stmt.executeUpdate("INSERT INTO CARS (COLOR, MODEL, OWNER_ID, LICENSE_PLATE) VALUES ('black', 'Bentley Continental Coupe', 9, '4315962')");
            stmt.executeUpdate("INSERT INTO CARS (COLOR, MODEL, OWNER_ID, LICENSE_PLATE) VALUES ('black', 'Cadillac ATS Coupe', 10, '6843173')");
            stmt.executeUpdate("INSERT INTO CARS (COLOR, MODEL, OWNER_ID, LICENSE_PLATE) VALUES ('white', 'Cadillac ATS Coupe', 11, '7359355')");
            stmt.executeUpdate("INSERT INTO CARS (COLOR, MODEL, OWNER_ID, LICENSE_PLATE) VALUES ('blue', 'Chevrolet Avalanche', 12, '2626968')");
            stmt.executeUpdate("INSERT INTO CARS (COLOR, MODEL, OWNER_ID, LICENSE_PLATE) VALUES ('blue', 'Chevrolet Colorado', 13, '5108966')");
        } catch (Exception e) {
            return "error " + e.getMessage();
        }
        return "";
    }

    @Bean
    public DataSource dataSource() throws SQLException {
        if (dbUrl == null || dbUrl.isEmpty()) {
            return new HikariDataSource();
        } else {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(dbUrl);
            return new HikariDataSource(config);
        }
    }
}

