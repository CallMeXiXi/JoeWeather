/**
 *
 */
package com.example.joe_pc.joeweather.comments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.amap.api.location.AMapLocation;
import com.example.joe_pc.joeweather.R;
import com.orhanobut.logger.Logger;

import java.util.Calendar;
import java.util.Date;

/**
 * 辅助工具类
 *
 * @类型名称: Utils
 */
public class Utils {
    /**
     * 开始定位
     */
    public final static int MSG_LOCATION_START = 0;
    /**
     * 定位完成
     */
    public final static int MSG_LOCATION_FINISH = 1;
    /**
     * 停止定位
     */

    /**
     * 根据定位结果返回定位信息的字符串
     *
     * @return
     */
    public synchronized static String getLocationStr(AMapLocation location, final int index) {
        if (null == location) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
        if (location.getErrorCode() == 0) {
            sb.append("定位成功" + "\n");
            sb.append("定位类型: " + location.getLocationType() + "\n");
            sb.append("经    度    : " + location.getLongitude() + "\n");
            sb.append("纬    度    : " + location.getLatitude() + "\n");
            sb.append("精    度    : " + location.getAccuracy() + "米" + "\n");
            sb.append("提供者    : " + location.getProvider() + "\n");

            if (location.getProvider().equalsIgnoreCase(
                    android.location.LocationManager.GPS_PROVIDER)) {
                // 以下信息只有提供者是GPS时才会有
                sb.append("速    度    : " + location.getSpeed() + "米/秒" + "\n");
                sb.append("角    度    : " + location.getBearing() + "\n");
                // 获取当前提供定位服务的卫星个数
                sb.append("星    数    : "
                        + location.getSatellites() + "\n");
            } else {
                // 提供者是GPS时是没有以下信息的
                sb.append("国    家    : " + location.getCountry() + "\n");
                sb.append("省            : " + location.getProvince() + "\n");
                sb.append("市            : " + location.getCity() + "\n");
                sb.append("城市编码 : " + location.getCityCode() + "\n");
                sb.append("区            : " + location.getDistrict() + "\n");
                sb.append("区域 码   : " + location.getAdCode() + "\n");
                sb.append("地    址    : " + location.getAddress() + "\n");
                sb.append("兴趣点    : " + location.getPoiName() + "\n");
                return (location.getAddress() + "," + location.getLongitude() + "," + location.getLatitude());
            }
        } else {
            //定位失败
            sb.append("定位失败" + "\n");
            sb.append("错误码:" + location.getErrorCode() + "\n");
            sb.append("错误信息:" + location.getErrorInfo() + "\n");
            sb.append("错误描述:" + location.getLocationDetail() + "\n");
            return sb.toString();
        }
        return sb.toString();
    }

    /**
     * 检查网络
     *
     * @param context
     */
    public static int checkNetworkInfo(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info == null || !info.isConnected()) {
            return Constants.NETWORK_DISCONNECTED;
        } else if (info != null && info.isConnected()) {
            int type = info.getType();
            if (type == ConnectivityManager.TYPE_MOBILE) {
                return Constants.NETWORK_MOBILE;
            } else if (type == ConnectivityManager.TYPE_WIFI) {
                return Constants.NETWORK_WIFI;
            }
        }
        return -1;
    }

    /**
     * 获取天气图标
     *
     * @param code 图标代码
     * @return
     */
    public static int getWeatherIcon(int code) {
        int str = R.drawable.clear;
        switch (code) {
            case 100:
                str = R.drawable.clear;
                break;
            case 101:
                str = R.drawable.icon_cloudy;
                break;
            case 104:
                str = R.drawable.icon_overcast;
                break;
            case 302:
                str = R.drawable.icon_thundershower;
                break;
            case 305:
                str = R.drawable.icon_light_rain;
                break;
            case 307:
                str = R.drawable.icon_heavy_rain;
                break;
        }
        return str;
    }


    /**
     * 根据长日期转换成 今天、明天、后天
     *
     * @param date 长日期
     * @return
     */
    public static int setDateName(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));
        Date dateWeather = new Date(year, month, day);

        Calendar calendarWeather = Calendar.getInstance();
        calendarWeather.setTime(dateWeather);
        Logger.d("calendarWeather__" + calendarWeather.get(Calendar.MONTH) + "__" + calendarWeather.get(Calendar.DATE));

        Calendar calendarNow = Calendar.getInstance();
        Logger.d("calendarNow___" + calendarNow.get(Calendar.MONTH) + "__" + calendarNow.get(Calendar.DATE));

        if (calendarWeather.get(Calendar.YEAR) == calendarNow.get(Calendar.YEAR)
                && calendarWeather.get(Calendar.MONTH) == calendarNow.get(Calendar.MONTH)
                && calendarWeather.get(Calendar.DATE) == calendarNow.get(Calendar.DATE)) {
            return R.string.today;
        } else if (calendarWeather.get(Calendar.YEAR) == calendarNow.get(Calendar.YEAR)
                && calendarWeather.get(Calendar.MONTH) == calendarNow.get(Calendar.MONTH)
                && calendarWeather.get(Calendar.DATE) - calendarNow.get(Calendar.DATE) == 1) {
            return R.string.tomorrow;
        } else if (calendarWeather.get(Calendar.YEAR) == calendarNow.get(Calendar.YEAR)
                && calendarWeather.get(Calendar.MONTH) == calendarNow.get(Calendar.MONTH)
                && calendarWeather.get(Calendar.DATE) - calendarNow.get(Calendar.DATE) == 2) {
            return R.string.day_after_tomorrow;
        }
        return R.string.today;
    }

    /**
     * 设置状态栏颜色
     *
     * @param activity
     * @param ColorStr
     */
    public static void setWindowStatusBarColor(Activity activity, String ColorStr) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.parseColor(ColorStr));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
