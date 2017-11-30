package com.salesforce.iblockedu.IBlockedU.utils;

/**
 * Created by doron.levi on 30/11/2017.
 */
public class ErrorsBuilder {

    private static String ERROR_PREFIX = "Error: ";

    public static String buildError(String message) {
        return ERROR_PREFIX + message;
    }


    public static String buildError(String message,String param) {
        return buildError(message) + ": " + param;
    }

}
