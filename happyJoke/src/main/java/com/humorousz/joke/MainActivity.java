package com.humorousz.joke;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.FrameLayout;


import com.humorousz.joke.base.BaseFragment;

import com.humorousz.joke.joke.view.JokeFragment;
import com.humorousz.joke.news.presenter.INewsPresenter;
import com.humorousz.joke.news.view.NewsFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ViewPager mFragmentPager;
    private TabLayout mTablayout;
    private HomePagerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentPager = (ViewPager) findViewById(R.id.viewpager);
        mTablayout = (TabLayout) findViewById(R.id.tablayout);
        init();
    }


    private void init(){
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(NewsFragment.newInstance(INewsPresenter.TYPE.HUABIAN));
        fragments.add(NewsFragment.newInstance(INewsPresenter.TYPE.GUONEI));
        fragments.add(new JokeFragment());
        mAdapter = new HomePagerAdapter(getSupportFragmentManager(),fragments);
        mFragmentPager.setAdapter(mAdapter);
        mTablayout.setupWithViewPager(mFragmentPager);
        mTablayout.setTabMode(TabLayout.MODE_FIXED);
    }
}
