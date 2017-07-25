package com.example.joe_pc.joeweather.bean;

import java.io.Serializable;

/**
 * Created by Joe_PC on 2017/3/19.
 */

public class CondNowBean implements Serializable {

    private String code;
    private String txt;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getTxt() {
        return txt;
    }

}
