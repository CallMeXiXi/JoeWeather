package com.example.joe_pc.joeweather.http;


import com.example.joe_pc.joeweather.bean.RootWeatherBean;
import com.example.joe_pc.joeweather.bean.WeatherBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * WeatherApi
 * Created by Joe_PC on 2017/3/19.
 */

public interface WeatherApi {

    /**
     * 获取天气
     * @param key
     * @param city
     * @return
     */
    @GET("weather")
    Observable<RootWeatherBean> getWeather(@Query("key") String key, @Query("city") String city);


    /**
     * 获取当前 实况天气
     * eg: https://free-api.heweather.com/v5/now?key=33e33f5d6ce140f686949d6cc18a6fce&city=珠海市
     *
     * @return
     */
    @GET("now")
    Observable<RootWeatherBean> getWeatherNow(@Query("key") String key, @Query("city") String city);


    /**
     * 获取 7-10天预报
     * eg: https://free-api.heweather.com/v5/forecast?key=33e33f5d6ce140f686949d6cc18a6fce&city=珠海市
     *
     * @return
     */
    @GET("forecast")
    Observable<RootWeatherBean> getWeatherForecast(@Query("key") String key, @Query("city") String city);


}
