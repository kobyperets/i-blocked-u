package com.salesforce.iblockedu.IBlockedU.dal;

import com.salesforce.iblockedu.IBlockedU.exceptions.IBlockedUException;
import com.salesforce.iblockedu.IBlockedU.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by doron.levi on 29/11/2017.
 */
public class UsersDal extends BaseDal<User> {

    public UsersDal(DataSource dataSource) {
        super(dataSource);
    }

    public void createUser(User user) {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO ticks VALUES (now())");

        } catch (Exception e) {
            ///log
            throw new IBlockedUException();
        }
    }

}
