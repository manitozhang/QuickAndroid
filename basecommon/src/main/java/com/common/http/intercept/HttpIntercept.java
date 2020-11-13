package com.common.http.intercept;


import com.blankj.utilcode.util.LogUtils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 * <p>
 * @Desc: 网络拦截器
 */
public class HttpIntercept implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl build = request.url()
                .newBuilder()
                .addQueryParameter("userId", "userId")//公共参数
                .build();
        Request requestNew = request.newBuilder()
                .url(build)
                .addHeader("userId", "userId")//请求头
                .build();
        return chain.proceed(requestNew);
    }

    /**
     * 数据请求拦截器
     *
     * @return
     */
    public HttpLoggingInterceptor httpBodyIntercept() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtils.i(message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }
}
