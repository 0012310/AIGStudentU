package com.aig.advanceinnovationgroup.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.adapter.AddProjectAdapter;
import com.aig.advanceinnovationgroup.model.DesiredData;
import com.aig.advanceinnovationgroup.model.DesiredJob;
import com.aig.advanceinnovationgroup.model.ProjectDetailData;
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

public class DesiredJobActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView jobTypeTV, employerTypeTV, categoryTV, physiChallengedTV, descriptionTV, workPermitTV, otherCountriesTV;
    private Dialog mProgressDialog;
    private List<DesiredJob> desiredJobList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desired_job);
        desiredJobList = new ArrayList<>();
        initView();
        desiredJobData();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Desired Jobs");

        jobTypeTV = (TextView) findViewById(R.id.tv_job_type);
        employerTypeTV = (TextView) findViewById(R.id.tv_employer_type);
        categoryTV = (TextView) findViewById(R.id.tv_category);
        physiChallengedTV = (TextView) findViewById(R.id.tv_phy_challenged);
        descriptionTV = (TextView) findViewById(R.id.tv_description);
        workPermitTV = (TextView) findViewById(R.id.tv_work_permit);
        otherCountriesTV = (TextView) findViewById(R.id.tv_other_countries);


    }

    public void desiredJobData(){
        mProgressDialog = Utils.showProgressDialog(DesiredJobActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.VIEW_DESIRED_JOB_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(response));
                reader.setLenient(true);
                DesiredData desiredData = gson.fromJson(reader, DesiredData.class);
                int status = desiredData.getStatus();
                if (status==1) {
                    desiredJobList = desiredData.getDesiredJob();

                    for (int i = 0; i < desiredJobList.size(); i++) {
                        jobTypeTV.setText(desiredJobList.get(i).getPermanent());
                        employerTypeTV.setText(desiredJobList.get(i).getEmploymentType());
                        categoryTV.setText(desiredJobList.get(i).getCategory());
                        physiChallengedTV.setText(desiredJobList.get(i).getPhyChallanged());
                        descriptionTV.setText(desiredJobList.get(i).getDescription());
                        workPermitTV.setText(desiredJobList.get(i).getWorkPermit());
                        otherCountriesTV.setText(desiredJobList.get(i).getOtherCountries());
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
                param.put("student_id", AppPreferences.getString(DesiredJobActivity.this, AppPreferences.PREF_KEY.STUDENT_ID));

                return param;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.edit:
                Intent intent = new Intent(DesiredJobActivity.this, EditDesiredJobActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
