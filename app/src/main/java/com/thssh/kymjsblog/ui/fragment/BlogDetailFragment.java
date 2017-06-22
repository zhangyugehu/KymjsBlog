package com.thssh.kymjsblog.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.thssh.kymjsblog.R;
import com.thssh.kymjsblog.base.AbsPresenterFragment;
import com.thssh.kymjsblog.contract.BlogDetailContract;
import com.thssh.kymjsblog.presenter.BlogDetailPresenter;
import com.thssh.kymjsblog.ui.activity.MainActivity;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/22
 */

public class BlogDetailFragment extends AbsPresenterFragment<MainActivity, BlogDetailPresenter, BlogDetailContract.OnEventsListener> {
    public static final String TAG = "BlogDetailFragment";
    private static final String PARAM_LINK = "PARAM_LINK";

    private String mLink;
    private WebView mBlogDetailWv;

    public static BlogDetailFragment newInstance(String blogLink) {
        BlogDetailFragment fragment = new BlogDetailFragment();
        fragment.setBlogLink(blogLink);
        return fragment;
    }

    public void setBlogLink(String link){
        Bundle args = new Bundle();
        args.putString(PARAM_LINK, link);
        this.setArguments(args);
    }

    @Override
    protected int requireLayoutResource() {
        return R.layout.fragment_blog_detail;
    }

    @Override
    protected void initEvents() {
        if(getArguments() != null){
            mLink = getArguments().getString(PARAM_LINK);
        }
    }

    @Override
    protected void initView(View view) {
        mBlogDetailWv = findViewById(R.id.blog_detail_wv);
        mBlogDetailWv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        WebSettings settings = mBlogDetailWv.getSettings();
    }

    @Override
    protected void onViewReady() {
        mBlogDetailWv.loadUrl(mLink);
    }

    @Override
    protected BlogDetailPresenter bindPresenter() {
        return new BlogDetailPresenter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mBlogDetailWv == null){ return; }
        ((ViewGroup)mBlogDetailWv.getParent()).removeView(mBlogDetailWv);
        mBlogDetailWv.removeAllViews();
        mBlogDetailWv.destroy();
    }
}
