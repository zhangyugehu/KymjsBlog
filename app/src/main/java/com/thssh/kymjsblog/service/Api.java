package com.thssh.kymjsblog.service;

import com.thssh.kymjsblog.bean.BlogBean;
import com.thssh.kymjsblog.bean.SplashImageBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/20
 */

public interface Api {

    String BASE_URL = "http://openapi.kymjs.com/";

    /**
     * 首屏图片获取
     * @return
     */
    @GET("splash") Observable<List<SplashImageBean>> getSplashIamge();

    @GET("oslab") Observable<BlogBean> getBlogList();


}
