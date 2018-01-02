package com.salesforce.iblockedu.IBlockedU.model.response;

import com.salesforce.iblockedu.IBlockedU.model.User;

import java.util.List;


/**
 * Created by kperets on 02/01/2018.
 */
public class UserResponse {
    private User user;
    private List<String> licensePlates;

    public UserResponse(User user, List<String> licensePlates) {
        this.user = user;
        this.licensePlates = licensePlates;
    }

    public UserResponse() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getLicensePlates() {
        return licensePlates;
    }

    public void setLicensePlates(List<String> licensePlates) {
        this.licensePlates = licensePlates;
    }
}
