package com.aig.advanceinnovationgroup.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.activity.ArticlesActivity;


public class DashboardFragment extends Fragment implements View.OnClickListener {

    private LinearLayout ll_article, ll_discussion, ll_project, ll_video, ll_job;


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
        ll_article = (LinearLayout) view.findViewById(R.id.ll_article);
        ll_discussion = (LinearLayout) view.findViewById(R.id.ll_discussion);
        ll_project = (LinearLayout) view.findViewById(R.id.ll_project);
        ll_video = (LinearLayout) view.findViewById(R.id.ll_video);
        ll_job = (LinearLayout) view.findViewById(R.id.ll_job);

        ll_article.setOnClickListener(this);
        ll_discussion.setOnClickListener(this);
        ll_project.setOnClickListener(this);
        ll_video.setOnClickListener(this);
        ll_job.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_article:
                Intent intent = new Intent(getActivity(), ArticlesActivity.class);
                intent.putExtra("article", "article");
                startActivity(intent);
                break;
            case R.id.ll_discussion:
                Intent intent1 = new Intent(getActivity(), ArticlesActivity.class);
                intent1.putExtra("article", "discussion");
                startActivity(intent1);
                break;
            case R.id.ll_project:
                Intent intent2 = new Intent(getActivity(), ArticlesActivity.class);
                intent2.putExtra("article", "project");
                startActivity(intent2);
                break;
            case R.id.ll_video:
                Intent intent3 = new Intent(getActivity(), ArticlesActivity.class);
                intent3.putExtra("article", "video");
                startActivity(intent3);
                break;
            case R.id.ll_job:
                Intent intent4 = new Intent(getActivity(), ArticlesActivity.class);
                intent4.putExtra("article", "job");
                startActivity(intent4);
                break;
        }
    }
}
