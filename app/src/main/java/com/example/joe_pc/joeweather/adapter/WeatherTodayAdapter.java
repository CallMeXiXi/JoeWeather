package com.example.joe_pc.joeweather.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joe_pc.joeweather.R;

/**
 * Created by Joe_PC on 2017/3/19.
 */

public class WeatherTodayAdapter extends RecyclerView.Adapter<WeatherTodayAdapter.ViewHolder> {



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTvTime;
        ImageView mIvIcon;
        TextView mTvAir;
        TextView mTvTemp;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvTime = (TextView) itemView.findViewById(R.id.tv_time);
            mIvIcon = (ImageView) itemView.findViewById(R.id.iv_weather_icon);
            mTvAir = (TextView) itemView.findViewById(R.id.tv_air);
            mTvTemp = (TextView) itemView.findViewById(R.id.tv_temp);
        }
    }
}
