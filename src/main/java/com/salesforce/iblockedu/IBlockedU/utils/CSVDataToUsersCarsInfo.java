package com.salesforce.iblockedu.IBlockedU.utils;

import com.salesforce.iblockedu.IBlockedU.model.UsersCarsInfo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.util.StringUtils;


import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by doron.levi on 05/12/2017.
 */
public class CSVDataToUsersCarsInfo {

    public List<UsersCarsInfo> convertToUsersCars(String CSVData) throws IOException {

        List<UsersCarsInfo> usersCarsInfos = new ArrayList<>();

        Reader in = new StringReader(CSVData);

        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
        for (CSVRecord record : records) {
            String name = record.get("Name");
            String email = record.get("Email");
            String phoneNumber = record.get("PhoneNumber");

            UsersCarsInfo usersCarsInfo = new UsersCarsInfo(name,phoneNumber,email);

            for (int i = 1;i<4;i++) {
                String car = record.get("Car" + i);
                if (!StringUtils.isEmpty(car))
                    usersCarsInfo.addCar(getClearCarNumber(car));
                else
                    break;
            }

            usersCarsInfos.add(usersCarsInfo);

        }

        return usersCarsInfos;
    }

    private String getClearCarNumber(String rawCar) {
        return rawCar.trim().replaceAll("-","");
    }
}
