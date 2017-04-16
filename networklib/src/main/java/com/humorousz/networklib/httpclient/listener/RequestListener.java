package com.humorousz.networklib.httpclient.listener;

import com.humorousz.networklib.httpclient.response.HttpResponse;



/**
 * Created by zhangzhiquan on 2017/4/16.
 */

public abstract class RequestListener<R> {

    public abstract void onFailure(Exception e);

    public abstract void onComplete(R response);

    abstract public R parse(HttpResponse httpResponse);
}
