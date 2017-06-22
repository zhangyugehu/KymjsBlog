package com.thssh.kymjsblog.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.thssh.kymjsblog.R;
import com.thssh.kymjsblog.base.AbsFragmentActivity;
import com.thssh.kymjsblog.contract.BlogListFragmentContract;
import com.thssh.kymjsblog.contract.MainActivityContract;
import com.thssh.kymjsblog.presenter.MainPresenter;
import com.thssh.kymjsblog.ui.fragment.BlogDetailFragment;
import com.thssh.kymjsblog.ui.fragment.BlogListFragment;

public class MainActivity extends AbsFragmentActivity<MainPresenter>
        implements MainActivityContract.View, BlogListFragmentContract.OnEventsListener {

    private BlogListFragment mBlogListFragment;
    private BlogDetailFragment mBlogDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBlogListFragment = new BlogListFragment();
        mBlogListFragment.setOnEventListener(this);
        addFragment(mBlogListFragment, BlogListFragment.TAG, false);
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
    public void enterBlogDetail(String link) {
        if(mBlogDetailFragment == null){
            mBlogDetailFragment = BlogDetailFragment.newInstance(link);
        }else{
            mBlogDetailFragment.setBlogLink(link);
        }
        addFragmentToStrack(mBlogDetailFragment, BlogDetailFragment.TAG);
    }
}
