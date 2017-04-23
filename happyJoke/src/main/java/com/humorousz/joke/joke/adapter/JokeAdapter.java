package com.humorousz.joke.joke.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.humorousz.joke.R;
import com.humorousz.joke.joke.model.JokeModel;

import java.util.List;

/**
 * Created by zhangzhiquan on 2017/4/23.
 */

public class JokeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<JokeModel.JokeItem> mlist;
    public JokeAdapter(List<JokeModel.JokeItem> list){
        this.mlist = list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_joke_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        JokeModel.JokeItem item = mlist.get(position);
        if(holder instanceof ViewHolder){
            ((ViewHolder)holder).bindData(item);
        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,content;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.joke_title);
            content = (TextView) itemView.findViewById(R.id.joke_content);
        }

        public void bindData(JokeModel.JokeItem item){
            title.setText(item.title);
            content.setText(Html.fromHtml(item.content));
        }
    }
}
