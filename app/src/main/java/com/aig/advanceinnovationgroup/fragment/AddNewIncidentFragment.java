package com.aig.advanceinnovationgroup.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aig.advanceinnovationgroup.R;
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

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AddNewIncidentFragment extends Fragment implements View.OnClickListener {

    private AppCompatSpinner incidentSP, staffSP, selectDateSP;
    private Dialog mProgressDialog;
    private List<IncidentSubject> incidentSubjectList;
    private ArrayList<String> subjectList;
    private ArrayList<String> subjectListId;

    private List<StaffDetail> staffDetailList;
    private ArrayList<String> staffList;
    private ArrayList<String> staffListId;


    private List<DaysList> daysLists;
    private ArrayList<String> dayList;
    private ArrayList<String> dayListId;
    List<EditText> allEds;
    List<EditText> startTime;
    List<EditText> endTime;

    private int mposition;
    private String subject;
    private LinearLayout selectDayLL;
    private LinearLayout single_ll;
    private Button addIncidentBT;
    private EditText dateET;
    private String[] strings;

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
        single_ll = (LinearLayout) view.findViewById(R.id.single_ll);

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
//                AttendanceData attendanceData = new Gson().fromJson(response, AttendanceData.class);
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
                daysData();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private EditText startTimeET, endTimeET;
    private AdapterView.OnItemSelectedListener days_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            if (position>0){

                int days = Integer.parseInt(dayList.get(position));
                for (int i = 0; i < days; i++) {
                    final View child = getLayoutInflater().inflate(R.layout.single_item_view, null);
                    dateET = (EditText) child.findViewById(R.id.et_date);
                    startTimeET = (EditText) child.findViewById(R.id.et_start_time);
                    endTimeET = (EditText) child.findViewById(R.id.et_end_time);
                    allEds.add(dateET);
                    startTime.add(startTimeET);
                    endTime.add(endTimeET);

                    single_ll.addView(child);


                }
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.btn_add_incident:

             StringBuilder commaSepValueBuilder = new StringBuilder();
             StringBuilder startTimeValueBuilder = new StringBuilder();
             StringBuilder endTimeValueBuilder = new StringBuilder();
             for(int i=0; i < allEds.size(); i++){
                 commaSepValueBuilder.append(allEds.get(i).getText().toString());
                 startTimeValueBuilder.append(startTime.get(i).getText().toString());
                 endTimeValueBuilder.append(endTime.get(i).getText().toString());

                 if ( i != allEds.size()-1){
                     commaSepValueBuilder.append(", ");
                 }
                 if ( i != startTime.size()-1){
                     startTimeValueBuilder.append(", ");
                 }
                 if ( i != endTime.size()-1){
                     endTimeValueBuilder.append(", ");
                 }
             }
             Toast.makeText(getActivity(),  commaSepValueBuilder.toString(), Toast.LENGTH_SHORT).show();
             Toast.makeText(getActivity(),  startTimeValueBuilder.toString(), Toast.LENGTH_SHORT).show();
             Toast.makeText(getActivity(),  endTimeValueBuilder.toString(), Toast.LENGTH_SHORT).show();
     }
    }
}
