package com.thssh.kymjsblog.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/21
 */

public abstract class AbsPresenterActivity<P extends AbsActivityPresenter> extends BaseSkinActivity implements IActivityView {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = initPresenter();
        if(mPresenter != null) {
            mPresenter.onAttach(this);
            mPresenter.onCreate(savedInstanceState);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mPresenter != null) { mPresenter.onPause(); }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        if(mPresenter != null){ mPresenter.onSaveInstanceState(outState, outPersistentState);}
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(mPresenter != null) { mPresenter.onRestoreInstanceState(savedInstanceState); }
        if(mPresenter != null) { mPresenter.onRestoreInstanceState(savedInstanceState); }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter.onDetach();
        }
    }

    protected abstract P initPresenter();
}
