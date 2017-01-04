package com.zhenghan.qunar.executor;

/**
 * Created by Administrator on 2016/11/16.
 */
public class ClientRqRateSourceException extends RuntimeException {
    public ClientRqRateSourceException() {
    }

    public ClientRqRateSourceException(String message) {
        super(message);
    }

    public ClientRqRateSourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientRqRateSourceException(Throwable cause) {
        super(cause);
    }

    public ClientRqRateSourceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
