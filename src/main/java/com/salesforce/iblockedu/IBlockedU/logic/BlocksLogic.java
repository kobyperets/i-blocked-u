package com.salesforce.iblockedu.IBlockedU.logic;

import com.salesforce.iblockedu.IBlockedU.dal.BlocksDal;
import com.salesforce.iblockedu.IBlockedU.dal.UsersDal;
import com.salesforce.iblockedu.IBlockedU.model.Block;
import com.salesforce.iblockedu.IBlockedU.model.User;

import java.sql.Date;

/**
 * Created by doron.levi on 29/11/2017.
 */
public class BlocksLogic {
    UsersDal usersDal;
    BlocksDal blocksDal;

    public void unBlock(String email) {
        User user = usersDal.getUserByEmail(email);

        blocksDal.removeBlock(user);
    }

    public void block(Block block) {
        blocksDal.addBlock(block);
    }

    public void updateExitHour(User user, Date date) {
        blocksDal.updateExitHour(user,date);
    }
}
