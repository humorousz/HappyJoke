package com.humorousz.joke.joke.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.humorousz.joke.joke.model.IJokeModel;
import com.humorousz.joke.joke.model.JokeModel;
import com.humorousz.joke.joke.view.IJokeView;
import com.humorousz.networklib.httpclient.HttpClientProxy;
import com.humorousz.networklib.httpclient.listener.RequestListener;
import com.humorousz.networklib.httpclient.response.HttpResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by zhangzhiquan on 2017/4/23.
 */

public class JokePresenter implements IJokePresenter{
    static final String KEY = "5689ede0a2e303e045f8ada57b9239cb";
    private static int SUCCESS = 1;
    private static int FAIL = 0;
    private Context mContext;
    private IJokeView mJokeView;
    public JokePresenter(Context context, IJokeView jokeView){
        this.mContext = context;
        this.mJokeView = jokeView;
    }

    @Override
    public void refresh() {
        request();
    }

    @Override
    public void request() {
        HashMap<String,String> params = new HashMap<>();
        params.put("key",KEY);
        params.put("num",String.valueOf(20));
        HttpClientProxy.getClient().getAsyn("https://api.tianapi.com/txapi/joke/", params, new RequestListener() {
            @Override
            public void onFailure(Exception e) {
                mJokeView.setFailView();
            }

            @Override
            public void onComplete(HttpResponse response) {
                try {
                    IJokeModel model = new JokeModel(new JSONObject(response.getBody()));
                    mJokeView.updateView(model);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mJokeView.setFailView();
                }
            }
        });
    }
}