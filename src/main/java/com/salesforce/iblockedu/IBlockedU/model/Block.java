package com.salesforce.iblockedu.IBlockedU.model;

import java.util.Date;

public class Block extends BaseEntity {
    private int blockerId;
    private int blockedCarId;
    private int blockedId;
    private Date blockingDate;
    private Date blockerExitTime;
    private boolean isActive;

    public Block(int id, int blockerId, int blockedCarId, int blockedId, Date blockingDate, Date blockerExitTime, Boolean isActive) {
        this.id = id;
        this.blockerId = blockerId;
        this.blockedCarId = blockedCarId;
        this.blockedId = blockedId;
        this.blockingDate = blockingDate;
        this.blockerExitTime = blockerExitTime;
        this.isActive = isActive;
    }

    public Block() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getBlockerId() {
        return blockerId;
    }

    public void setBlockerId(int blockerId) {
        this.blockerId = blockerId;
    }

    public int getBlockedCarId() {
        return blockedCarId;
    }

    public void setBlockedCarId(int blockedCarId) {
        this.blockedCarId = blockedCarId;
    }

    public int getBlockedId() {
        return blockedId;
    }

    public void setBlockedId(int blockedId) {
        this.blockedId = blockedId;
    }

    public Date getBlockingDate() {
        return blockingDate;
    }

    public void setBlockingDate(Date blockingDate) {
        this.blockingDate = blockingDate;
    }

    public Date getBlockerExitTime() {
        return blockerExitTime;
    }

    public void setBlockerExitTime(Date blockerExitTime) {
        this.blockerExitTime = blockerExitTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}



