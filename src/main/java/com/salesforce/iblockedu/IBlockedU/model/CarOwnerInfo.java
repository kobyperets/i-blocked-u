package com.salesforce.iblockedu.IBlockedU.model;

/**
 * Created by doron.levi on 30/11/2017.
 */
public class CarOwnerInfo {

    private String ownerName;
    private String licensePlate;

    public String getOwnerName() {
        return ownerName;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public CarOwnerInfo(String ownerName, String licensePlate) {
        this.ownerName = ownerName;
        this.licensePlate = licensePlate;
    }
}
