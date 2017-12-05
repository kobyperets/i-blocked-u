package com.salesforce.iblockedu.IBlockedU.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by doron.levi on 05/12/2017.
 */
public class UsersCarsInfo {
    private String name;
    private List<String> carNumbers = new ArrayList<>();
    private String phoneNumber;
    private String email;

    public UsersCarsInfo(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public void addCar(String carNumber) {
        if (!carNumbers.contains(carNumber))
            carNumbers.add(carNumber);
    }

    public String getName() {
        return name;
    }

    public List<String> getCarNumbers() {
        return carNumbers;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
