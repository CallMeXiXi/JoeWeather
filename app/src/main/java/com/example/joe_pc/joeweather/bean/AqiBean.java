package com.example.joe_pc.joeweather.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Joe_PC on 2017/3/21.
 */

public class AqiBean implements Serializable {

    @SerializedName("city")
    private CityBean city;

    public void setCity(CityBean city) {
        this.city = city;
    }

    public CityBean getCity() {
        return city;
    }

}
