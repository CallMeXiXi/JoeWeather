package com.example.joe_pc.joeweather.presenter;

import android.content.Context;

import com.example.joe_pc.joeweather.base.BaseRxJava;
import com.example.joe_pc.joeweather.bean.CondBean;
import com.example.joe_pc.joeweather.bean.RootWeatherBean;
import com.example.joe_pc.joeweather.bean.WeatherBean;
import com.example.joe_pc.joeweather.comments.Constants;
import com.example.joe_pc.joeweather.fragment.IMainFragment;
import com.example.joe_pc.joeweather.http.NetworkService;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * MainFragmentPresenter加载数据
 * Created by Joe_PC on 2017/3/20.
 */

public class MainFragmentPresenter extends BaseRxJava implements IMainFragment.IMainFragmentPresenter {

    private IMainFragment iMainFragment;

    public MainFragmentPresenter(IMainFragment mainFragment) {
        this.iMainFragment = mainFragment;
    }

    @Override
    public void getNowWeather(String city) {
        iMainFragment.loadAlert();
        Subscription subscription = NetworkService.
                getWeatherApi().
                getWeather(Constants.WEATHER_API_KEY, city).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Action1<RootWeatherBean>() {
                    @Override
                    public void call(RootWeatherBean weatherBean) {
                        iMainFragment.showNowWeather(weatherBean);
                        iMainFragment.unloadAlert();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Logger.e("getNowWeather_throwable:" + throwable.toString());
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        iMainFragment.unloadAlert();
                    }
                });
        addSubscription(subscription);
    }

    public void getNowWeather(String city, boolean refresh) {
        if (!refresh) {

        }
        Subscription subscription = NetworkService.
                getWeatherApi().
                getWeatherNow(Constants.WEATHER_API_KEY, city).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(observer);
        addSubscription(subscription);
    }

    Observer<RootWeatherBean> observer = new Observer<RootWeatherBean>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(RootWeatherBean weatherBean) {
            iMainFragment.showNowWeather(weatherBean);
        }
    };


    @Override
    public void unSubscribe() {
        super.baseUnSubscription();
    }
}
