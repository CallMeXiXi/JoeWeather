package com.example.joe_pc.joeweather.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.example.joe_pc.joeweather.R;
import com.example.joe_pc.joeweather.adapter.ForecastAdapter;
import com.example.joe_pc.joeweather.base.BaseAppCompatActivity;
import com.example.joe_pc.joeweather.bean.DailyForecastBean;
import com.example.joe_pc.joeweather.bean.RootWeatherBean;
import com.example.joe_pc.joeweather.cache.ForecastCache;
import com.example.joe_pc.joeweather.comments.Constants;
import com.example.joe_pc.joeweather.comments.Utils;
import com.example.joe_pc.joeweather.fragment.IForecastActivity;
import com.example.joe_pc.joeweather.presenter.ForecastWeatherPresenter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe_PC on 2017/7/25.
 */

public class ForecastActivity extends BaseAppCompatActivity implements IForecastActivity {

    private String cityName;
    private Context mContext;
    private Toolbar mToolbar;
    private List<DailyForecastBean> mLists;
    private RecyclerView rv_forecast;
    private ForecastAdapter mAdapter;
    private ForecastWeatherPresenter mPresenter;
    private boolean isNetwotkOk = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setWindowStatusBarColor(this, "#00796B");
        mContext = this;
        setContentView(R.layout.forecast_activity_layout);
    }

    @Override
    public void initView() {
        mToolbar = $(R.id.toolbar);
        initToolbar();

        rv_forecast = $(R.id.rv_forecast);
        rv_forecast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    //初始化Toolbar
    private void initToolbar() {
        mToolbar.setTitle(R.string.seven);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void initData() {
        mLists = new ArrayList<>();
        mAdapter = new ForecastAdapter(mLists, this);

        //获取MainActivity传来的城市名称和网络是否已经开启的标识
        cityName = getIntent().getStringExtra(Constants.CITY_NAME);
        isNetwotkOk = getIntent().getBooleanExtra(Constants.IS_NETWORK_OK, false);

        //先获取缓存，在显示在界面上
        mLists = ForecastCache.getForecastCache(mContext).getResult();
        if (mLists != null) {
            setForecastData(mLists);
        }

        //获取接口数据
        if (isNetwotkOk) {
            mPresenter = new ForecastWeatherPresenter(this);
            mPresenter.getForecastWeather(cityName);
        }
    }

    /**
     * 先设置缓存数据
     */
    private void setForecastData(List<DailyForecastBean> list) {
        mAdapter.setForecastAdapter(list);
        rv_forecast.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void doOnClick(int id) {

    }

    //得到接口数据，在界面显示数据
    @Override
    public void showForecastWeather(RootWeatherBean bean) {
        if (bean != null && bean.getWeather().get(0).getStatus().equals("ok")) {
            ForecastCache.getForecastCache(mContext).addResult(JSONObject.toJSONString(bean));//添加缓存

            mLists = bean.getWeather().get(0).getDailyForecast();
            mAdapter.setForecastAdapter(mLists);
            rv_forecast.setAdapter(mAdapter);
        }
    }

    @Override
    public void loadAlert() {

    }

    @Override
    public void unloadAlert() {

    }
}
