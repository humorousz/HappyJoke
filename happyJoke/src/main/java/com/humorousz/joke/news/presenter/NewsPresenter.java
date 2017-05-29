package com.humorousz.joke.news.presenter;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;

import com.humorousz.commonutils.json.JsonTools;
import com.humorousz.commonutils.log.Logger;
import com.humorousz.joke.base.api.RequestAPI;
import com.humorousz.joke.news.model.INewsModel;
import com.humorousz.joke.news.model.NewsModel;
import com.humorousz.joke.news.view.INewsView;
import com.humorousz.networklib.httpclient.HttpClientProxy;
import com.humorousz.networklib.httpclient.listener.RequestListener;
import com.humorousz.networklib.httpclient.response.HttpResponse;

import java.util.HashMap;

/**
 * Created by zhangzhiquan on 2017/5/29.
 */

public class NewsPresenter implements INewsPresenter {
    private static final String TAG = "NewsPresenter";
    private int page = 1;
    private INewsView mNewsView;
    private boolean isRequest = false;
    private Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            isRequest = false;
           switch (msg.what){
               case 0x01:
                   INewsModel model = (INewsModel) msg.obj;
                   mNewsView.updateView(model);
                   break;
               case 0x02:
                   mNewsView.setFailView();
                   break;
           }
        }
    };

    public NewsPresenter(INewsView view){
        mNewsView = view;
    }
    @Override
    public void refresh(TYPE type) {
        Logger.d(TAG,"refresh");
        page = 1;
        request(type);
    }

    @Override
    public void request(TYPE type) {
        Logger.d(TAG,"request");
        if(isRequest)
            return;
        isRequest = true;
        final HashMap<String,String> params = new HashMap<>();
        params.put("num",String.valueOf(20));
        params.put("page",String.valueOf(page++));
        String url = getRequestURL(type);
        if(TextUtils.isEmpty(url)){
            Logger.e(TAG,"wrong type for request");
            return;
        }
        HttpClientProxy.getClient().getAsyn(url, params, new RequestListener() {
            @Override
            public void onFailure(Exception e) {
                Message message = mHandler.obtainMessage();
                message.what = 0x02;
                message.obj = e.getMessage();
                mHandler.sendMessage(message);
            }

            @Override
            public void onComplete(HttpResponse response) {
                INewsModel model = JsonTools.parse(response.getBody(), NewsModel.class);
                Message message = mHandler.obtainMessage();
                message.what = 0x01;
                message.obj = model;
                mHandler.sendMessage(message);
            }
        },10);
    }

    private String getRequestURL(TYPE type){
        switch (type){
            case GUONEI:
                return RequestAPI.getInstance().GET_GUONEI;
        }

        return null;
    }
}
