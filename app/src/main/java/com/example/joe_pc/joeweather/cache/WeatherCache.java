package com.example.joe_pc.joeweather.cache;

import android.content.Context;

import com.alibaba.fastjson.JSONArray;
import com.example.joe_pc.joeweather.base.WeatherApplication;
import com.example.joe_pc.joeweather.bean.RootWeatherBean;
import com.example.joe_pc.joeweather.bean.WeatherBean;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import joe.weather.greendao.Weather;
import joe.weather.greendao.WeatherDao;

/**
 * Created by Joe_PC on 2017/3/26.
 */

public class WeatherCache extends BaseCache {

    private static WeatherCache mWeatherCache;
    private static WeatherDao mWeatherDao;

    private WeatherCache() {
    }

    public static WeatherCache getWeatherCache(Context context) {
        if (mWeatherCache == null) {
            mWeatherCache = new WeatherCache();
        }
        mDaoSession = WeatherApplication.getDaoSession(context);
        mWeatherDao = mDaoSession.getWeatherDao();
        return mWeatherCache;
    }

    @Override
    public void clearAll() {
        mWeatherDao.deleteAll();
    }

    @Override
    public ArrayList<WeatherBean> getResult() {
        ArrayList<WeatherBean> list = null;
        List<Weather> WeatherList = mWeatherDao.queryBuilder().
                orderDesc(WeatherDao.Properties.Id).
                list();
        if (WeatherList.size() > 0) {
            String str = WeatherList.get(0).getResult();//.substring(1, WeatherList.get(0).getResult().length() - 1);
            list = (ArrayList<WeatherBean>) JSONArray.parseArray(str, RootWeatherBean.class).get(0).getWeather();
        }
        return list;
    }

    @Override
    public void addResult(String result) {
        Weather weatherDaoBean = new Weather();
        weatherDaoBean.setResult("[" + result + "]");
        weatherDaoBean.setDate(System.currentTimeMillis());
        mWeatherDao.insert(weatherDaoBean);
    }
}
