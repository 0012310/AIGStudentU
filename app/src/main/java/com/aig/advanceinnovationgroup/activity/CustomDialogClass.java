package com.aig.advanceinnovationgroup.activity;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.model.DesignationData;
import com.aig.advanceinnovationgroup.model.DesignationDatum;
import com.aig.advanceinnovationgroup.model.IncidentData;
import com.aig.advanceinnovationgroup.model.MonthData;
import com.aig.advanceinnovationgroup.model.MonthName;
import com.aig.advanceinnovationgroup.model.YearData;
import com.aig.advanceinnovationgroup.model.YearName;
import com.aig.advanceinnovationgroup.util.AppController;
import com.aig.advanceinnovationgroup.util.Constant;
import com.aig.advanceinnovationgroup.util.Utils;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 3/30/2018.
 */

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button addBT;
    private AppCompatSpinner employerTypeSP, fromMonthSP, fromYearSP, toMonthSP, toYearSP, designationSP;
    private EditText employerET, jobProfileET;
    private Dialog mProgressDialog;
    private int mposition;
    private List<DesignationDatum> designationDatumList;
    private List<MonthName> monthNameList;
    private List<YearName> yearNameList;
    private ArrayList<String> designationList;
    private ArrayList<String> designationListId;
    private ArrayList<String> monthList;
    private ArrayList<String> yearList;
    String[] employerType={"Select","Current Employer","Previous Employer","Other Employer"};


    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        designationDatumList = new ArrayList<>();
        monthNameList = new ArrayList<>();
        designationList = new ArrayList<>();
        designationListId = new ArrayList<>();
        monthList = new ArrayList<>();
        yearNameList = new ArrayList<>();
        yearList = new ArrayList<>();

        employerET = (EditText) findViewById(R.id.et_employer_name);
        jobProfileET = (EditText) findViewById(R.id.et_job_profile);

        employerTypeSP = (AppCompatSpinner) findViewById(R.id.sp_employer_type);
        fromMonthSP = (AppCompatSpinner) findViewById(R.id.sp_month);
        fromYearSP = (AppCompatSpinner) findViewById(R.id.sp_year);
        toMonthSP = (AppCompatSpinner) findViewById(R.id.sp_to_month);
        toYearSP = (AppCompatSpinner) findViewById(R.id.sp_to_year);
        designationSP = (AppCompatSpinner) findViewById(R.id.sp_designation);


        addBT = (Button) findViewById(R.id.btn_add);
        addBT.setOnClickListener(this);

        designationData();
        monthData();
        yearData();

        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                c, android.R.layout.simple_spinner_dropdown_item, employerType) {
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
    }


    public void designationData(){
        mProgressDialog = Utils.showProgressDialog(c);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.DESIGNATION_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(response));
                reader.setLenient(true);
                DesignationData designationData = gson.fromJson(reader, DesignationData.class);
                int status = designationData.getStatus();
                if (status==1) {
                      designationDatumList = designationData.getDesignationData();

                    designationList.add("Select");
                    designationListId.add("0");

                    for (int i = 0; i < designationDatumList.size(); i++) {
                        designationList.add(designationDatumList.get(i).getDesignation());

                    }


                    // Initializing an ArrayAdapter
                    final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                           c, android.R.layout.simple_spinner_dropdown_item, designationList) {
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
                    designationSP.setAdapter(spinnerArrayAdapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.cancelProgressDialog(mProgressDialog);
            }
        });
        AppController.getInstance().addToRequestQueue(stringRequest);
    }


    public void monthData(){
        mProgressDialog = Utils.showProgressDialog(c);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.MONTH_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(response));
                reader.setLenient(true);
                MonthData monthData = gson.fromJson(reader, MonthData.class);
                int status = monthData.getStatus();
                if (status==1) {
                    monthNameList = monthData.getMonthName();

                    monthList.add("Month");


                    for (int i = 0; i < monthNameList.size(); i++) {
                        monthList.add(String.valueOf(monthNameList.get(i).getMonth()));

                    }


                    // Initializing an ArrayAdapter
                    final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                            c, android.R.layout.simple_spinner_dropdown_item, monthList) {
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
                    fromMonthSP.setAdapter(spinnerArrayAdapter);
                    toMonthSP.setAdapter(spinnerArrayAdapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.cancelProgressDialog(mProgressDialog);
            }
        });
        AppController.getInstance().addToRequestQueue(stringRequest);
    }


    public void yearData(){
        mProgressDialog = Utils.showProgressDialog(c);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.YEAR_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(response));
                reader.setLenient(true);
                YearData yearData = gson.fromJson(reader, YearData.class);
                int status = yearData.getStatus();
                if (status==1) {
                    yearNameList = yearData.getYearName();

                    yearList.add("Year");


                    for (int i = 0; i < yearNameList.size(); i++) {
                        yearList.add(String.valueOf(yearNameList.get(i).getYear()));

                    }


                    // Initializing an ArrayAdapter
                    final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                            c, android.R.layout.simple_spinner_dropdown_item, yearList) {
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
                    fromYearSP.setAdapter(spinnerArrayAdapter);
                    toYearSP.setAdapter(spinnerArrayAdapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.cancelProgressDialog(mProgressDialog);
            }
        });
        AppController.getInstance().addToRequestQueue(stringRequest);
    }


    @Override
    public void onClick(View v) {

    }
}
