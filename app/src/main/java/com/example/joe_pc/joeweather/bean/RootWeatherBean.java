package com.example.joe_pc.joeweather.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Joe_PC on 2017/3/19.
 */

public class RootWeatherBean implements Serializable {

    @SerializedName("HeWeather5")
    private List<WeatherBean> weather;

    public void setWeather(List<WeatherBean> weather) {
        this.weather = weather;
    }

    public List<WeatherBean> getWeather() {
        return weather;
    }
}
