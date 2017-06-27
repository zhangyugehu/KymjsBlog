package com.thssh.kymjsblog.presenter;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.Log;

import com.thssh.kymjsblog.GlobalApplication;
import com.thssh.kymjsblog.base.AbsActivityPresenter;
import com.thssh.kymjsblog.bean.SplashImageBean;
import com.thssh.kymjsblog.bean.User;
import com.thssh.kymjsblog.contract.SplashActivityContract;
import com.thssh.kymjsblog.db.dao.UserDao;
import com.thssh.kymjsblog.service.Api;
import com.thssh.kymjsblog.service.ObserverAdapter;
import com.thssh.kymjsblog.utils.ApiStringUtils;
import com.thssh.library.sqlorm.IBaseDao;
import com.thssh.library.sqlorm.SqlOrmManager;
import com.thssh.library.sqlorm.exception.SqlOrmExecption;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/21
 */

public class SplashPresenter extends AbsActivityPresenter<SplashActivityContract.View>
        implements SplashActivityContract.Presenter{
    private static final String TAG = "SplashPresenter";

    private GlobalApplication mApplication;
    private SplashImageBean mSplashImageBean;
    private int maxOrder;
    private CountDownTimer mTimeCounter = new CountDownTimer(4000, 1000){

        @Override
        public void onTick(long millisUntilFinished) {
            Log.i(TAG, "onTick: " + millisUntilFinished);
            if(isDetached){ return; }
            mView.setTimerText(millisUntilFinished / 1000 + "秒 (跳过)");
        }

        @Override
        public void onFinish() {
            if(isDetached){ return; }
            mView.startMainActivity();
            mView.finishActivity();
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mApplication = mView.getGApplication();
        mApplication.gRetrofit.create(Api.class)
                .getSplashIamge()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverAdapter<List<SplashImageBean>>() {
                    @Override
                    protected void onSuccess() {
                        if(isDetached) { return; }
                        String imageUrl = mSplashImageBean.getImageUrl();
                        mView.setDescriptionText(mSplashImageBean.getDescription());
                        mView.setSplashImage(ApiStringUtils.replaceImageUrlSize(imageUrl,
                                mApplication.gPhoneInfo.screenWidth,
                                mApplication.gPhoneInfo.screenHeight));
                        startSplashCounter();
                    }

                    @Override
                    protected void next(List<SplashImageBean> beans) {
                        if(isDetached) { return; }
                        filterFinalIamgeBean(beans);
                    }

                    @Override
                    protected void onFailure(String error) {
                        if(isDetached) { return; }
                        mView.toast(error);
                    }
                });
    }

    private void startSplashCounter() {
        if(mTimeCounter != null) { mTimeCounter.start(); }
    }

    @Override
    protected void onDestroy() {

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

    @Override
    public void skipTimer() {
        if(isDetached) { return; }
        if(mTimeCounter != null) { mTimeCounter.cancel(); }
        mView.startMainActivity();
        mView.finishActivity();

        try {
            UserDao userDao = SqlOrmManager.getInstance().init(mView.getGApplication()).createDao(UserDao.class, User.class);
//            userDao.insert(new User("zhangsan", 20, "male"));

            List<User> query = userDao.newBuilder()
//                    .columns("f_sex")
//                    .columnArgs("male")
                    .query();
            Log.d(TAG, "skipTimer: " + query);
        } catch (SqlOrmExecption e) {
            e.printStackTrace();
        }
    }
}
