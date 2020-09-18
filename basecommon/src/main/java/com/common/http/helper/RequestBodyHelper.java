package com.common.http.helper;

import com.google.gson.Gson;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by zhang on 2020/7/17
 * <p>
 * HashMap转RequestBody
 */
public class RequestBodyHelper {

    public static RequestBody getRequestBody(HashMap<String, Object> map) {
        Gson gson = new Gson();
        String toJson = gson.toJson(map);
        return RequestBody.create(MediaType.parse("application/json;charset=utf-8"), toJson);
    }

}
