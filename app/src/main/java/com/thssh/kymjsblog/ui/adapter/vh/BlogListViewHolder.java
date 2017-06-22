package com.thssh.kymjsblog.ui.adapter.vh;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.thssh.kymjsblog.R;
import com.thssh.kymjsblog.bean.BlogItemBean;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/22
 */

public class BlogListViewHolder extends RecyclerView.ViewHolder{

    private TextView mTitleTv;
    private TextView mDesTv;
    private TextView mDateTv;
    private TextView mTagTv;
    private BlogItemBean mItemBean;

    public BlogItemBean getItemBean() {
        return mItemBean;
    }

    public void renderView(BlogItemBean itemBean) {
        this.mItemBean = itemBean;
        mTitleTv.setText(itemBean.getTitle());
        mDesTv.setText(itemBean.getDescription());
        mTagTv.setText(itemBean.getTag());
        mDateTv.setText(itemBean.getPubDate());
    }

    public BlogListViewHolder(View itemView) {
        super(itemView);
        mTitleTv = (TextView) itemView.findViewById(R.id.title_tv);
        mDesTv = (TextView) itemView.findViewById(R.id.des_tv);
        mDateTv = (TextView) itemView.findViewById(R.id.date_tv);
        mTagTv = (TextView) itemView.findViewById(R.id.tag_tv);
    }
}
