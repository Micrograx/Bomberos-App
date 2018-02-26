package com.breakapp.bomberosapp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import static android.webkit.CookieManager.*;
import static android.webkit.CookieSyncManager.*;


public class MainActivity extends Activity {

    private WebView mWebView;
    String web_page = "http://breakappgames.com/bomberos/alertas";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(com.breakapp.bomberosapp.R.id.activity_main_webview);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        CookieManager.getInstance().setAcceptCookie(true);



        // Use remote resource
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String web_page) {
                super.onPageFinished(mWebView, web_page);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        CookieManager.getInstance().flush();

                    } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
                        CookieSyncManager.getInstance().sync();

                    }

            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getApplicationContext(), "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }
        });
        mWebView.loadUrl(web_page);

        // Stop local links and redirects from opening in browser instead of WebView
        CookieManager.getInstance().setAcceptCookie(true);
        // Use local resource
        // mWebView.loadUrl("file:///android_asset/www/index.html");
    }

    // Prevent the back-button from closing the app
    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }


}

