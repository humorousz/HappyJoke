package com.humorousz.networklib.httpclient.client;


import com.humorousz.networklib.httpclient.listener.BaseRequestListener;
import com.humorousz.networklib.httpclient.response.HttpResponse;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangzhiquan on 2017/4/16.
 *
 * 1.HttpClient的抽象类,目前唯一的实现类是ZQHttpClient,这样做的目的是当我们如果换用其他第三方http库的时候,可以直接继承
 * 这个类并且实现抽象的方法,对目前项目中的各种调用不会造成影响。
 * 2.为什么要使用抽象类而不是接口？接口只能定义抽象的方法，不可以实现，例如{@link #getAsyn(String , BaseRequestListener)}
 * 类似于这种方法没有必要在每个子类中都实现，可以在此类中完成，如果定义成接口无法实现这个效果
 */

public abstract class HttpClient {
    protected static final int TIME_OUT_READ = 30;
    protected static final int TIME_OUT_CONNECT = 10;
    protected static final int TIME_OUT_WRITE = 30;

    protected static final Object lock = new Object();
    protected static HttpClient mInstance;

    /**
     * 初始化
     */
    protected abstract void initClient();

    /**
     * 异步get请求
     * @param url
     * @param listener
     */
    public void getAsyn(String url, BaseRequestListener listener){
        getAsyn(url,null,listener);
    }

    /**
     * 异步get请求，可以直接通过params拼接参数到末尾(eg:url="http://abc.com/index",params={{name="jack"}
     * ,{age=18}},转换成:http://abc.com/index?name=jack&age=18)
     * @param url
     * @param params
     * @param listener
     */
    public  void getAsyn(String url,Map<String,String> params,BaseRequestListener listener){
        getAsyn(url,params,listener,0);
    }

    /**
     * 功能同上，可以设置使用最近多久以内的缓存
     * @param url
     * @param params
     * @param listener
     * @param cacheTime
     */


    public  void getAsyn(String url,Map<String,String> params,BaseRequestListener listener,int cacheTime){
        getAsyn(url,params,listener,cacheTime,TimeUnit.SECONDS);
    }

    /**
     * 功能同上，可以设置缓存时间的TimeUnit
     * @param url
     * @param params
     * @param listener
     * @param cacheTime
     * @param timeUnit
     */
    public abstract void getAsyn(String url, Map<String,String> params, BaseRequestListener listener, int cacheTime, TimeUnit timeUnit);


    /**
     * 创建返回给调用者的Response
     * @param response
     * @param <T>
     * @return
     * @throws IOException
     */
    public abstract <T> HttpResponse makeResponse(T response) throws IOException;
    /**
     * 拼接get的url
     * @param url
     * @param params
     * @return
     */
    protected final String getRealGetUrl(String url,Map<String,String> params){
        StringBuilder builder = new StringBuilder(url);
        builder.append("?");
        Iterator<Map.Entry<String,String>> it = params.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String,String> entry = it.next();
            builder.append(entry.getKey()+"="+entry.getValue());
            if(it.hasNext()){
                builder.append("&");
            }
        }
        return builder.toString();
    }

}
