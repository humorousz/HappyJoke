package com.humorousz.joke.joke.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.humorousz.commonutils.json.JsonTools;
import com.humorousz.commonutils.log.Logger;
import com.humorousz.commonutils.service.CommonService;
import com.humorousz.joke.joke.model.IJokeModel;
import com.humorousz.joke.joke.model.JokeModel;
import com.humorousz.joke.joke.view.IJokeView;
import com.humorousz.networklib.httpclient.HttpClientProxy;
import com.humorousz.networklib.httpclient.listener.RequestListener;
import com.humorousz.networklib.httpclient.response.HttpResponse;
import com.humorousz.networklib.networkutils.NetWorkUtils;

import java.util.HashMap;

/**
 * Created by zhangzhiquan on 2017/4/23.
 */

public class JokePresenter implements IJokePresenter{
    private static final String TAG = "JokePresenter";
    static final String KEY = "5689ede0a2e303e045f8ada57b9239cb";
    private int page = 1;
    private static int SUCCESS = 1;
    private static int FAIL = 0;
    private Context mContext;
    private IJokeView mJokeView;
    private Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0x01){
                IJokeModel model = (IJokeModel) msg.obj;
                mJokeView.updateView(model);
            }else {
                Logger.e(TAG,"error msg:"+msg.obj);
                mJokeView.setFailView();
            }
        }
    };
    public JokePresenter(Context context, IJokeView jokeView){
        this.mContext = context;
        this.mJokeView = jokeView;
    }

    @Override
    public void onResume() {
    }

    @Override
    public void refresh() {
        page = 1;
        request();
    }

    @Override
    public void request() {
        final HashMap<String,String> params = new HashMap<>();
        params.put("key",KEY);
        params.put("num",String.valueOf(20));
        params.put("page",String.valueOf(page++));
        HttpClientProxy.getClient().getAsyn("https://api.tianapi.com/txapi/joke/", params, new RequestListener() {
            @Override
            public void onFailure(Exception e) {
                Message message = mHandler.obtainMessage();
                message.what = 0x02;
                message.obj = e.getMessage();
                mHandler.sendMessage(message);
            }

            @Override
            public void onComplete(HttpResponse response) {
                IJokeModel model = JsonTools.parse(response.getBody(), JokeModel.class);
                Logger.e(TAG, response.getBody());
                Message message = mHandler.obtainMessage();
                message.what = 0x01;
                message.obj = model;
                mHandler.sendMessage(message);
            }
        },10);
    }
}
