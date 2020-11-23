package com.common.http.base

import com.common.constants.HttpConstants

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 *
 *
 * @Desc: 数据统一返回格式
 */
class BaseResponse<T> {
    var code = 0
    var msg: String? = null
    var data: T? = null
        private set

    fun setData(data: T) {
        this.data = data
    }

    val isSuccess: Boolean
        get() = code == HttpConstants.CODE_SUCCESS

    override fun toString(): String {
        return "BaseResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}'
    }
}