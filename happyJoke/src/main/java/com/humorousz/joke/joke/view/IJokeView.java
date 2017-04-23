package com.humorousz.joke.joke.view;

import com.humorousz.joke.joke.model.IJokeModel;

/**
 * Created by zhangzhiquan on 2017/4/23.
 */

public interface IJokeView {
    void updateView(IJokeModel data);
    void setFailView();}
