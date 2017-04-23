package com.humorousz.joke;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.humorousz.joke.base.BaseFragment;
import com.humorousz.joke.fragment.TestFragment;
import com.humorousz.joke.joke.view.JokeFragment;
import com.humorousz.networklib.httpclient.HttpClientProxy;
import com.humorousz.networklib.httpclient.listener.RequestListener;
import com.humorousz.networklib.httpclient.listener.StringBaseRequestListener;
import com.humorousz.networklib.httpclient.response.HttpResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
