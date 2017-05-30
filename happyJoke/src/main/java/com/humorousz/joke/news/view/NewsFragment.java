package com.humorousz.joke.news.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.humorousz.commonutils.log.Logger;
import com.humorousz.joke.R;
import com.humorousz.joke.base.BaseRefreshFragment;
import com.humorousz.joke.base.listener.RecyclerViewStateListener;
import com.humorousz.joke.base.widget.FooterViewHolder;
import com.humorousz.joke.base.widget.LinearItemDecoration;
import com.humorousz.joke.news.adapter.NewsAdapter;
import com.humorousz.joke.news.model.INewsModel;
import com.humorousz.joke.news.presenter.INewsPresenter;
import com.humorousz.joke.news.presenter.NewsPresenter;

/**
 * Created by zhangzhiquan on 2017/5/27.
 */

public class NewsFragment extends BaseRefreshFragment implements INewsView {
    private static final String TAG = "NewsFragment";
    private RecyclerView mRecyclerView;
    private NewsAdapter mAdapter;
    private INewsPresenter mPresenter;
    private RecyclerView.LayoutManager mLayoutManager;
    private INewsPresenter.TYPE mType;
    private String mTitle;


    public static NewsFragment newInstance(INewsPresenter.TYPE type) {

        Bundle args = new Bundle();
        args.putSerializable("type", type);
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        fragment.setTitle(getTitle(type));
        return fragment;
    }

    private static String getTitle(INewsPresenter.TYPE type) {
        switch (type) {
            case GUONEI:
                return "国内";
            case HUABIAN:
                return "娱乐花边";
            default:
                return "新闻";
        }
    }

    public void setTitle(String title) {
        mTitle = title;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = (INewsPresenter.TYPE) getArguments().getSerializable("type");
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onFirstVisible() {
        Logger.d(TAG, mTitle + "onFirstVisible");
        startRefresh();
    }

    @Override
    protected void onFirstInvisible() {
        Logger.d(TAG, mTitle + "onFirstInvisible");
        super.onFirstInvisible();
    }

    @Override
    protected void onVisible() {
        Logger.d(TAG, mTitle + "onVisible:" + init);
        super.onVisible();
    }

    @Override
    protected void onInVisible() {
        Logger.d(TAG, mTitle + "onInVisible");
        super.onInVisible();
    }

    @Override
    protected void refresh() {
        isPullExecute = true;
        mPresenter.refresh(mType);
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_news_base_fragment, container, false);
    }

    @Override
    public void initView(View root) {
        mRecyclerView = (RecyclerView) root.findViewById(R.id.news_recycler_view);
        mPresenter = new NewsPresenter(this);
        mAdapter = new NewsAdapter();
        mLayoutManager = new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new LinearItemDecoration());
        mRecyclerView.addOnScrollListener(mListener);
        mRecyclerView.setLayoutManager(mLayoutManager);
        setPrepared(true);
    }

    RecyclerViewStateListener mListener = new RecyclerViewStateListener() {
        @Override
        public void reachMiddle() {

        }

        @Override
        public void reachBottom() {
            mPresenter.request(mType);
            isPullExecute = false;
        }

        @Override
        public void reachTop() {

        }
    };

    @Override
    public String getLogTitle() {
        return TAG;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public void updateView(INewsModel data) {
        if (isPullExecute) {
            isPullExecute = false;
            mAdapter.resetData(data.getNewsItems());
            stopRefresh();
        } else {
            mAdapter.addData(data.getNewsItems());
        }
        mAdapter.updateFooterViewState(FooterViewHolder.FooterState.FOOTER_STATE_HAS_NEXT_PAGE);
        Logger.e("MRZ", "data size:" + data.getModel().newslist.size());
    }

    @Override
    public void setFailView() {
        stopRefresh();
    }
}
