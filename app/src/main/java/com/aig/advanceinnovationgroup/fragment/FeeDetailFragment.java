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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.adapter.DepositDetailAdapter;
import com.aig.advanceinnovationgroup.adapter.DownloadAdapter;
import com.aig.advanceinnovationgroup.adapter.OverDueAmountAdapter;
import com.aig.advanceinnovationgroup.model.DepositDetailData;
import com.aig.advanceinnovationgroup.model.DepositFeeDetail;
import com.aig.advanceinnovationgroup.model.DownloadData;
import com.aig.advanceinnovationgroup.model.OverDueAmountData;
import com.aig.advanceinnovationgroup.model.OverdueFeeDetail;
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


public class FeeDetailFragment extends Fragment implements View.OnClickListener {

    private ImageView totalFeeIV, depositDetailIV, amountIV, dueAmountIV;
    private LinearLayout totalFeeLL, depositDetailLL,depositLL, amountLL,amountDetailLL, dueAmountLL, totalFeeDetailLL;
    private boolean totalFee = true, deposit = true, amount = true, dueAmount = true;
    private TextView totalFeeTV, depositAmountTV, dueAmountTV;
    private Dialog mProgressDialog;
    private RecyclerView rv_deposit_detail, rv_amount;
    private LinearLayoutManager layoutManager;
    private List<DepositFeeDetail> depositFeeDetailList;
    private List<OverdueFeeDetail> overDueAmountList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        depositFeeDetailList = new ArrayList<>();
        overDueAmountList = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fee_detail, container, false);

        initViews(view);

        return view;
    }

    private void initViews(View view) {

        totalFeeIV = (ImageView) view.findViewById(R.id.iv_total_fee);
        depositDetailIV = (ImageView) view.findViewById(R.id.iv_deposit_details);
        amountIV = (ImageView) view.findViewById(R.id.iv_amount);
        dueAmountIV = (ImageView) view.findViewById(R.id.iv_due_amount);

        totalFeeTV = (TextView) view.findViewById(R.id.tv_course_fee);
        depositAmountTV = (TextView) view.findViewById(R.id.tv_deposit_amount);
        dueAmountTV = (TextView) view.findViewById(R.id.tv_due_amount);

        totalFeeLL = (LinearLayout) view.findViewById(R.id.ll_total_fee);
        depositDetailLL = (LinearLayout) view.findViewById(R.id.ll_deposit_details);
        depositLL = (LinearLayout) view.findViewById(R.id.ll_deposit);
        amountLL = (LinearLayout) view.findViewById(R.id.ll_amount);
        amountDetailLL = (LinearLayout) view.findViewById(R.id.ll_amount_detail);
        dueAmountLL = (LinearLayout) view.findViewById(R.id.ll_due_amount);
        totalFeeDetailLL = (LinearLayout) view.findViewById(R.id.ll_total_fee_detail);
        rv_deposit_detail = (RecyclerView) view.findViewById(R.id.rv_deposit);
        rv_deposit_detail.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv_deposit_detail.setLayoutManager(layoutManager);
        rv_amount = (RecyclerView) view.findViewById(R.id.rv_amount);
        rv_amount.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv_amount.setLayoutManager(layoutManager);

        totalFeeLL.setOnClickListener(this);
        depositDetailLL.setOnClickListener(this);
        amountLL.setOnClickListener(this);
        dueAmountLL.setOnClickListener(this);

    }

    public void feeDetailData(){

        mProgressDialog = Utils.showProgressDialog(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.TOTALFEEDETAIL_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray jsonArray = object.getJSONArray("total_fee_details");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        totalFeeTV.setText(getString(R.string.Rs)+" "+jsonObject.getString("total_course_fee"));
                        depositAmountTV.setText(getString(R.string.Rs)+" "+jsonObject.getString("deposit_amount"));
                        dueAmountTV.setText(getString(R.string.Rs)+" "+jsonObject.getString("due_amount"));

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

    public void depositDetailData(){

        mProgressDialog = Utils.showProgressDialog(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.DEPOSITDETAIL_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                DepositDetailData detailData = new Gson().fromJson(response, DepositDetailData.class);
                int status = detailData.getStatus();
                if (status==1) {
                    depositFeeDetailList = detailData.getDepositFeeDetails();
                    rv_deposit_detail.setAdapter(new DepositDetailAdapter(getActivity(), depositFeeDetailList));
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


    public void amountDetailData(){

        mProgressDialog = Utils.showProgressDialog(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.AMOUNT_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(response));
                reader.setLenient(true);
                OverDueAmountData dueAmountData = gson.fromJson(response, OverDueAmountData.class);
                int status = dueAmountData.getStatus();
                if (status==1) {
                    overDueAmountList = dueAmountData.getOverdueFeeDetails();
                    rv_amount.setAdapter(new OverDueAmountAdapter(getActivity(), overDueAmountList));
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_total_fee:
                if(totalFee){
                    totalFeeIV.setImageResource(R.mipmap.down_arrow);
                    totalFeeDetailLL.setVisibility(View.VISIBLE);
                    feeDetailData();
                    totalFee = false;
                }else {
                    totalFeeIV.setImageResource(R.mipmap.next);
                    totalFeeDetailLL.setVisibility(View.GONE);
                    totalFee = true;
                }
                break;
            case R.id.ll_deposit_details:
                if(deposit){
                    depositDetailIV.setImageResource(R.mipmap.down_arrow);
                    depositLL.setVisibility(View.VISIBLE);
                    depositDetailData();
                    deposit = false;
                }else {
                    depositDetailIV.setImageResource(R.mipmap.next);
                    depositLL.setVisibility(View.GONE);
                    deposit = true;
                }
                break;
            case R.id.ll_amount:
                if(amount){
                    amountIV.setImageResource(R.mipmap.down_arrow);
                    amountDetailLL.setVisibility(View.VISIBLE);
                    amountDetailData();
                    amount = false;
                }else {
                    amountIV.setImageResource(R.mipmap.next);
                    amountDetailLL.setVisibility(View.GONE);
                    amount = true;
                }
                break;
            case R.id.ll_due_amount:
                if(dueAmount){
                    dueAmountIV.setImageResource(R.mipmap.down_arrow);
                   // amountDetailLL.setVisibility(View.VISIBLE);
                   // amountDetailData();
                    dueAmount = false;
                }else {
                    dueAmountIV.setImageResource(R.mipmap.next);
                    //amountDetailLL.setVisibility(View.GONE);
                    dueAmount = true;
                }
                break;
        }
    }
}


