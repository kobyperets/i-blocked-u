package com.salesforce.iblockedu.IBlockedU.logic;

import com.salesforce.iblockedu.IBlockedU.dal.UsersDal;
import com.salesforce.iblockedu.IBlockedU.model.User;

import java.util.List;

/**
 * Created by doron.levi on 29/11/2017.
 */
public class UsersLogic {

    UsersDal usersDal;

    public UsersLogic(UsersDal usersDal) {
        this.usersDal = usersDal;
    }

    public List<User> getAllUsers(boolean active) {
         return usersDal.getAllUsers(active);
    }

    public String getUserName(String email) {
        User user = usersDal.getUserByEmail(email);

        return user.getName();
    }
}
