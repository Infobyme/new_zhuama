package com.base.net;

/**
 * Created by tongyang on 16/7/19.
 */
public class ResultException extends RuntimeException {

    private int errCode = 0;

    public ResultException(int code, String message) {
        super(message);
        this.errCode = code;
    }


    public int getErrCode() {
        return errCode;
    }
}
