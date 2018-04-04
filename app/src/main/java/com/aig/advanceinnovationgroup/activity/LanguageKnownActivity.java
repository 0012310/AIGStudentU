package com.aig.advanceinnovationgroup.activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.adapter.LanguageAdapter;
import com.aig.advanceinnovationgroup.model.LanguageData;
import com.aig.advanceinnovationgroup.model.LanguageDetail;
import com.aig.advanceinnovationgroup.util.AppController;
import com.aig.advanceinnovationgroup.util.AppPreferences;
import com.aig.advanceinnovationgroup.util.Constant;
import com.aig.advanceinnovationgroup.util.Utils;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LanguageKnownActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private Button languageBT;
    private RecyclerView languageRV;
    private LinearLayoutManager layoutManager;
    private Dialog mProgressDialog;
    private LanguageAdapter languageAdapter;
    private List<LanguageDetail> languageDetailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_known);
        languageDetailList = new ArrayList<>();
        initView();
        languageData();

    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Language Known");

        languageRV = (RecyclerView) findViewById(R.id.rv_add_skill);
        languageRV.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(LanguageKnownActivity.this, LinearLayoutManager.VERTICAL, false);
        languageRV.setLayoutManager(layoutManager);

        languageBT = (Button) findViewById(R.id.btn_add_skill);
        languageBT.setOnClickListener(this);

    }

    public void languageData(){
        mProgressDialog = Utils.showProgressDialog(LanguageKnownActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.VIEW_LANGUAGE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(response));
                reader.setLenient(true);
                LanguageData languageData = gson.fromJson(reader, LanguageData.class);
                int status = languageData.getStatus();
                if (status==1) {
                    languageDetailList = languageData.getLanguageDetails();

                    languageAdapter = new LanguageAdapter(LanguageKnownActivity.this, languageDetailList);
                    languageRV.setAdapter(languageAdapter);

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
                param.put("student_id", AppPreferences.getString(LanguageKnownActivity.this, AppPreferences.PREF_KEY.STUDENT_ID));

                return param;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_skill:
                LanguageDialogClass dialogClass=new LanguageDialogClass(LanguageKnownActivity.this);
                dialogClass.show();
                break;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
