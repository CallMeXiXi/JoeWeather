package com.example.joe_pc.joeweather.cache;

import android.content.Context;

import com.alibaba.fastjson.JSONArray;
import com.example.joe_pc.joeweather.base.WeatherApplication;
import com.example.joe_pc.joeweather.bean.DailyForecastBean;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;
import joe.weather.greendao.Forecast;
import joe.weather.greendao.ForecastDao;

/**
 * Created by Joe_PC on 2017/3/26.
 */

public class ForecastCache extends BaseCache {

    public static ForecastCache mForecastCache;
    public static ForecastDao mForecastDao;

    private ForecastCache() {
    }

    public static ForecastCache getForecastCache(Context context) {
        if (mForecastCache == null) {
            mForecastCache = new ForecastCache();
        }
        mDaoSession = WeatherApplication.getDaoSession(context);
        mForecastDao = mDaoSession.getForecastDao();
        return mForecastCache;
    }

    @Override
    public void clearAll() {
        mForecastDao.deleteAll();
    }

    @Override
    public ArrayList<DailyForecastBean> getResult() {
        ArrayList<DailyForecastBean> list = null;
        List<Forecast> forecastList = mForecastDao.queryBuilder().
                orderDesc(ForecastDao.Properties.Id).
                list();
        if (forecastList.size() > 0) {
            list = (ArrayList<DailyForecastBean>) JSONArray.parseArray(forecastList.get(0).getResult(), DailyForecastBean.class);
        }
        return list;
    }

    @Override
    public void addResult(String result) {
        Forecast forecastCacheBean = new Forecast();
        forecastCacheBean.setResult(result);
        forecastCacheBean.setDate(System.currentTimeMillis());
        mForecastDao.insert(forecastCacheBean);
    }
}
