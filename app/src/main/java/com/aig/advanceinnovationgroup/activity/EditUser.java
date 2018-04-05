package com.aig.advanceinnovationgroup.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.model.UserDetail;
import com.aig.advanceinnovationgroup.util.AppController;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditUser extends AppCompatActivity implements View.OnClickListener {
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInVzZXJJZCI6IjEiLCJyb2xlIjoiU1VQRVJBRE1JTiJ9.II1eEmwbKL8ke2-UAm1mdflIkbe5RZi3I3su0x7Ccn1sAwGOhXnTX38sHrMKzLIZtShv19i9eL9zhreUw8rylg";

    private EditText userNameET, passwordET, nameET, roleET;
    private UserDetail detail;
    private Button updateBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        Intent intent = getIntent();
     detail = (UserDetail) intent.getSerializableExtra("user");

        userNameET = (EditText) findViewById(R.id.et_username);
        passwordET = (EditText) findViewById(R.id.et_passsword);
        nameET = (EditText) findViewById(R.id.et_name);
        roleET = (EditText) findViewById(R.id.et_role);
        updateBT = (Button) findViewById(R.id.btn_update);


        userNameET.setText(detail.getUserName());
        passwordET.setText(detail.getPassword());
        nameET.setText(detail.getName());
        roleET.setText(detail.getRole());

        updateBT.setOnClickListener(this);
    }


    private void editUser() {
        JSONObject object = new JSONObject();
        try {
            object.put("id", detail.getId());
            object.put("userName", userNameET.getText().toString());
            object.put("password", passwordET.getText().toString());
            object.put("name",nameET.getText().toString());
            object.put("role", roleET.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://139.59.84.10/HadariOnlineR/rest/hello/update", object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                    Toast.makeText(EditUser.this, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("Authorization", "Token "+ token);
                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_update:
                editUser();
                break;
        }
    }
}
