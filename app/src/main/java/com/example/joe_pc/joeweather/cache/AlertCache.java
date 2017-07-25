package com.example.joe_pc.joeweather.cache;

import android.content.Context;

import com.alibaba.fastjson.JSONArray;
import com.example.joe_pc.joeweather.base.WeatherApplication;
import com.example.joe_pc.joeweather.bean.AlertBean;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;
import joe.weather.greendao.Alert;
import joe.weather.greendao.AlertDao;

/**
 * Created by Joe_PC on 2017/3/26.
 */

public class AlertCache extends BaseCache {

    public static AlertCache mAlertCache;
    public static AlertDao mAlertDao;

    private AlertCache() {
    }

    public static AlertCache getAlertCache(Context context) {
        if (mAlertCache == null) {
            mAlertCache = new AlertCache();
        }
        mDaoSession = WeatherApplication.getDaoSession(context);
        mAlertDao = mDaoSession.getAlertDao();
        return mAlertCache;
    }

    @Override
    public void clearAll() {
        mAlertDao.deleteAll();
    }

    @Override
    public ArrayList<AlertBean> getResult() {
        ArrayList<AlertBean> list = null;
        List<Alert> alertList = mAlertDao.queryBuilder().
                orderDesc(AlertDao.Properties.Id).
                list();
        if (alertList.size() > 0) {
            list = (ArrayList<AlertBean>) JSONArray.parseArray(alertList.get(0).getResult(), AlertBean.class);
        }
        return list;
    }

    @Override
    public void addResult(String result) {
        Alert alertCacheBean = new Alert();
        alertCacheBean.setResult(result);
        alertCacheBean.setDate(System.currentTimeMillis());
        mAlertDao.insert(alertCacheBean);
    }
}
