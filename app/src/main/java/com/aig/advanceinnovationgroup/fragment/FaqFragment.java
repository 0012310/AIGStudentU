package com.aig.advanceinnovationgroup.fragment;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.adapter.FaqAdapter;
import com.aig.advanceinnovationgroup.model.FaqData;
import com.aig.advanceinnovationgroup.model.FaqsDatum;
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


public class FaqFragment extends Fragment {

    private ExpandableListView listView;
    private List<FaqsDatum> faqsDatumList;
    private List<String>  headerList;
    private List<String> childList;
    FaqAdapter faqAdapter;
    private Dialog mProgressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faq, container, false);
        faqsDatumList = new ArrayList<>();
        headerList = new ArrayList<>();
        childList = new ArrayList<>();
        initView(view);
        faqData();

        return view;
    }

    private void initView(View view) {
        listView = (ExpandableListView) view.findViewById(R.id.etv_faq);
    }

    public void faqData(){
        mProgressDialog = Utils.showProgressDialog(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.FAQ_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(response));
                reader.setLenient(true);
                FaqData faqData = gson.fromJson(reader, FaqData.class);
                int status = faqData.getStatus();
                if (status==1) {
                    faqsDatumList = faqData.getFaqsData();

                    for (int i = 0; i < faqsDatumList.size(); i++) {
                        headerList.add(faqsDatumList.get(i).getSn()+". "+faqsDatumList.get(i).getTitle());
                        childList.add(faqsDatumList.get(i).getDescription());

                        faqAdapter = new FaqAdapter(getActivity(), headerList, childList);
                        listView.setAdapter(faqAdapter);
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



}
