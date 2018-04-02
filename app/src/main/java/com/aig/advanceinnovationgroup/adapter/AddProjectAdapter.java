package com.aig.advanceinnovationgroup.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.model.ProjectDetailData;
import com.aig.advanceinnovationgroup.model.ProjectListDatum;

import java.util.List;

/**
 * Created by admin on 4/2/2018.
 */

public class AddProjectAdapter extends RecyclerView.Adapter<AddProjectAdapter.MyHolder> {

    private Context context;
    private List<ProjectListDatum> projectDetailData;

    public AddProjectAdapter(Context context, List<ProjectListDatum> projectDetailList){
        this.context = context;
        this.projectDetailData = projectDetailList;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_project_single_row, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

      ProjectListDatum listDatum = projectDetailData.get(position);
        holder.employerNameTV.setText(listDatum.getClientName());
        holder.employerTypeTV.setText(listDatum.getProjectType());
        holder.fromDateTV.setText(listDatum.getFromMonth()+"/"+listDatum.getFromYear());
        holder.toDateTV.setText(listDatum.getToMonth()+"/"+listDatum.getToYear());
        holder.designationTV.setText(listDatum.getRole());
        holder.projectTitleTV.setText(listDatum.getProjectTitle());
        holder.projectDescTV.setText(listDatum.getProjectDetails());
        holder.teamSizeTV.setText(listDatum.getTeamSize());
        holder.roleDescTV.setText(listDatum.getRoleDescription());

    }

    @Override
    public int getItemCount() {
        return projectDetailData.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private TextView employerNameTV, employerTypeTV, fromDateTV, toDateTV, designationTV, jobTypeTV, projectTitleTV, projectDescTV, teamSizeTV, roleDescTV;
        public MyHolder(View itemView) {
            super(itemView);

            employerNameTV = (TextView) itemView.findViewById(R.id.tv_employer_name);
            employerTypeTV = (TextView) itemView.findViewById(R.id.tv_employer_type);
            fromDateTV = (TextView) itemView.findViewById(R.id.tv_duration_from);
            toDateTV = (TextView) itemView.findViewById(R.id.tv_duration_to);
            designationTV = (TextView) itemView.findViewById(R.id.tv_designation);
            projectTitleTV = (TextView) itemView.findViewById(R.id.tv_project_title);
            projectDescTV = (TextView) itemView.findViewById(R.id.tv_project_detail);
            teamSizeTV =  (TextView) itemView.findViewById(R.id.tv_team_size);
            roleDescTV =  (TextView) itemView.findViewById(R.id.tv_role_desc);
        }
    }
}
