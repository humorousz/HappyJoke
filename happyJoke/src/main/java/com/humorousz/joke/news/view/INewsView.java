package com.humorousz.joke.news.view;

import com.humorousz.joke.news.model.INewsModel;

/**
 * Created by zhangzhiquan on 2017/5/29.
 */

public interface INewsView {
    void updateView(INewsModel data);

    void setFailView();
}

