package com.thssh.kymjsblog.presenter;

import com.thssh.kymjsblog.base.AbsFragmentPresenter;
import com.thssh.kymjsblog.contract.BlogDetailContract;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/22
 */

public class BlogDetailPresenter extends
        AbsFragmentPresenter<BlogDetailContract.View, BlogDetailContract.Model> {

    @Override
    protected BlogDetailContract.Model bindModel() {
        return null;
    }

    @Override
    protected void onDestroy() {

    }
}
