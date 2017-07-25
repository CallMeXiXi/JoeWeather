package com.example.joe_pc.joeweather.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Joe_PC on 2017/3/19.
 */

public class DailyForecastBean implements Serializable {


    @SerializedName("astro")
    private AstroBean astro;
    @SerializedName("cond")
    private CondBean cond;
    private String date;
    private String hum;
    private String pcpn;
    private String pop;
    private String pres;
    @SerializedName("tmp")
    private TempBean temp;
    private String uv;
    private String vis;
    @SerializedName("wind")
    private WindBean wind;

    public void setAstro(AstroBean astro) {
        this.astro = astro;
    }

    public AstroBean getAstro() {
        return astro;
    }

    public void setCond(CondBean cond) {
        this.cond = cond;
    }

    public CondBean getCond() {
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

    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
    }

    public String getPcpn() {
        return pcpn;
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

    public void setTemp(TempBean temp) {
        this.temp = temp;
    }

    public TempBean getTemp() {
        return temp;
    }

    public void setUv(String uv) {
        this.uv = uv;
    }

    public String getUv() {
        return uv;
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
