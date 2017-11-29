package com.salesforce.iblockedu.IBlockedU.model;

import java.util.Date;

public class Block extends BaseEntity{
    private int blockerId;
    private int blockedId;
    private Date blockingDate;
    private int blockerExitHour;
    private int blockedExitHour;

    public Block(int id, int blockerId, int blockedId, Date blockingDate, int blockerExitHour, int blockedExitHour) {
        this.id = id;
        this.blockerId = blockerId;
        this.blockedId = blockedId;
        this.blockingDate = blockingDate;
        this.blockerExitHour = blockerExitHour;
        this.blockedExitHour = blockedExitHour;
    }

    public int getBlockerId() {
        return blockerId;
    }

    public void setBlockerId(int blockerId) {
        this.blockerId = blockerId;
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

    public int getBlockerExitHour() {
        return blockerExitHour;
    }

    public void setBlockerExitHour(int blockerExitHour) {
        this.blockerExitHour = blockerExitHour;
    }

    public int getBlockedExitHour() {
        return blockedExitHour;
    }

    public void setBlockedExitHour(int blockedExitHour) {
        this.blockedExitHour = blockedExitHour;
    }
}


