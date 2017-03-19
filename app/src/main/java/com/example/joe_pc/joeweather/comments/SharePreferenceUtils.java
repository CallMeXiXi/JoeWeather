package com.example.joe_pc.joeweather.comments;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Joe_PC on 2017/3/16.
 */

public class SharePreferenceUtils {

    public static void setSharePerferences(Context context, String name) {
        SharedPreferences.Editor edit = context.getSharedPreferences(Constants.CITY_NAME_FILE, Context.MODE_PRIVATE).edit();
        edit.putString(Constants.CITY_NAME, name);
        edit.commit();
    }

    public static String getSharePerferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.CITY_NAME_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Constants.CITY_NAME, "beijing");
    }

}
