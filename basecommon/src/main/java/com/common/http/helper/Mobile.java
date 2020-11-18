package com.common.http.helper;

import java.security.PublicKey;
import java.util.HashMap;

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 * <p>
 * @Desc: 接口参数封装
 */
public class Mobile {

    /**
     * example参数
     *
     * @return
     */
    public static HashMap<String, Object> userLogin(String params1, String params2) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("params1", params1);
        map.put("params2", params2);
        return map;
    }

    /**
     * 公共的map
     *
     * @param username
     * @param password
     * @return
     */
    public static HashMap<String, Object> commonParamsMap(String username, String password) {
        HashMap<String, Object> map = new HashMap<>();
        //用户名的参数key: username
        map.put("username", username);
        //密码的参数key: password
        map.put("password", password);
        return map;
    }

    /**
     * 获取集合
     * @param page: 页数
     * @param pageNum: 每页的条目数
     * @return
     */
    public static HashMap<String, Object> getList(int page, int pageNum) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("pageNum",pageNum);
        return map;
    }
}
