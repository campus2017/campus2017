package com.zhenghan.qunar.Exception;

/**
 * Author: 郑含
 * Date: 2016/12/30
 * Time: 23:51
 */
public class notSupportTypeCountException extends Exception {
    public notSupportTypeCountException() {
    }

    public notSupportTypeCountException(String message) {
        super(message);
    }

    public notSupportTypeCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public notSupportTypeCountException(Throwable cause) {
        super(cause);
    }

    public notSupportTypeCountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
