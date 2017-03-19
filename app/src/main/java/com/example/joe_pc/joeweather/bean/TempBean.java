package com.example.joe_pc.joeweather.bean;

import java.io.Serializable;

/**
 * Created by Joe_PC on 2017/3/19.
 */

public class TempBean implements Serializable {

    private String max;
    private String min;
    public void setMax(String max) {
        this.max = max;
    }
    public String getMax() {
        return max;
    }

    public void setMin(String min) {
        this.min = min;
    }
    public String getMin() {
        return min;
    }

}
