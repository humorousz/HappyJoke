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
import com.humorousz.networklib.httpclient.HttpClientProxy;
import com.humorousz.networklib.httpclient.listener.RequestListener;
import com.humorousz.networklib.httpclient.listener.StringBaseRequestListener;
import com.humorousz.networklib.httpclient.response.HttpResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    static final String KEY = "5689ede0a2e303e045f8ada57b9239cb";
    Button button;
    TextView textView;
    FrameLayout mContainer;
    BaseFragment mFramgemt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.commit_btn);
        textView = (TextView) findViewById(R.id.res_textview);
        button.setOnClickListener(listener);
        mFramgemt = new TestFragment();
        mContainer = (FrameLayout) findViewById(R.id.container);
        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        tr.add(R.id.container,mFramgemt);
        tr.commit();


    }

    private String parseJson(JSONObject object) throws JSONException {
        StringBuilder sb = new StringBuilder();
        JSONArray list = object.optJSONArray("newslist");
        for(int i=0;i<list.length();i++){
            JSONObject item = list.getJSONObject(i);
            String title = item.optString("title");
            String id = item.optString("id");
            String content = item.optString("content");
            sb.append("\ntitle:"+title);
            sb.append("\nid:"+id);
            sb.append("\ncontent:"+content);
        }
        return sb.toString();
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            HttpClientProxy.getClient().getAsyn("https://api.tianapi.com/txapi/joke/?key=" + KEY, new RequestListener() {
                @Override
                public void onFailure(Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
                        }
                    });
                    Log.e("MRZ","error:"+e.getMessage());
                }

                @Override
                public void onComplete(HttpResponse response) {
                    final StringBuilder res = new StringBuilder();
                    res.append("Code:"+response.getCode());
                    res.append("\n"+"message:"+response.getMessage());
                    try {
                        res.append(parseJson(new JSONObject(response.getBody())));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(res.toString());
                        }
                    });
                }
            });
        }
    };
}
