package com.example.joe_pc.joeweather.bean;

import java.io.Serializable;

/**
 * Created by Joe_PC on 2017/3/19.
 */

public class UpdateBean implements Serializable {

    private String loc;
    private String utc;
    public void setLoc(String loc) {
        this.loc = loc;
    }
    public String getLoc() {
        return loc;
    }

    public void setUtc(String utc) {
        this.utc = utc;
    }
    public String getUtc() {
        return utc;
    }
}
