package com.example.joe_pc.joeweather.comments;

/**
 * 广播回调
 * Created by Joe_PC on 2017/3/16.
 */

public interface LocationReceiverCallback {
    void updateUI(String cityName);

    void failed();
}
