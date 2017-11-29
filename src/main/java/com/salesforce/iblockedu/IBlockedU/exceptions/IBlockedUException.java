package com.salesforce.iblockedu.IBlockedU.exceptions;

/**
 * Created by doron.levi on 29/11/2017.
 */
public class IBlockedUException extends RuntimeException {
    public IBlockedUException() {
    }

    public IBlockedUException(String message) {
        super(message);
    }

    public IBlockedUException(String message, Throwable cause) {
        super(message, cause);
    }

    public IBlockedUException(Throwable cause) {
        super(cause);
    }

    public IBlockedUException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
