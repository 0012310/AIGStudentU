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
import com.aig.advanceinnovationgroup.adapter.ProjectDetailAdapter;
import com.aig.advanceinnovationgroup.model.ProjectData;
import com.aig.advanceinnovationgroup.model.ProjectView;
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


public class ViewProjectFragment extends Fragment {

    private RecyclerView viewProjectRV;
    private List<ProjectView> projectViewList;
    private LinearLayoutManager layoutManager;
    private Dialog mProgressDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        projectViewList = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_project, container, false);

        initViews(view);
        projectDetail();

        return view;
    }

    private void initViews(View view) {

        viewProjectRV = (RecyclerView) view.findViewById(R.id.rv_view_project);
        viewProjectRV.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        viewProjectRV.setLayoutManager(layoutManager);
    }


    public void projectDetail(){
        mProgressDialog = Utils.showProgressDialog(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.PROJECTDETAIL_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                ProjectData projectData = new Gson().fromJson(response, ProjectData.class);
                int status = projectData.getStatus();
                if(status==1){
                    projectViewList = projectData.getProjectView();

                    viewProjectRV.setAdapter(new ProjectDetailAdapter(getActivity(), projectViewList));
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
