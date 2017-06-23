package com.thssh.kymjsblog.model;

import com.thssh.kymjsblog.bean.BlogBean;
import com.thssh.kymjsblog.contract.BlogListFragmentContract;
import com.thssh.kymjsblog.service.Api;
import com.thssh.kymjsblog.service.ObserverAdapter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/22
 */

public class BlogListNetModel implements BlogListFragmentContract.Model{

    private Retrofit mRetrofit;

    public BlogListNetModel(Retrofit retrofit) {
        this.mRetrofit = retrofit;
    }

    @Override
    public void loadData(final BlogListFragmentContract.ModelListener listener) {
        mRetrofit.create(Api.class)
                .getBlogList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverAdapter<BlogBean>() {
                    @Override
                    protected void onSuccess() {

                    }

                    @Override
                    protected void next(BlogBean blogs) {
                        if(blogs == null){ listener.onResponseFailure("blogs is null.");}
                        listener.onDataResponse(blogs);
                    }

                    @Override
                    protected void onFailure(String error) {
                        listener.onResponseFailure(error);
                    }
                });
    }
}
