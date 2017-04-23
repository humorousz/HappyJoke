package com.humorousz.joke.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.humorousz.joke.R;
import com.humorousz.joke.base.BaseRefreshFragment;

/**
 * Created by zhangzhiquan on 2017/4/21.
 */

public class TestFragment extends BaseRefreshFragment {
    private static final String TAG = "TestFragment";
    private TextView mText;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_test_fragment,container,false);
    }

    @Override
    public void initView(View root) {
        mText = (TextView) root.findViewById(R.id.textView);
        mText.postDelayed(new Runnable() {
            @Override
            public void run() {
                startRefresh();
            }
        },1000);
    }

    @Override
    public String getTitle() {
        return TAG;
    }

    @Override
    protected boolean logLife() {
        return true;
    }

    @Override
    protected void refresh() {
        mText.postDelayed(new Runnable() {
            @Override
            public void run() {
                mText.setText("dfdsfdfsd");
                stopRefresh();
            }
        }, 5000);
    }
}
