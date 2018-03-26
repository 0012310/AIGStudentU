package com.aig.advanceinnovationgroup.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.adapter.CustomAdapter;
import com.aig.advanceinnovationgroup.model.DaysData;
import com.aig.advanceinnovationgroup.model.DaysList;
import com.aig.advanceinnovationgroup.model.IncidentData;
import com.aig.advanceinnovationgroup.model.IncidentSubject;
import com.aig.advanceinnovationgroup.model.StaffData;
import com.aig.advanceinnovationgroup.model.StaffDetail;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class AddNewIncidentFragment extends Fragment implements View.OnClickListener {

    private AppCompatSpinner incidentSP, staffSP, selectDateSP;
    private Dialog mProgressDialog;
    private List<IncidentSubject> incidentSubjectList;
    private ArrayList<String> subjectList;
    private ArrayList<String> subjectListId;
    private EditText et_project_description;

    private List<StaffDetail> staffDetailList;
    private ArrayList<String> staffList;
    private ArrayList<String> staffListId;


    private List<DaysList> daysLists;
    private ArrayList<String> dayList;
    private ArrayList<String> dayListId;
    List<EditText> allEds;
    List<EditText> startTime;
    List<EditText> endTime;
    private String count;
    private String staff;
    private String subjectName;

    private int mposition;
    private String subject;
    private LinearLayout selectDayLL;
    private RecyclerView single_ll;
    private Button addIncidentBT;
    private EditText dateET;
    private String[] strings;
    private Calendar myCalendar;
    private LinearLayoutManager layoutManager;
    private CustomAdapter customAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        incidentSubjectList = new ArrayList<>();
        subjectList = new ArrayList<>();
        subjectListId = new ArrayList<>();
        staffDetailList = new ArrayList<>();
        staffList = new ArrayList<>();
        staffListId = new ArrayList<>();

        daysLists = new ArrayList<>();
        dayList = new ArrayList<>();
        dayListId = new ArrayList<>();

        allEds = new ArrayList<EditText>();
        startTime = new ArrayList<EditText>();
        endTime = new ArrayList<EditText>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_new_incident, container, false);

        initView(view);
        incidentData();

        return view;
    }

    private void initView(View view) {
        incidentSP = (AppCompatSpinner) view.findViewById(R.id.sp_incident_subject);
        staffSP = (AppCompatSpinner) view.findViewById(R.id.sp_staff);
        selectDateSP = (AppCompatSpinner) view.findViewById(R.id.sp_select_day);
        selectDayLL = (LinearLayout) view.findViewById(R.id.ll_select_day);
        single_ll = (RecyclerView) view.findViewById(R.id.single_ll);
        et_project_description = (EditText) view.findViewById(R.id.et_project_description);
        single_ll.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        single_ll.setLayoutManager(layoutManager);

        addIncidentBT = (Button) view.findViewById(R.id.btn_add_incident);


        incidentSP.setOnItemSelectedListener(subject_listener);
        staffSP.setOnItemSelectedListener(staff_listener);
        selectDateSP.setOnItemSelectedListener(days_listener);

        addIncidentBT.setOnClickListener(this);

    }

    public void staffData(final String incidentID){
        mProgressDialog = Utils.showProgressDialog(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.STAFF_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(response));
                reader.setLenient(true);
                StaffData staffData = gson.fromJson(reader, StaffData.class);
                int status = staffData.getStatus();
                if (status==1) {
                    staffDetailList = staffData.getStaffDetails();

                    staffList.add("Select Staff");
                    staffListId.add("0");

                    for (int i = 0; i < staffDetailList.size(); i++) {
                        staffList.add(staffDetailList.get(i).getStaffName());
                        staffListId.add(staffDetailList.get(i).getStaffId());

                    }

                    // Initializing an ArrayAdapter
                    final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                            getActivity(), android.R.layout.simple_spinner_dropdown_item, staffList) {
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
                    staffSP.setAdapter(spinnerArrayAdapter);

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
                param.put("student_id", AppPreferences.getString(getActivity(), AppPreferences.PREF_KEY.STUDENT_ID));
                param.put("subject_id", incidentID);

                return param;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }



    public void incidentData(){
        mProgressDialog = Utils.showProgressDialog(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.INCIDENT_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(response));
                reader.setLenient(true);
                IncidentData incidentData = gson.fromJson(reader, IncidentData.class);
                int status = incidentData.getStatus();
                if (status==1) {
                    incidentSubjectList = incidentData.getIncidentSubject();

                    subjectList.add("Select Subject");
                    subjectListId.add("0");

                    for (int i = 0; i < incidentSubjectList.size(); i++) {
                        subjectList.add(incidentSubjectList.get(i).getSubjectName());
                        subjectListId.add(incidentSubjectList.get(i).getIncidentId());

                    }

                    // Initializing an ArrayAdapter
                    final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                            getActivity(), android.R.layout.simple_spinner_dropdown_item, subjectList) {
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
                    incidentSP.setAdapter(spinnerArrayAdapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.cancelProgressDialog(mProgressDialog);
            }
        });
        AppController.getInstance().addToRequestQueue(stringRequest);
    }


    public void daysData(){
        mProgressDialog = Utils.showProgressDialog(getActivity());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.SELECT_DAY_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("Response :", response);
                            Utils.cancelProgressDialog(mProgressDialog);
                            Gson gson = new Gson();
                            JsonReader reader = new JsonReader(new StringReader(response));
                            reader.setLenient(true);
                            DaysData daysData = gson.fromJson(reader, DaysData.class);
                            int status = daysData.getStatus();
                            if (status==1) {
                                daysLists = daysData.getDaysList();

                                dayList.add("Select Day");
                                dayListId.add("0");

                                for (int i = 0; i < daysLists.size(); i++) {
                                    dayList.add(String.valueOf(daysLists.get(i).getDays()));

                                }

                                // Initializing an ArrayAdapter
                    final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                            getActivity(), android.R.layout.simple_spinner_dropdown_item, dayList) {
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
                    selectDateSP.setAdapter(spinnerArrayAdapter);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.cancelProgressDialog(mProgressDialog);
            }
        });
        AppController.getInstance().addToRequestQueue(stringRequest);
    }



    private AdapterView.OnItemSelectedListener subject_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            staffList.clear();
            staffListId.clear();
            if (position>0) {
                subject = subjectListId.get(position);
                subjectName = subjectList.get(position);
                staffData(subject);
                if (position==1){
                    selectDayLL.setVisibility(View.VISIBLE);
                    single_ll.setVisibility(View.VISIBLE);
                }else {
                    selectDayLL.setVisibility(View.GONE);
                    single_ll.setVisibility(View.GONE);
                }
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private AdapterView.OnItemSelectedListener staff_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             dayList.clear();
            if (position>0) {
                staff = staffList.get(position);
                daysData();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    private AdapterView.OnItemSelectedListener days_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            if (position>0){
                count = dayList.get(position);
                int days = Integer.parseInt(dayList.get(position));
                customAdapter = new  CustomAdapter(getActivity(), days);
                single_ll.setAdapter(customAdapter);

            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    public void addProjectData(){

        mProgressDialog = Utils.showProgressDialog(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.ADDPROJECT_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(response));
                reader.setLenient(true);
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getInt("status")==1) {
                        Toast.makeText(getActivity(), object.getString("incident_detail"), Toast.LENGTH_SHORT).show();
                    }
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
                param.put("student_id", AppPreferences.getString(getActivity(), AppPreferences.PREF_KEY.STUDENT_ID));
                param.put("subject", subjectName);
                param.put("staff", staff);
                param.put("description", et_project_description.getText().toString());
                param.put("fields_count",count);
                param.put("class_date",  mytoString(customAdapter.dateString, ","));
                param.put("start_time",  mytoString(customAdapter.startTimeString, ","));
                param.put("end_time ",  mytoString(customAdapter.endTimeString, ","));
                return param;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.btn_add_incident:

           addProjectData();
           break;
     }
    }

    private static String mytoString(String[] theAray, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < theAray.length; i++) {
            if (i > 0) {
                sb.append(delimiter);
            }
            String item = theAray[i];
            sb.append(item);
        }
        return sb.toString();
    }
}
