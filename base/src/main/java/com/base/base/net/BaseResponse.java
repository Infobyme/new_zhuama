package com.base.base.net;

import java.io.Serializable;

/**
 * Created by tongyang on 16/7/19.
 */
public class BaseResponse<T> extends BaseHead implements Serializable {

    public T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
