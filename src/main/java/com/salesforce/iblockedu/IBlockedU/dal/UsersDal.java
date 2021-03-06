package com.salesforce.iblockedu.IBlockedU.dal;

import com.salesforce.iblockedu.IBlockedU.exceptions.IBlockedUException;
import com.salesforce.iblockedu.IBlockedU.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
            stmt.executeUpdate(String.format("INSERT INTO USERS (EMAIL, NAME, PHONE_NUMBER, IMAGE_LOCATION, ACTIVE) VALUES ('%s', '%s', '%s', '',%s )", user.getEmail(), user.getName(), user.getPhoneNumber(), user.isActive()));

        } catch (Exception e) {
            throw new IBlockedUException(e);
        }
    }

    public User getUserByEmail(String email) {

        User user = User.getEmpty();

        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM USERS WHERE lower(EMAIL) = lower('%s')", email));

            while (rs.next()) {
                user = getUserFromRecord(rs);
            }

        } catch (Exception e) {
            throw new IBlockedUException(e);
        }

        return user;
    }

    public User getUserById(int id) {
        User user = User.getEmpty();
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(String.format(String.format("SELECT * FROM USERS WHERE ID = %d", id)));

            while (rs.next()) {
                user = getUserFromRecord(rs);
            }

        }catch (Exception e) {
            throw new IBlockedUException(e);
        }

        return user;
    }

    public List<User> getAllUsers(boolean active) {

        List<User> allUsers = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();

            String query;

            if (active)
                query = "SELECT * FROM USERS WHERE ACTIVE=True";
            else
                query = "SELECT * FROM USERS";

            ResultSet rs = stmt.executeQuery(query);

            User user;

            while (rs.next()) {

                user = getUserFromRecord(rs);

                allUsers.add(user);
            }

        } catch (Exception e) {
            throw new IBlockedUException(e);
        }

        return allUsers;
    }

    public static User getUserFromRecord(ResultSet rs) throws SQLException {
        User user;
        user = new User(rs.getInt("ID"),rs.getString("EMAIL"));
        user.setActive(rs.getBoolean("ACTIVE"));
        user.setName(rs.getString("NAME"));
        user.setPhoneNumber(rs.getString("PHONE_NUMBER"));
        return user;
    }
}
