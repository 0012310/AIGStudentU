package com.aig.advanceinnovationgroup.activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.adapter.AddEmployerAdapter;
import com.aig.advanceinnovationgroup.model.AddEmployerData;
import com.aig.advanceinnovationgroup.model.EmployerDatum;
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

public class AddEmployerActivity extends AppCompatActivity implements View.OnClickListener {

    private Button addEmployerBT;
    private RecyclerView employerRV;
    private LinearLayoutManager layoutManager;
    private AddEmployerAdapter employerAdapter;
    List<EmployerDatum> employerDataList;
    private Dialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employer);
        employerDataList = new ArrayList<>();


        initView();
        AddEmployerData();

    }

    private void initView() {
        employerRV = (RecyclerView) findViewById(R.id.rv_addEmployer);
        employerRV.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(AddEmployerActivity.this, LinearLayoutManager.VERTICAL, false);
        employerRV.setLayoutManager(layoutManager);


        addEmployerBT = (Button) findViewById(R.id.btn_add_employer);
        addEmployerBT.setOnClickListener(this);
    }

  public void AddEmployerData(){
    mProgressDialog = Utils.showProgressDialog(AddEmployerActivity.this);
    StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.VIEW_EMPLOYER_URL, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            Log.d("Response :", response);
            Utils.cancelProgressDialog(mProgressDialog);
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new StringReader(response));
            reader.setLenient(true);
            AddEmployerData employerData = gson.fromJson(reader, AddEmployerData.class);
            int status = employerData.getStatus();
            if (status==1) {
                employerDataList = employerData.getEmployerData();

                employerAdapter = new AddEmployerAdapter(AddEmployerActivity.this, employerDataList);
                employerRV.setAdapter(employerAdapter);

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
            param.put("student_id", AppPreferences.getString(AddEmployerActivity.this, AppPreferences.PREF_KEY.STUDENT_ID));

            return param;
        }
    };
        AppController.getInstance().addToRequestQueue(stringRequest);
  }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_employer:
                CustomDialogClass cdd=new CustomDialogClass(AddEmployerActivity.this);
                cdd.show();
                break;
        }
    }
}
