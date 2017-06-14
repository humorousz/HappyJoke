package com.humorousz.joke;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.humorousz.joke.base.BaseFragment;
import com.humorousz.joke.fragment.HomeFragment;



public class MainActivity extends FragmentActivity {

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
