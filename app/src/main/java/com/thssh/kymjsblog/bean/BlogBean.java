package com.thssh.kymjsblog.bean;

import java.util.List;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/21
 */

public class BlogBean {
//    {
//        "title": "开源实验室",
//            "description": "kymjs张涛的开源实验室",
//            "link": "https://www.kymjs.com/",
//            "pubDate": "2017-02-19",
//            "lastBuildDate": "2017-02-19",
//            "generator": "Jekyll v3.1.2",
//            "item": [
//            {
//                "title": "Service Worker:让网页无网络也能访问",
//                "description": "想要开发一个 PWA(Progressive Web App)应用，你必须知道首先知道，Service Worker 是什么，他是怎么工作的。",
//                "pubDate": "2017-02-18",
//                "link": "https://www.kymjs.com/code/2017/02/18/01",
//                "tag": "技术讲解",
//                "category": "code"
//            }
//        ]
//    }

    private String title;
    private String description;
    private String link;
    private String pubDate;
    private String lastBuildDate;
    private String generator;
    private List<BlogItemBean> item;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public List<BlogItemBean> getItem() {
        return item;
    }

    public void setItem(List<BlogItemBean> item) {
        this.item = item;
    }
}
