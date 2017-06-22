package com.thssh.kymjsblog.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thssh.kymjsblog.R;
import com.thssh.kymjsblog.bean.BlogItemBean;
import com.thssh.kymjsblog.ui.adapter.vh.BlogListViewHolder;

import java.util.List;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/22
 */

public class BlogListAdapter extends RecyclerView.Adapter<BlogListViewHolder>{
    private List<BlogItemBean> mDatas;
    private Context mContext;

    public BlogListAdapter(Context mContext, List<BlogItemBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public BlogListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_blog_list, parent, false);
        return new BlogListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BlogListViewHolder holder, int position) {
        if(getItemCount() < 1) { return; }
        if(position < 0 || position + 1 > getItemCount()){ return; }
        BlogItemBean itemBean = mDatas.get(position);
        if(itemBean == null){ return; }
        holder.renderView(itemBean);
    }

    @Override
    public int getItemCount() {
        if(mDatas == null){ return 0; }
        return mDatas.size();
    }
}
