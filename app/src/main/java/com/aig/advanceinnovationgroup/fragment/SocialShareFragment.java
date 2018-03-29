package com.aig.advanceinnovationgroup.fragment;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.activity.ViewBlogDetailActivity;
import com.aig.advanceinnovationgroup.util.Utils;


public class SocialShareFragment extends Fragment {

    private WebView webView;
    private double PIC_WIDTH;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_social_share, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        //Get webview
        webView = (WebView) view.findViewById(R.id.webView);
        startWebView("http://aigpl.com/android/social_media.php");
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
                    progressDialog = Utils.showProgressDialog(getActivity());
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
