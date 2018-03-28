package com.aig.advanceinnovationgroup.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.model.PersonalDetailData;
import com.aig.advanceinnovationgroup.model.ProfileDetail;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class EditPersonalDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText  fullNameET, dobET, genderET, contactNumberET, emailET, matarialStatusET, addressET, stateET, countryET;
    private Dialog mProgressDialog;
    private List<ProfileDetail> profileDetailList;
    private Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal_detail);
        profileDetailList = new ArrayList<>();
        initView();
        personalDetailData();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Personal Detail");

        fullNameET = (EditText) findViewById(R.id.et_full_name);
        dobET = (EditText) findViewById(R.id.et_dob);
        genderET = (EditText) findViewById(R.id.et_gender);
        contactNumberET = (EditText) findViewById(R.id.et_contact);
        emailET = (EditText) findViewById(R.id.et_email);
        matarialStatusET = (EditText) findViewById(R.id.et_marital_status);
        addressET = (EditText) findViewById(R.id.et_address);
        stateET = (EditText) findViewById(R.id.et_state);
        countryET = (EditText) findViewById(R.id.et_country);

        myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Log.d("date :",sdf.format(myCalendar.getTime()));

                dobET.setText(sdf.format(myCalendar.getTime()));

            }

        };
        dobET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(EditPersonalDetailActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();



            }
        });

    }

    public void personalDetailData(){
        mProgressDialog = Utils.showProgressDialog(EditPersonalDetailActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.PERSONAL_DETAIL_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                PersonalDetailData personalDetailData = new Gson().fromJson(response, PersonalDetailData.class);
                int status = personalDetailData.getStatus();
                if (status==1) {
                    profileDetailList = personalDetailData.getProfileDetail();

                    for (int i = 0; i < profileDetailList.size(); i++) {
                        fullNameET.setText(profileDetailList.get(i).getFullName());
                        dobET.setText(profileDetailList.get(i).getDateOfBirth());
                        if (profileDetailList.get(i).getGender().equalsIgnoreCase("1")) {
                            genderET.setText("Male");
                        }else {
                            genderET.setText("Female");
                        }
                        contactNumberET.setText(profileDetailList.get(i).getContactNo());
                        emailET.setText(profileDetailList.get(i).getEmail());
                        if (profileDetailList.get(i).getMaritalStatus().equalsIgnoreCase("1")) {
                            matarialStatusET.setText("Married");
                        }else {
                            matarialStatusET.setText("Unmarried");
                        }
                        addressET.setText(profileDetailList.get(i).getAddress());
                        stateET.setText(profileDetailList.get(i).getState());
                        countryET.setText(profileDetailList.get(i).getCountry());


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
                param.put("student_id", AppPreferences.getString(EditPersonalDetailActivity.this, AppPreferences.PREF_KEY.STUDENT_ID));

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
