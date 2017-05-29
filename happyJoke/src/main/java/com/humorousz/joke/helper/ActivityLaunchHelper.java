package com.humorousz.joke.helper;

import android.content.Context;
import android.content.Intent;

import com.humorousz.joke.base.webview.WebViewActivity;

/**
 * Created by zhangzhiquan on 2017/5/29.
 */

public class ActivityLaunchHelper {

    public static void launchWebView(Context context,String url){
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }
}
