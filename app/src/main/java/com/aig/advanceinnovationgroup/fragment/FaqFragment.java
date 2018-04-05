package com.aig.advanceinnovationgroup.fragment;

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
import com.aig.advanceinnovationgroup.activity.AddSkillActivity;
import com.aig.advanceinnovationgroup.adapter.SkillAdapter;
import com.aig.advanceinnovationgroup.adapter.UserAdapter;
import com.aig.advanceinnovationgroup.model.SkillData;
import com.aig.advanceinnovationgroup.model.UserDetail;
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


public class FaqFragment extends Fragment {

    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInVzZXJJZCI6IjEiLCJyb2xlIjoiU1VQRVJBRE1JTiJ9.II1eEmwbKL8ke2-UAm1mdflIkbe5RZi3I3su0x7Ccn1sAwGOhXnTX38sHrMKzLIZtShv19i9eL9zhreUw8rylg";

    RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private List<UserDetail> userDetailList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userDetailList = new ArrayList<>();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faq, container, false);
        initView(view);
        skillData();
        return view;
    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_user);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }


    public void skillData(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://139.59.84.10/HadariOnlineR/rest/hello/allusers", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response :", response);
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jresponse = array.getJSONObject(i);
                        int id= jresponse.getInt("id");
                        String userName = jresponse.getString("userName");
                        String pass = jresponse.getString("password");
                        String name = jresponse.getString("name");
                        String role = jresponse.getString("role");

                        UserDetail  detail = new UserDetail(id, userName, pass, name, role);

                        userDetailList.add(detail);
                        recyclerView.setAdapter(new UserAdapter(getActivity(), userDetailList));

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
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> map = new HashMap<>();
                map.put("Authorization", "Token "+ token);
                return map;
            }


        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

}
