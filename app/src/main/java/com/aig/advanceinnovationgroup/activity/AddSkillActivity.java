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
import com.aig.advanceinnovationgroup.adapter.AddProjectAdapter;
import com.aig.advanceinnovationgroup.adapter.SkillAdapter;
import com.aig.advanceinnovationgroup.model.ProjectDetailData;
import com.aig.advanceinnovationgroup.model.SkillData;
import com.aig.advanceinnovationgroup.model.ViewSkillsDatum;
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

public class AddSkillActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private Button addSkillBT;
    private RecyclerView skillDetailRV;
    private LinearLayoutManager layoutManager;
    private Dialog mProgressDialog;
    private List<ViewSkillsDatum> skillDatumList;
    private SkillAdapter skillAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_skill);
        skillDatumList = new ArrayList<>();
        initView();
        skillData();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Skill");

        skillDetailRV = (RecyclerView) findViewById(R.id.rv_add_skill);
        skillDetailRV.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(AddSkillActivity.this, LinearLayoutManager.VERTICAL, false);
        skillDetailRV.setLayoutManager(layoutManager);

        addSkillBT = (Button) findViewById(R.id.btn_add_skill);
        addSkillBT.setOnClickListener(this);

    }


    public void skillData(){
        mProgressDialog = Utils.showProgressDialog(AddSkillActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.VIEW_SKILL_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(response));
                reader.setLenient(true);
                SkillData skillData = gson.fromJson(reader, SkillData.class);
                int status = skillData.getStatus();
                if (status==1) {
                    skillDatumList = skillData.getViewSkillsData();

                    skillAdapter = new SkillAdapter(AddSkillActivity.this, skillDatumList);
                    skillDetailRV.setAdapter(skillAdapter);

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
                param.put("student_id", AppPreferences.getString(AddSkillActivity.this, AppPreferences.PREF_KEY.STUDENT_ID));

                return param;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_skill:
                AddSkillCustomDialog customDialog=new AddSkillCustomDialog(AddSkillActivity.this);
                customDialog.show();
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
