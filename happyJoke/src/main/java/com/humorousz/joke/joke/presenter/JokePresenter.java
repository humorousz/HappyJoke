package com.humorousz.joke.joke.presenter;

import android.content.Context;

import com.humorousz.commonutils.json.JsonTools;
import com.humorousz.commonutils.log.Logger;
import com.humorousz.joke.joke.model.IJokeModel;
import com.humorousz.joke.joke.model.JokeModel;
import com.humorousz.joke.joke.view.IJokeView;
import com.humorousz.networklib.httpclient.HttpClientProxy;
import com.humorousz.networklib.httpclient.listener.RequestListener;
import com.humorousz.networklib.httpclient.response.HttpResponse;

import java.util.HashMap;

/**
 * Created by zhangzhiquan on 2017/4/23.
 */

public class JokePresenter implements IJokePresenter{
    private static final String TAG = "JokePresenter";
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
                IJokeModel model = JsonTools.parse(response.getBody(), JokeModel.class);
                Logger.e(TAG, response.getBody());
                mJokeView.updateView(model);
            }
        });
    }
}
