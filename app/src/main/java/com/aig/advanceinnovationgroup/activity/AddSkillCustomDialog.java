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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.model.DesignationData;
import com.aig.advanceinnovationgroup.model.DesignationDatum;
import com.aig.advanceinnovationgroup.model.MonthData;
import com.aig.advanceinnovationgroup.model.MonthName;
import com.aig.advanceinnovationgroup.model.YearData;
import com.aig.advanceinnovationgroup.model.YearName;
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

/**
 * Created by admin on 3/30/2018.
 */

public class AddSkillCustomDialog extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button addBT;
    private AppCompatSpinner lastUsedSP,monthSP, yearSP;
    private EditText skillET, versionET;
    private Dialog mProgressDialog;
    private int mposition;

    private List<MonthName> monthNameList;
    private List<YearName> yearNameList;

    private ArrayList<String> monthList;
    private ArrayList<String> yearList;
    private String month, year, last_used_year;


    public AddSkillCustomDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_skill_custom_dialog);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        monthNameList = new ArrayList<>();

        monthList = new ArrayList<>();
        yearNameList = new ArrayList<>();
        yearList = new ArrayList<>();

        skillET = (EditText) findViewById(R.id.et_skill);
        versionET = (EditText) findViewById(R.id.et_version);

        lastUsedSP = (AppCompatSpinner) findViewById(R.id.sp_last_used);
        monthSP = (AppCompatSpinner) findViewById(R.id.sp_month);
        yearSP = (AppCompatSpinner) findViewById(R.id.sp_year);

        yearSP.setOnItemSelectedListener(year_listener);
        lastUsedSP.setOnItemSelectedListener(last_year_listener);
        monthSP.setOnItemSelectedListener(month_listener);

        addBT = (Button) findViewById(R.id.btn_add_skill);
        addBT.setOnClickListener(this);


        monthData();
        yearData();

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
                    monthSP.setAdapter(spinnerArrayAdapter);
                    yearSP.setAdapter(spinnerArrayAdapter);
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

                    lastUsedSP.setAdapter(spinnerArrayAdapter);
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


    private AdapterView.OnItemSelectedListener month_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               month = monthList.get(position);

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private AdapterView.OnItemSelectedListener year_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                year = yearList.get(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private AdapterView.OnItemSelectedListener last_year_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            if (position>0) {
                last_used_year = yearList.get(position);

            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };



    public void addSkillData(){
        mProgressDialog = Utils.showProgressDialog(c);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.ADD_SKILL_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                try {
                    JSONObject object = new JSONObject(response);
                    Toast.makeText(c, object.getString("add_skills_data"), Toast.LENGTH_SHORT).show();
                    dismiss();
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
                param.put("student_id", AppPreferences.getString(c, AppPreferences.PREF_KEY.STUDENT_ID));
                param.put("skills", skillET.getText().toString());
                param.put("version", versionET.getText().toString());
                param.put("last_used", last_used_year);
                param.put("experience_year",year);
                param.put("experience_month",  month);
                return param;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_skill:
                addSkillData();
                break;
        }
    }
}
