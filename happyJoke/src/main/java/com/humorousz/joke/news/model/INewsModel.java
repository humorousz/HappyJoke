package com.humorousz.joke.news.model;

import java.util.List;

/**
 * Created by zhangzhiquan on 2017/5/29.
 */

public interface INewsModel {
    NewsModel getModel();
    List<NewsModel.NewsItem> getNewsItems();

}
