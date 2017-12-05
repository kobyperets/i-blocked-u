package com.salesforce.iblockedu.IBlockedU.utils;

import com.salesforce.iblockedu.IBlockedU.model.UsersCarsInfo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

/**
 * Created by doron.levi on 05/12/2017.
 */
public class CSVDataToUsersCarsInfo {



    public List<UsersCarsInfo> convertToUsersCars(String CSVData) throws IOException {

        Reader in = new StringReader(CSVData);

        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
        for (CSVRecord record : records) {
            String lastName = record.get("Name");
            String firstName = record.get("Car1");
        }

        return null;
    }

}
