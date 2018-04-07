package com.aig.advanceinnovationgroup.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.activity.ChangePasswordActivity;
import com.aig.advanceinnovationgroup.model.ComplaintData;
import com.aig.advanceinnovationgroup.model.ComplaintType;
import com.aig.advanceinnovationgroup.model.DesignationData;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AddNewComplaintFragment extends Fragment implements View.OnClickListener {

    private AppCompatSpinner complaint_typeSP;
    private Dialog mProgressDialog;
    private List<ComplaintType> complaintTypeList;
    private List<String> complaintList;
    private List<String> complaintListID;
    private int mposition;
    private EditText et_message;
    private Button sendBT;
    private String complain_id;
    private Dialog mProgressDialog1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        complaintList = new ArrayList<>();
        complaintTypeList = new ArrayList<>();
        complaintListID = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_new_complaint, container, false);
        initView(view);
        complaintType();
        return view;
    }

    private void initView(View view) {
        complaint_typeSP = (AppCompatSpinner) view.findViewById(R.id.sp_complaint_type);
        et_message = (EditText) view.findViewById(R.id.et_message);
        sendBT = (Button) view.findViewById(R.id.btn_send);

        complaint_typeSP.setOnItemSelectedListener(complaint_listener);
        sendBT.setOnClickListener(this);
    }

    public void complaintType(){
        mProgressDialog = Utils.showProgressDialog(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.COMPLAINT_TYPE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(response));
                reader.setLenient(true);
                ComplaintData complaintData = gson.fromJson(reader, ComplaintData.class);
                int status = complaintData.getStatus();
                if (status==1) {
                    complaintTypeList = complaintData.getComplaintType();

                    complaintList.add("Select");
                    complaintListID.add("0");


                    for (int i = 0; i < complaintTypeList.size(); i++) {
                        complaintList.add(complaintTypeList.get(i).getComplaintType());
                        complaintListID.add(complaintTypeList.get(i).getComplaintId());

                    }

                    // Initializing an ArrayAdapter
                    final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                            getActivity(), android.R.layout.simple_spinner_dropdown_item, complaintList) {
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
                    complaint_typeSP.setAdapter(spinnerArrayAdapter);
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


        private AdapterView.OnItemSelectedListener complaint_listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position>0) {
                    complain_id = complaintListID.get(position);


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };


        public void addComplaint() {
          //  mProgressDialog1 = Utils.showProgressDialog(getActivity());
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.ADD_COMPLAINT_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("Response :", response);

                    try {
                        Utils.cancelProgressDialog(mProgressDialog);
                        JSONObject object = new JSONObject(response);

                        if (object.getInt("status") == 1) {
                            Toast.makeText(getActivity(), "Your complaint has been successfully registered.", Toast.LENGTH_SHORT).show();
                        } else if (object.getInt("status") == 2) {
                            Toast.makeText(getActivity(), object.getString(" complaint_data"), Toast.LENGTH_SHORT).show();
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
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> param = new HashMap<>();
                    param.put("student_id", AppPreferences.getString(getActivity(), AppPreferences.PREF_KEY.STUDENT_ID));
                    param.put("message", et_message.getText().toString());
                    param.put("complain_id", complain_id);
                    return param;
                }
            };
            AppController.getInstance().addToRequestQueue(stringRequest);
        }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.btn_send:
              if (TextUtils.isEmpty(et_message.getText().toString())) {
                  showBottomSheet(et_message, "Please enter message");
                  //Toast.makeText(context,getResources().getString(R.string.enter_first_name),Toast.LENGTH_LONG).show();
              } else if (complaint_typeSP.getSelectedItem().toString().equalsIgnoreCase("Select")) {
                  showBottomSheet(complaint_typeSP, "Please select complaint type");
                  //Toast.makeText(context,getResources().getString(R.string.enter_last_name),Toast.LENGTH_LONG).show();
              } else {
                  mProgressDialog = Utils.showProgressDialog(getActivity());
                  addComplaint();
              }
              addComplaint();
              break;
      }

    }

      private void showBottomSheet(View view, String messgae) {
            Snackbar.make(view, messgae, Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            }).show();
        }

}
