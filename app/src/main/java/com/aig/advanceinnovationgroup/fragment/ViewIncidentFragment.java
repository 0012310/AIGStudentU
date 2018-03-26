package com.aig.advanceinnovationgroup.fragment;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.adapter.DownloadAdapter;
import com.aig.advanceinnovationgroup.model.DownloadData;
import com.aig.advanceinnovationgroup.model.IncidentDatum;
import com.aig.advanceinnovationgroup.model.TrainerData;
import com.aig.advanceinnovationgroup.model.TrainerDatum;
import com.aig.advanceinnovationgroup.model.ViewIncidentData;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ViewIncidentFragment extends Fragment implements View.OnClickListener {
    private TextView incidentSubjectTV, classDateTV, startTimeTV, endTimeTV,noofDaysTV, incidentDateTV,
                     messageTV, byTV, incidentNameTV, trainerTV, detailsTV, startDateTV, endDateTV, statusTV,
                    examincidentSubjectTV, examclassDateTV, examstartTimeTV, examendTimeTV,examnoofDaysTV, examincidentDateTV,
                    exammessageTV, exambyTV, examincidentNameTV, examtrainerTV, examdetailsTV, examstartDateTV, examendDateTV, examstatusTV;
    private Dialog mProgressDialog;
    private List<IncidentDatum> incidentDatumList;
    private List<TrainerDatum> trainerDatumList;
    private LinearLayout classSchduleLL, examScheduleLL, ll_class, ll_exam;
    private ImageView classSchduleIV, examScheduleIV;
    private boolean classSchedule = true, examSchedule = true;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        incidentDatumList = new ArrayList<>();
        trainerDatumList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_incident, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        classSchduleLL = (LinearLayout) view.findViewById(R.id.ll_class_schedule);
        examScheduleLL = (LinearLayout) view.findViewById(R.id.ll_exam_sch);
        ll_class = (LinearLayout) view.findViewById(R.id.ll_class);
        ll_exam = (LinearLayout) view.findViewById(R.id.ll_exam);
        classSchduleIV = (ImageView) view.findViewById(R.id.iv_exam);
        examScheduleIV = (ImageView) view.findViewById(R.id.iv_exam_schedule);
        incidentSubjectTV = (TextView) view.findViewById(R.id.tv_incident_subject);
        classDateTV = (TextView) view.findViewById(R.id.tv_class_date);
        startTimeTV = (TextView) view.findViewById(R.id.tv_start_time);
        endTimeTV = (TextView) view.findViewById(R.id.tv_end_time);
        noofDaysTV = (TextView) view.findViewById(R.id.tv_no_day);
        incidentDateTV = (TextView) view.findViewById(R.id.tv_incident_date);
        messageTV = (TextView) view.findViewById(R.id.tv_message);
        byTV = (TextView) view.findViewById(R.id.tv_by);
        incidentNameTV = (TextView) view.findViewById(R.id.tv_incident_name);
        trainerTV = (TextView) view.findViewById(R.id.tv_trainer);
        detailsTV = (TextView) view.findViewById(R.id.tv_details);
        startDateTV = (TextView) view.findViewById(R.id.tv_start_date);
        endDateTV = (TextView) view.findViewById(R.id.tv_end_date);
        statusTV = (TextView) view.findViewById(R.id.tv_status);

        examincidentSubjectTV = (TextView) view.findViewById(R.id.tv_exam_incident_subject);
        examclassDateTV = (TextView) view.findViewById(R.id.tv_exam_class_date);
        examstartTimeTV = (TextView) view.findViewById(R.id.tv_exam_start_time);
        examendTimeTV = (TextView) view.findViewById(R.id.tv_exam_end_time);
        examnoofDaysTV = (TextView) view.findViewById(R.id.tv_exam_no_day);
        examincidentDateTV = (TextView) view.findViewById(R.id.tv_exam_incident_date);
        exammessageTV = (TextView) view.findViewById(R.id.tv_exam_message);
        exambyTV = (TextView) view.findViewById(R.id.tv_exam_by);
        examincidentNameTV = (TextView) view.findViewById(R.id.tv_exam_incident_name);
        examtrainerTV = (TextView) view.findViewById(R.id.tv_exam_trainer);
        examdetailsTV = (TextView) view.findViewById(R.id.tv_exam_details);
        examstartDateTV = (TextView) view.findViewById(R.id.tv_exam_start_date);
        examendDateTV = (TextView) view.findViewById(R.id.tv_exam_end_date);
        examstatusTV = (TextView) view.findViewById(R.id.tv_exam_status);

        classSchduleLL.setOnClickListener(this);
        examScheduleLL.setOnClickListener(this);


    }

    public void incidentView(){
        mProgressDialog = Utils.showProgressDialog(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.INCIDENT_VIEW_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                ViewIncidentData viewIncidentData = new Gson().fromJson(response, ViewIncidentData.class);
                int status = viewIncidentData.getStatus();
                if (status==1) {
                    incidentDatumList = viewIncidentData.getIncidentData();

                    for (int i = 0; i < incidentDatumList.size(); i++) {
                        incidentSubjectTV.setText(incidentDatumList.get(0).getIncidentSubject());
                        classDateTV.setText(incidentDatumList.get(0).getClassDate());
                        startTimeTV.setText(incidentDatumList.get(0).getStartTime());
                        endTimeTV.setText(incidentDatumList.get(0).getEndTime());
                        noofDaysTV.setText(incidentDatumList.get(0).getNoOfDays());
                        incidentDateTV.setText(incidentDatumList.get(0).getStartDate());
                        viewMessage(incidentDatumList.get(0).getIncidentId());
                        viewTrainer(incidentDatumList.get(0).getIncidentId());
                    }

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

                return param;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }


    public void examScheduleData(){
        mProgressDialog = Utils.showProgressDialog(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.INCIDENT_VIEW_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                ViewIncidentData viewIncidentData = new Gson().fromJson(response, ViewIncidentData.class);
                int status = viewIncidentData.getStatus();
                if (status==1) {
                    incidentDatumList = viewIncidentData.getIncidentData();

                    for (int i = 0; i < incidentDatumList.size(); i++) {
                        examincidentSubjectTV.setText(incidentDatumList.get(1).getIncidentSubject());
                        examclassDateTV.setText(incidentDatumList.get(1).getClassDate());
                        examstartTimeTV.setText(incidentDatumList.get(1).getStartTime());
                        examendTimeTV.setText(incidentDatumList.get(1).getEndTime());
                        examnoofDaysTV.setText(incidentDatumList.get(1).getNoOfDays());
                        examincidentDateTV.setText(incidentDatumList.get(1).getStartDate());

                        viewMessage(incidentDatumList.get(1).getIncidentId());
                        viewTrainer(incidentDatumList.get(1).getIncidentId());
                    }

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

                return param;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);

    }



    public void viewMessage(final String incedentID){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.INCIDENT_VIEW_MESSAGE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response :", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray  = jsonObject.getJSONArray("incident_message");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        messageTV.setText(object.getString("message"));
                        byTV.setText(object.getString("message_by"));
                        exammessageTV.setText(object.getString("message"));
                        exambyTV.setText(object.getString("message_by"));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("incident_id", incedentID);

                return param;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);

    }

    public void viewTrainer(final String incedentID){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.INCIDENT_TRAINER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response :", response);
                TrainerData trainerData = new Gson().fromJson(response, TrainerData.class);
                int status = trainerData.getStatus();
                if (status==1){
                    trainerDatumList  = trainerData.getTrainerData();
                    for (int i = 0; i < trainerDatumList.size(); i++) {
                        incidentNameTV.setText(trainerDatumList.get(0).getIncidentTitle());
                        trainerTV.setText(trainerDatumList.get(0).getTrainerName());
                        detailsTV.setText(trainerDatumList.get(0).getDescription());
                        startDateTV.setText(trainerDatumList.get(0).getStartDate());
                        endDateTV.setText(trainerDatumList.get(0).getEndDate());
                        statusTV.setText(trainerDatumList.get(0).getIncidentStatus());

                        examincidentNameTV.setText(trainerDatumList.get(0).getIncidentTitle());
                        examtrainerTV.setText(trainerDatumList.get(0).getTrainerName());
                        examdetailsTV.setText(trainerDatumList.get(0).getDescription());
                        examstartDateTV.setText(trainerDatumList.get(0).getStartDate());
                        examendDateTV.setText(trainerDatumList.get(0).getEndDate());
                        examstatusTV.setText(trainerDatumList.get(0).getIncidentStatus());
                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("incident_id", incedentID);

                return param;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_class_schedule:
                if (classSchedule) {
                    classSchduleIV.setImageResource(R.mipmap.down_arrow);
                    ll_class.setVisibility(View.VISIBLE);
                    incidentView();
                    classSchedule = false;
                } else {
                    classSchduleIV.setImageResource(R.mipmap.next);
                    ll_class.setVisibility(View.GONE);
                    classSchedule = true;
                }
                break;
            case R.id.ll_exam_sch:
                if (examSchedule) {
                    examScheduleIV.setImageResource(R.mipmap.down_arrow);
                    ll_exam.setVisibility(View.VISIBLE);
                    examScheduleData();
                    examSchedule = false;
                } else {
                    examScheduleIV.setImageResource(R.mipmap.next);
                    ll_exam.setVisibility(View.GONE);
                    examSchedule = true;
                }
                break;

        }
    }
}
