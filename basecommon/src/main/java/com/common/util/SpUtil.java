package com.common.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by zhang on 2020/7/17
 *
 * Sp存储
 */
public class SpUtil {

    private final static String SP_NAME = "sp_name";
    private static SharedPreferences sp;
    private static SharedPreferences.Editor edit;

    public static void init(Context context) {
        sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        edit = sp.edit();
    }


    /**
     * obj仅限int,float,boolean,long,String类型
     */
    public static void put(String key, Object obj) {
        SharedPreferences.Editor editor = sp.edit();
        if (obj instanceof String) {
            editor.putString(key, (String) obj);
        } else if (obj instanceof Integer) {
            editor.putInt(key, (int) obj);
        } else if (obj instanceof Boolean) {
            editor.putBoolean(key, (boolean) obj);
        } else if (obj instanceof Float) {
            editor.putFloat(key, (float) obj);
        } else if (obj instanceof Long) {
            editor.putLong(key, (long) obj);
        } else if (obj instanceof Set) {
            editor.putStringSet(key, (Set<String>) obj);
        }
        editor.apply();
    }

    public static String getString(String key){
        return sp.getString(key, "");
    }

    public static int getInt(String key){
        return sp.getInt(key, 0);
    }
    public static long getLong(String key){
        return sp.getLong(key, 0);
    }

    public static boolean getBoolean(String key){
        return sp.getBoolean(key, false);
    }

}
