package com.aig.advanceinnovationgroup.activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.adapter.LanguageAdapter;
import com.aig.advanceinnovationgroup.model.EducationDetail;
import com.aig.advanceinnovationgroup.model.GraduationData;
import com.aig.advanceinnovationgroup.model.LanguageData;
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

public class EducationActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView graduationTV, specializationTV, typeTV, universityTV, yearTV,
                     postGraduationTV, postSpecializationTV, postTypeTV, postUniversityTV, postYearTV,
                     phdGraduationTV, phdSpecializationTV, phdTypeTV, phdUniversityTV, phdYearTV;
    private Dialog mProgressDialog;
    private List<EducationDetail> educationDetailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);
        educationDetailList = new ArrayList<>();
        initView();
        educationData();
    }

    private void initView() {

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Education");

        graduationTV = (TextView) findViewById(R.id.tv_basic_graduation);
        specializationTV = (TextView) findViewById(R.id.tv_specialization);
        typeTV = (TextView) findViewById(R.id.tv_type);
        universityTV = (TextView) findViewById(R.id.tv_university);
        yearTV = (TextView) findViewById(R.id.tv_year);

        postGraduationTV = (TextView) findViewById(R.id.tv_post_graduation);
        postSpecializationTV = (TextView) findViewById(R.id.tv_post_specialization);
        postTypeTV = (TextView) findViewById(R.id.tv_post_type);
        postUniversityTV = (TextView) findViewById(R.id.tv_post_university);
        postYearTV = (TextView) findViewById(R.id.tv_post_year);

        phdGraduationTV = (TextView) findViewById(R.id.tv_phd_graduation);
        phdSpecializationTV = (TextView) findViewById(R.id.tv_phd_specialization);
        phdTypeTV = (TextView) findViewById(R.id.tv_phd_type);
        phdUniversityTV = (TextView) findViewById(R.id.tv_phd_university);
        phdYearTV = (TextView) findViewById(R.id.tv_phd_year);
    }

    public void educationData(){
        mProgressDialog = Utils.showProgressDialog(EducationActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.EDUCATION_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(response));
                reader.setLenient(true);
                GraduationData graduationData = gson.fromJson(reader, GraduationData.class);
                int status = graduationData.getStatus();
                if (status==1) {
                    educationDetailList = graduationData.getEducationDetails();

                    for (int i = 0; i < educationDetailList.size(); i++) {
                        graduationTV.setText(educationDetailList.get(i).getBasic());
                        typeTV.setText(educationDetailList.get(i).getBasicType());
                        specializationTV.setText(educationDetailList.get(i).getBasicSpecialization());
                        universityTV.setText(educationDetailList.get(i).getBasicUniversity());
                        yearTV.setText(educationDetailList.get(i).getBasicYear());

                        postGraduationTV.setText(educationDetailList.get(i).getPg());
                        postTypeTV.setText(educationDetailList.get(i).getPgType());
                        postSpecializationTV.setText(educationDetailList.get(i).getPgSpecialization());
                        postUniversityTV.setText(educationDetailList.get(i).getPgUniversity());
                        postYearTV.setText(educationDetailList.get(i).getPgYear());


                        phdGraduationTV.setText(educationDetailList.get(i).getDoctorate());
                        phdTypeTV.setText(educationDetailList.get(i).getDoctType());
                        phdSpecializationTV.setText(educationDetailList.get(i).getDoctSpecialization());
                        phdUniversityTV.setText(educationDetailList.get(i).getDoctUniversity());
                        phdYearTV.setText(educationDetailList.get(i).getDoctYear());
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
                param.put("student_id", AppPreferences.getString(EducationActivity.this, AppPreferences.PREF_KEY.STUDENT_ID));

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
