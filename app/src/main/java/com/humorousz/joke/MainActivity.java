package com.humorousz.joke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.humorousz.networklib.httpclient.ZQHttpClient;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    static final String KEY="5689ede0a2e303e045f8ada57b9239cb";
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
                ZQHttpClient.getInstance().getAsyn("https://api.tianapi.com/txapi/joke/?key=" + KEY, new ZQHttpClient.RequestListener<String>() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Toast.makeText(MainActivity.this,"fail",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete(Call call, Response response) {
                        try {
                            final String res = response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(res);
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}
