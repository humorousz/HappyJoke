package com.humorousz.joke.base.widget;


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
import com.humorousz.uiutils.helper.UIUtils;

/**
 * Created by zhangzhiquan on 2017/5/3.
 */

public class LinearItemDecoration extends BaseItemDecoration{

    private static final String TAG = "LinearItemDecoration";

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildLayoutPosition(view);
        int type = parent.getAdapter().getItemViewType(position);
        outRect.set(0,0,0,getItemDecorationInsetValue(position,type));
    }

    @Override
    protected int getItemDecorationInsetValue(int position, int type) {
        return UIUtils.dip2px(3);
    }

    @Override
    protected Drawable getItemDecorationDrawable(int position, int type) {
        return new ColorDrawable(Color.parseColor("#f1f1f1"));
    }
}
