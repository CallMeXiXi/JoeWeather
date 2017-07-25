package com.example.joe_pc.joeweather.cache;


import joe.weather.greendao.DaoSession;

/**
 * Created by Joe_PC on 2017/3/26.
 */

public abstract class BaseCache<T> {

    protected static DaoSession mDaoSession;

    public abstract void clearAll();

    public abstract T getResult();

    public abstract void addResult(String result);
}
