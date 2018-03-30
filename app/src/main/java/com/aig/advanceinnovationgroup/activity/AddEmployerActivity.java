package com.aig.advanceinnovationgroup.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aig.advanceinnovationgroup.R;

public class AddEmployerActivity extends AppCompatActivity implements View.OnClickListener {

    private Button addEmployerBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employer);


        initView();
    }

    private void initView() {
        addEmployerBT = (Button) findViewById(R.id.btn_add_employer);


        addEmployerBT.setOnClickListener(this);
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
