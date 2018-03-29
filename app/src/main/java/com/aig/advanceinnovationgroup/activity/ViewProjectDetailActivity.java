package com.aig.advanceinnovationgroup.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.adapter.ProjectDetailAdapter;
import com.aig.advanceinnovationgroup.model.ProjectData;
import com.aig.advanceinnovationgroup.model.ProjectDatum;
import com.aig.advanceinnovationgroup.model.ProjectDetail;
import com.aig.advanceinnovationgroup.model.ProjectView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewProjectDetailActivity extends AppCompatActivity {

    private List<ProjectDatum> projectViewList;
    private Dialog mProgressDialog;
    private TextView projectNameTV, projectTypeTV, trainerNameTV, projectFIleTV;
    private String projectID;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_project_detail);
        projectViewList = new ArrayList<>();
        Intent intent = getIntent();
        if (intent!=null){
            projectID = intent.getStringExtra("project_id");
        }

        initView();
        projectDetail();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Project Detail");
        projectNameTV = (TextView) findViewById(R.id.tv_project_name);
        projectTypeTV = (TextView) findViewById(R.id.tv_project_type);
        trainerNameTV = (TextView) findViewById(R.id.tv_trainer_name);
        projectFIleTV = (TextView) findViewById(R.id.tv_project_file);
    }

    public void projectDetail(){
        mProgressDialog = Utils.showProgressDialog(ViewProjectDetailActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.PROJECTVIEWDETAIL_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                ProjectDetail projectView = new Gson().fromJson(response, ProjectDetail.class);
                int status = projectView.getStatus();
                if(status==1){
                    projectViewList = projectView.getProjectData();

                    for (int i = 0; i < projectViewList.size(); i++) {
                        projectNameTV.setText(projectViewList.get(i).getProjectName());
                        projectTypeTV.setText(projectViewList.get(i).getProjectType());
                        trainerNameTV.setText(projectViewList.get(i).getTrainerName());
                        projectFIleTV.setText(projectViewList.get(i).getFilename());
                    }


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
                param.put("project_id", projectID);

                return param;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
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
