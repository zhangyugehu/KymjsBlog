package com.thssh.kymjsblog.base;

import com.thssh.kymjsblog.GlobalApplication;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/22
 */

public interface IFragmentView extends BaseView {

    GlobalApplication getGApplication();
    void toast(String text);
    void toast(int resid);
}
