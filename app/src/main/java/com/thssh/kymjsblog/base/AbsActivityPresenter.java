package com.thssh.kymjsblog.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/21
 */

public abstract class AbsActivityPresenter<V extends IActivityView> implements BasePresenter<V>{
    protected boolean isDetached;
    protected V mView;

    @Override
    public void onAttach(V view) {
        this.mView = view;
    }

    @Override
    public void onDetach() {
        this.mView = null;
        this.isDetached = true;
    }

    protected abstract void onCreate(@Nullable Bundle savedInstanceState);
    protected void onPause(){}
    protected void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState){}
    protected void onRestoreInstanceState(Bundle savedInstanceState){}

    /**
     * 所依赖的Activity被销毁应中止所以操作防止内存泄露
     */
    protected abstract void onDestroy();


}
