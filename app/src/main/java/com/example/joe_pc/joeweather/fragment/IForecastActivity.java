package com.example.joe_pc.joeweather.fragment;

import com.example.joe_pc.joeweather.base.IBaseFragmentAlert;
import com.example.joe_pc.joeweather.bean.RootWeatherBean;
import com.example.joe_pc.joeweather.bean.WeatherBean;

import java.util.List;

/**
 * 未来3天的天气
 * Created by Joe_PC on 2017/7/26.
 */

public interface IForecastActivity extends IBaseFragmentAlert {

    void showForecastWeather(RootWeatherBean bean);

    interface iForecastWeatherPresenter {
        void getForecastWeather(String cityName);

        void unSubscribe();
    }
}
