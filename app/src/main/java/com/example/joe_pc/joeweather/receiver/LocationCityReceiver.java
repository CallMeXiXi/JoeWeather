package com.example.joe_pc.joeweather.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.joe_pc.joeweather.comments.LocationReceiverCallback;
import com.example.joe_pc.joeweather.comments.SharePreferenceUtils;
import com.example.joe_pc.joeweather.ui.MainActivity;
import com.orhanobut.logger.Logger;

/**
 * Created by Joe_PC on 2017/3/16.
 */

public class LocationCityReceiver extends BroadcastReceiver {

    private MainActivity mActivity;
    private LocationReceiverCallback callback;


    //------------------声明定位相关的类-------------------------
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new MyAMapLocationListener();
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;


    @Override
    public void onReceive(Context context, Intent intent) {
        mActivity = MainActivity.getInstance();
        initLocation(context);
    }

    /**
     * 定位服务
     */
    private void initLocation(Context context) {
        //初始化定位
        mLocationClient = new AMapLocationClient(context);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();//设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(false);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    /**
     * 定位回调监听
     */
    private class MyAMapLocationListener implements AMapLocationListener {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    Logger.d("城市名称：" + aMapLocation.getCity());
                    String cityName = aMapLocation.getCity();
                    String oldCityName = SharePreferenceUtils.getSharePerferences(mActivity);
                    if (!oldCityName.equals(cityName)) {//定位成功，回传修改UI
                        SharePreferenceUtils.setSharePerferences(mActivity, cityName);
                        callback.updateUI(cityName);
                    }
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Logger.d("AmapError : location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                    callback.failed();
                }
            }
        }
    }
}
