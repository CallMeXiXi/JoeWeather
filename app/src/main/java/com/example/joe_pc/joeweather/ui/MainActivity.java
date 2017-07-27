package com.example.joe_pc.joeweather.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.joe_pc.joeweather.R;
import com.example.joe_pc.joeweather.adapter.FragmentAdapter;
import com.example.joe_pc.joeweather.base.BaseAppCompatActivity;
import com.example.joe_pc.joeweather.cache.WeatherCache;
import com.example.joe_pc.joeweather.comments.Constants;
import com.example.joe_pc.joeweather.comments.LocationReceiverCallback;
import com.example.joe_pc.joeweather.comments.SharePreferenceUtils;
import com.example.joe_pc.joeweather.comments.Utils;
import com.example.joe_pc.joeweather.fragment.MainFragment;
import com.orhanobut.logger.Logger;

public class MainActivity extends BaseAppCompatActivity implements LocationReceiverCallback {

    private CollapsingToolbarLayout mCtlLayout;
    private ViewPager mViewPager;
    private Toolbar mToolbar;
    private FloatingActionButton mFabAdd;

    public String city = "";
    public boolean isNetworkOk = true;
    private FragmentAdapter mAdapter;
    private static MainActivity mActivity;

    public static MainActivity getInstance() {
        return mActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(R.layout.activity_main);
    }


    @Override
    public void initView() {
        mCtlLayout = $(R.id.ctl_toolbar);
        mViewPager = $(R.id.view_pager);
        mToolbar = $(R.id.toolbar);
        mFabAdd = $(R.id.fb_add);

        initToolbarMenu();
        initViewPager();
    }


    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        mAdapter = new FragmentAdapter(getSupportFragmentManager(), MainFragment.getInstance(city));
        mViewPager.setAdapter(mAdapter);
    }


    /**
     * 给Toolbar创建菜单
     */
    private void initToolbarMenu() {
        mToolbar.setTitle(city);
        setSupportActionBar(mToolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                WeatherCache.getWeatherCache(mActivity).clearAll();
                showShortToast("1");
                break;
            case R.id.seven:
                Intent intent = new Intent(mActivity, ForecastActivity.class);
                intent.putExtra(Constants.CITY_NAME, city);
                intent.putExtra(Constants.IS_NETWORK_OK, isNetworkOk);
                mActivity.startActivity(intent);
                break;
            case R.id.city_manage:
                showShortToast("3");
                break;
            case R.id.city_warn:
                showShortToast("4");
                break;
        }
        return true;
    }

    /**
     * 发送定位广播
     */
    private void initReceiver() {
        Intent locationIntent = new Intent(Constants.LOCATION_RECEIVER);
        sendBroadcast(locationIntent);
    }

    @Override
    public void initData() {
        if (SharePreferenceUtils.getSharePreferencesCityName(this) != null) {
            city = SharePreferenceUtils.getSharePreferencesCityName(this);
        }
        if (Utils.checkNetworkInfo(mActivity) != -1) {
            initReceiver();
            isNetworkOk = true;
        } else {
            showShortToast(R.string.no_network);
            isNetworkOk = false;
        }
    }

    @Override
    public void initListener() {

    }

    @Override
    public void doOnClick(int id) {
        switch (id) {
            case R.id.fb_add:
                showShortToast("新增城市天气");
                break;

        }
    }

    /**
     * 广播回调刷新定位信息
     *
     * @param cityName
     */
    @Override
    public void updateUI(final String cityName, final boolean replaceCityName) {
        Logger.d("updateUI_cityName: " + cityName);
        AlertDialog dialog = new AlertDialog.Builder(this).
                setCancelable(false).
                setMessage(R.string.relocatCity).
                setNegativeButton(R.string.cancle, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        isNetworkOk = false;
                    }
                }).
                setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showShortToast(cityName);
                        SharePreferenceUtils.setSharePreferencesCityName(MainActivity.this, cityName);
                        Logger.d("updateUI_cityName1: " + cityName);
                        if (replaceCityName) {
                            city = cityName;
                        }
                    }
                }).create();
        dialog.show();
    }


    /**
     * 广播回调失败
     */
    @Override
    public void failed() {
        Logger.d("failed: 定位失败");
        isNetworkOk = false;
    }
}
