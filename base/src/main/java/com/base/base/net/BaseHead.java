package com.base.base.net;

import java.io.Serializable;

/**
 * Created by tongyang on 16/7/19.
 */
public class BaseHead implements Serializable {

    public int status;

    public String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
