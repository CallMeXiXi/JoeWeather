package com.example.joe_pc.joeweather.http;

import com.example.joe_pc.joeweather.comments.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *  定义接口服务
 * Created by Joe_PC on 2017/3/19.
 */
public class NetworkService {

    private static Retrofit mRetrofit;
    private static WeatherApi mWeatherApi;
    private static Gson gson = new GsonBuilder().setLenient().create();
    //定义获取接口Url
    private static OkHttpClient mClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Logger.d("URL: " + request.url());
            Response response = chain.proceed(request);
            return response;
        }
    }).build();

    private static Retrofit getRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder().
                    baseUrl(Constants.WEATHER_API).
                    client(mClient).
                    addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                    addConverterFactory(GsonConverterFactory.create(gson)).
                    build();
        }
        return mRetrofit;
    }

    public static WeatherApi getWeatherApi() {
        if (mWeatherApi == null) {
            if (mRetrofit == null) {
                mRetrofit = getRetrofit();
            }
            mWeatherApi = mRetrofit.create(WeatherApi.class);
        }
        return mWeatherApi;
    }
}
