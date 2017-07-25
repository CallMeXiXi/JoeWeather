package com.example.joe_pc.joeweather.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Joe_PC on 2017/3/21.
 */

public class SuggestionBean implements Serializable {

    @SerializedName("air")
//    @SerializedName("comf")
//    @SerializedName("cw")
//    @SerializedName("drsg")
//    @SerializedName("flu")
//    @SerializedName("sport")
//    @SerializedName("trav")
//    @SerializedName("uv")
    private AirBean air;

    public AirBean getAir() {
        return air;
    }

    public void setAir(AirBean air) {
        this.air = air;
    }
}
