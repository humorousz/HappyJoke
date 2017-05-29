package com.humorousz.joke.base.api;

/**
 * Created by zhangzhiquan on 2017/5/27.
 */

public class RequestAPI {
    public String TIANXINGBASE = "https://api.tianapi.com";
    public String KEY = "/?key=5689ede0a2e303e045f8ada57b9239cb&";
    public String GET_JOKE = TIANXINGBASE + "/txapi/joke" + KEY;
    public String GET_GUONEI = TIANXINGBASE + "/guonei" + KEY;
    public String GET_HUABIAN = TIANXINGBASE + "/huabian" + KEY;

    private RequestAPI(){
    }

    private static class Holder{
        static final RequestAPI INSTANCE = new RequestAPI();
    }

    public static RequestAPI getInstance(){
        return Holder.INSTANCE;
    }
}
