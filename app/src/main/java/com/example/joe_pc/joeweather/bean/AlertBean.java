package com.example.joe_pc.joeweather.bean;

import java.io.Serializable;

/**
 * Created by Joe_PC on 2017/3/26.
 */

public class AlertBean implements Serializable {

    private String level;

    private String stat;

    private String title;

    private String txt;

    private String type;

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return this.level;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getStat() {
        return this.stat;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getTxt() {
        return this.txt;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
