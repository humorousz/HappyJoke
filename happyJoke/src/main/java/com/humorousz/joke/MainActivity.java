package com.humorousz.joke;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.FrameLayout;


import com.humorousz.joke.base.BaseFragment;

import com.humorousz.joke.joke.view.JokeFragment;


public class MainActivity extends AppCompatActivity {
    FrameLayout mContainer;
    BaseFragment mFramgemt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFramgemt = new JokeFragment();
        mContainer = (FrameLayout) findViewById(R.id.container);
        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        tr.add(R.id.container,mFramgemt);
        tr.commit();


    }
}
