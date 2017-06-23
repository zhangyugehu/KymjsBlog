package com.thssh.kymjsblog.presenter;

import com.thssh.kymjsblog.base.AbsFragmentPresenter;
import com.thssh.kymjsblog.contract.BlogDetailFragmentContract;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/22
 */

public class BlogDetailPresenter extends
        AbsFragmentPresenter<BlogDetailFragmentContract.View, BlogDetailFragmentContract.Model>
        implements BlogDetailFragmentContract.Presenter{

    @Override
    protected BlogDetailFragmentContract.Model bindModel() {
        return null;
    }

    @Override
    protected void onDestroy() {

    }
}
