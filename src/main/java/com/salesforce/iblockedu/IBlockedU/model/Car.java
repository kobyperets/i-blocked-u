package com.salesforce.iblockedu.IBlockedU.model;

public class Car extends BaseEntity{
    private String color;
    private String model;
    private int ownerId;
    private String licensePlate;
    private static Car empty = new Car(-1,null,null,-1,null);

    public Car(int id, String color, String model, int ownerId, String licensePlate) {
        this.id = id;
        this.color = color;
        this.model = model;
        this.ownerId = ownerId;
        this.licensePlate = licensePlate;
    }

    public Car(String color, String model, int ownerId, String licensePlate) {
        this(-1,color,model,ownerId,licensePlate);
    }

    public Car(int ownerId, String licensePlate) {
        this("Unknown","Unknown",ownerId,licensePlate);
    }

    public Car() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public static Car getEmpty() {
        return empty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (ownerId != car.ownerId) return false;
        if (color != null ? !color.equals(car.color) : car.color != null) return false;
        if (model != null ? !model.equals(car.model) : car.model != null) return false;
        return licensePlate.equals(car.licensePlate);

    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + ownerId;
        result = 31 * result + licensePlate.hashCode();
        return result;
    }

    public static boolean isEmpty(Car car) {
        return car == empty || empty.equals(car);
    }
}
