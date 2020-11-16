package com.common.http.bean;

/**
 * @Author: https://github.com/manitozhang
 * @Date: 2020/9/3
 * @Email: 1271396448@qq.com
 * <p>
 * @Desc: 返回的整个的信息 sample: {"code":10000,"msg":"请求成功","data":{"username":"用户名","password":"密码"}}  只需要写data里面的就可以
 */
public class ExampleBean {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ExampleBean{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
