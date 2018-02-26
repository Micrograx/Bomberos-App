package com.breakapp.bomberosapp;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyAppWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        //if (Uri.parse(url).getHost().endsWith("http://www.breakappgames.com/Alertas")) {
        return false;
        //}

        // Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        //view.getContext().startActivity(intent);
        //return true;
    }
}