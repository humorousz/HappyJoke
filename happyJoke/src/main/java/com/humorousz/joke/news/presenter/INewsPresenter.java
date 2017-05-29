package com.humorousz.joke.news.presenter;

/**
 * Created by zhangzhiquan on 2017/5/29.
 */

public interface INewsPresenter {
    enum TYPE{
        GUONEI,HUABIAN
    }
    void refresh(TYPE type);
    void request(TYPE type);
}
