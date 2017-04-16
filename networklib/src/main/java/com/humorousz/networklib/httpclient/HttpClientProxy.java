package com.humorousz.networklib.httpclient;

import com.humorousz.networklib.httpclient.client.HttpClient;
import com.humorousz.networklib.httpclient.client.ZQHttpClient;

/**
 * Created by zhangzhiquan on 2017/4/16.
 */

public class HttpClientProxy {

    public static HttpClient getClient(){
        return ZQHttpClient.getInstance();
    }
}
