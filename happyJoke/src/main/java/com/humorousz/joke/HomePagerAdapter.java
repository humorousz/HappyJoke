package com.humorousz.joke;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.humorousz.joke.base.BaseFragment;

import java.util.List;

/**
 * Created by zhangzhiquan on 2017/5/30.
 */

public class HomePagerAdapter extends FragmentPagerAdapter {
    private String[] tableTitle = new String[]{"娱乐","国内","笑话"};
    private List<BaseFragment> mFragments;
    public HomePagerAdapter(FragmentManager fm,List<BaseFragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        if(mFragments == null || mFragments.size() <= position)
            return null;
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        if(mFragments == null)
            return 0;
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(mFragments == null || mFragments.size() <= position){
            return null;
        }
        return tableTitle[position];
    }
}
