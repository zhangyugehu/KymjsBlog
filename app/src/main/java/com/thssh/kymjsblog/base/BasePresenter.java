package com.thssh.kymjsblog.base;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/21
 */

public interface BasePresenter<V extends BaseView> {

    /**
     * 绑定View
     * @param view
     */
    void onAttach(V view);


    /**
     * 解绑View
     */
    void onDetach();
}
