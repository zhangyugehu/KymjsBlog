package com.thssh.kymjsblog.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.thssh.kymjsblog.R;
import com.thssh.kymjsblog.base.AbsPresenterActivity;
import com.thssh.kymjsblog.contract.SplashActivityContract;
import com.thssh.kymjsblog.presenter.SplashPresenter;
import com.thssh.library.image.ImageLoader;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/20
 */

public class SplashActivity extends AbsPresenterActivity<SplashPresenter>
        implements SplashActivityContract.View {

    private ImageView mSplashIv;
    private TextView mDesTv, mTimerTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toggleHideyBar();
        setContentView(R.layout.activity_splash);
        mSplashIv = (ImageView) findViewById(R.id.splash_iv);
        mDesTv = (TextView) findViewById(R.id.des_tv);
        mTimerTv = (TextView) findViewById(R.id.timer_tv);

    }

    @Override
    protected SplashPresenter initPresenter() {
        return new SplashPresenter();
    }

    @Override
    public void setDescriptionText(String description) {
        mDesTv.setText(description);
    }

    @Override
    public void setSplashImage(String url) {
        ImageLoader.getInstance().loadUrl(this, url, mSplashIv);
    }

    @Override
    public void startMainActivity() {
        MainActivity.actionMain(this);
    }

    @Override
    public void setTimerText(String text) {
        mTimerTv.setText(text);
    }

    public void skipTimerClick(View view) {
        mPresenter.skipTimer();
    }
}
