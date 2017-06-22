package com.thssh.kymjsblog.contract;

import com.thssh.kymjsblog.base.IActivityView;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/21
 */

public class SplashActivityContract {

    public interface View extends IActivityView {


        void setDescriptionText(String description);

        void setSplashImage(String url);

        void startMainActivity();

        void setTimerText(String text);
    }

    public interface Presenter{

        void skipTimer();
    }
}
