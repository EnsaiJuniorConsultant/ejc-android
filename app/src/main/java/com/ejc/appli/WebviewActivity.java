package com.ejc.appli;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebviewActivity extends AppCompatActivity {

    public static String url = "http://www.blog.ejc.fr/";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final WebView myWebView = new WebView(this);
        myWebView.loadUrl(url.replaceAll("http://", "https://"));
        myWebView.getSettings().setJavaScriptEnabled(true);

        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url)
            {
                // hide element by class name
                myWebView.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('container clearfix')[0].style.display='none'; })()");
                myWebView.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('copyright')[0].style.display='none'; })()");
            }
        });

        Toast.makeText(myWebView.getContext(),"Appuyez sur retour pour revenir à la page précédente", Toast.LENGTH_LONG).show();
        setContentView(myWebView);
    }

}
