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
import com.aig.advanceinnovationgroup.adapter.PrepaExamAdapter;
import com.aig.advanceinnovationgroup.model.ExamDetail;
import com.aig.advanceinnovationgroup.model.PrepaExamData;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewPreparatoryExamFragment extends Fragment {

    private RecyclerView prepaExamRV;
    private LinearLayoutManager layoutManager;
    private Dialog mProgressDialog;
    private List<ExamDetail> examDetailList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        examDetailList = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view =  inflater.inflate(R.layout.fragment_view_preparatory_exam, container, false);

        initView(view);
        finalExamView();

        return view;
    }

    private void initView(View view) {
        prepaExamRV = (RecyclerView) view.findViewById(R.id.rv_view_prepa_exam);
        prepaExamRV.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        prepaExamRV.setLayoutManager(layoutManager);

    }


    public void finalExamView(){
        mProgressDialog = Utils.showProgressDialog(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.FINAL_EXAM_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                PrepaExamData prepaExamData = new Gson().fromJson(response, PrepaExamData.class);
                int status = prepaExamData.getStatus();
                if (status==1){
                    examDetailList = prepaExamData.getExamDetails();

                    prepaExamRV.setAdapter(new PrepaExamAdapter(getActivity(), examDetailList));
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
