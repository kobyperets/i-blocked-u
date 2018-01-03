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
            if (block.isActive()){
                User blockedUser = usersDal.getUserById(block.getBlockedId());
                MessageSender.sendMessage("You are free. Nobody blocks you",blockedUser.getPhoneNumber());
                message = String.format("Your block has been removed,%s will be notified", blockedUser.getName());
            } else {
                message = "Note: no active blocking found";
            }
        } else {
            message = ErrorsBuilder.buildError("No active user found for: ",email);
        }
        return message;
    }

    public String block(String email, String licensePlate, Date exitTime) {

        String message = "";

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

                message = String.format("%s was notified about his blocking", blockedUser.getName());
            } else
                message = ErrorsBuilder.buildError("No Car found for license plate" ,licensePlate);
        } else {
            message = ErrorsBuilder.buildError("No user found for",email);
        }

        return message;
    }

    public List<Block> getAllBlocks(boolean active) {
        return blocksDal.getAllBlocks(active);
    }

    public String getMyBlocker(String email) {
        String message;

        User blocked = usersDal.getUserByEmail(email);
        if (blocked.isActive()) {

            Block block = blocksDal.getMyBlocker(blocked);

            if (block.isActive()) {

                User blocker = usersDal.getUserById(block.getBlockerId());

                if (blocker.isActive())
                    message = "You're currently blocked by " + blocker.getName();
                else
                    message = "Blocker not found";
            } else {
                message = "No registered blocking, You are free to go";
            }

        } else {
            message = ErrorsBuilder.buildError("No active user found for", email);
        }

        return message;
    }

    public User getBlockerForEmail(String email) {

        User blocked = usersDal.getUserByEmail(email);
        if (blocked.isActive()) {

            Block block = blocksDal.getMyBlocker(blocked);

            if (block.isActive()) {

                User blocker = usersDal.getUserById(block.getBlockerId());

                if (blocker.isActive())
                    return blocker;
                else
                    return User.getEmpty();
            } else {
                return User.getEmpty();
            }

        } else {
            return User.getEmpty();
        }
    }
}
