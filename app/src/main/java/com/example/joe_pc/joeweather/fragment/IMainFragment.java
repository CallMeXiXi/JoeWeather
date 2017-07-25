package com.example.joe_pc.joeweather.fragment;

import com.example.joe_pc.joeweather.base.IBaseFragmentAlert;
import com.example.joe_pc.joeweather.bean.RootWeatherBean;
import com.example.joe_pc.joeweather.bean.WeatherBean;

import java.util.List;

/**
 * Created by Joe_PC on 2017/3/20.
 */

public interface IMainFragment extends IBaseFragmentAlert {

    //刷新显示数据
    void showNowWeather(RootWeatherBean weatherBean);

    /**
     * IMainFragmentPresenter获取数据
     */
    interface IMainFragmentPresenter {

        void getNowWeather(String city);

        void unSubscribe();
    }
}
