package com.aig.advanceinnovationgroup.activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonalDetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView  dobET, genderET, contactNumberET, emailET, matarialStatusET, addressET, stateET, countryET;
    private TextView fullNameET;
    private Dialog mProgressDialog;
    private List<ProfileDetail> profileDetailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        profileDetailList = new ArrayList<>();
        initView();
        personalDetailData();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Personal Detail");

        fullNameET = (TextView) findViewById(R.id.et_full_name);
        dobET = (TextView) findViewById(R.id.et_date_of_birth);
        genderET = (TextView) findViewById(R.id.et_gender);
        contactNumberET = (TextView) findViewById(R.id.et_contact_number);
        emailET = (TextView) findViewById(R.id.et_email);
        matarialStatusET = (TextView) findViewById(R.id.et_marital_status);
        addressET = (TextView) findViewById(R.id.et_address);
        stateET = (TextView) findViewById(R.id.et_state);
        countryET = (TextView) findViewById(R.id.et_country);

    }


    public void personalDetailData(){
        mProgressDialog = Utils.showProgressDialog(PersonalDetailsActivity.this);
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
                param.put("student_id", AppPreferences.getString(PersonalDetailsActivity.this, AppPreferences.PREF_KEY.STUDENT_ID));

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
        }
        return super.onOptionsItemSelected(item);
    }
}
