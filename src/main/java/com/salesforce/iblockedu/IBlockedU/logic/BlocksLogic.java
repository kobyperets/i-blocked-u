package com.salesforce.iblockedu.IBlockedU.logic;

import com.salesforce.iblockedu.IBlockedU.dal.BlocksDal;
import com.salesforce.iblockedu.IBlockedU.dal.CarsDal;
import com.salesforce.iblockedu.IBlockedU.dal.UsersDal;
import com.salesforce.iblockedu.IBlockedU.model.Block;
import com.salesforce.iblockedu.IBlockedU.model.Car;
import com.salesforce.iblockedu.IBlockedU.model.User;
import com.salesforce.iblockedu.IBlockedU.utils.ErrorsBuilder;

import java.sql.Date;
import java.time.Instant;
import java.util.Calendar;
import java.util.List;

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

    public String unBlock(String email) {
        String message = "";
        User user = usersDal.getUserByEmail(email);
        if (user.isActive()) {
            long timeInMillis = Calendar.getInstance().getTime().getTime();
            blocksDal.updateExitHour(user,new Date(timeInMillis));
            Block block = blocksDal.removeBlock(user);
            if (block != null){
                User blockedUser = usersDal.getUserById(block.getBlockedId());
                MessageSender.sendMessage("You are free. Nobody blocks you",blockedUser.getPhoneNumber());
                message = String.format("Your block has been removed, %s will be notified", blockedUser.getName());
            } else {
                message = "Note: no active blocking found";
            }
        } else {
            message = ErrorsBuilder.buildError("No active user found for: ",email);
        }
        return message;
    }

    public String block(String email, String licensePlate, Date exitTime) {

        String error = "";

        User user = usersDal.getUserByEmail(email);

        if (user.isActive()) {

            Car car = carsDal.getCarByLicensePlate(licensePlate);

            if (car.getId() != -1) {

                Block block = new Block();
                block.setBlockerId(user.getId());
                block.setBlockingDate(new Date(Instant.now().toEpochMilli()));
                block.setBlockerExitTime(exitTime);
                block.setBlockedCarId(car.getId());
                block.setActive(true);
                block.setBlockedId(car.getOwnerId());

                blocksDal.addBlock(block);
                User blockedUser = usersDal.getUserById(block.getBlockedId());
                MessageSender.sendMessage(String.format("You have been blocked by: %s", email),blockedUser.getPhoneNumber());
            } else
                error = ErrorsBuilder.buildError("No Car found for license plate" ,licensePlate);
        } else {
            error = ErrorsBuilder.buildError("No user found for",email);
        }

        return error;
    }

    public List<Block> getAllBlocks(boolean active) {
        return blocksDal.getAllBlocks(active);
    }

    public String getMyBlocker(String email) {
        String blocker_email = "";
        User user = usersDal.getUserByEmail(email);
        if (user.isActive()) {
            Block block = blocksDal.getMyBlocker(user);
            blocker_email = usersDal.getUserById(block.getBlockerId()).getEmail();
        } else {
            blocker_email = ErrorsBuilder.buildError("No active user found for", email);
        }

        return blocker_email;
    }
}
