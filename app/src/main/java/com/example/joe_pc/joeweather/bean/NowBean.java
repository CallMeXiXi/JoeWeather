package com.example.joe_pc.joeweather.bean;

import java.io.Serializable;

/**
 * Created by Joe_PC on 2017/3/19.
 */

public class NowBean implements Serializable{

    private CondBean cond;
    private String fl;
    private String hum;
    private String pcpn;
    private String pres;
    private String tmp;
    private String vis;
    private WindBean wind;
    public void setCond(CondBean cond) {
        this.cond = cond;
    }
    public CondBean getCond() {
        return cond;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }
    public String getFl() {
        return fl;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }
    public String getHum() {
        return hum;
    }

    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
    }
    public String getPcpn() {
        return pcpn;
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

    public void setVis(String vis) {
        this.vis = vis;
    }
    public String getVis() {
        return vis;
    }

    public void setWind(WindBean wind) {
        this.wind = wind;
    }
    public WindBean getWind() {
        return wind;
    }
}
