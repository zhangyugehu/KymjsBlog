package com.thssh.kymjsblog.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.thssh.kymjsblog.R;
import com.thssh.kymjsblog.base.BaseActivity;
import com.thssh.kymjsblog.bean.SplashImageBean;
import com.thssh.kymjsblog.service.Api;
import com.thssh.kymjsblog.service.ObserverAdapter;
import com.thssh.kymjsblog.utils.ApiStringUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/20
 */

public class SplashActivity extends BaseActivity {
    private static final String TAG = "SplashActivity";
    private static final int WHAT_START_ACTIVITY = 0;
    private static final int DELAY_START_MAIN = 3 * 1000;

    private ImageView mSplashIv;
    private TextView mDesTv;
    private SplashImageBean mSplashImageBean;
    private int maxOrder = -1;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case WHAT_START_ACTIVITY:
                    MainActivity.actionMain(SplashActivity.this);
                    SplashActivity.this.finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toggleHideyBar();
        setContentView(R.layout.activity_splash);
        mSplashIv = (ImageView) findViewById(R.id.splash_iv);
        mDesTv = (TextView) findViewById(R.id.des_tv);

        getGlobalRetrofit().create(Api.class)
                .getSplashIamge()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverAdapter<List<SplashImageBean>>() {
                    @Override
                    protected void onSuccess() {
                        String imageUrl = mSplashImageBean.getImageUrl();

                        mDesTv.setText(mSplashImageBean.getDescription());
                        showSplashIamge(ApiStringUtils.replaceImageUrlSize(imageUrl,
                                SplashActivity.this.application.gPhoneInfo.screenWidth,
                                SplashActivity.this.application.gPhoneInfo.screenHeight));

                        handler.sendEmptyMessageDelayed(WHAT_START_ACTIVITY, DELAY_START_MAIN);
                    }

                    @Override
                    protected void next(List<SplashImageBean> beans) {
                        filterFinalIamgeBean(beans);
                    }

                    @Override
                    protected void onFailure(String error) {
                        failured(error);
                    }
                });
    }

    private void failured(String error) {
        toast(error);
    }

    private void filterFinalIamgeBean(List<SplashImageBean> beans) {
        for(SplashImageBean bean : beans){
            int orderIndex = bean.getOrderIndex();
            if(orderIndex > maxOrder) {
                mSplashImageBean = bean;
                maxOrder = orderIndex;
            }
        }
    }

    private void showSplashIamge(String url) {
        Log.d(TAG, "showSplashIamge: url " + url);
        Glide.with(this)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(mSplashIv);
    }
}
