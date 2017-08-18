package com.example.bckj.qqq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = ((WebView) findViewById(R.id.webview));
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webView.addJavascriptInterface(new JS(), "android");
        webView.loadUrl("http://118.190.91.24:8080/freewifi/index.html");
    }
    class JS{
        @JavascriptInterface
        public void get(String p){
            System.out.println("打印"+p);
            Intent intent = new Intent();
            intent.putExtra("num",p);
            intent.setClass(MainActivity.this, Main2Activity.class);
            startActivity(intent);
        }
        @JavascriptInterface
        public void set(){
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, Main2Activity.class);
            startActivity(intent);
        }

    }
}
