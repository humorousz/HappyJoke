package com.humorousz.joke.joke.view;


import android.app.UiAutomation;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewUtils;
import android.util.TypedValue;
import android.view.View;

import com.humorousz.joke.application.JokeApplication;
import com.humorousz.joke.base.BaseItemDecoration;

/**
 * Created by zhangzhiquan on 2017/5/3.
 */

public class JokeItemDecoration extends BaseItemDecoration{

    private static final String TAG = "JokeItemDecoration";

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildLayoutPosition(view);
        int count = parent.getChildCount();
        int type = parent.getAdapter().getItemViewType(position);
        if(position != count-1){
            outRect.set(0,0,0,getItemDecorationInsetValue(position,type));
        }
    }

    @Override
    protected int getItemDecorationInsetValue(int position, int type) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,2, JokeApplication.getInstance().getResources().getDisplayMetrics());
    }

    @Override
    protected Drawable getItemDecorationDrawable(int position, int type) {
        return new ColorDrawable(Color.BLACK);
    }
}
