package com.salesforce.iblockedu.IBlockedU.dal;

import com.salesforce.iblockedu.IBlockedU.exceptions.IBlockedUException;
import com.salesforce.iblockedu.IBlockedU.model.Car;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

public class CarsDal extends BaseDal<Car> {
    public CarsDal(DataSource dataSource) {
        super(dataSource);
    }

    public void createCar(Car car) {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(String.format("INSERT INTO CARS (COLOR, MODEL, OWNER_ID, LICENSE_PLATE) VALUES (%s, %s, %d, %s)",
                    car.getColor(), car.getModel(), car.getOwnerId(), car.getLicensePlate()));

        } catch (Exception e) {
            ///log
            throw new IBlockedUException();
        }
    }

    public void deleteCarById(int carId) {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(String.format("DELETE FROM CARS WHERE ID = %d", carId));
        } catch (Exception e) {
            throw new IBlockedUException();
        }
    }

}
