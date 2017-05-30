package com.humorousz.joke.news.presenter;

/**
 * Created by zhangzhiquan on 2017/5/29.
 */

public interface INewsPresenter {
    void refresh(String url);
    void request(String url);
}
