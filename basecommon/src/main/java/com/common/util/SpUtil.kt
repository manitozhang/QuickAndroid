package com.common.util

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by zhang on 2020/7/17
 *
 * Sp存储
 */
object SpUtil {
    private const val SP_NAME = "sp_name"
    private var sp: SharedPreferences? = null
    private var edit: SharedPreferences.Editor? = null
    fun init(context: Context) {
        sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        edit = sp?.edit()
    }

    /**
     * obj仅限int,float,boolean,long,String类型
     */
    fun put(key: String?, obj: Any?) {
        val editor = sp!!.edit()
        if (obj is String) {
            editor.putString(key, obj as String?)
        } else if (obj is Int) {
            editor.putInt(key, obj)
        } else if (obj is Boolean) {
            editor.putBoolean(key, obj)
        } else if (obj is Float) {
            editor.putFloat(key, obj)
        } else if (obj is Long) {
            editor.putLong(key, obj)
        } else if (obj is Set<*>) {
            editor.putStringSet(key, obj as Set<String?>?)
        }
        editor.apply()
    }

    fun getString(key: String?): String? {
        return sp!!.getString(key, "")
    }

    fun getInt(key: String?): Int {
        return sp!!.getInt(key, 0)
    }

    fun getLong(key: String?): Long {
        return sp!!.getLong(key, 0)
    }

    fun getBoolean(key: String?): Boolean {
        return sp!!.getBoolean(key, false)
    }
}