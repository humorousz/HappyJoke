package com.humorousz.joke.application;

import android.app.Application;

import com.humorousz.commonutils.service.CommonService;
import com.humorousz.uiutils.helper.ImageLoaderHelper;

/**
 * Created by zhangzhiquan on 2017/5/3.
 */

public class JokeApplication extends Application {
    private static final String TAG = "JokeApplication";
    private static  JokeApplication mInstance = null ;

    public static JokeApplication getInstance(){
        return mInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        CommonService.getService().init(this);
        ImageLoaderHelper.init(getApplicationContext());
    }
}
