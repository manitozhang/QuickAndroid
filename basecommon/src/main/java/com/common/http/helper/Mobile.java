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

}
