package com.salesforce.iblockedu.IBlockedU.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
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

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello(@RequestParam String helloTo) {
        return "hello " + helloTo;
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
        return email + " is now signed-in";
    }

    @RequestMapping("/dbinit")
    String dbinit() {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS USERS (ID serial primary key, EMAIL text not null unique, NAME text not null, PHONE_NUMBER text not null, IMAGE_LOCATION text, ACTIVE boolean)");
            //stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
            /*ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

            ArrayList<String> output = new ArrayList<String>();
            while (rs.next()) {
                output.add("Read from DB: " + rs.getTimestamp("tick"));
            }

            model.put("records", output);*/
            return "db created";
        } catch (Exception e) {
            return "error " + e.getMessage();
        }
    }
}

