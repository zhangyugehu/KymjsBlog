package com.thssh.kymjsblog.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.thssh.kymjsblog.R;
import com.thssh.kymjsblog.bean.BlogItemBean;
import com.thssh.kymjsblog.contract.BlogDetailFragmentContract;
import com.thssh.kymjsblog.presenter.BlogDetailPresenter;
import com.thssh.library.common.WebViewUtils;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/22
 */

public class BlogDetailFragment extends EventsFragment<BlogDetailPresenter>
        implements BlogDetailFragmentContract.View{
    public static final String TAG = "BlogDetailFragment";
    private static final String PARAM_LINK = "param_link";
    private static final String PARAM_TITLE = "param_title";

    private String mLink;
    private String mTitle;
    private WebView mBlogDetailWv;

    public static BlogDetailFragment newInstance(BlogItemBean itemBean) {
        BlogDetailFragment fragment = new BlogDetailFragment();
        fragment.setBlogLink(itemBean);
        return fragment;
    }

    public void setBlogLink(BlogItemBean itemBean){
        Bundle args = new Bundle();
        args.putString(PARAM_TITLE, itemBean.getTitle());
        args.putString(PARAM_LINK, itemBean.getLink());
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
            mTitle = getArguments().getString(PARAM_TITLE);
        }
    }

    @Override
    protected void onViewReady() {
        mListener.onFragmentSetBackVisable(true);
        mListener.onFragmentSetCloseVisable(false);
        mListener.onFragmentSetTitle(mTitle);
        mBlogDetailWv.loadUrl(mLink);
    }

    @Override
    protected BlogDetailPresenter bindPresenter() {
        return new BlogDetailPresenter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mListener.onFragmentSetBackVisable(false);
        mListener.onFragmentSetCloseVisable(false);
        if(mBlogDetailWv == null){ return; }
        ((ViewGroup)mBlogDetailWv.getParent()).removeView(mBlogDetailWv);
        mBlogDetailWv.removeAllViews();
        mBlogDetailWv.destroy();
    }

    @Override
    protected void initView(View view) {
        mBlogDetailWv = findViewById(R.id.blog_detail_wv);
        WebViewUtils.defaultSetting(mBlogDetailWv);
    }
}
