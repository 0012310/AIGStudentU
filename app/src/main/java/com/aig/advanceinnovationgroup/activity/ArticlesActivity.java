package com.aig.advanceinnovationgroup.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.adapter.ArticleAdapter;
import com.aig.advanceinnovationgroup.model.ArticleDetail;
import com.aig.advanceinnovationgroup.model.GetBlogDta;
import com.aig.advanceinnovationgroup.util.AppController;
import com.aig.advanceinnovationgroup.util.Constant;
import com.aig.advanceinnovationgroup.util.Utils;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticlesActivity extends AppCompatActivity {

    private RecyclerView articlesRV;
    private Toolbar toolbar;
    private LinearLayoutManager mLayoutManager;
    private String type;
    private Dialog mProgressDialog;
    private List<ArticleDetail> articleDetailList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        articleDetailList = new ArrayList<>();

        Intent intent = getIntent();
        if (intent!=null){
            type = intent.getStringExtra("article");
        }

        initView();
        getBlogData(type);
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (type.equalsIgnoreCase("article")) {
            getSupportActionBar().setTitle("Articles");
        }else if (type.equalsIgnoreCase("discussion")) {
            getSupportActionBar().setTitle("Discussion");
        }else if (type.equalsIgnoreCase("project")) {
            getSupportActionBar().setTitle("Project");
        }else if (type.equalsIgnoreCase("video")) {
            getSupportActionBar().setTitle("Video");
        }else if (type.equalsIgnoreCase("job")) {
            getSupportActionBar().setTitle("Job");
        }
        articlesRV = (RecyclerView) findViewById(R.id.rv_article);
        articlesRV.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(ArticlesActivity.this, LinearLayoutManager.VERTICAL, false);
        articlesRV.setLayoutManager(mLayoutManager);


    }


    private void getBlogData(final String type){
        mProgressDialog = Utils.showProgressDialog(ArticlesActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.BLOG_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                GetBlogDta getBlogDta = new Gson().fromJson(response, GetBlogDta.class);
                int status = getBlogDta.getStatus();
                if (status==1){
                    articleDetailList = getBlogDta.getArticleDetail();

                articlesRV.setAdapter(new ArticleAdapter(ArticlesActivity.this, articleDetailList));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.cancelProgressDialog(mProgressDialog);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("type", type);

                return param;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
