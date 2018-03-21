package com.aig.advanceinnovationgroup.fragment;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.adapter.AttendanceAdapter;
import com.aig.advanceinnovationgroup.model.AttendanceData;
import com.aig.advanceinnovationgroup.model.AttendanceDetail;
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


public class ViewAttendanceFragment extends Fragment {

    private RecyclerView attendanceRV;
    private RecyclerView.LayoutManager layoutManager;
    private AttendanceAdapter attendanceAdapter;
    private Dialog mProgressDialog;
    private List<AttendanceDetail> attendanceDetailList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attendanceDetailList = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_view_attendance, container, false);

        initViews(view);
        attendanceData();

        return view;
    }

    private void initViews(View view) {

        attendanceRV = (RecyclerView) view.findViewById(R.id.rv_attendance);
        attendanceRV.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        attendanceRV.setLayoutManager(layoutManager);

    }

    public void attendanceData(){
        mProgressDialog = Utils.showProgressDialog(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.ATTENDANCE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(response));
                reader.setLenient(true);
                AttendanceData attendanceData = gson.fromJson(reader, AttendanceData.class);
//                AttendanceData attendanceData = new Gson().fromJson(response, AttendanceData.class);
                int status = attendanceData.getStatus();
                if (status==1) {
                    attendanceDetailList = attendanceData.getAttendanceDetail();

                    attendanceAdapter = new AttendanceAdapter(getActivity(), attendanceDetailList);
                    attendanceRV.setAdapter(attendanceAdapter);

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

}
