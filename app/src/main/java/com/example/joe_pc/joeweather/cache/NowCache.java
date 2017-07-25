package com.example.joe_pc.joeweather.cache;

import android.content.Context;

import com.alibaba.fastjson.JSONArray;
import com.example.joe_pc.joeweather.base.WeatherApplication;
import com.example.joe_pc.joeweather.bean.NowBean;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;
import joe.weather.greendao.Now;
import joe.weather.greendao.NowDao;

/**
 * Created by Joe_PC on 2017/3/26.
 */

public class NowCache extends BaseCache {

    public static NowCache mNowCache;
    public static NowDao mNowDao;

    private NowCache() {
    }

    public static NowCache getNowCache(Context context) {
        if (mNowCache == null) {
            mNowCache = new NowCache();
        }
        mDaoSession = WeatherApplication.getDaoSession(context);
        mNowDao = mDaoSession.getNowDao();
        return mNowCache;
    }


    @Override
    public void clearAll() {
        mNowDao.deleteAll();
    }

    @Override
    public ArrayList<NowBean> getResult() {
        ArrayList<NowBean> list = null;
        List<Now> nowList = mNowDao.queryBuilder().
                orderDesc(NowDao.Properties.Id).
                list();
        if (nowList.size() > 0) {
            list = (ArrayList<NowBean>) JSONArray.parseArray(nowList.get(0).getResult(), NowBean.class);
        }
        return list;
    }

    @Override
    public void addResult(String result) {
        Now nowCacheBean = new Now();
        nowCacheBean.setResult(result);
        nowCacheBean.setDate(System.currentTimeMillis());
        mNowDao.insert(nowCacheBean);
    }
}
