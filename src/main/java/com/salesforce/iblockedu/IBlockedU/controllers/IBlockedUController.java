package com.salesforce.iblockedu.IBlockedU.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by doron.levi on 27/11/2017.
 */

@RestController
@RequestMapping("/iblockedu/api")
public class IBlockedUController {


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello(@RequestParam String helloTo) {
        return "hello " + helloTo;
    }

    @RequestMapping(value = "/whoBlocks", method = RequestMethod.GET)
    public String whosBlocking(@RequestParam String email) {
        return "Mock Car is blocking " + email;
    }

    @RequestMapping(value = "/goingHome", method = RequestMethod.GET)
    public String goingHome(@RequestParam String email) {
        return email + " Is going home. Mock Car is now unblocked";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signIn(@RequestParam String email) {
        return email + " is now signed-in";
    }
}

