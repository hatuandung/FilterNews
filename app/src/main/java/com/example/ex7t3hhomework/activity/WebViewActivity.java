package com.example.ex7t3hhomework.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.ex7t3hhomework.R;

public class WebViewActivity extends AppCompatActivity {
    public static final String EXTRA_URL = "extra_url";

    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        initViews();
    }
    @SuppressLint("JavascriptInterface")
    private void initViews() {
        String url = getIntent().getStringExtra(EXTRA_URL);

        dialog = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);
        dialog.setContentView(R.layout.dialog_progress_loading);
        dialog.setCancelable(false);
        dialog.show();
        WebView webView = findViewById(R.id.wv_news);

//        WebSettings webSetting = webView.getSettings();
//        webSetting.setJavaScriptEnabled(true);
//        webSetting.setDomStorageEnabled(true);

        //webView.loadUrl(url);
        webView.loadUrl( url);
        Log.e( "initViews: ", url );
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dialog.dismiss();
            }
        });
    }

    public static Intent getInstance(Context context, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(EXTRA_URL, url);
        return intent;
    }
}