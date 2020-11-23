package com.common.util

import android.widget.EditText

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 * @Date: 2020/8/30  13:27
 *
 *
 * Desc: 参数处理的工具类
 */
object ParamUtil {
    /**
     * 字符串是否相同
     * @param str1
     * @param str2
     * @return
     */
    fun isEquals(str1: String, str2: String): Boolean {
        return str1 == str2
    }

    /**
     * 获取输入框字符串
     * @param editText
     * @return
     */
    @JvmStatic
    fun getEditStr(editText: EditText): String {
        return editText.text.toString().trim { it <= ' ' }
    }

    /**
     * 验证是否为空参
     *
     * @param stringObj
     * @return
     */
    fun isBlank(stringObj: Any?): Boolean {
        return null == stringObj || "" == stringObj.toString().trim { it <= ' ' }
    }

    /**
     * 字符串是否为空
     *
     * @param s
     * @return
     */
    fun isBlank(s: CharSequence?): Boolean {
        return if (s == null) {
            true
        } else {
            s.length == 0
        }
    }

    /**
     * 数值是否为空
     *
     * @param value: 数字
     * @return
     */
    fun isBlank(value: Int?): Boolean {
        return if (value == null) {
            true
        } else {
            value == 0
        }
    }

    /**
     * 数值是否为空
     *
     * @param value: 数字
     * @return
     */
    fun isBlank(value: Long?): Boolean {
        return if (value == null) {
            true
        } else {
            value == 0L
        }
    }
}