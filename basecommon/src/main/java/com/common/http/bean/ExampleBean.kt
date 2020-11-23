package com.common.http.bean

/**
 * @Author: 张鹏飞
 * @Date: 2020/9/3
 * @Email: 1271396448@qq.com
 *
 *
 * @Desc: 返回的整个的信息 sample: {"code":10000,"msg":"请求成功","data":{"username":"用户名","password":"密码"}}  只需要写data里面的就可以
 */
class ExampleBean {
    var username: String? = null
    var password: String? = null

    override fun toString(): String {
        return "ExampleBean{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}'
    }
}