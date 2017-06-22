package com.thssh.kymjsblog.bean;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/21
 */

public class BlogItemBean {
//    {
//        "title": "Service Worker:让网页无网络也能访问",
//        "description": "想要开发一个 PWA(Progressive Web App)应用，你必须知道首先知道，Service Worker 是什么，他是怎么工作的。",
//        "pubDate": "2017-02-18",
//        "link": "https://www.kymjs.com/code/2017/02/18/01",
//        "tag": "技术讲解",
//        "category": "code"
//    }

    private String title;
    private String description;
    private String pubDate;
    private String link;
    private String tag;
    private String category;

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

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
