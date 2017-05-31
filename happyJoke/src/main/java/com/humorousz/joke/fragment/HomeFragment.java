package com.humorousz.joke.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.humorousz.joke.HomePagerAdapter;
import com.humorousz.joke.R;
import com.humorousz.joke.base.BaseFragment;
import com.humorousz.joke.base.api.RequestAPI;
import com.humorousz.joke.joke.view.JokeFragment;
import com.humorousz.joke.news.view.NewsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangzhiquan on 2017/5/31.
 */

public class HomeFragment extends BaseFragment{
    private static final String TAG = "HomeFragment";
    private ViewPager mFragmentPager;
    private TabLayout mTablayout;
    private HomePagerAdapter mAdapter;
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_home_fragment,container,false);
    }

    @Override
    public void initView(View root) {
        mFragmentPager = (ViewPager) root.findViewById(R.id.home_viewpager);
        mTablayout = (TabLayout) root.findViewById(R.id.home_tab);
        init();
    }

    private void init(){
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new JokeFragment());
        NewsFragment.Config config = new NewsFragment.Config(RequestAPI.getInstance().GET_GUONEI,"国内");
        NewsFragment.Config config2 = new NewsFragment.Config(RequestAPI.getInstance().GET_HUABIAN,"娱乐花边");
        NewsFragment.Config config3 = new NewsFragment.Config(RequestAPI.getInstance().GET_HEALTH,"健康");
        NewsFragment.Config config4 = new NewsFragment.Config(RequestAPI.getInstance().GET_MEINV,"美女");
        fragments.add(NewsFragment.newInstance(config));
        fragments.add(NewsFragment.newInstance(config2));
        fragments.add(NewsFragment.newInstance(config3));
        fragments.add(NewsFragment.newInstance(config4));
        mAdapter = new HomePagerAdapter(getChildFragmentManager(),fragments);
        mFragmentPager.setAdapter(mAdapter);
        mFragmentPager.setOffscreenPageLimit(4);
        mTablayout.setupWithViewPager(mFragmentPager);
        mTablayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public String getLogTitle() {
        return TAG;
    }

    @Override
    public String getTitle() {
        return "首页";
    }
}
