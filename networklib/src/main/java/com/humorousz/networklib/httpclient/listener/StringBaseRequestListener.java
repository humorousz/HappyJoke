package com.humorousz.networklib.httpclient.listener;

import com.humorousz.networklib.httpclient.response.HttpResponse;

/**
 * Created by zhangzhiquan on 2017/4/16.
 */

public abstract class StringBaseRequestListener extends BaseRequestListener<String> {

    @Override
    public  String parse(HttpResponse response){
        String res = response.getBody();
        return res;
    }
}
