package com.example.joe_pc.joeweather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.joe_pc.joeweather.R;
import com.example.joe_pc.joeweather.bean.HourlyForecastBean;
import com.example.joe_pc.joeweather.comments.Constants;

import java.util.List;

/**
 * Created by Joe_PC on 2017/3/19.
 */

public class WeatherTodayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int ITEM = 1;
    private Context mContext;
    private List<HourlyForecastBean> mLists;

    public WeatherTodayAdapter(Context context, List<HourlyForecastBean> list) {
        this.mContext = context;
        this.mLists = list;
    }

    public void setHourlyForecastList(List<HourlyForecastBean> list) {
        mLists = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            ITEM = Constants.ITEM_WEATHER_DEFAULT;
        } else if (position > 0) {
            ITEM = Constants.ITEM_WEATHER;
        }
        return ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Constants.ITEM_WEATHER) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_weather_now_layout, parent, false);
            return new ViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_weather_now_default_layout, parent, false);
            return new ViewHolderDefault(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            HourlyForecastBean bean = mLists.get(position);
            if (bean != null) {
                ((ViewHolder) holder).mTvTime.setText(bean.getDate().substring(11));
                ((ViewHolder) holder).mTvWeather.setText(bean.getCond().getTxt());
                ((ViewHolder) holder).mTvTemp.setText(bean.getTmp() + "°");

                String url = Constants.WEATHER_ICON + bean.getCond().getCode() + ".png";
                Glide.with(mContext).
                        load(url).
                        centerCrop().
                        diskCacheStrategy(DiskCacheStrategy.ALL).
                        into(((ViewHolder) holder).mIvIcon);
            } else {

            }
        } else if (holder instanceof ViewHolderDefault) {
            ((ViewHolderDefault) holder).mTvTime.setText(R.string.dailt_forecast);
            ((ViewHolderDefault) holder).mIvIcon.setImageResource(R.drawable.icon_cloke);
        }
    }

    @Override
    public int getItemCount() {
        return mLists.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTvTime;
        ImageView mIvIcon;
        TextView mTvWeather;
        TextView mTvTemp;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvTime = (TextView) itemView.findViewById(R.id.tv_time);
            mIvIcon = (ImageView) itemView.findViewById(R.id.iv_weather_icon);
            mTvWeather = (TextView) itemView.findViewById(R.id.tv_weather);
            mTvTemp = (TextView) itemView.findViewById(R.id.tv_temp);
        }
    }

    public class ViewHolderDefault extends RecyclerView.ViewHolder {
        TextView mTvTime;
        ImageView mIvIcon;

        public ViewHolderDefault(View itemView) {
            super(itemView);
            mTvTime = (TextView) itemView.findViewById(R.id.tv_time);
            mIvIcon = (ImageView) itemView.findViewById(R.id.iv_weather_icon);
        }
    }
}
