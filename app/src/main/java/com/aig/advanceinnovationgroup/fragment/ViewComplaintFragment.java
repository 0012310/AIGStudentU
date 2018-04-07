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
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ExpandableListView;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.adapter.FaqAdapter;
import com.aig.advanceinnovationgroup.adapter.ViewComplAdapter;
import com.aig.advanceinnovationgroup.model.ComplaintDetsils;
import com.aig.advanceinnovationgroup.model.FaqData;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ViewComplaintFragment extends Fragment {

    private ExpandableListView listView;
    private Dialog mProgressDialog;
    private List<ComplaintDetsils> listHeader;
    private List<ComplaintDetsils> childDataList;
    private ViewComplAdapter complaintAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listHeader = new ArrayList<>();
        childDataList = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_view_complaint, container, false);
        initView(view);
        viewComplaintData();
        return view;
    }

    private void initView(View view) {
        listView = (ExpandableListView) view.findViewById(R.id.etv_view_complaint);

    }
    public void viewComplaintData(){
        mProgressDialog = Utils.showProgressDialog(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.VIEW_COMPLAINT_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);

                try {
                    JSONObject object = new JSONObject(response);
                    int status = object.getInt("status");
                    if (status==1){
                        JSONArray jsonArray = object.getJSONArray("complaint_details");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            String complaint_type = jsonObject.getString("complaint_type");
                            String complaint_date = jsonObject.getString("complaint_date");

                            String complaint_id = jsonObject.getString("complaint_id");
                            String reply_id = jsonObject.getString("reply_id");
                            String message = jsonObject.getString("message");
                            String status1 = jsonObject.getString("status1");
                            String allocated_to = jsonObject.getString("allocated_to");
                            String assign_to = jsonObject.getString("assign_to");
                            String assign_date = jsonObject.getString("assign_date");
                            String reply_date = jsonObject.getString("reply_date");
                            String problem_statement = jsonObject.getString("problem_statement");
                            String rca = jsonObject.getString("rca");
                            String recommended_action = jsonObject.getString("recommended_action");
                            String status_msg = jsonObject.getString("status_msg");

                            ComplaintDetsils detsils = new ComplaintDetsils(complaint_type, complaint_date);
                            ComplaintDetsils complaintDetsils = new ComplaintDetsils(complaint_type,complaint_id, reply_id, message, status1, allocated_to, assign_to, assign_date,
                                                                                      reply_date, problem_statement, rca,  recommended_action, status_msg);

                            listHeader.add(detsils);
                            childDataList.add(complaintDetsils);


                            complaintAdapter = new ViewComplAdapter(getActivity(), listHeader, childDataList);
                            listView.setAdapter(complaintAdapter);
                        }
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

                return param;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }


}
