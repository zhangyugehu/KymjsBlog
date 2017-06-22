package com.thssh.kymjsblog.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.thssh.kymjsblog.base.AbsActivityPresenter;
import com.thssh.kymjsblog.contract.MainActivityContract;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/22
 */

public class MainPresenter extends AbsActivityPresenter<MainActivityContract.View> implements MainActivityContract.Presenter{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void onDestroy() {

    }
}
