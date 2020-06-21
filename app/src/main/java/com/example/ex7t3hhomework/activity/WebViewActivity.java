package com.example.ex7t3hhomework.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.ex7t3hhomework.R;

public class WebViewActivity extends AppCompatActivity {
    public static final String EXTRA_URL = "extra_url";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        initViews();
    }
    @SuppressLint("JavascriptInterface")
    private void initViews() {
        String url = getIntent().getStringExtra(EXTRA_URL);

        WebView webView = findViewById(R.id.wv_news);
        webView.loadUrl(url);
//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                //dialog.dismiss();
//            }
//        });
    }

    public static Intent getInstance(Context context, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(EXTRA_URL, url);
        return intent;
    }
}