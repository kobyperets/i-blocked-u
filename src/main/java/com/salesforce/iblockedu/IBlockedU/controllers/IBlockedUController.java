package com.salesforce.iblockedu.IBlockedU.controllers;

import com.salesforce.iblockedu.IBlockedU.dal.UtilsDal;
import com.salesforce.iblockedu.IBlockedU.logic.BlocksLogic;
import com.salesforce.iblockedu.IBlockedU.logic.UsersLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.Instant;

/**
 * Created by doron.levi on 27/11/2017.
 */

@RestController
@RequestMapping("/iblockedu/api")
public class IBlockedUController {

    @Autowired
    private UsersLogic usersLogic;

    @Autowired
    private UtilsDal utilsDal;

    @Autowired
    private BlocksLogic blocksLogic;

    @RequestMapping(value = "/iAmBlocking", method = RequestMethod.GET)
    public String iAmBlocking(@RequestParam String email, @RequestParam String licensePlate) {

        return blocksLogic.block(email,licensePlate,new Date(Instant.now().toEpochMilli()));
    }

    @RequestMapping(value = "/whoBlocks", method = RequestMethod.GET)
    public String whosBlocking(@RequestParam String email) {
        return blocksLogic.getMyBlocker(email);
    }

    @RequestMapping(value = "/goingHome", method = RequestMethod.GET)
    public String goingHome(@RequestParam String email) {

        return blocksLogic.unBlock(email);
    }

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signIn(@RequestParam String email) {

        String userName = usersLogic.getUserName(email);

        if (StringUtils.isEmpty(email))
            return "Guest";
        else
            return userName;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users() {

        return "{\"users\":[{\"id\":\"1\", \"name\":\"dor\", \"phone\":\"1234\"}]}";
    }

    @RequestMapping("/dbinit")
    String dbinit() {
        return utilsDal.initDB();
    }
}

