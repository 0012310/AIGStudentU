package com.aig.advanceinnovationgroup.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.activity.AddEmployerActivity;
import com.aig.advanceinnovationgroup.activity.AddProjectActivity;
import com.aig.advanceinnovationgroup.activity.AddSkillActivity;
import com.aig.advanceinnovationgroup.activity.DesiredJobActivity;
import com.aig.advanceinnovationgroup.activity.EducationActivity;
import com.aig.advanceinnovationgroup.activity.LanguageKnownActivity;
import com.aig.advanceinnovationgroup.activity.PersonalDetailsActivity;
import com.aig.advanceinnovationgroup.activity.ProfileSummaryActivity;
import com.aig.advanceinnovationgroup.activity.UploadResumeActivity;


public class MyProfileFragment extends Fragment implements View.OnClickListener {

    private CardView personalDetailCV, profileSummaryCV, addEmployerCV, addProjectCV, addSkillCV, languageKnownCV, desiredJobCV,
                     uploadResumeCV,educationCV;

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
        personalDetailCV = (CardView) view.findViewById(R.id.cv_personal_details);
        profileSummaryCV = (CardView) view.findViewById(R.id.cv_profile_summary);
        addEmployerCV = (CardView) view.findViewById(R.id.cv_add_employer);
        addProjectCV = (CardView) view.findViewById(R.id.cv_add_project);
        addSkillCV = (CardView) view.findViewById(R.id.cv_add_skill);
        languageKnownCV = (CardView) view.findViewById(R.id.cv_known_language);
        desiredJobCV = (CardView) view.findViewById(R.id.cv_desired_job);
        uploadResumeCV = (CardView) view.findViewById(R.id.cv_upload_resume);
        educationCV = (CardView) view.findViewById(R.id.cv_education);

        personalDetailCV.setOnClickListener(this);
        profileSummaryCV.setOnClickListener(this);
        addEmployerCV.setOnClickListener(this);
        addProjectCV.setOnClickListener(this);
        addSkillCV.setOnClickListener(this);
        languageKnownCV.setOnClickListener(this);
        desiredJobCV.setOnClickListener(this);
        uploadResumeCV.setOnClickListener(this);
        educationCV.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cv_personal_details:
                Intent intent = new Intent(getActivity(), PersonalDetailsActivity.class);
                startActivity(intent);
                break;
            case R.id.cv_profile_summary:
                Intent intent1 = new Intent(getActivity(), ProfileSummaryActivity.class);
                startActivity(intent1);
                break;
            case R.id.cv_add_employer:
                Intent intent2 = new Intent(getActivity(), AddEmployerActivity.class);
                startActivity(intent2);
                break;
            case R.id.cv_add_project:
                Intent intent3 = new Intent(getActivity(), AddProjectActivity.class);
                startActivity(intent3);
                break;
            case R.id.cv_add_skill:
                Intent intent4 = new Intent(getActivity(), AddSkillActivity.class);
                startActivity(intent4);
                break;
            case R.id.cv_known_language:
                Intent intent5 = new Intent(getActivity(), LanguageKnownActivity.class);
                startActivity(intent5);
                break;
            case R.id.cv_desired_job:
                Intent intent6 = new Intent(getActivity(), DesiredJobActivity.class);
                startActivity(intent6);
                break;
            case R.id.cv_upload_resume:
                Intent intent7 = new Intent(getActivity(), UploadResumeActivity.class);
                startActivity(intent7);
                break;
            case R.id.cv_education:
                Intent intent8 = new Intent(getActivity(), EducationActivity.class);
                startActivity(intent8);
                break;
        }
    }
}
