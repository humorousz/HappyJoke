package com.humorousz.joke;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.FrameLayout;


import com.humorousz.joke.base.BaseFragment;

import com.humorousz.joke.base.api.RequestAPI;
import com.humorousz.joke.fragment.HomeFragment;
import com.humorousz.joke.joke.view.JokeFragment;
import com.humorousz.joke.news.presenter.INewsPresenter;
import com.humorousz.joke.news.view.NewsFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    FrameLayout mContainer;
    BaseFragment mFramgemt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFramgemt = new HomeFragment();
        mContainer = (FrameLayout) findViewById(R.id.container);
        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        tr.add(R.id.container,mFramgemt);
        tr.commit();


    }
}
