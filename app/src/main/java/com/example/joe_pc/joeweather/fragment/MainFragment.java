package com.example.joe_pc.joeweather.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joe_pc.joeweather.R;
import com.example.joe_pc.joeweather.base.BaseFragment;

/**
 * Created by Joe_PC on 2017/3/17.
 */

public class MainFragment extends BaseFragment {

    private View mView;
    private ImageView mIvBgWeather;
    private TextView mTvWeather;
    private ImageView mIvWeatherIcon;
    private TextView mTvTemp;
    private TextView mTvAir;
    private RecyclerView mWeatherRecyclerView;


    private static MainFragment instance;

    public static MainFragment getInstance() {
        if (instance == null) {
            instance = new MainFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_main_layout, container, false);
        init(mView);
        return mView;
    }

    @Override
    public void initView(View view) {
        mIvBgWeather = $(view, R.id.iv_bg_weather);
        mTvWeather = $(view, R.id.tv_weather);
        mIvWeatherIcon = $(view, R.id.iv_weather_icon);
        mTvTemp = $(view, R.id.tv_temp);
        mTvAir = $(view, R.id.tv_air);
        mWeatherRecyclerView = $(view, R.id.rv_weather);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void doOnClick(@IdRes int id) {

    }
}
