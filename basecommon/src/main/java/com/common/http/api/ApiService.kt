package com.common.http.api

import com.common.http.base.BaseResponse
import com.common.http.bean.ExampleBean
import com.common.http.bean.ExampleFileBean
import com.common.http.bean.ExampleListBean
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import java.util.*

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 *
 *
 * @Desc: BaseResponse<T> 是后台返回的数据,T代表Data里面的数据 无数据返回可直接写BaseReponse
</T> */
interface ApiService {
    /**
     * Get请求
     *
     * @param map
     * @return
     */
    @GET("api/get")
    fun testGet(@QueryMap map: HashMap<String, Any>): Observable<BaseResponse<ExampleBean>>

    /**
     * Post请求
     *
     * @param map
     * @return
     */
    @POST("api/post")
    @FormUrlEncoded
    fun testPost(@FieldMap map: HashMap<String, Any>): Observable<BaseResponse<Any>>

    /**
     * Put请求
     *
     * @param map
     * @return
     */
    @PUT("api/put")
    @FormUrlEncoded
    fun testPut(@FieldMap map: HashMap<String, Any>): Observable<BaseResponse<Any>>

    /**
     * Delete请求
     *
     * @param map
     * @return
     */
    @DELETE("api/delete")
    fun testDelete(@QueryMap map: HashMap<String, Any>): Observable<BaseResponse<ExampleBean>>

    /**
     * post提交json格式数据
     *
     * @param body
     * @return
     */
    @POST("api/postJson")
    fun testPostJson(@Body body: RequestBody?): Observable<BaseResponse<ExampleBean>>

    /**
     * 文件上传
     *
     * @param file
     * @param map
     * @return
     */
    @POST("api/uploadFile")
    @Multipart
    fun testUploadFile(@Part file: MultipartBody.Part?, @PartMap map: HashMap<String, Any>): Observable<BaseResponse<ExampleFileBean>>

    /**
     * 例子3: RequestBody
     *
     * @return
     */
    @POST("example/3")
    fun example3(@Body body: RequestBody?): Observable<BaseResponse<ExampleBean>>

    /**
     * 获取集合
     *
     * @param map
     * @return
     */
    @GET("api/getList")
    fun getList(@QueryMap map: HashMap<String, Any>): Observable<BaseResponse<List<ExampleListBean>>>
}