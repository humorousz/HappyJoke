package com.humorousz.joke.base.api;

/**
 * Created by zhangzhiquan on 2017/5/27.
 */

public class RequestAPI {
    public String TIANXINGBASE = "https://api.tianapi.com";
    public String KEY = "/?key=5689ede0a2e303e045f8ada57b9239cb&";
    public String GET_JOKE = TIANXINGBASE + "/txapi/joke" + KEY; //笑话
    public String GET_GUONEI = TIANXINGBASE + "/guonei" + KEY; //国内新闻
    public String GET_HUABIAN = TIANXINGBASE + "/huabian" + KEY; //娱乐花边
    public String GET_HEALTH = TIANXINGBASE +"/health" + KEY; //健康
    public String GET_MEINV = TIANXINGBASE +"/meinv" + KEY;//美女

    private RequestAPI(){
    }

    private static class Holder{
        static final RequestAPI INSTANCE = new RequestAPI();
    }

    public static RequestAPI getInstance(){
        return Holder.INSTANCE;
    }
}
