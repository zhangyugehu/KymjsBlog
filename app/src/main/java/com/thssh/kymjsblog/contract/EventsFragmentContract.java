package com.thssh.kymjsblog.contract;

import com.thssh.kymjsblog.base.BaseEventsListener;
import com.thssh.kymjsblog.bean.BlogItemBean;

/**
 *
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/23
 */

public class EventsFragmentContract {

    public interface OnEventsListener extends BaseEventsListener {

        /**
         * @link EventsFragmentContract
         * 设置MainActivity toolbar主题
         * @param title
         */
        void onFragmentSetTitle(String title);

        /**
         * EventsFragmentContract
         * 设置MainActivity 后退按钮是否可用
         * @param flag
         */
        void onFragmentSetBackVisable(boolean flag);

        /**
         * EventsFragmentContract
         * 设置MainActivity 关闭按钮是否可用
         * @param flag
         */
        void onFragmentSetCloseVisable(boolean flag);

        void onListFragmentEnterBlogDetail(BlogItemBean itemBean);
    }

}
