package com.example.joe_pc.joeweather.base;

import android.app.Application;
import android.content.Context;

import com.example.joe_pc.joeweather.comments.Constants;

import joe.weather.greendao.DaoMaster;
import joe.weather.greendao.DaoSession;

/**
 * Created by Joe_PC on 2017/3/20.
 */

public class WeatherApplication extends Application {

    private static DaoSession mDaoSession;
    private static DaoMaster mDaoMaster;
    private static WeatherApplication instance;

    public static WeatherApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static DaoSession getDaoSession(Context context){
        if(mDaoSession == null){
            if(mDaoMaster == null) {
                DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, Constants.DB_GREEEN_DAO, null);
                mDaoMaster = new DaoMaster(helper.getWritableDatabase());
            }
            mDaoSession = mDaoMaster.newSession();
        }
        return mDaoSession;
    }
}
