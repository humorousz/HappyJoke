package com.humorousz.joke.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.humorousz.commonutils.log.Logger;

/**
 * -- abstract Class --
 * BaseFragment
 * 所有Fragment的基类，所有使用的fragment都需要从此类继承，方便对生命周期等内容的控制
 */


public abstract class BaseFragment extends Fragment {


    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        printLog("onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        printLog("onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        printLog("onCreateView");
        View view = createView(inflater,container,savedInstanceState);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        printLog("onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        printLog("onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        printLog("onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        printLog("onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        printLog("onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        printLog("onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        printLog("onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        printLog("onDetach");
        super.onDetach();
    }

    protected void printLog(String methodName){
        if(logLife()){
            Logger.d(getTitle(),methodName);
        }
    }

    public abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public abstract void initView(View root);


    public abstract String getTitle();

    protected boolean logLife(){
        return false;
    }


}
