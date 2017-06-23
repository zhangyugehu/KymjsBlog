package com.thssh.kymjsblog.contract;

import com.thssh.kymjsblog.base.BaseModel;
import com.thssh.kymjsblog.base.IFragmentView;
import com.thssh.kymjsblog.bean.BlogBean;
import com.thssh.kymjsblog.bean.BlogItemBean;

import java.util.List;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/22
 */

public class BlogListFragmentContract {
    public interface View extends IFragmentView{
        void notifyAdapteDataChanged();

        void enterBlog(BlogItemBean link);

        void setBarTitle(String title);
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
        void onDataResponse(BlogBean blogs);
        void onResponseFailure(String error);
    }
}
