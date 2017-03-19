package com.example.joe_pc.joeweather.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Joe_PC on 2017/3/19.
 */

public class WeatherBean implements Serializable {

    @SerializedName("basic")
    private BasicBean basic;
    @SerializedName("now")
    private NowBean now;
    @SerializedName("daily_forecast")
    private List<DailyForecastBean> dailyForecast;
    @SerializedName("status")
    private String status;

    public void setBasic(BasicBean basic) {
        this.basic = basic;
    }

    public BasicBean getBasic() {
        return basic;
    }

    public void setNow(NowBean now) {
        this.now = now;
    }

    public NowBean getNow() {
        return now;
    }

    public List<DailyForecastBean> getDailyForecast() {
        return dailyForecast;
    }

    public void setDailyForecast(List<DailyForecastBean> dailyForecast) {
        this.dailyForecast = dailyForecast;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
