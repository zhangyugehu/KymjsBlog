package com.thssh.library.image;

import android.content.Context;
import android.widget.ImageView;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/21
 */

public class ImageLoader {

    private static ImageLoader instance = new ImageLoader();

    private IImageloader mImageloader;

    private ImageLoader(){
    }

    public void init(IImageloader loader){
        this.mImageloader = loader;
    }

    public static ImageLoader getInstance(){
        return instance;
    }

    public void loadUrl(Context context, String url, ImageView target){
        mImageloader.loadUrl(context, url, target);
    }
}
