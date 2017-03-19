package com.example.joe_pc.joeweather.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.joe_pc.joeweather.R;
import com.example.joe_pc.joeweather.base.BaseAppCompatActivity;
import com.example.joe_pc.joeweather.comments.Constants;
import com.example.joe_pc.joeweather.comments.LocationReceiverCallback;
import com.orhanobut.logger.Logger;

public class MainActivity extends BaseAppCompatActivity implements LocationReceiverCallback {

    private AppBarLayout mAppBarLayout;
    private CollapsingToolbarLayout mCtlLayout;
    private ViewPager mViewPager;
    private Toolbar mToolbar;
    private FloatingActionButton mFabAdd;

    private static MainActivity mActivity;

    public static MainActivity getInstance() {
        return mActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;
    }


    @Override
    public void initView() {
        mAppBarLayout = $(R.id.appbar_layout);
        mCtlLayout = $(R.id.ctl_toolbar);
        mViewPager = $(R.id.view_pager);
        mToolbar = $(R.id.toolbar);
        mFabAdd = $(R.id.fb_add);

        initToolbarMenu();
    }


    /**
     * 给Toolbar创建菜单
     */
    private void initToolbarMenu() {

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
                showShortToast("1");
                break;
            case R.id.seven:
                showShortToast("2");
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

//        initReceiver();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void doOnClick(int id) {

    }

    /**
     * 广播回调刷新定位信息
     *
     * @param cityName
     */
    @Override
    public void updateUI(String cityName) {
        AlertDialog dialog = new AlertDialog.Builder(this).
                setCancelable(false).
                setMessage(R.string.relocatCity).
                setNegativeButton(R.string.cancle, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).
                setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();
        dialog.show();
    }

    /**
     * 广播回调失败
     */
    @Override
    public void failed() {

    }
}
