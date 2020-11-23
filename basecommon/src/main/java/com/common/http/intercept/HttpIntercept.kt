package com.common.http.intercept

import com.blankj.utilcode.util.LogUtils
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 *
 *
 * @Desc: 网络拦截器
 */
class HttpIntercept : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val build = request.url()
                .newBuilder()
                .addQueryParameter("userId", "userId") //公共参数
                .build()
        val requestNew = request.newBuilder()
                .url(build)
                .addHeader("userId", "userId") //请求头
                .build()
        return chain.proceed(requestNew)
    }

    /**
     * 数据请求拦截器
     *
     * @return
     */
    fun httpBodyIntercept(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> LogUtils.i(message) })
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }
}