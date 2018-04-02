package com.aig.advanceinnovationgroup.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.aig.advanceinnovationgroup.R;

public class AddSkillActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private Button addSkillBT;
    private RecyclerView skillDetailRV;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_skill);
        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Skill");

        skillDetailRV = (RecyclerView) findViewById(R.id.rv_add_skill);
        skillDetailRV.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(AddSkillActivity.this, LinearLayoutManager.VERTICAL, false);
        skillDetailRV.setLayoutManager(layoutManager);

        addSkillBT = (Button) findViewById(R.id.btn_add_skill);
        addSkillBT.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_skill:
                AddSkillCustomDialog customDialog=new AddSkillCustomDialog(AddSkillActivity.this);
                customDialog.show();
                break;
        }
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
