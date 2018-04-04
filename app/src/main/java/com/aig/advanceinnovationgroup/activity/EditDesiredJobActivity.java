package com.aig.advanceinnovationgroup.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.model.CounryData;
import com.aig.advanceinnovationgroup.model.CountryList;
import com.aig.advanceinnovationgroup.model.DesignationData;
import com.aig.advanceinnovationgroup.model.DesiredData;
import com.aig.advanceinnovationgroup.model.DesiredJob;
import com.aig.advanceinnovationgroup.model.EditDesiredData;
import com.aig.advanceinnovationgroup.model.EditWorkPermit;
import com.aig.advanceinnovationgroup.model.WorkPermitData;
import com.aig.advanceinnovationgroup.model.WorkPermitDatum;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditDesiredJobActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private AppCompatSpinner jobTypeSP, employerTypeSP, categorySP, physiChallengedSP,  workPermitSP, otherCountriesSP;
    private Dialog mProgressDialog;
    private EditText descriptionET;
    private List<EditWorkPermit> desiredJobList;
    String[] employerType={"Empty","Full Time","Part Time"};
    String[] jobType={"Empty","Permanent","Temporary/Contractual"};
    String[] phyType={"Empty","Yes","No"};
    String[] categoryType={"Empty","General", "SC", "ST", "OBC - Creamy", "OBC - Non-Creamy"};
    private int mposition;
    private List<String> workPermitList;
    private List<WorkPermitDatum> workPermitData;
    private List<CountryList> countryLists;
    private List<String> countryList;
    private List<String> countryIDList;
    private List<String> workPermitIDList;
    private Button updateBTN;
    private String jobID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_desired_job);
        workPermitList = new ArrayList<>();
        countryList = new ArrayList<>();
        workPermitData = new ArrayList<>();
        countryLists = new ArrayList<>();
        desiredJobList = new ArrayList<>();
        countryIDList = new ArrayList<>();
        workPermitIDList = new ArrayList<>();
        initView();

        desiredJobData();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Desired Jobs");

        jobTypeSP = (AppCompatSpinner) findViewById(R.id.sp_job_type);
        employerTypeSP = (AppCompatSpinner) findViewById(R.id.sp_employer_type);
        categorySP = (AppCompatSpinner) findViewById(R.id.sp_category);
        physiChallengedSP = (AppCompatSpinner) findViewById(R.id.sp_phy_challenged);
        descriptionET = (EditText) findViewById(R.id.et_description);
        workPermitSP = (AppCompatSpinner) findViewById(R.id.sp_work_permit);
        otherCountriesSP = (AppCompatSpinner) findViewById(R.id.sp_other_countries);
        updateBTN = (Button) findViewById(R.id.btn_update);

        updateBTN.setOnClickListener(this);


    }

    public void workPermitData(final String workPermit){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.WORK_PERMIT_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response :", response);

                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(response));
                reader.setLenient(true);
                WorkPermitData permitData = gson.fromJson(reader, WorkPermitData.class);
                int status = permitData.getStatus();
                if (status==1) {
                    workPermitData = permitData.getWorkPermitData();

                    workPermitList.add("Empty");
                    workPermitIDList.add("0");

                    for (int i = 0; i < workPermitData.size(); i++) {
                        workPermitList.add(workPermitData.get(i).getWorkPermit());
                       // workPermitIDList.add(workPermitData.get(i).get)

                    }


                    // Initializing an ArrayAdapter
                    final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                            EditDesiredJobActivity.this, android.R.layout.simple_spinner_dropdown_item, workPermitList) {
                        @Override
                        public boolean isEnabled(int position) {

                            position = mposition;

                            return true;
                        }

                        @Override
                        public View getDropDownView(int position, View convertView,
                                                    ViewGroup parent) {
                            View view = super.getDropDownView(position, convertView, parent);
                            TextView tv = (TextView) view;

                            if (position == 0) {
                                // Set the hint text color gray
                                tv.setTextColor(Color.GRAY);
                            } else {
                                tv.setTextColor(Color.BLACK);
                            }
                            return view;
                        }
                    };
                    workPermitSP.setAdapter(spinnerArrayAdapter);
                    if (workPermit != null){
                        for (int i = 0; i <  workPermitList.size(); i++) {
                            if (workPermitList.get(i).equals(workPermit))
                            {
                                workPermitSP.setSelection(i);
                            }
                        }
                    } else {
                        workPermitSP.setSelection(0);
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(stringRequest);
    }



    public void countryData(final String countries){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.COUNTRY_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response :", response);

                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(response));
                reader.setLenient(true);
                CounryData counryData = gson.fromJson(reader, CounryData.class);
                int status = counryData.getStatus();
                if (status==1) {
                    countryLists = counryData.getCountryList();

                    countryList.add("Empty");
                    countryIDList.add("0");

                    for (int i = 0; i < countryLists.size(); i++) {
                        countryList.add(countryLists.get(i).getCountry());
                        countryIDList.add(countryLists.get(i).getCountryId());

                    }


                    // Initializing an ArrayAdapter
                    final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                            EditDesiredJobActivity.this, android.R.layout.simple_spinner_dropdown_item, countryList) {
                        @Override
                        public boolean isEnabled(int position) {

                            position = mposition;

                            return true;
                        }

                        @Override
                        public View getDropDownView(int position, View convertView,
                                                    ViewGroup parent) {
                            View view = super.getDropDownView(position, convertView, parent);
                            TextView tv = (TextView) view;

                            if (position == 0) {
                                // Set the hint text color gray
                                tv.setTextColor(Color.GRAY);
                            } else {
                                tv.setTextColor(Color.BLACK);
                            }
                            return view;
                        }
                    };
                    otherCountriesSP.setAdapter(spinnerArrayAdapter);
                    if (countries != null){
                        for (int i = 0; i <  countryIDList.size(); i++) {
                            if (countryIDList.get(i).equals(countries))
                            {
                                otherCountriesSP.setSelection(i);
                            }
                        }
                    } else {
                        otherCountriesSP.setSelection(0);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public void desiredJobData(){
        mProgressDialog = Utils.showProgressDialog(EditDesiredJobActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.EDIT_DESIRED_JOB_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(response));
                reader.setLenient(true);
                EditDesiredData desiredData = gson.fromJson(reader, EditDesiredData.class);
                int status = desiredData.getStatus();
                if (status==1) {
                    desiredJobList = desiredData.getEditWorkPermit();


                    final ArrayAdapter<String> jobArrayAdapter = new ArrayAdapter<String>(
                            EditDesiredJobActivity.this, android.R.layout.simple_spinner_dropdown_item, jobType) {
                        @Override
                        public boolean isEnabled(int position) {

                            position = mposition;

                            return true;
                        }

                        @Override
                        public View getDropDownView(int position, View convertView,
                                                    ViewGroup parent) {
                            View view = super.getDropDownView(position, convertView, parent);
                            TextView tv = (TextView) view;

                            if (position == 0) {
                                // Set the hint text color gray
                                tv.setTextColor(Color.GRAY);
                            } else {
                                tv.setTextColor(Color.BLACK);
                            }
                            return view;
                        }
                    };
                    jobTypeSP.setAdapter(jobArrayAdapter);






                        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                                EditDesiredJobActivity.this, android.R.layout.simple_spinner_dropdown_item, employerType) {
                            @Override
                            public boolean isEnabled(int position) {
                                position = mposition;
                                return true;
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;

                                if (position == 0) {
                                    // Set the hint text color gray
                                    tv.setTextColor(Color.GRAY);
                                } else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        employerTypeSP.setAdapter(spinnerArrayAdapter);

                        final ArrayAdapter<String> categoryArrayAdapter = new ArrayAdapter<String>(
                                EditDesiredJobActivity.this, android.R.layout.simple_spinner_dropdown_item, categoryType) {
                            @Override
                            public boolean isEnabled(int position) {

                                position = mposition;

                                return true;
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;

                                if (position == 0) {
                                    // Set the hint text color gray
                                    tv.setTextColor(Color.GRAY);
                                } else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        categorySP.setAdapter(categoryArrayAdapter);


                        final ArrayAdapter<String> challengeArrayAdapter = new ArrayAdapter<String>(
                                EditDesiredJobActivity.this, android.R.layout.simple_spinner_dropdown_item, phyType) {
                            @Override
                            public boolean isEnabled(int position) {

                                position = mposition;

                                return true;
                            }

                            @Override
                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;

                                if (position == 0) {
                                    // Set the hint text color gray
                                    tv.setTextColor(Color.GRAY);
                                } else {
                                    tv.setTextColor(Color.BLACK);
                                }
                                return view;
                            }
                        };
                        physiChallengedSP.setAdapter(challengeArrayAdapter);
                    for (int i = 0; i < desiredJobList.size(); i++) {

                        jobID = desiredJobList.get(i).getJobId();

                        if (desiredJobList.get(i).getJobType()==null){
                            jobTypeSP.setSelection(0);
                        }else if (desiredJobList.get(i).getJobType().equalsIgnoreCase("1")) {
                            jobTypeSP.setSelection(1);
                        }else if (desiredJobList.get(i).getJobType().equalsIgnoreCase("2")) {
                            jobTypeSP.setSelection(2);
                        }
                        if (desiredJobList.get(i).getCategory()==null){
                            categorySP.setSelection(0);
                        }else if (desiredJobList.get(i).getCategory().equalsIgnoreCase("1")) {
                            categorySP.setSelection(1);
                        }else if (desiredJobList.get(i).getCategory().equalsIgnoreCase("2")) {
                            categorySP.setSelection(2);
                        }
                        if (desiredJobList.get(i).getEmploymentType()==null){
                            employerTypeSP.setSelection(0);
                        }else if (desiredJobList.get(i).getEmploymentType().equalsIgnoreCase("1")) {
                            employerTypeSP.setSelection(1);
                        }else if (desiredJobList.get(i).getEmploymentType().equalsIgnoreCase("2")) {
                            employerTypeSP.setSelection(2);
                        }

                        if (desiredJobList.get(i).getPhysically()==null){
                            physiChallengedSP.setSelection(0);
                        }else if (desiredJobList.get(i).getPhysically().equalsIgnoreCase("1")) {
                            physiChallengedSP.setSelection(1);
                        }else if (desiredJobList.get(i).getPhysically().equalsIgnoreCase("2")) {
                            physiChallengedSP.setSelection(2);
                        }

                        workPermitData(desiredJobList.get(i).getWorkPermit());
                        countryData(desiredJobList.get(i).getCountries());

                        if (desiredJobList.get(i).getDescription()==null){
                            descriptionET.setText("Empty");
                        }else {
                            descriptionET.setText(desiredJobList.get(i).getDescription());
                        }
//
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
                param.put("student_id", AppPreferences.getString(EditDesiredJobActivity.this, AppPreferences.PREF_KEY.STUDENT_ID));

                return param;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }



    public void editDesiredJobData(){
        mProgressDialog = Utils.showProgressDialog(EditDesiredJobActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.UPDATE_DESIRED_JOB_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);

                try {
                    JSONObject object = new JSONObject(response);

                    Toast.makeText(EditDesiredJobActivity.this, object.getString("update_work_permit"), Toast.LENGTH_SHORT).show();
                    Intent intent  = new Intent(EditDesiredJobActivity.this, DesiredJobActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
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
                param.put("student_id", AppPreferences.getString(EditDesiredJobActivity.this, AppPreferences.PREF_KEY.STUDENT_ID));
               if (jobTypeSP.getSelectedItem().toString().equalsIgnoreCase("Permanent")){
                   param.put("job_type", "1");
               }else if (jobTypeSP.getSelectedItem().toString().equalsIgnoreCase("Temporary/Contractual")){
                   param.put("job_type", "2");
               }else {
                   param.put("job_type", "0");
               }
                if (employerTypeSP.getSelectedItem().toString().equalsIgnoreCase("Full Time")){
                    param.put("employment_type", "1");
                }else if (employerTypeSP.getSelectedItem().toString().equalsIgnoreCase("Part Time")){
                    param.put("employment_type", "2");
                }else {
                    param.put("employment_type", "0");
                }
                param.put("category",categorySP.getSelectedItem().toString());
                if (physiChallengedSP.getSelectedItem().toString().equalsIgnoreCase("Yes")){
                    param.put("physically", "1");
                }else if (physiChallengedSP.getSelectedItem().toString().equalsIgnoreCase("No")){
                    param.put("physically", "2");
                }else {
                    param.put("physically", "0");
                }
                param.put("description", descriptionET.getText().toString());

                param.put("work_permit", workPermitSP.getSelectedItem().toString());
                param.put("countries", otherCountriesSP.getSelectedItem().toString());
                param.put("job_id", jobID);
                return param;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_update:
               editDesiredJobData();
        }
    }
}