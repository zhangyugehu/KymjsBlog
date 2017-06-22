package com.thssh.kymjsblog.presenter;

import android.util.Log;

import com.thssh.kymjsblog.base.AbsFragmentPresenter;
import com.thssh.kymjsblog.bean.BlogItemBean;
import com.thssh.kymjsblog.contract.BlogListFragmentContract;
import com.thssh.kymjsblog.model.BlogListNetModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/22
 */

public class BlogListPresenter extends AbsFragmentPresenter<BlogListFragmentContract.View, BlogListFragmentContract.Model>
        implements BlogListFragmentContract.Presenter{
    private static final String TAG = "BlogListPresenter";

    private List<BlogItemBean> mDatas;
    private boolean isRefresh;
    public BlogListPresenter() {
        mDatas = new ArrayList<>();
    }

    @Override
    protected BlogListFragmentContract.Model bindModel() {
        Log.d(TAG, "bindModel: ");
        return new BlogListNetModel(mView.getGApplication().gRetrofit);
    }

    @Override
    protected void onDestroy() {

    }


    @Override
    public List<BlogItemBean> requireDatas() {
        return mDatas;
    }

    @Override
    public void loadData() {
        if(mModel == null) {
            mView.toast("model not init.");
            return;
        }
        isRefresh = true;
        mModel.loadData(new BlogListFragmentContract.ModelListener() {
            @Override
            public void onDataResponse(List<BlogItemBean> blogs) {
                if(isRefresh){ mDatas.clear(); }
                mDatas.addAll(blogs);
                mView.notifyAdapteDataChanged();
            }

            @Override
            public void onResponseFailure(String error) {
                mView.toast(error);
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        if(checkDatas(position)) { return; }
        BlogItemBean itemBean = mDatas.get(position);
        String link = itemBean.getLink();
        mView.enterBlog(link);
    }

    private boolean checkDatas(int position) {
        if(mDatas == null) {
            mView.toast("model not init.");
            return true;
        }
        if(position < 0 || position > mDatas.size() - 1){
            mView.toast("model not init.");
            return true;
        }
        return false;
    }
}
