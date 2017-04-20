package com.humorousz.networklib.httpclient.listener;

import com.humorousz.networklib.httpclient.response.HttpResponse;

/**
 * Created by zhangzhiquan on 2017/4/20.
 */

public abstract class RequestListener extends BaseRequestListener<HttpResponse>{

    @Override
    public HttpResponse parse(HttpResponse httpResponse) {
        return httpResponse;
    }
}
