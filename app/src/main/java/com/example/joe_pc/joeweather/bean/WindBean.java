package com.example.joe_pc.joeweather.bean;

import java.io.Serializable;

/**
 * Created by Joe_PC on 2017/3/19.
 */

public class WindBean implements Serializable{

    private String deg;
    private String dir;
    private String sc;
    private String spd;
    public void setDeg(String deg) {
        this.deg = deg;
    }
    public String getDeg() {
        return deg;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
    public String getDir() {
        return dir;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }
    public String getSc() {
        return sc;
    }

    public void setSpd(String spd) {
        this.spd = spd;
    }
    public String getSpd() {
        return spd;
    }

}
