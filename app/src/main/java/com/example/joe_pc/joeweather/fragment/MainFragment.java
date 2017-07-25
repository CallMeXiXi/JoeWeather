package com.example.joe_pc.joeweather.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.joe_pc.joeweather.R;
import com.example.joe_pc.joeweather.adapter.WeatherTodayAdapter;
import com.example.joe_pc.joeweather.base.BaseFragment;
import com.example.joe_pc.joeweather.bean.RootWeatherBean;
import com.example.joe_pc.joeweather.bean.WeatherBean;
import com.example.joe_pc.joeweather.cache.WeatherCache;
import com.example.joe_pc.joeweather.comments.Constants;
import com.example.joe_pc.joeweather.comments.SharePreferenceUtils;
import com.example.joe_pc.joeweather.comments.Utils;
import com.example.joe_pc.joeweather.presenter.MainFragmentPresenter;
import com.example.joe_pc.joeweather.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Joe_PC on 2017/3/17.
 */

public class MainFragment extends BaseFragment implements IMainFragment {

    private View mView;
    private MainActivity mActivity;
    private ImageView mIvBgWeather;
    private TextView mTvWeather;
    private ImageView mIvWeatherIcon;
    private TextView mTvTemp;
    private TextView mTvAir;
    private RecyclerView mWeatherRecyclerView;
    private MainFragmentPresenter mPresenter;
    private List<WeatherBean> mWeatherLists;
    private RecyclerView.LayoutManager mManager;
    private WeatherTodayAdapter mAdapter;
    private boolean isNetworkOk;

    private static MainFragment instance;

    public static MainFragment getInstance(String cityName) {
        if (instance == null) {
            instance = new MainFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constants.CITY_NAME, cityName);
            instance.setArguments(bundle);
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_main_layout, container, false);
        mActivity = (MainActivity) getActivity();
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
        isNetworkOk = mActivity.isNetworkOk;

        mWeatherLists = new ArrayList<>();
        mManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        mWeatherRecyclerView.setLayoutManager(mManager);

        //先获取缓存
        mWeatherLists = WeatherCache.getWeatherCache(mActivity).getResult();
        if (mWeatherLists != null) {
            setWeatherData(mWeatherLists);
        }
        if (isNetworkOk) {
            com.orhanobut.logger.Logger.d("updateUI_cityName: " + mActivity.city);
            mPresenter = new MainFragmentPresenter(this);
            mPresenter.getNowWeather(mActivity.city);
        }
    }

    /**
     * 加载时先显示缓存数据
     */
    private void setWeatherData(List<WeatherBean> list) {
        WeatherBean weatherBean = list.get(0);
        mTvWeather.setText(weatherBean.getNow().getCond().getTxt());
        mTvTemp.setText(weatherBean.getNow().getTmp() + "°");
        String qlty = weatherBean.getAqi().getCity().getAqi().toString() + " " +
                weatherBean.getAqi().getCity().getQlty();
        mTvAir.setText(qlty);
        int code = Integer.parseInt(weatherBean.getNow().getCond().getCode());
        mIvWeatherIcon.setImageResource(Utils.getWeatherIcon(code));

//        String url = Constants.WEATHER_ICON + weatherBean.getNow().getCond().getCode() + ".png";
//        Glide.with(mActivity).
//                load(url).
//                centerCrop().
//                diskCacheStrategy(DiskCacheStrategy.ALL).
//                into(mIvWeatherIcon);

        mWeatherLists = list;
        mAdapter = new WeatherTodayAdapter(mActivity, mWeatherLists.get(0).getHourlyForecast());
        mWeatherRecyclerView.setAdapter(mAdapter);
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

    /**
     * 显示加载完成后显示数据
     *
     * @param rootWeatherBean
     */
    @Override
    public void showNowWeather(RootWeatherBean rootWeatherBean) {
        if (rootWeatherBean != null && rootWeatherBean.getWeather().get(0).getStatus().equals("ok")) {
            WeatherBean weatherBean = rootWeatherBean.getWeather().get(0);
            WeatherCache.getWeatherCache(mActivity).addResult(JSONArray.toJSONString(rootWeatherBean));//缓存数据

            //设置视图控件数据
            mTvWeather.setText(weatherBean.getNow().getCond().getTxt());
            mTvTemp.setText(weatherBean.getNow().getTmp() + "°");
            String qlty = weatherBean.getAqi().getCity().getAqi().toString() + " " +
                    weatherBean.getAqi().getCity().getQlty();
            mTvAir.setText(qlty);
            int code = Integer.parseInt(weatherBean.getNow().getCond().getCode());
            mIvWeatherIcon.setImageResource(Utils.getWeatherIcon(code));

//            String url = Constants.WEATHER_ICON + weatherBean.getNow().getCond().getCode() + ".png";
//            Glide.with(mActivity).
//                    load(url).
//                    centerCrop().
//                    diskCacheStrategy(DiskCacheStrategy.ALL).
//                    into(mIvWeatherIcon);

            mWeatherLists = rootWeatherBean.getWeather();
            mAdapter = new WeatherTodayAdapter(mActivity, weatherBean.getHourlyForecast());
            mWeatherRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void loadAlert() {
        showAlert();
    }

    @Override
    public void unloadAlert() {
        dismissAlert();
    }
}
