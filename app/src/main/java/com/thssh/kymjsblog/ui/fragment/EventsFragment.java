package com.thssh.kymjsblog.ui.fragment;

import android.view.View;

import com.thssh.kymjsblog.base.AbsFragmentPresenter;
import com.thssh.kymjsblog.base.AbsPresenterFragment;
import com.thssh.kymjsblog.contract.EventsFragmentContract;
import com.thssh.kymjsblog.ui.activity.MainActivity;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/23
 */

public abstract class EventsFragment<P extends AbsFragmentPresenter>
        extends AbsPresenterFragment<MainActivity, P, EventsFragmentContract.OnEventsListener>{

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void onViewReady() {

    }
}
