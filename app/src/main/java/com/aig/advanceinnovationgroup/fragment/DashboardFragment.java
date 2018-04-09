package com.aig.advanceinnovationgroup.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.activity.ArticlesActivity;
import com.aig.advanceinnovationgroup.activity.MainActivity;


public class DashboardFragment extends Fragment implements View.OnClickListener {

    private CardView cv_article, cv_discussion, cv_project, cv_video, cv_job, cv_help;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        cv_article = (CardView) view.findViewById(R.id.cv_articles);
        cv_discussion = (CardView) view.findViewById(R.id.cv_discussion);
        cv_project = (CardView) view.findViewById(R.id.cv_project);
        cv_video = (CardView) view.findViewById(R.id.cv_video);
        cv_job = (CardView) view.findViewById(R.id.cv_job);
        cv_help = (CardView) view.findViewById(R.id.cv_help);

        cv_article.setOnClickListener(this);
        cv_discussion.setOnClickListener(this);
        cv_project.setOnClickListener(this);
        cv_video.setOnClickListener(this);
        cv_job.setOnClickListener(this);
        cv_help.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cv_articles:
                Intent intent = new Intent(getActivity(), ArticlesActivity.class);
                intent.putExtra("article", "article");
                startActivity(intent);
                break;
            case R.id.cv_discussion:
                Intent intent1 = new Intent(getActivity(), ArticlesActivity.class);
                intent1.putExtra("article", "discussion");
                startActivity(intent1);
                break;
            case R.id.cv_project:
                Intent intent2 = new Intent(getActivity(), ArticlesActivity.class);
                intent2.putExtra("article", "project");
                startActivity(intent2);
                break;
            case R.id.cv_video:
                Intent intent3 = new Intent(getActivity(), ArticlesActivity.class);
                intent3.putExtra("article", "video");
                startActivity(intent3);
                break;
            case R.id.cv_job:
                Intent intent4 = new Intent(getActivity(), ArticlesActivity.class);
                intent4.putExtra("article", "job");
                startActivity(intent4);
                break;
            case R.id.cv_help:
                ((MainActivity)getActivity()).changeFragment(17);
                break;
        }
    }
}
