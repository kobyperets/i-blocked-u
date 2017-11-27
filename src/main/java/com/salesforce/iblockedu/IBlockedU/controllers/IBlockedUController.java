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
    public String whosBlocking(@RequestParam String emailAddress) {
        return "Mock Car is blocking" + emailAddress;
    }
}

