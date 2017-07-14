package com.thssh.kymjsblog.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.thssh.kymjsblog.R;
import com.thssh.kymjsblog.bean.BlogItemBean;
import com.thssh.kymjsblog.contract.BlogListFragmentContract;
import com.thssh.kymjsblog.contract.EventsFragmentContract;
import com.thssh.kymjsblog.presenter.BlogListPresenter;
import com.thssh.kymjsblog.ui.adapter.BlogListAdapter;
import com.thssh.kymjsblog.ui.listener.OnItemClickListener;
import com.thssh.umengshare.UmengShare;
import com.umeng.socialize.UMShareAPI;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/21
 */

public class BlogListFragment extends EventsFragment<BlogListPresenter>
        implements BlogListFragmentContract.View{

    public static final String TAG = "BlogListFragment";

    private RecyclerView mBlogListRv;
    private BlogListAdapter mAdapter;

    @Override
    protected int requireLayoutResource() {
        return R.layout.fragment_blog_list;
    }

    @Override
    protected void initView(View view) {
        Log.d(TAG, "initView: ");
        mBlogListRv = findViewById(R.id.blog_list_rv);
        initRecyclerView();
    }

    @Override
    protected void initEvents() {
        mAdapter = new BlogListAdapter(mContext, mPresenter.requireDatas());
        mBlogListRv.setAdapter(mAdapter);
    }

    @Override
    protected void onViewReady() {
        mPresenter.loadData();
        mListener.onFragmentSetBackVisable(false);
        mListener.onFragmentSetCloseVisable(false);

    }

    @Override
    protected BlogListPresenter bindPresenter() {
        return new BlogListPresenter();
    }

    private void initRecyclerView() {
        mBlogListRv.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mBlogListRv.setLayoutManager(new LinearLayoutManager(mContext));
        mBlogListRv.setItemAnimator(new DefaultItemAnimator());
        mBlogListRv.addOnItemTouchListener(new OnItemClickListener(){

            @Override
            public void onItemClick(RecyclerView view, int position) {
                mPresenter.onItemClick(position);

//                mListener.openUmengShare();
            }
        });
    }

    @Override
    public void notifyAdapteDataChanged() {
        if(mAdapter == null) { return; }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void enterBlog(BlogItemBean link) {
        mListener.onListFragmentEnterBlogDetail(link);
    }

    @Override
    public void setBarTitle(String title) {
        mListener.onFragmentSetTitle(title);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("BlogListFragment", "onDestroy: ");
    }
}
