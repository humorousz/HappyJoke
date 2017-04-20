package com.humorousz.networklib.httpclient.client;

import com.humorousz.commonutils.log.Logger;
import com.humorousz.networklib.httpclient.listener.BaseRequestListener;
import com.humorousz.networklib.httpclient.response.HttpResponse;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhangzhiquan on 2017/4/9.
 *
 * 依赖于okhttp的封装
 */

public  class ZQHttpClient extends HttpClient{
    private OkHttpClient mOkHttpClient;

    private ZQHttpClient() {
        initClient();
    }

    public static HttpClient getInstance(){
        if(mInstance == null){
            synchronized (lock){
                if(mInstance == null){
                    mInstance = new ZQHttpClient();
                }
            }
        }

        return mInstance;
    }

    @Override
    protected void initClient() {
        mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(TIME_OUT_READ, TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT_CONNECT,TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT_WRITE,TimeUnit.SECONDS)
                .build();
    }


    @Override
    public void getAsyn(String url, Map<String, String> params, final BaseRequestListener listener) {
        if(params != null){
            url = getRealGetUrl(url,params);
        }
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(listener != null){
                    listener.onFailure(e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(listener != null){
                    HttpResponse rp = makeResponse(response);
                    listener.onComplete(listener.parse(rp));
                }
                Logger.e("MRZ",response.toString());
            }
        });
    }

    private HttpResponse makeResponse(Response response) throws IOException {
        HttpResponse rp ;
        rp = new HttpResponse.Builder()
                .setCode(response.code())
                .setMessage(response.message())
                .setBody(response.body().string())
                .setError(response.isSuccessful())
                .setRequestUrl(response.request().url().toString())
                .build();
        return rp;
    }



}
