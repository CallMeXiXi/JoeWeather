package com.example.joe_pc.joeweather.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Joe_PC on 2017/3/21.
 */

public class HourlyForecastBean implements Serializable {

    @SerializedName("cond")
    private CondNowBean cond;
    private String date;
    private String hum;
    private String pop;
    private String pres;
    private String tmp;
    @SerializedName("wind")
    private WindBean wind;

    public void setCond(CondNowBean cond) {
        this.cond = cond;
    }
    public CondNowBean getCond() {
        return cond;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return date;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }
    public String getHum() {
        return hum;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }
    public String getPop() {
        return pop;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }
    public String getPres() {
        return pres;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }
    public String getTmp() {
        return tmp;
    }

    public void setWind(WindBean wind) {
        this.wind = wind;
    }
    public WindBean getWind() {
        return wind;
    }

}
