package com.humorousz.networklib.httpclient;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhangzhiquan on 2017/4/9.
 */

public final class ZQHttpClient {

    private static final int TIME_OUT_READ = 30;
    private static final int TIME_OUT_CONNECT = 10;
    private static final int TIME_OUT_WRITE = 30;

    private OkHttpClient mOkHttpClient;

    private ZQHttpClient(){
        mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(TIME_OUT_READ, TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT_CONNECT,TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT_WRITE,TimeUnit.SECONDS)
                .build();

    }

    private static class Holder{
        public static final ZQHttpClient INSTANCE = new ZQHttpClient();
    }



    public synchronized static ZQHttpClient getInstance(){
        return Holder.INSTANCE;
    }

    public void getAsyn(String url,RequestListener listener){
        getAsyn(url,null,listener);
    }

    public void getAsyn(String url, Map<String,String> params, final RequestListener listener){
        if(params != null){
            url = getRealGetUrl(url,params);
        }
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(listener != null){
                    listener.onFailure(call,e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(listener != null){
                    listener.onComplete(call,response);
                }
            }
        });
    }

    private String getRealGetUrl(String url,Map<String,String> params){
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


    public interface RequestListener<T>{
        void onFailure(Call call,IOException e);
        void onComplete(Call call,Response response);
    }


}
