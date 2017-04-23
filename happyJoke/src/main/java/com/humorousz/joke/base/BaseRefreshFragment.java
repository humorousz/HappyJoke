package com.humorousz.joke.base;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.humorousz.joke.R;

/**
 * Created by zhangzhiquan on 2017/4/21.
 */

public abstract class BaseRefreshFragment extends BaseFragment {

    private FrameLayout mContentView;
    private SwipeRefreshLayout mRefreshlayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_refresh_fragment,container,false);
        mContentView = (FrameLayout) view.findViewById(R.id.content);
        mRefreshlayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        initRefreshLayout();
        addChildView(inflater,savedInstanceState);
        return view;
    }

    /**
     * 初始化刷新组件
     */
    private void initRefreshLayout(){
        mRefreshlayout.setOnRefreshListener(new RefreshListener());
        mRefreshlayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        mRefreshlayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        mRefreshlayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
    }

    /**
     * 添加contentView 内容
     */

    private void addChildView(LayoutInflater inflater,Bundle savedInstanceState){
        View child = createView(inflater,mContentView,savedInstanceState);
        initView(child);
        mContentView.addView(child);
    }

    protected void startRefresh(){
        mRefreshlayout.setRefreshing(true);
        refresh();
    }

    protected void stopRefresh(){
        mRefreshlayout.setRefreshing(false);
    }

    protected abstract void refresh();


    private  class RefreshListener implements SwipeRefreshLayout.OnRefreshListener{
        @Override
        public void onRefresh() {
            refresh();
        }
    }

}
