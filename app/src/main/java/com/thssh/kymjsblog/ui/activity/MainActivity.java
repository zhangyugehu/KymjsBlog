package com.thssh.kymjsblog.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.thssh.kymjsblog.R;
import com.thssh.kymjsblog.base.AbsFragmentActivity;
import com.thssh.kymjsblog.bean.BlogItemBean;
import com.thssh.kymjsblog.contract.EventsFragmentContract;
import com.thssh.kymjsblog.contract.MainActivityContract;
import com.thssh.kymjsblog.presenter.MainPresenter;
import com.thssh.kymjsblog.ui.fragment.BlogDetailFragment;
import com.thssh.kymjsblog.ui.fragment.BlogListFragment;

public class MainActivity extends AbsFragmentActivity<MainPresenter>
        implements MainActivityContract.View,
        View.OnClickListener,
        EventsFragmentContract.OnEventsListener{

    private Toolbar mToolbar;
    private ImageView mBarBackIv;
    private ImageView mBarCloseIv;
    private View mBarLineView;
    private TextView mBarTitle;
    private BlogListFragment mBlogListFragment;
    private BlogDetailFragment mBlogDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolBar();
        initEvents();
        mBlogListFragment = new BlogListFragment();
        mBlogListFragment.setOnEventListener(this);
        addFragment(mBlogListFragment, BlogListFragment.TAG, false);
    }

    private void initEvents() {
        mBarCloseIv.setOnClickListener(this);
        mBarBackIv.setOnClickListener(this);
    }

    private void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mBarBackIv = (ImageView) findViewById(R.id.bar_back_iv);
        mBarCloseIv = (ImageView) findViewById(R.id.bar_finish_iv);
        mBarLineView = findViewById(R.id.bar_line_view);
        mBarTitle = (TextView) findViewById(R.id.bar_title_tv);
    }

    public void setTitle(String text){
        if(mBarTitle != null){ mBarTitle.setText(text); }
    }

    public void setBackVisable(boolean visable){
        if(mBarBackIv != null){
            mBarBackIv.setVisibility(visable?View.VISIBLE:View.GONE);
        }
    }

    public void setCloseVisable(boolean visable){
        if(mBarCloseIv != null){
            mBarCloseIv.setVisibility(visable?View.VISIBLE:View.GONE);
        }
        if(mBarLineView != null){
            mBarLineView.setVisibility(visable?View.VISIBLE:View.GONE);
        }
    }

    @Override
    public void onFragmentSetTitle(String title) {
        setTitle(title);
    }

    @Override
    public void onFragmentSetBackVisable(boolean flag) {
        setBackVisable(flag);
    }

    @Override
    public void onFragmentSetCloseVisable(boolean flag) {
        setCloseVisable(flag);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bar_back_iv:
                onBackPressed();
                break;
            case R.id.bar_finish_iv:
                onBackPressed();
                break;
        }
    }


    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int setFragmentContainer() {
        return R.id.fragment_container;
    }

    public static void actionMain(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    public void onListFragmentEnterBlogDetail(BlogItemBean itemBean) {
        if(mBlogDetailFragment == null){
            mBlogDetailFragment = BlogDetailFragment.newInstance(itemBean);
        }else{
            mBlogDetailFragment.setBlogLink(itemBean);
        }
        mBlogDetailFragment.setOnEventListener(this);
        addFragmentToStrack(mBlogDetailFragment, BlogDetailFragment.TAG);
    }
}
