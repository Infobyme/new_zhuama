package com.base.net;

import java.io.Serializable;

/**
 * Created by tongyang on 16/7/19.
 */
public class BaseHead implements Serializable {

    public int code;

    public String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
