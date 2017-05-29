package com.humorousz.joke.base.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.humorousz.joke.R;

public class WebViewActivity extends AppCompatActivity {
    private WebView mWebView;
    private String url;
    private ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWebView = (WebView) findViewById(R.id.web_view);
        mProgressBar = (ProgressBar) findViewById(R.id.mProgressBar);
        url = getIntent().getStringExtra("url");
        init();
    }

    private void init(){
        if(TextUtils.isEmpty(url)){
            return;
        }
        setProgressBar();
        setSize();
        setBackListen();
        loadUrl();
    }

    /**
     * 设置进度条
     */
    private void setProgressBar(){
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress == 100){
                    mProgressBar.setVisibility(View.GONE);
                }else{
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
            }
        });
    }

    /**
     * 设置适应屏幕大小
     */

    private void setSize(){
        WebSettings settings = mWebView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);//settings.setBuiltInZoomControls(true)必须要加
        settings.setDisplayZoomControls(false);
    }

    /**
     * 监听返回键
     */

    private void setBackListen(){
        mWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()){
                    mWebView.goBack();
                    return true;
                }
                return false;
            }
        });
    }

    /**
     *  载入URL 拦截内部新跳转
     */
    private void loadUrl(){
        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new WebViewClient(){


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mWebView.loadUrl(url);
                return true;
            }
        });
    }

}
