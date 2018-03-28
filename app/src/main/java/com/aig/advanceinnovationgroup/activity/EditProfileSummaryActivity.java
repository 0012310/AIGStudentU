package com.aig.advanceinnovationgroup.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.util.AppController;
import com.aig.advanceinnovationgroup.util.AppPreferences;
import com.aig.advanceinnovationgroup.util.Constant;
import com.aig.advanceinnovationgroup.util.Utils;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditProfileSummaryActivity extends AppCompatActivity implements View.OnClickListener {

    private Dialog mProgressDialog;
    private EditText resumeHeadingET, profileSummaryET;
    private Toolbar toolbar;
    private Button updateBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_summary);

        initViews();
        personalDetailData();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Profile Summary");

        resumeHeadingET = findViewById(R.id.et_resume_headline);
        profileSummaryET = (EditText) findViewById(R.id.et_profile_summary);

        updateBT = (Button) findViewById(R.id.btn_update);

        updateBT.setOnClickListener(this);
    }


    public void personalDetailData(){
        mProgressDialog = Utils.showProgressDialog(EditProfileSummaryActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.PROFILE_SUMMARY_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);

                try {
                    JSONObject object = new JSONObject(response);

                    JSONArray array = object.getJSONArray("profile_summary_detail");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.getJSONObject(i);
                        resumeHeadingET.setText(jsonObject.getString("resume_headline"));
                        profileSummaryET.setText(jsonObject.getString("profile_summary"));
                    }
                } catch (JSONException e) {


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
                param.put("student_id", AppPreferences.getString(EditProfileSummaryActivity.this, AppPreferences.PREF_KEY.STUDENT_ID));

                return param;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);

    }


    public void editProfileSummaryData(){
        mProgressDialog = Utils.showProgressDialog(EditProfileSummaryActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.EDIT_PROFILE_SUMMARY_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);

                try {
                    JSONObject object = new JSONObject(response);

                    Toast.makeText(EditProfileSummaryActivity.this, object.getString("update_profile_summary"), Toast.LENGTH_SHORT).show();
                    Intent intent  = new Intent(EditProfileSummaryActivity.this, ProfileSummaryActivity.class);
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
                param.put("student_id", AppPreferences.getString(EditProfileSummaryActivity.this, AppPreferences.PREF_KEY.STUDENT_ID));
                param.put("resume_headline", resumeHeadingET.getText().toString());
                param.put("profile_summary", profileSummaryET.getText().toString());
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_update:
                editProfileSummaryData();
                break;
        }
    }
}
