package com.thssh.kymjsblog.contract;

import com.thssh.kymjsblog.base.BaseEventsListener;
import com.thssh.kymjsblog.base.BaseModel;
import com.thssh.kymjsblog.base.IFragmentView;
import com.thssh.kymjsblog.bean.BlogItemBean;

import java.util.List;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/22
 */

public class BlogListFragmentContract {
    /**
     * 和Activity通信桥梁
     */
    public interface OnEventsListener extends BaseEventsListener{

        void enterBlogDetail(String link);
    }
    public interface View extends IFragmentView{
        void notifyAdapteDataChanged();

        void enterBlog(String link);
    }

    public interface Presenter {
        List<BlogItemBean> requireDatas();

        void loadData();

        void onItemClick(int position);
    }

    public interface Model extends BaseModel{
        void loadData(ModelListener listener);
    }
    public interface ModelListener{
        void onDataResponse(List<BlogItemBean> blogs);
        void onResponseFailure(String error);
    }
}
