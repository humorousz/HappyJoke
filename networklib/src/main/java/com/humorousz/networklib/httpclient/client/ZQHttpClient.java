package com.humorousz.networklib.httpclient.client;

import android.content.Context;

import com.humorousz.commonutils.log.Logger;
import com.humorousz.commonutils.service.CommonService;
import com.humorousz.networklib.httpclient.listener.BaseRequestListener;
import com.humorousz.networklib.httpclient.response.HttpResponse;
import com.humorousz.networklib.networkutils.NetWorkUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
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
        Context context = CommonService.getApplication();
        mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new CacheInterceptor())
                .addNetworkInterceptor(new CacheInterceptor())
                .readTimeout(TIME_OUT_READ, TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT_CONNECT,TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT_WRITE,TimeUnit.SECONDS)
                .cache(new Cache(context.getCacheDir(),10*1024*1024))
                .build();
    }

    @Override
    public void getAsyn(String url, Map<String, String> params, final BaseRequestListener listener, int cacheTime,TimeUnit timeUnit) {
        if(params != null){
            url = getRealGetUrl(url,params);
        }
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if(cacheTime >0){
            CacheControl.Builder cacheBuilder = new CacheControl.Builder();
            cacheBuilder.maxAge(cacheTime,timeUnit);
            builder.cacheControl(cacheBuilder.build());
        }
        final Request request = builder.build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(listener != null){
                    listener.onFailure(e);
                }
            }

            @Override
            public void onResponse(Call call, Response response){
                if(listener != null){
                    try {
                        HttpResponse rp;
                        rp = makeResponse(response);
                        listener.onComplete(listener.parse(rp));
                    } catch (IOException e) {
                        listener.onFailure(e);
                    }

                }
                Logger.e("MRZ",response.toString());
            }
        });
    }

    @Override
    public <T> HttpResponse makeResponse(T response) throws IOException {
        return createResponse((Response)response);
    }



    public HttpResponse createResponse(Response response) throws IOException {
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


    public class CacheInterceptor implements Interceptor {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            boolean connected = NetWorkUtils.isNetWorkConnected();
            if (!connected) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response response = chain.proceed(request);
            if (connected) {
                int maxAge = request.cacheControl().maxAgeSeconds();
                if (maxAge > 0) {
                    response = response.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public ,max-age=" + maxAge)
                            .build();
                }
            }
            return response;
        }
    }



}
