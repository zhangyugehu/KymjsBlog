package com.thssh.kymjsblog.utils;

import android.content.Context;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/20
 */

public class AppUtils {

    public static int getScreenWidth(Context context){
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context){
        return context.getResources().getDisplayMetrics().heightPixels;
    }

}
