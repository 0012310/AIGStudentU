package com.aig.advanceinnovationgroup.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.activity.ArticlesActivity;
import com.aig.advanceinnovationgroup.adapter.ArticleAdapter;
import com.aig.advanceinnovationgroup.adapter.DownloadAdapter;
import com.aig.advanceinnovationgroup.model.DownloadData;
import com.aig.advanceinnovationgroup.model.DueFeeDetail;
import com.aig.advanceinnovationgroup.model.GetBlogDta;
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


public class DownloadFragment extends Fragment {

    private RecyclerView downloadRV;
    private RecyclerView.LayoutManager mLayoutManager;
    private DownloadAdapter downloadAdapter;
    private Dialog mProgressDialog;
    private List<DueFeeDetail> dueFeeDetailList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dueFeeDetailList = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_download, container, false);

        initViews(view);
        downloadData();

        return view;
    }

    private void initViews(View view) {

        downloadRV = (RecyclerView) view.findViewById(R.id.rv_download);
        downloadRV.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        downloadRV.setLayoutManager(mLayoutManager);

        downloadRV.setAdapter(downloadAdapter);

        DividerItemDecoration verticalDecoration = new DividerItemDecoration(downloadRV.getContext(),
                DividerItemDecoration.HORIZONTAL);
        Drawable verticalDivider = ContextCompat.getDrawable(getActivity(), R.drawable.vertical_divider);
        verticalDecoration.setDrawable(verticalDivider);
        downloadRV.addItemDecoration(verticalDecoration);

        DividerItemDecoration horizontalDecoration = new DividerItemDecoration(downloadRV.getContext(),
                DividerItemDecoration.VERTICAL);
        Drawable horizontalDivider = ContextCompat.getDrawable(getActivity(), R.drawable.horizontal_divider);
        horizontalDecoration.setDrawable(horizontalDivider);
        downloadRV.addItemDecoration(horizontalDecoration);
    }


    public void downloadData(){
        mProgressDialog = Utils.showProgressDialog(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.DOWNLOAD_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                DownloadData downloadData = new Gson().fromJson(response, DownloadData.class);
                int status = downloadData.getStatus();
                if (status==1) {
                    dueFeeDetailList = downloadData.getDueFeeDetails();

                    downloadAdapter = new DownloadAdapter(getActivity(), dueFeeDetailList);
                    downloadRV.setAdapter(downloadAdapter);

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


    @Override
    public void onResume() {
        super.onResume();
        isStoragePermissionGranted();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Log.v("","Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
            isStoragePermissionGranted();
        }
    }


    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (getActivity().checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("","Permission is granted");
                return true;
            } else {

                Log.v("","Permission is revoked");
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("","Permission is granted");
            return true;
        }
    }


}
