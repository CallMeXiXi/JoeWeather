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
import com.example.joe_pc.joeweather.bean.DailyForecastBean;
import com.example.joe_pc.joeweather.comments.Constants;
import com.example.joe_pc.joeweather.comments.Utils;

import java.util.List;

/**
 * Created by Joe_PC on 2017/7/26.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {

    private List<DailyForecastBean> mLists;
    private Context mContext;

    public ForecastAdapter(List<DailyForecastBean> list, Context context) {
        this.mLists = list;
        this.mContext = context;
    }

    public void setForecastAdapter(List<DailyForecastBean> list) {
        this.mLists = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_weather_forecast_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DailyForecastBean bean = mLists.get(position);
        if (bean != null) {
//            holder.mTvForecastName.setText(Utils.setDateName(bean.getDate()));
            holder.mTvForecastDate.setText(bean.getDate().substring(5).replace('-','/'));
            holder.mTvForecastTempMin.setText(bean.getTemp().getMin());
            holder.mTvForecastTempMax.setText(bean.getTemp().getMax()+"°");
            holder.mTvForecastDWeather.setText(bean.getCond().getTxtD());
            holder.mTvForecastNWeather.setText(bean.getCond().getTxtN());
            holder.mTvForecastWind.setText(bean.getWind().getSpd() + " 级");
            holder.mTvForecastHum.setText(bean.getHum());

            String urlD = Constants.WEATHER_ICON + bean.getCond().getCodeD() + ".png";
            String urlN = Constants.WEATHER_ICON + bean.getCond().getCodeN() + ".png";
            Glide.with(mContext).
                    load(urlD).
                    diskCacheStrategy(DiskCacheStrategy.ALL).
                    centerCrop().
                    into(holder.mTvForecastDIcon);
            Glide.with(mContext).
                    load(urlN).
                    diskCacheStrategy(DiskCacheStrategy.ALL).
                    centerCrop().
                    into(holder.mTvForecastNIcon);
        }
    }

    @Override
    public int getItemCount() {
        return mLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

//        TextView mTvForecastName;
        TextView mTvForecastDate;
        TextView mTvForecastTempMin;
        TextView mTvForecastTempMax;
        TextView mTvForecastDWeather;
        TextView mTvForecastNWeather;
        TextView mTvForecastWind;
        TextView mTvForecastHum;
        ImageView mTvForecastDIcon;
        ImageView mTvForecastNIcon;

        public ViewHolder(View itemView) {
            super(itemView);
//            mTvForecastName = (TextView) itemView.findViewById(R.id.tv_forecast_name);
            mTvForecastDate = (TextView) itemView.findViewById(R.id.tv_forecast_date);
            mTvForecastTempMin = (TextView) itemView.findViewById(R.id.tv_forecast_temp_min);
            mTvForecastTempMax = (TextView) itemView.findViewById(R.id.tv_forecast_temp_max);
            mTvForecastDWeather = (TextView) itemView.findViewById(R.id.tv_forecast_d_weather);
            mTvForecastNWeather = (TextView) itemView.findViewById(R.id.tv_forecast_n_weather);
            mTvForecastWind = (TextView) itemView.findViewById(R.id.tv_forecast_wind);
            mTvForecastHum = (TextView) itemView.findViewById(R.id.tv_forecast_hum);
            mTvForecastDIcon = (ImageView) itemView.findViewById(R.id.iv_forecast_d_icon);
            mTvForecastNIcon = (ImageView) itemView.findViewById(R.id.iv_forecast_n_icon);
        }
    }
}
