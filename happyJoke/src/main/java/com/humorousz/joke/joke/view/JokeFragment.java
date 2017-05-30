package com.humorousz.joke.joke.view;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.humorousz.commonutils.log.Logger;
import com.humorousz.joke.R;
import com.humorousz.joke.base.BaseRefreshFragment;
import com.humorousz.joke.base.listener.RecyclerViewStateListener;
import com.humorousz.joke.base.widget.FooterViewHolder;
import com.humorousz.joke.base.widget.LinearItemDecoration;
import com.humorousz.joke.joke.adapter.JokeAdapter;
import com.humorousz.joke.joke.model.IJokeModel;
import com.humorousz.joke.joke.model.JokeModel;
import com.humorousz.joke.joke.presenter.IJokePresenter;
import com.humorousz.joke.joke.presenter.JokePresenter;
import com.humorousz.uiutils.helper.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangzhiquan on 2017/4/22.
 */

public class JokeFragment extends BaseRefreshFragment implements IJokeView {
    private static final String TAG = "JokeFragment";

    private RecyclerView mRecyclerView;
    private JokeAdapter mAdapter;
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
        mRecyclerView.addItemDecoration(new LinearItemDecoration());
        mRecyclerView.addOnScrollListener(mListener);
        registerForContextMenu(mRecyclerView);
        setPrepared(true);
    }

    RecyclerViewStateListener mListener = new RecyclerViewStateListener() {
        @Override
        public void reachMiddle() {

        }

        @Override
        public void reachBottom() {
            mPresenter.request();
            isPullExecute = false;
        }

        @Override
        public void reachTop() {

        }
    };

    @Override
    protected void refresh() {
        Logger.e("MRZ","refresh");
        isPullExecute = true;
        mPresenter.refresh();
    }

    @Override
    protected void onFirstVisible() {
        Logger.d(TAG,"onFirstVisible");
        startRefresh();
    }

    @Override
    protected void onVisible() {
        Logger.d(TAG,"onVisible");
        super.onVisible();
    }

    @Override
    protected void onInVisible() {
        Logger.d(TAG,"onInVisible");
        super.onInVisible();
    }

    @Override
    public String getLogTitle() {
        return TAG;
    }

    @Override
    public String getTitle() {
        return "笑话";
    }

    @Override
    public void updateView(final IJokeModel data) {
        if(isPullExecute){
            isPullExecute = false;
            mAdapter.resetData(data.getJokeItems());
            stopRefresh();
        }else {
            mAdapter.addData(data.getJokeItems());
        }
        mAdapter.updateFooterViewState(FooterViewHolder.FooterState.FOOTER_STATE_HAS_NEXT_PAGE);
        Logger.e("MRZ","data size:"+data.getModel().newslist.size());
    }

    @Override
    public void setFailView() {
        stopRefresh();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position;
        String message;
        try{
            position = mAdapter.getPosition();
            message = mAdapter.getJokeString(position);
        }catch (Exception e){
            return super.onContextItemSelected(item);
        }

        switch (item.getItemId()){
            case R.id.copy:
                ClipboardManager cmb = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData data =  ClipData.newPlainText("",message);
                cmb.setPrimaryClip(data);
                ToastUtil.showToast(getContext(),"成功");
                break;
        }
        return super.onContextItemSelected(item);
    }
}
