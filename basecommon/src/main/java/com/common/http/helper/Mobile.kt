package com.common.http.helper

import java.util.*

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 *
 *
 * @Desc: 接口参数封装
 */
object Mobile {
    /**
     * example参数
     *
     * @return
     */
    fun userLogin(params1: String, params2: String): HashMap<String, Any> {
        val map = HashMap<String, Any>()
        map["params1"] = params1
        map["params2"] = params2
        return map
    }

    /**
     * 公共的map
     *
     * @param username
     * @param password
     * @return
     */
    @JvmStatic
    fun commonParamsMap(username: String, password: String): HashMap<String, Any> {
        val map = HashMap<String, Any>()
        //用户名的参数key: username
        map["username"] = username
        //密码的参数key: password
        map["password"] = password
        return map
    }

    /**
     * 获取集合
     * @param page: 页数
     * @param pageNum: 每页的条目数
     * @return
     */
    @JvmStatic
    fun getList(page: Int, pageNum: Int): HashMap<String, Any> {
        val map = HashMap<String, Any>()
        map["page"] = page
        map["pageNum"] = pageNum
        return map
    }
}