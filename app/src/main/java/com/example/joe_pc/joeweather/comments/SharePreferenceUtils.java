package com.example.joe_pc.joeweather.comments;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Joe_PC on 2017/3/16.
 */

public class SharePreferenceUtils {

    //设置城市名称
    public static void setSharePreferencesCityName(Context context, String name) {
        SharedPreferences.Editor edit = context.getSharedPreferences(Constants.CITY_NAME_FILE, Context.MODE_PRIVATE).edit();
        edit.putString(Constants.CITY_NAME, name);
        edit.commit();
    }

    //获取城市名称
    public static String getSharePreferencesCityName(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.CITY_NAME_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Constants.CITY_NAME, "beijing");
    }

    //设置时间
    public static void setSharePreferencesOldTime(Context context, long oldTime) {
        SharedPreferences.Editor edit = context.getSharedPreferences(Constants.CITY_OLD_TIME_FILE, Context.MODE_PRIVATE).edit();
        edit.putLong(Constants.CITY_OLD_TIME, oldTime);
        edit.commit();
    }

    //获取时间
    public static Long getSharePreferencesOldTime(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.CITY_OLD_TIME_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(Constants.CITY_OLD_TIME, 0);
    }

}
