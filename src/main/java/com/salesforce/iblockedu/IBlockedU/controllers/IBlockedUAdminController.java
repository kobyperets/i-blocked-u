package com.salesforce.iblockedu.IBlockedU.controllers;

import com.salesforce.iblockedu.IBlockedU.dal.CarsDal;
import com.salesforce.iblockedu.IBlockedU.logic.BlocksLogic;
import com.salesforce.iblockedu.IBlockedU.logic.UsersLogic;
import com.salesforce.iblockedu.IBlockedU.model.Block;
import com.salesforce.iblockedu.IBlockedU.model.Car;
import com.salesforce.iblockedu.IBlockedU.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by doron.levi on 29/11/2017.
 */

@RestController
@RequestMapping("/iblockedu/ui")
public class IBlockedUAdminController {

  @Autowired
  UsersLogic usersLogic;

  @Autowired
  CarsDal carsDal;

  @Autowired
  BlocksLogic blocksLogic;

  @RequestMapping(value = "/users", method = RequestMethod.GET)
  public List<User> users() {

    return usersLogic.getAllUsers(true);
  }

  @RequestMapping(value = "/cars", method = RequestMethod.GET)
  public List<Car> cars() {

    return carsDal.getAllCars();
  }

  @RequestMapping(value = "/blocks", method = RequestMethod.GET)
  public List<Block> blocks() {

    return blocksLogic.getAllBlocks(true);
  }

}
