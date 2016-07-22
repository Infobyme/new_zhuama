package com.base.base.net;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by tongyang on 16/7/19.
 */
public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private Gson gson;
    private Type type;

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

        String response = value.string();

        Log.d("Network", "response" + response);

        BaseHead head = gson.fromJson(response, BaseHead.class);

        if (head.getStatus() == 0) {
            return gson.fromJson(response, type);
        } else {
            throw new ResultException(head.getStatus(), head.getMessage());
        }
    }
}
