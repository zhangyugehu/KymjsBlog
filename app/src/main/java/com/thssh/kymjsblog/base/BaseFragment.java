package com.thssh.kymjsblog.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/21
 */

public abstract class BaseFragment<A extends BaseActivity> extends Fragment {
    protected Context mContext;
    protected A mActivity;
    protected LayoutInflater mInflater;
    protected View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflater = LayoutInflater.from(getContext());
        return mInflater.inflate(requireLayoutResource(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mView = view;
        initView(view);
        initEvents();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (A) getActivity();
        onPresenterActivityCreated(savedInstanceState);
        onViewReady();
    }

    protected abstract void onPresenterActivityCreated(Bundle savedInstanceState);

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }


    protected  <T extends View> T findViewById(int resid) {
        return (T) mView.findViewById(resid);
    }

    protected abstract int requireLayoutResource();

    protected abstract void initEvents();

    protected abstract void initView(View view);

    /**
     * onActivityCreated之后执行，所有view准备工作和变量都已完成
     */
    protected abstract void onViewReady();
}
