package com.humorousz.joke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.humorousz.networklib.httpclient.HttpClientProxy;
import com.humorousz.networklib.httpclient.listener.StringRequestListener;

public class MainActivity extends AppCompatActivity {
    static final String KEY = "5689ede0a2e303e045f8ada57b9239cb";
    Button button;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.commit_btn);
        textView = (TextView) findViewById(R.id.res_textview);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpClientProxy.getClient().getAsyn("https://api.tianapi.com/txapi/joke/?key=" + KEY, new StringRequestListener() {
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
                    public void onComplete(String response) {
                        final String res = response;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(res);
                            }
                        });
                    }
                });
            }
        });
    }
}
