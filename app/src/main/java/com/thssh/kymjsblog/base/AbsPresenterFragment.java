package com.thssh.kymjsblog.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.thssh.kymjsblog.GlobalApplication;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/22
 */

public abstract class AbsPresenterFragment<A extends BaseActivity, P extends AbsFragmentPresenter, L extends BaseEventsListener> 
        extends BaseFragment<A> implements IFragmentView {
//    onAttach:
//    onCreate:
//    onCreateView:
//    onViewCreated:
//    onActivityCreated:
//    onStart:
//    onResume:
//    onPause
//    onSaveInstanceState
//    onStop
//    onDestroyView
//    onDestroy
//    onDetach
    private static final String TAG = "AbsPresenterFragment";

    protected P mPresenter;
    protected L mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: bindPresenter");
        mPresenter = bindPresenter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onActivityCreate: mPresenter.onActivityCreate(savedInstanceState)");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(mPresenter != null){ mPresenter.onStart(); }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mPresenter != null){ mPresenter.onResume(); }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mPresenter != null) mPresenter.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: mPresenter.onSaveInstanceState");
        if(mPresenter != null) mPresenter.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: mPresenter.onDestroy");
        if(mPresenter != null) mPresenter.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: mPresenter.onDetach");
        if(mPresenter != null) mPresenter.onDetach();
    }

    @Override
    protected void onPresenterActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "onPresenterActivityCreated: mPresenter.onAttach -> mPresenter.onActivityCreate");
        if(mPresenter != null) {
            mPresenter.onAttach(this);
        }
        if(mPresenter != null) mPresenter.onActivityCreate(savedInstanceState);
    }

    @Override
    public GlobalApplication getGApplication() {
        return mActivity.getGApplication();
    }

    @Override
    public void toast(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toast(int resid) {
        Toast.makeText(mContext, resid, Toast.LENGTH_SHORT).show();
    }

    protected abstract P bindPresenter();

    public void setOnEventListener(L listener){
        this.mListener = listener;
    }

}
