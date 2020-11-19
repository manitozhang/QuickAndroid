package com.common.http.bean

/**
 * @Author: 张鹏飞
 * @Date: 2020/11/12
 * @Email: 1271396448@qq.com
 *
 *
 * @Desc: 返回的整个的信息 sample: {"code":10000,"msg":"请求成功","data":{"fileUrl":"文件地址"}}  只需要写data里面的就可以
 */
class ExampleFileBean {
    var fileUrl: String?=null

    override fun toString(): String {
        return "ExampleFileBean{" +
                "fileUrl='" + fileUrl + '\'' +
                '}'
    }
}