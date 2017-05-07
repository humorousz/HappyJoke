package com.humorousz.joke.joke.view;

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
import com.humorousz.joke.joke.adapter.JokeAdapter;
import com.humorousz.joke.joke.model.IJokeModel;
import com.humorousz.joke.joke.model.JokeModel;
import com.humorousz.joke.joke.presenter.IJokePresenter;
import com.humorousz.joke.joke.presenter.JokePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangzhiquan on 2017/4/22.
 */

public class JokeFragment extends BaseRefreshFragment implements IJokeView {
    private static final String TAG = "JokeFragment";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private  List<JokeModel.JokeItem> mlist;
    private IJokePresenter mPresenter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.joke_fragment,container,false);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void initView(View root) {
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
        mPresenter = new JokePresenter(getContext(),this);
        mlist = new ArrayList<>();
        mAdapter = new JokeAdapter(mlist);
        mLayoutManager = new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new JokeItemDecoration());
    }

    @Override
    protected void refresh() {
        Logger.e("MRZ","refresh");
        mPresenter.refresh();
    }


    @Override
    public String getTitle() {
        return TAG;
    }

    @Override
    public void updateView(final IJokeModel data) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mlist.clear();
                mlist.addAll(data.getJokeItems());
                mAdapter.notifyDataSetChanged();
                stopRefresh();
                Logger.e("MRZ","data size:"+data.getModel().newslist.size());
            }
        });
    }

    @Override
    public void setFailView() {
        stopRefresh();
    }
}
