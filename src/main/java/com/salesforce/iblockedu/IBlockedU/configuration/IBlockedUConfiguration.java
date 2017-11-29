package com.salesforce.iblockedu.IBlockedU.configuration;

import com.salesforce.iblockedu.IBlockedU.dal.BlocksDal;
import com.salesforce.iblockedu.IBlockedU.dal.UsersDal;
import com.salesforce.iblockedu.IBlockedU.dal.UtilsDal;
import com.salesforce.iblockedu.IBlockedU.logic.BlocksLogic;
import com.salesforce.iblockedu.IBlockedU.logic.UsersLogic;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by doron.levi on 29/11/2017.
 */
@Configuration
public class IBlockedUConfiguration {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Bean
    public DataSource dataSource() throws SQLException {
        if (dbUrl == null || dbUrl.isEmpty()) {
            return new HikariDataSource();
        } else {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(dbUrl);
            return new HikariDataSource(config);
        }
    }

    @Bean
    public UsersDal usersDal(DataSource dataSource) {
        return new UsersDal(dataSource);
    }

    @Bean
    public UtilsDal utilsDal(DataSource dataSource) {
        return new UtilsDal(dataSource);
    }

    @Bean
    public BlocksDal blocksDal(DataSource dataSource) {
        return new BlocksDal(dataSource);
    }

    @Bean
    public UsersLogic usersLogic(UsersDal usersDal) {
        return new UsersLogic(usersDal);
    }

    @Bean
    public BlocksLogic blocksLogic(BlocksDal blocksDal, UsersDal usersDal) {
        return new BlocksLogic(usersDal,blocksDal);
    }

}
