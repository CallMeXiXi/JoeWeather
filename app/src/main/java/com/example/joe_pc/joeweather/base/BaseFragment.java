package com.example.joe_pc.joeweather.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.joe_pc.joeweather.comments.Constants;
import com.example.joe_pc.joeweather.view.LoadingAlert;
import com.orhanobut.logger.Logger;

/**
 * Fragment 基类
 * Created by Joe_PC on 2017/3/16.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    private LoadingAlert mAlert;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Logger.d(Constants.FRAGMENT_NAME + getActivity().getResources().getClass().getName());
    }


    protected void init(View view) {
        initData();
        initView(view);
        initListener();
    }

    @Override
    public void onClick(View v) {
        doOnClick(v.getId());
    }

    protected void showShortToast(@StringRes int id) {
        Toast.makeText(getActivity(), getActivity().getResources().getString(id), Toast.LENGTH_SHORT).show();
    }

    protected void showLongToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    protected <T extends View> T $(View view, @IdRes int id) {
        return (T) view.findViewById(id);
    }

    protected void showAlert() {
        if (mAlert == null) {
            mAlert = new LoadingAlert(getActivity());
        }
        mAlert.show();
    }

    protected void showAlert(String msg) {
        if (mAlert == null) {
            mAlert = new LoadingAlert(getActivity(), msg);
        }
        mAlert.show();
    }

    protected void dismissAlert() {
        if (mAlert != null) {
            mAlert.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //初始化视图
    public abstract void initView(View view);

    //初始化数据
    public abstract void initData();

    //初始化事件
    public abstract void initListener();

    //点击事件
    public abstract void doOnClick(@IdRes int id);
}
