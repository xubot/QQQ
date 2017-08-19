package com.example.bckj.qqq;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = ((WebView) findViewById(R.id.webview));
        img = (ImageView) findViewById(R.id.img);
        img.setVisibility(ViewGroup.VISIBLE);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webView.addJavascriptInterface(new JS(), "android");
        //启用数据库
        webSettings.setDatabaseEnabled(true);
        //设置定位的数据库路径
        String dir = this.getApplicationContext().getDir("database", Context.MODE_APPEND).getPath();
        webSettings.setGeolocationDatabasePath(dir);
        //启用地理定位
        webSettings.setGeolocationEnabled(true);
        //开启DomStorage缓存
        webSettings.setDomStorageEnabled(true);
        //配置权限
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin,GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, true);
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }
        });
        webView.loadUrl("http://118.190.91.24:8080/freewifi/app/index.html");
    }

    class JS{
        @JavascriptInterface
        //js那边的方法名
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

        @JavascriptInterface
        //js那边的方法名
        public void hide(){
            System.out.println("打印:"+"走了雷锋精神浪费");
            img.setVisibility(ViewGroup.GONE);
        }
        /*@JavascriptInterface
        //js那边的方法名
        public void show(){
            System.out.println("打印:"+"走了vsdsf雷锋精神浪费");
            img.setVisibility(ViewGroup.VISIBLE);
        }*/
    }

}
