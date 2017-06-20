package com.thssh.kymjsblog;

import android.app.Application;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.thssh.kymjsblog.base.PhoneInfo;
import com.thssh.kymjsblog.service.Api;
import com.thssh.kymjsblog.utils.AppUtils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/20
 */

public class GlobalApplication extends Application {

    public Retrofit gRetrofit;
    public OkHttpClient gClient;
    public PhoneInfo gPhoneInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        initPhoneInfo();
        initOkHttpClient();
        initRetrofit();
    }

    private void initPhoneInfo() {
        gPhoneInfo = new PhoneInfo();
        gPhoneInfo.screenHeight = AppUtils.getScreenHeight(this);
        gPhoneInfo.screenWidth = AppUtils.getScreenWidth(this);
    }

    private void initOkHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ?
                HttpLoggingInterceptor.Level.BODY :
                HttpLoggingInterceptor.Level.NONE);
        gClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    private void initRetrofit() {
        gRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Api.BASE_URL)
                .client(gClient)
                .build();
    }
}
