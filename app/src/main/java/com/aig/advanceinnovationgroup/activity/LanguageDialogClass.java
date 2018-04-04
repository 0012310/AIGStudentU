package com.aig.advanceinnovationgroup.activity;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.model.DesignationData;
import com.aig.advanceinnovationgroup.model.DesignationDatum;
import com.aig.advanceinnovationgroup.model.MonthData;
import com.aig.advanceinnovationgroup.model.MonthName;
import com.aig.advanceinnovationgroup.model.YearData;
import com.aig.advanceinnovationgroup.model.YearName;
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

/**
 * Created by admin on 3/30/2018.
 */

public class LanguageDialogClass extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button addBT;
    private AppCompatSpinner proficientLevelSP, readSP, writeSP, speakSP;
    private EditText languageET;
    private Dialog mProgressDialog;
    private int mposition;

    String[] yesNo={"Select","Yes","No"};
    String[] proficientLevel={"Select","Beginner","Proficient", "Expert"};

    public LanguageDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.language_custom_dialog);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);


        languageET = (EditText) findViewById(R.id.et_language);


        proficientLevelSP = (AppCompatSpinner) findViewById(R.id.sp_proficiency_level);
        readSP = (AppCompatSpinner) findViewById(R.id.sp_read);
        writeSP = (AppCompatSpinner) findViewById(R.id.sp_write);
        speakSP = (AppCompatSpinner) findViewById(R.id.sp_speak);


        addBT = (Button) findViewById(R.id.btn_add);
        addBT.setOnClickListener(this);


        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                c, android.R.layout.simple_spinner_dropdown_item, proficientLevel) {
            @Override
            public boolean isEnabled(int position) {

                position = mposition;

                return true;
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;

                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        proficientLevelSP.setAdapter(spinnerArrayAdapter);


        final ArrayAdapter<String> yesNoAdapter = new ArrayAdapter<String>(
                c, android.R.layout.simple_spinner_dropdown_item, yesNo) {
            @Override
            public boolean isEnabled(int position) {

                position = mposition;

                return true;
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;

                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        readSP.setAdapter(yesNoAdapter);
        writeSP.setAdapter(yesNoAdapter);
        speakSP.setAdapter(yesNoAdapter);
    }



    public void addLanguageData(){
        mProgressDialog = Utils.showProgressDialog(c);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.ADD_LANGUAGE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                try {
                    JSONObject object = new JSONObject(response);
                    Toast.makeText(c, object.getString("add_employer"), Toast.LENGTH_SHORT).show();
                    dismiss();
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
                param.put("student_id", AppPreferences.getString(c, AppPreferences.PREF_KEY.STUDENT_ID));
                param.put("languag", languageET.getText().toString());
                if (proficientLevelSP.getSelectedItem().toString().equalsIgnoreCase("Beginner")){
                    param.put("proficiency", "1");
                }else if (proficientLevelSP.getSelectedItem().toString().equalsIgnoreCase("Proficient")){
                    param.put("proficiency", "2");
                }else if (proficientLevelSP.getSelectedItem().toString().equalsIgnoreCase("Expert")){
                    param.put("proficiency", "3");
                }

                if (readSP.getSelectedItem().toString().equalsIgnoreCase("Yes")){
                    param.put("read", "1");
                }else if (readSP.getSelectedItem().toString().equalsIgnoreCase("No")){
                    param.put("read", "2");
                }
                if (writeSP.getSelectedItem().toString().equalsIgnoreCase("Yes")){
                    param.put("write", "1");
                }else if (writeSP.getSelectedItem().toString().equalsIgnoreCase("No")){
                    param.put("write", "2");
                }
                if (speakSP.getSelectedItem().toString().equalsIgnoreCase("Yes")){
                    param.put("speak", "1");
                }else if (speakSP.getSelectedItem().toString().equalsIgnoreCase("No")){
                    param.put("speak", "2");
                }
                return param;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                addLanguageData();
                break;
        }
    }
}
