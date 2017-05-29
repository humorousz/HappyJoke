package com.humorousz.joke.news.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.humorousz.joke.R;
import com.humorousz.joke.news.model.NewsModel;
import com.humorousz.uiutils.helper.ImageLoaderHelper;

/**
 * Created by zhangzhiquan on 2017/5/29.
 */

public class NewsItemView extends RelativeLayout{
    private TextView mTitle;
    private ImageView mPic;
    private TextView mTag;
    private TextView mDate;
    private Context mContext;
    public NewsItemView(Context context) {
        this(context,null);
    }

    public NewsItemView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NewsItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView(){
        View root = LayoutInflater.from(mContext).inflate(R.layout.layout_news_base_item,this);
        mTitle = (TextView) root.findViewById(R.id.text_title);
        mPic = (ImageView) root.findViewById(R.id.image_pic);
        mDate = (TextView) root.findViewById(R.id.text_date);
        mTag = (TextView) root.findViewById(R.id.text_tag);
    }

    public void setData(NewsModel.NewsItem data){
        if(data == null){
            return;
        }
        if(data.title != null){
            mTitle.setText(data.title);
        }
        if(data.description != null){
            mTag.setText(data.description);
        }
        if(data.ctime != null){
            mDate.setText(data.ctime);
        }
        ImageLoaderHelper.displayImage(data.picUrl,mPic);
    }
}
