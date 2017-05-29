package com.humorousz.joke.news.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


import com.humorousz.joke.R;
import com.humorousz.joke.base.widget.FooterViewHolder;
import com.humorousz.joke.news.model.NewsModel;
import com.humorousz.joke.news.widget.NewsItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangzhiquan on 2017/5/29.
 */

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "NewsAdapter";
    public static final int TYPE_NEWS = 1;
    public static final int TYPE_FOOTER = 2;
    private List<NewsModel.NewsItem> mList;
    private FooterViewHolder.FooterState mFooterState;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case TYPE_FOOTER:
                View footer = View.inflate(parent.getContext(), R.layout.layout_recycler_view_footer, null);
                holder = new FooterViewHolder(footer);
                break;
            case TYPE_NEWS:
                NewsItemView newsItemView = new NewsItemView(parent.getContext());
                holder = new ViewHolder(newsItemView);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case TYPE_FOOTER:
                if (holder instanceof FooterViewHolder) {
                    FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
                    footerViewHolder.updataFooterState(mFooterState);
                }
                break;
            case TYPE_NEWS:
                if (holder instanceof ViewHolder) {
                    ViewHolder viewHolder = (ViewHolder) holder;
                    viewHolder.newsItemView.setData(mList.get(position));
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (mList == null || mList.size() == 0)
            return 0;
        return mList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < mList.size())
            return TYPE_NEWS;
        if (getItemCount() == (position + 1)) {
            return TYPE_FOOTER;
        }
        return super.getItemViewType(position);
    }

    public void addData(List<NewsModel.NewsItem> list) {
        if (mList == null) {
            mList = new ArrayList<>();
        }
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void resetData(List<NewsModel.NewsItem> list) {
        if (mList != null && mList.size() > 0) {
            mList.clear();
        }
        addData(list);
    }

    public void updateFooterViewState(FooterViewHolder.FooterState footerState) {
        mFooterState = footerState;
        int itemCount = getItemCount();
        notifyItemChanged(itemCount > 0 ? itemCount - 1 : -1);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        NewsItemView newsItemView;

        public ViewHolder(View itemView) {
            super(itemView);
            newsItemView = (NewsItemView) itemView;
        }
    }
}
