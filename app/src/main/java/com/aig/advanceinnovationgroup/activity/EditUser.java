package com.aig.advanceinnovationgroup.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.model.UserDetail;

public class EditUser extends AppCompatActivity {
    private EditText userNameET, passwordET, nameET, roleET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        Intent intent = getIntent();
        UserDetail detail = (UserDetail) intent.getSerializableExtra("user");

        userNameET = (EditText) findViewById(R.id.et_username);
        passwordET = (EditText) findViewById(R.id.et_passsword);
        nameET = (EditText) findViewById(R.id.et_name);
        roleET = (EditText) findViewById(R.id.et_role);

        userNameET.setText(detail.getUserName());
        passwordET.setText(detail.getPassword());
        nameET.setText(detail.getName());
        roleET.setText(detail.getRole());
    }
}
