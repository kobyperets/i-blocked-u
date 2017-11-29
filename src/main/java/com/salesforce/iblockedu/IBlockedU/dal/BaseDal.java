package com.salesforce.iblockedu.IBlockedU.dal;

import com.salesforce.iblockedu.IBlockedU.model.User;

import javax.sql.DataSource;

/**
 * Created by doron.levi on 29/11/2017.
 */
public abstract class BaseDal<BaseEntity> {

    protected DataSource dataSource;

    public BaseDal(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
