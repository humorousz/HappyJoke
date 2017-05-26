package com.humorousz.joke.joke.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.humorousz.joke.R;
import com.humorousz.joke.base.widget.FooterViewHolder;
import com.humorousz.joke.joke.model.JokeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangzhiquan on 2017/4/23.
 */

public class JokeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_JOKE = 1;
    public static final int TYPE_FOOTER = 2;
    private List<JokeModel.JokeItem> mList;
    private FooterViewHolder.FooterState mFooterState;

    public JokeAdapter(List<JokeModel.JokeItem> list) {
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case TYPE_FOOTER:
                View footer = View.inflate(parent.getContext(), R.layout.layout_recycler_view_footer, null);
                holder = new FooterViewHolder(footer);
                break;
            case TYPE_JOKE:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_joke_item, parent, false);
                holder = new ViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_JOKE:
                if (holder instanceof ViewHolder) {
                    JokeModel.JokeItem item = mList.get(position);
                    ((ViewHolder) holder).bindData(item);
                }
                break;
            case TYPE_FOOTER:
                if (holder instanceof FooterViewHolder) {
                    FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
                    footerViewHolder.updataFooterState(mFooterState);
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
            return TYPE_JOKE;
        if (getItemCount() == (position + 1)) {
            return TYPE_FOOTER;
        }
        return super.getItemViewType(position);
    }

    public void addData(List<JokeModel.JokeItem> list) {
        if (mList == null) {
            mList = new ArrayList<>();
        }
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void resetData(List<JokeModel.JokeItem> list) {
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
        TextView title, content;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.joke_title);
            content = (TextView) itemView.findViewById(R.id.joke_content);
        }

        public void bindData(JokeModel.JokeItem item) {
            title.setText(item.title);
            content.setText(Html.fromHtml(item.content));
        }
    }
}
