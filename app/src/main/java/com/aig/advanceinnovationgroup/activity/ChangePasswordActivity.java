package com.aig.advanceinnovationgroup.activity;

import android.app.Dialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private Dialog mProgressDialog;
    private EditText oldPassET, newPassET, cNewPassET;
    private Button changePassBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initView();

    }

    private void initView() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Change Password");

        oldPassET = (EditText) findViewById(R.id.et_old_password);
        newPassET = (EditText) findViewById(R.id.et_new_password);
        cNewPassET = (EditText) findViewById(R.id.et_com_new_password);
        changePassBT = (Button) findViewById(R.id.btn_change_pass);

        changePassBT.setOnClickListener(this);

    }

    public void changePasswordData(){
        mProgressDialog = Utils.showProgressDialog(ChangePasswordActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.CHANGE_PASSWORD_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response :", response);

                finish();

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
                param.put("student_id", AppPreferences.getString(ChangePasswordActivity.this, AppPreferences.PREF_KEY.STUDENT_ID));
                param.put("oldpass", oldPassET.getText().toString() );
                param.put("newpass", newPassET.getText().toString());
                param.put("conpass" ,cNewPassET.getText().toString());
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
            case R.id.btn_change_pass:
                if(TextUtils.isEmpty(oldPassET.getText().toString()))
                {
                    showBottomSheet(oldPassET,"Please enter old password");
                    //Toast.makeText(context,getResources().getString(R.string.enter_first_name),Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(newPassET.getText().toString()))
                {
                    showBottomSheet(newPassET,"Please enter new password");
                    //Toast.makeText(context,getResources().getString(R.string.enter_last_name),Toast.LENGTH_LONG).show();
                }else if(!Utils.isNetWorkAvailable(ChangePasswordActivity.this))
                {
                    showBottomSheet(cNewPassET,"Please enter confirm new password");
                    //Toast.makeText(context,getResources().getString(R.string.no_internet_connection_available),Toast.LENGTH_LONG).show();
                }else{
                    mProgressDialog = Utils.showProgressDialog(ChangePasswordActivity.this);
                   changePasswordData();
                }

        }
    }

    private void showBottomSheet(View view, String messgae)
    {
        Snackbar.make(view,messgae, Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok, new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
            }
        }).show();
    }
}
