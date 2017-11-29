package com.salesforce.iblockedu.IBlockedU.dal;

import com.salesforce.iblockedu.IBlockedU.exceptions.IBlockedUException;
import com.salesforce.iblockedu.IBlockedU.model.Block;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

public class BlocksDal extends BaseDal<Block> {
    public BlocksDal(DataSource dataSource) {
        super(dataSource);
    }

    public void addBlock(Block block) {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(String.format("INSERT INTO BLOCKS (BLOCKER_ID, BLOCKED_ID, BLOCKING_DATE, BLOCKER_EXIT, BLOCKED_EXIT) VALUES (%d, %d, %s, %d, %d)",
                    block.getBlockerId(), block.getBlockedId(), block.getBlockingDate(), block.getBlockerExitHour(), block.getBlockedExitHour()));

        } catch (Exception e) {
            ///log
            throw new IBlockedUException();
        }
    }
}
