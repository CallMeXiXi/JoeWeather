package com.example.joe_pc.joeweather.bean;

import java.io.Serializable;

/**
 * Created by Joe_PC on 2017/3/19.
 */

public class BasicBean implements Serializable {

    private String city;
    private String cnty;
    private String id;
    private String lat;
    private String lon;
    private UpdateBean update;
    public void setCity(String city) {
        this.city = city;
    }
    public String getCity() {
        return city;
    }

    public void setCnty(String cnty) {
        this.cnty = cnty;
    }
    public String getCnty() {
        return cnty;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
    public String getLat() {
        return lat;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
    public String getLon() {
        return lon;
    }

    public void setUpdate(UpdateBean update) {
        this.update = update;
    }
    public UpdateBean getUpdate() {
        return update;
    }

}
