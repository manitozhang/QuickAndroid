package com.common.util;

import android.widget.EditText;

/**
 * @Author: https://github.com/manitozhang
 * @Email: 1271396448@qq.com
 * @Date: 2020/8/30  13:27
 * <p>
 * Desc: 参数处理的工具类
 */
public class ParamUtil {
    /**
     * 字符串是否相同
     * @param str1
     * @param str2
     * @return
     */
    public static boolean isEquals(String str1, String str2) {
        return str1.equals(str2);
    }

    /**
     * 获取输入框字符串
     * @param editText
     * @return
     */
    public static String getEditStr(EditText editText) {
        return editText.getText().toString().trim();
    }


    /**
     * 验证是否为空参
     *
     * @param stringObj
     * @return
     */
    public static boolean isBlank(Object stringObj) {
        return null == stringObj || "".equals(stringObj.toString().trim());
    }


    /**
     * 字符串是否为空
     *
     * @param s
     * @return
     */
    public static boolean isBlank(CharSequence s) {
        if (s == null) {
            return true;
        } else {
            return s.length() == 0;
        }
    }

    /**
     * 数值是否为空
     *
     * @param value: 数字
     * @return
     */
    public static boolean isBlank(Integer value) {
        if (value == null) {
            return true;
        } else {
            return value == 0;
        }
    }

    /**
     * 数值是否为空
     *
     * @param value: 数字
     * @return
     */
    public static boolean isBlank(Long value) {
        if (value == null) {
            return true;
        } else {
            return value == 0;
        }
    }
}