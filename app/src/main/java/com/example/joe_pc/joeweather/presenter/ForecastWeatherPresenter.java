package com.example.joe_pc.joeweather.presenter;

import android.support.annotation.MainThread;

import com.example.joe_pc.joeweather.base.BaseRxJava;
import com.example.joe_pc.joeweather.bean.RootWeatherBean;
import com.example.joe_pc.joeweather.bean.WeatherBean;
import com.example.joe_pc.joeweather.comments.Constants;
import com.example.joe_pc.joeweather.fragment.IForecastActivity;
import com.example.joe_pc.joeweather.http.NetworkService;
import com.example.joe_pc.joeweather.http.WeatherApi;
import com.orhanobut.logger.Logger;

import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Joe_PC on 2017/7/26.
 */

public class ForecastWeatherPresenter extends BaseRxJava implements IForecastActivity.iForecastWeatherPresenter {
    private IForecastActivity iForecastActivity;

    public ForecastWeatherPresenter(IForecastActivity forecastActivity) {
        this.iForecastActivity = forecastActivity;
    }

    @Override
    public void getForecastWeather(String cityName) {
        Subscription subscription = NetworkService.getWeatherApi().
                getWeatherForecast(Constants.WEATHER_API_KEY, cityName).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Action1<RootWeatherBean>() {
                    @Override
                    public void call(RootWeatherBean rootWeatherBean) {
                        iForecastActivity.showForecastWeather(rootWeatherBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Logger.d("getForecastWeather_throwable" + throwable.toString());
                    }
                }, new Action0() {
                    @Override
                    public void call() {

                    }
                });
        addSubscription(subscription);
    }

    @Override
    public void unSubscribe() {
        super.baseUnSubscription();
    }
}
