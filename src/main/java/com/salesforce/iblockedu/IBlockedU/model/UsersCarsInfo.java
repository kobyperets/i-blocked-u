package com.salesforce.iblockedu.IBlockedU.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by doron.levi on 05/12/2017.
 */
public class UsersCarsInfo {
    private User user;
    private List<Car> cars = new ArrayList<>();


    public User getUser() {
        return user;
    }

    public List<Car> getCars() {
        return cars;
    }
}
