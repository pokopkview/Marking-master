package com.intelligent.marking.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesUtils {

    public static String PREFERENCE_NAME = "account";

    /**用户名的key值*/
    public static String UUID = "uuid";
    public static String HOSPITALNAME = "hospitalName";
    public static String AREANAME = "areaName";
    public static String DEPARTNAME = "departName";
    public static String SUBAREANAME = "subareaName";

    public static String HOSPITALNAMEID = "hospitalNameid";
    public static String AREANAMEID = "areaNameid";
    public static String DEPARTNAMEID = "departNameid";
    public static String SUBAREANAMEID = "subareaNameid";

    public static String PROVINCE = "province";
    public static String CITY = "city";
    public static String ARE = "are";

    public static String PROVINCEID = "provinceid";
    public static String CITYID = "cityid";
    public static String AREID = "areid";


    public static String LOCATION = "location";


    /**存储字符串*/
    public static boolean putString(Context context, String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        return editor.commit();
    }
    /**读取字符串*/
    public static String getString(Context context, String key) {
        return getString(context, key, null);
    }
    /**读取字符串（带默认值的）*/
    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return preferences.getString(key, defaultValue);
    }
    /**存储整型数字*/
    public static boolean putInt(Context context, String key, int value) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        return editor.commit();
    }
    /**读取整型数字*/
    public static int getInt(Context context, String key) {
        return getInt(context, key, -1);
    }
    /**读取整型数字（带默认值的）*/
    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return preferences.getInt(key, defaultValue);
    }
    /**存储长整型数字*/
    public static boolean putLong(Context context, String key, long value) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        return editor.commit();
    }
    /**读取长整型数字*/
    public static long getLong(Context context, String key) {
        return getLong(context, key, 0xffffffff);
    }
    /**读取长整型数字（带默认值的）*/
    public static long getLong(Context context, String key, long defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return preferences.getLong(key, defaultValue);
    }
    /**存储Float数字*/
    public static boolean putFloat(Context context, String key, float value) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }
    /**读取Float数字*/
    public static float getFloat(Context context, String key) {
        return getFloat(context, key, -1.0f);
    }
    /**读取Float数字（带默认值的）*/
    public static float getFloat(Context context, String key, float defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return preferences.getFloat(key, defaultValue);
    }
    /**存储boolean类型数据*/
    public static boolean putBoolean(Context context, String key, boolean value) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }
    /**读取boolean类型数据*/
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }
    /**读取boolean类型数据（带默认值的）*/
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return preferences.getBoolean(key, defaultValue);
    }
    /**清除数据*/
    public static boolean clearPreferences(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        return editor.commit();
    }
}
