package com.common.http

import com.common.constants.HttpConstants
import com.common.http.api.ApiService
import com.common.http.intercept.HttpIntercept
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 *
 *
 * @Desc: 网络请求入口
 */
object XRetrofit {
    private var apiService: ApiService? = null
    /**
     * 请求的入口
     *
     * @return
     */
    @JvmStatic
    val api: ApiService?
        get() {
            if (apiService == null) {
                synchronized(ApiService::class.java) {
                    if (apiService == null)
                        apiService = initRetrofit().create(ApiService::class.java) }
            }
            return apiService!!
        }

    /**
     * 初始化Retrofit
     *
     * @return
     */
    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(HttpConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(initOkHttpClient())
                .build()
    }

    /**
     * 初始化OkHttp
     *
     * @return
     */
    private fun initOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(HttpIntercept())
                .addInterceptor(HttpIntercept().httpBodyIntercept())
                .connectTimeout(HttpConstants.TIME_OUT_CONNECT.toLong(), TimeUnit.SECONDS)
                .readTimeout(HttpConstants.TIME_OUT_READ.toLong(), TimeUnit.SECONDS)
                .build()
    }
}