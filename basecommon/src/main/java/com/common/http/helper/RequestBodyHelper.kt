package com.common.http.helper

import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.RequestBody
import java.util.*

/**
 * Created by zhang on 2020/7/17
 *
 *
 * HashMap转RequestBody
 */
object RequestBodyHelper {
    @JvmStatic
    fun getRequestBody(map: HashMap<String, Any>): RequestBody {
        val gson = Gson()
        val toJson = gson.toJson(map)
        return RequestBody.create(MediaType.parse("application/json;charset=utf-8"), toJson)
    }
}