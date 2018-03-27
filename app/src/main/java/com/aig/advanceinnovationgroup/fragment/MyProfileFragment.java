package com.aig.advanceinnovationgroup.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.activity.PersonalDetailsActivity;


public class MyProfileFragment extends Fragment implements View.OnClickListener {

    private Button personalDetailBT;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {

       /// personalDetailBT = (Button) view.findViewById(R.id.btn_personal_detail);


//        personalDetailBT.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case 0:
                Intent intent = new Intent(getActivity(), PersonalDetailsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
