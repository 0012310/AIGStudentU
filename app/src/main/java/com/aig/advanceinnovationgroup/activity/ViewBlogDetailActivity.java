package com.aig.advanceinnovationgroup.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.util.Utils;

public class ViewBlogDetailActivity extends AppCompatActivity {
    private WebView webView;
    private String blogURl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_blog_detail);

        Intent intent = getIntent();
        if (intent!=null){
            blogURl = intent.getStringExtra("url");
        }

        //Get webview
        webView = (WebView) findViewById(R.id.webView);

        startWebView(blogURl);

    }

    private void startWebView(String url) {

        //Create new webview Client to show progress dialog
        //When opening a url or click on link

        webView.setWebViewClient(new WebViewClient() {
            Dialog progressDialog;

            //If you will not use this method url links are opeen in new brower not in webview
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //Show loader on url load
            public void onLoadResource (WebView view, String url) {
                if (progressDialog == null) {
                    // in standard case YourActivity.this
                   progressDialog = Utils.showProgressDialog(ViewBlogDetailActivity.this);
                }
            }
            public void onPageFinished(WebView view, String url) {
                try{
                    if (progressDialog.isShowing()) {
                        Utils.cancelProgressDialog(progressDialog);
                    }
                }catch(Exception exception){
                    exception.printStackTrace();

                }
            }

        });

        // Javascript inabled on webview
        webView.getSettings().setJavaScriptEnabled(true);



        //Load url in webview
        webView.loadUrl(url);


    }

}
