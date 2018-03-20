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

import java.util.HashMap;
import java.util.Map;


public class FeeDetailFragment extends Fragment implements View.OnClickListener {

    private ImageView totalFeeIV, depositDetailIV, amountIV, dueAmountIV;
    private LinearLayout totalFeeLL, depositDetailLL, amountLL, dueAmountLL, totalFeeDetailLL;
    private boolean totalFee = true, deposit = false, amount = false, dueAmount = false;
    private TextView totalFeeTV, depositAmountTV, dueAmountTV;
    private Dialog mProgressDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        amountLL = (LinearLayout) view.findViewById(R.id.ll_amount);
        dueAmountLL = (LinearLayout) view.findViewById(R.id.ll_due_amount);
        totalFeeDetailLL = (LinearLayout) view.findViewById(R.id.ll_total_fee_detail);

        totalFeeLL.setOnClickListener(this);

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
        }
    }
}


