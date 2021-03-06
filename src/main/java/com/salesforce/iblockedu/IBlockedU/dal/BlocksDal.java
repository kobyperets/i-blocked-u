package com.salesforce.iblockedu.IBlockedU.dal;

import com.salesforce.iblockedu.IBlockedU.exceptions.IBlockedUException;
import com.salesforce.iblockedu.IBlockedU.model.Block;
import com.salesforce.iblockedu.IBlockedU.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlocksDal extends BaseDal<Block> {
    public BlocksDal(DataSource dataSource) {
        super(dataSource);
    }

    public void addBlock(Block block) {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(String.format("INSERT INTO BLOCKS (BLOCKER_ID, BLOCKED_CAR_ID, BLOCKED_ID, BLOCKING_DATE, BLOCKER_EXIT, IS_ACTIVE) VALUES (%d,%d,%d,'%s','%s',%s)", block.getBlockerId(), block.getBlockedCarId(), block.getBlockedId(), block.getBlockingDate(), block.getBlockerExitTime(), block.isActive()));

        } catch (Exception e) {
            ///log
            throw new IBlockedUException(e);
        }
    }

    public Block removeBlock(User user) {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            Block block = checkIfBlocker(user, stmt);
            if (block.isActive()) {
                stmt.executeUpdate(String.format("UPDATE BLOCKS SET IS_ACTIVE = FALSE WHERE ID = %d", block.getId()));
                return block;
            }
        } catch (Exception e) {
            throw new IBlockedUException(e);
        }
        return Block.getEmpty();
    }

    public Block getMyBlocker(User user) {
        Block block = Block.getEmpty();

        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM BLOCKS WHERE BLOCKED_ID = %d", user.getId()));
            while (rs.next()) {
                block = getBlockFromRecord(rs);
            }
        } catch (Exception e) {
            throw new IBlockedUException(e);
        }
        return block;
    }

    public void updateExitHour(User user, Date date) {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            Block block = checkIfBlocker(user, stmt);
            if (block.isActive()) {
                stmt.executeUpdate(String.format("UPDATE BLOCKS SET BLOCKER_EXIT = '%s' WHERE ID = %d", date, block.getId()));
            }

        } catch (Exception e) {
            ///log
            throw new IBlockedUException(e);
        }
    }

    private Block checkIfBlocker(User user, Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM BLOCKS WHERE BLOCKER_ID = %d", user.getId()));
        Block block = Block.getEmpty();
        while (rs.next()) {
            block = getBlockFromRecord(rs);
        }
        return block;
    }

    public List<Block> getAllBlocks(boolean active) {
        List<Block> blocks = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM BLOCKS WHERE IS_ACTIVE = %s", active));
            Block block;
            while (rs.next()) {
                block = getBlockFromRecord(rs);
                blocks.add(block);
            }

        } catch (Exception e) {
            ///log
            throw new IBlockedUException(e);
        }

        return blocks;
    }

    private static Block getBlockFromRecord(ResultSet rs)  throws SQLException {
        Block block;
        block = new Block();
        block.setId(rs.getInt("ID"));
        block.setBlockerId(rs.getInt("BLOCKER_ID"));
        block.setBlockedCarId(rs.getInt("BLOCKED_CAR_ID"));
        block.setBlockedId(rs.getInt("BLOCKED_ID"));
        block.setBlockingDate(rs.getDate("BLOCKING_DATE"));
        block.setBlockerExitTime(rs.getDate("BLOCKER_EXIT"));
        block.setActive(rs.getBoolean("IS_ACTIVE"));

        return block;
    }
}
