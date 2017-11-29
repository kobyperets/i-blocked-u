package com.salesforce.iblockedu.IBlockedU.model;

public class Car extends BaseEntity{
    private String color;
    private String model;
    private int ownerId;
    private String licensePlate;

    public Car(int id, String color, String model, int ownerId, String licensePlate) {
        this.id = id;
        this.color = color;
        this.model = model;
        this.ownerId = ownerId;
        this.licensePlate = licensePlate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

}
