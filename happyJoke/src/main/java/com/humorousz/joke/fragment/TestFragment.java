package com.humorousz.joke.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.humorousz.commonutils.log.Logger;
import com.humorousz.joke.R;
import com.humorousz.joke.base.BaseRefreshFragment;

/**
 * Created by zhangzhiquan on 2017/4/21.
 */

public class TestFragment extends BaseRefreshFragment {
    private static final String TAG = "TestFragment";
    private TextView mText;
    private SeekBar mSeekBar;

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
        mSeekBar = (SeekBar) root.findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                final int val = seekBar.getProgress();
                mText.setTextSize(dip2px(getContext(),val));
                mText.post(new Runnable() {
                    @Override
                    public void run() {
                        String str = "my text dp is:"+px2dip(getContext(), mText.getTextSize())+"\n"
                                +"my text width is"+px2dip(getContext(),mText.getHeight());
                        Logger.d(TAG,str);
                    }
                });
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public String getLogTitle() {
        return TAG;
    }

    @Override
    public String getTitle() {
        return null;
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

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
