package com.aig.advanceinnovationgroup.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.model.LoginResponse;
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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 3/12/2018.
 */

public class LoginActivity extends BaseActivity  implements View.OnClickListener {
    private EditText userNameET, passwordET;
    private Dialog mProgressDialog;

    @Override
    protected void initUi() {
        Button btnLogin = (Button)findViewById(R.id.btn_login);
         userNameET = (EditText) findViewById(R.id.et_email);
         passwordET = (EditText) findViewById(R.id.et_passsword);
        btnLogin.setOnClickListener(this);
    }

    @Override
    protected int getLayoutById() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                if(TextUtils.isEmpty(userNameET.getText().toString()))
                {
                    showBottomSheet(userNameET,getResources().getString(R.string.invalid_user_id_message));
                    //Toast.makeText(context,getResources().getString(R.string.enter_first_name),Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(passwordET.getText().toString()))
                {
                    showBottomSheet(passwordET,getResources().getString(R.string.password_validation_message));
                    //Toast.makeText(context,getResources().getString(R.string.enter_last_name),Toast.LENGTH_LONG).show();
                }else if(!Utils.isNetWorkAvailable(LoginActivity.this))
                {
                    showBottomSheet(userNameET,getResources().getString(R.string.no_internet_connection_available));
                    //Toast.makeText(context,getResources().getString(R.string.no_internet_connection_available),Toast.LENGTH_LONG).show();
                }else{
                    mProgressDialog = Utils.showProgressDialog(LoginActivity.this);
                    callLoginApi(userNameET.getText().toString().trim(), passwordET.getText().toString().trim());
                }



        }
    }

    private void callLoginApi(final String username, final String password) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.LOGIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("res : ", response);
                Utils.cancelProgressDialog(mProgressDialog);
                LoginResponse loginResponse = new Gson().fromJson(response, LoginResponse.class);
                AppPreferences.putBoolean(LoginActivity.this, AppPreferences.PREF_KEY.IS_LOGGED_IN, true);
                AppPreferences.putString(LoginActivity.this, AppPreferences.PREF_KEY.STUDENT_ID, loginResponse.getId());
                AppPreferences.putString(LoginActivity.this, AppPreferences.PREF_KEY.EMAIL, loginResponse.getEmail());
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);

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
                param.put("username", username);
                param.put("password", password);

                return param;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);

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
