package com.thssh.library.image;

import android.content.Context;
import android.widget.ImageView;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/21
 */

public interface IImageloader {
    void loadUrl(Context context, String url, ImageView target, Config config);
    void loadUrl(Context context, String url, ImageView target);
}
