package com.thssh.kymjsblog.bean;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/20
 */

public class SplashImageBean {
    /*
    api	http://openapi.kymjs.com/splash
    请求方式	GET
    ---------------------------------------------------------------------
    返回信息：
    [
        {
            "action_type": 1,
            "action_url": "www.kymjs.com",
            "start_time": "2017-01-28 00:00:00",
            "description": "开源实验室开放API",
            "image_url": "http://cn.bing.com/az/hprichbg/rb/DoorGods_ZH-CN12360444323_1366x768.jpg",
            "order_index": 1234,
            "scheme": ""
        }
    ]
    ---------------------------------------------------------------------
    action_type	点击闪屏后的事件类型(0:无操作，1:跳转网页，2:跳转scheme)
    action_url	跳转网页的链接
    start_time	闪屏图片起始时间
    description	闪屏图片描述
    image_url	图片地址
    scheme	scheme链接
    order_index	图片预加载优先级(越大优先级越高)
    */


    @SerializedName("action_type")
    private int actioType;
    @SerializedName("action_url")
    private String actionUrl;
    @SerializedName("start_time")
    private String startTime;
    @SerializedName("description")
    private String description;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("order_index")
    private int orderIndex;
    @SerializedName("scheme")
    private String scheme;

    public int getActioType() {
        return actioType;
    }

    public void setActioType(int actioType) {
        this.actioType = actioType;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    @Override
    public String toString() {
        return "SplashImageBean{" +
                "actioType=" + actioType +
                ", actionUrl='" + actionUrl + '\'' +
                ", startTime='" + startTime + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", orderIndex='" + orderIndex + '\'' +
                ", scheme='" + scheme + '\'' +
                '}';
    }
}
