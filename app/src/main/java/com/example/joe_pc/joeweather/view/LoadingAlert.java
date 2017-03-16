package com.example.joe_pc.joeweather.view;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.joe_pc.joeweather.R;

/**
 * 显示 加载
 * Created by Joe_PC on 2017/3/15.
 */

public class LoadingAlert extends AlertDialog {

    private Context mContext;
    private TextView tvAlert;


    public LoadingAlert(Context context) {
        super(context);
        this.mContext = context;
        init("加载中...");
    }

    public LoadingAlert(Context context, String msg) {
        super(context);
        this.mContext = context;
        init(msg);
    }

    private void init(String msg) {
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        View view = LayoutInflater.from(mContext).inflate(R.layout.loading_alert_layout, null, false);
        tvAlert = (TextView) view.findViewById(R.id.tv_alert);
        tvAlert.setText(msg);
        setView(view);
    }

    public void setTextViewMsg(String msg) {
        tvAlert.setText(msg);
    }
}
