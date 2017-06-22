package com.thssh.library.image.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.thssh.library.image.Config;
import com.thssh.library.image.IImageloader;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/21
 */

public class GlideImageloader implements IImageloader {

    @Override
    public void loadUrl(Context context, String url, ImageView target, Config config) {
        RequestBuilder<Drawable> transition = Glide.with(context)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade());
        transition.into(target);
    }

    @Override
    public void loadUrl(Context context, String url, ImageView target) {
        Glide.with(context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(target);
    }
}
