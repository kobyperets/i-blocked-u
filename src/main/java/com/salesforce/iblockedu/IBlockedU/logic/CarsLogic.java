package com.salesforce.iblockedu.IBlockedU.logic;

import com.salesforce.iblockedu.IBlockedU.dal.CarsDal;
import com.salesforce.iblockedu.IBlockedU.dal.UsersDal;
import com.salesforce.iblockedu.IBlockedU.model.Car;
import com.salesforce.iblockedu.IBlockedU.model.CarOwnerInfo;

import java.util.List;

/**
 * Created by doron.levi on 30/11/2017.
 */
public class CarsLogic {

    private CarsDal carsDal;

    public List<CarOwnerInfo> getAllCarsOwnersInfo() {
        return carsDal.getAllCarsOwnersInfo();
    }

    public CarsLogic(CarsDal carsDal) {
        this.carsDal = carsDal;
    }

    public boolean addCar(Car car) {

        Car car1 = carsDal.getCarByLicensePlate(car.getLicensePlate());

        if (Car.isEmpty(car1)) {
            carsDal.createCar(car);
            return true;
        }

        return false;
    }

}
