package com.example.joe_pc.joeweather.bean;

import java.io.Serializable;

/**
 * Created by Joe_PC on 2017/3/21.
 */

public class AirBean implements Serializable {

    private String brf;
    private String txt;

    public void setBrf(String brf) {
        this.brf = brf;
    }
    public String getBrf() {
        return brf;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
    public String getTxt() {
        return txt;
    }
}
