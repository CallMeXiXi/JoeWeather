package com.example.joe_pc.joeweather.bean;

import java.io.Serializable;

/**
 * Created by Joe_PC on 2017/3/19.
 */

public class AstroBean implements Serializable {

    private String mr;
    private String ms;
    private String sr;
    private String ss;
    public void setMr(String mr) {
        this.mr = mr;
    }
    public String getMr() {
        return mr;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }
    public String getMs() {
        return ms;
    }

    public void setSr(String sr) {
        this.sr = sr;
    }
    public String getSr() {
        return sr;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }
    public String getSs() {
        return ss;
    }
}
