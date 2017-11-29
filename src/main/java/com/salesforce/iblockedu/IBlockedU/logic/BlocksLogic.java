package com.salesforce.iblockedu.IBlockedU.logic;

import com.salesforce.iblockedu.IBlockedU.dal.BlocksDal;
import com.salesforce.iblockedu.IBlockedU.dal.CarsDal;
import com.salesforce.iblockedu.IBlockedU.dal.UsersDal;
import com.salesforce.iblockedu.IBlockedU.model.Block;
import com.salesforce.iblockedu.IBlockedU.model.Car;
import com.salesforce.iblockedu.IBlockedU.model.User;

import java.sql.Date;
import java.util.Locale;

/**
 * Created by doron.levi on 29/11/2017.
 */
public class BlocksLogic {
    private UsersDal usersDal;
    private BlocksDal blocksDal;
    private CarsDal carsDal;

    public BlocksLogic(UsersDal usersDal, BlocksDal blocksDal,CarsDal carsDal) {
        this.usersDal = usersDal;
        this.blocksDal = blocksDal;
        this.carsDal = carsDal;
    }

    public void unBlock(String email) {
        User user = usersDal.getUserByEmail(email);

        blocksDal.removeBlock(user);
    }

    public String block(String email, String licensePlate, Date exitTime) {

        String error = "";

        User user = usersDal.getUserByEmail(email);

        if (user.isActive()) {

            Car car = carsDal.getCarByLicensePlate(licensePlate);

            Block block = new Block();
            block.setBlockerId(user.getId());
            //block.setBlockingDate();
            block.setBlockerExitTime(exitTime);
            block.setBlockedCarId(car.getId());
            block.setActive(true);
            block.setBlockedId(car.getOwnerId());

            blocksDal.addBlock(block);
        } else {
            error = "No user for " + email;
        }

        return error;
    }

    public void updateExitHour(User user, Date date) {
        blocksDal.updateExitHour(user,date);
    }


}
