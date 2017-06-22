package com.thssh.kymjsblog.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/22
 */

public abstract class AbsFragmentPresenter<V extends IFragmentView, M extends BaseModel> implements BasePresenter<V> {
    protected boolean isDetached;
    protected V mView;
    protected M mModel;

    @Override
    public void onAttach(V view) {
        this.mView = view;
        this.mModel = bindModel();
    }

    protected abstract M bindModel();

    @Override
    public void onDetach() {
        this.mView = null;
        this.isDetached = true;
    }

    protected void onActivityCreate(@Nullable Bundle savedInstanceState){}
    protected void onResume(){}
    protected void onStart(){}
    protected void onPause(){}
    protected void onSaveInstanceState(Bundle outState){}

    protected abstract void onDestroy();



}
