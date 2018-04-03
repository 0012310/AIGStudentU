package com.aig.advanceinnovationgroup.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

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

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditDesiredJobActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_desired_job);
        workPermitList = new ArrayList<>();
        countryList = new ArrayList<>();
        workPermitData = new ArrayList<>();
        countryLists = new ArrayList<>();
        desiredJobList = new ArrayList<>();
        initView();
        workPermitData();
        countryData();
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


    }

    public void workPermitData(){
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

                    for (int i = 0; i < workPermitData.size(); i++) {
                        workPermitList.add(workPermitData.get(i).getWorkPermit());

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
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(stringRequest);
    }



    public void countryData(){
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

                    for (int i = 0; i < countryLists.size(); i++) {
                        countryList.add(countryLists.get(i).getCountry());

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
}