package com.humorousz.joke.news.model;

import java.util.List;

/**
 * Created by zhangzhiquan on 2017/5/29.
 */

public class NewsModel  implements INewsModel{
    private static final String TAG = "NewsModel";
    public List<NewsItem> newslist;

    @Override
    public NewsModel getModel() {
        return this;
    }

    @Override
    public List<NewsItem> getNewsItems() {
        return newslist;
    }

    public static class NewsItem{
        public String ctime;
        public String title;
        public String description;
        public String picUrl;
        public String url;
    }
}
