package com.example.joe_pc.joeweather.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.*;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.joe_pc.joeweather.R;
import com.example.joe_pc.joeweather.comments.Constants;
import com.example.joe_pc.joeweather.view.LoadingAlert;
import com.orhanobut.logger.Logger;


/**
 * Activity基础类
 * Created by Joe_PC on 2017/3/15.
 */

public abstract class BaseAppCompatActivity extends AppCompatActivity implements View.OnClickListener {

    private LoadingAlert mAlert;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //定义全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Logger.d(Constants.ACTIVITY_NAME + getResources().getClass().getName());
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //定义全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Logger.d(Constants.ACTIVITY_NAME + getResources().getClass().getName());
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        init();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        init();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        init();
    }

    private void init() {
        initData();
        initView();
        initListener();
    }

    @Override
    public void onClick(View v) {
        doOnClick(v.getId());
    }

    protected <T extends View> T $(@IdRes int id) {
        return (T) findViewById(id);
    }

    protected <T extends View> T $(View view, @IdRes int id) {
        return (T) view.findViewById(id);
    }

    protected void showShortToast(@StringRes int id) {
        Toast.makeText(this, getResources().getString(id), Toast.LENGTH_SHORT).show();
    }

    protected void showLongToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    protected void showAlert() {
        if (mAlert == null) {
            mAlert = new LoadingAlert(this);
        }
        mAlert.show();
    }

    protected void showAlert(String msg) {
        if (mAlert == null) {
            mAlert = new LoadingAlert(this, msg);
        }
        mAlert.show();
    }

    protected void dismissAlert() {
        if (mAlert != null) {
            mAlert.dismiss();
        }
    }

    //初始化视图
    public abstract void initView();

    //初始化数据
    public abstract void initData();

    //初始化事件
    public abstract void initListener();

    //点击事件
    public abstract void doOnClick(int id);
}
