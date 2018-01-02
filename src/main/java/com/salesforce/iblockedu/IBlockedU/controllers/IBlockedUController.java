package com.salesforce.iblockedu.IBlockedU.controllers;

import com.salesforce.iblockedu.IBlockedU.dal.UtilsDal;
import com.salesforce.iblockedu.IBlockedU.logic.BlocksLogic;
import com.salesforce.iblockedu.IBlockedU.logic.CarsLogic;
import com.salesforce.iblockedu.IBlockedU.logic.UsersLogic;
import com.salesforce.iblockedu.IBlockedU.model.Car;
import com.salesforce.iblockedu.IBlockedU.model.CarOwnerInfo;
import com.salesforce.iblockedu.IBlockedU.model.User;
import com.salesforce.iblockedu.IBlockedU.model.UsersCarsInfo;
import com.salesforce.iblockedu.IBlockedU.model.response.UserResponse;
import com.salesforce.iblockedu.IBlockedU.utils.CSVDataToUsersCarsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.util.List;

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

    @Autowired
    private CarsLogic carsLogic;

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

    @RequestMapping(value = "/carownersinfo", method = RequestMethod.GET)
    public List<CarOwnerInfo> carOwners() {
        return carsLogic.getAllCarsOwnersInfo();
    }

    @RequestMapping("/dbinit")
    String dbinit() {
        return utilsDal.initDB();
    }

    @RequestMapping(value = "/loadBulk", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String loadBulk(@RequestBody String dataCSV) throws IOException {

        CSVDataToUsersCarsInfo csvDataToUsersCarsInfo = new CSVDataToUsersCarsInfo();

        List<UsersCarsInfo> usersCarsInfo = csvDataToUsersCarsInfo.convertToUsersCars(dataCSV);

        int numCarsAdded = 0;
        int numUsersAdded = 0;

        for(UsersCarsInfo userCarsInfo: usersCarsInfo) {
            User user = usersLogic.getUser(userCarsInfo.getEmail());

            if (User.isEmpty(user)) {
                usersLogic.addUser(new User(userCarsInfo.getEmail(),userCarsInfo.getName(),userCarsInfo.getPhoneNumber(),"",true));
                user = usersLogic.getUser(userCarsInfo.getEmail());
                numUsersAdded++;
            }

            for(String licensePlate :userCarsInfo.getCarNumbers()) {
                if (carsLogic.addCar(new Car(user.getId(), licensePlate)))
                    numCarsAdded++;
            }
        }

        return String.format("Number of new users: %d number of new cars: %d", numUsersAdded, numCarsAdded);
    }

    /***************************************************************************************/

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public UserResponse login(@RequestParam String email) {
        UserResponse response = new UserResponse();
        User user = usersLogic.getUser(email);
        if(user.isActive()) {
            response.setUser(user);
            List<String> licensePlates = carsLogic.getMyLicensePlates(user.getId());
            response.setLicensePlates(licensePlates);
            response.setValid(true);
        }
        return response;
    }
}

