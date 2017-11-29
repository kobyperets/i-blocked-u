package com.salesforce.iblockedu.IBlockedU.controllers;

import com.salesforce.iblockedu.IBlockedU.logic.UsersLogic;
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

  @RequestMapping(value = "/users", method = RequestMethod.GET)
  public List<User> users() {

    return usersLogic.getAllUsers(true);
  }
}
