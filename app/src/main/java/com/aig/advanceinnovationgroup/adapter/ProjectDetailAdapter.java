package com.aig.advanceinnovationgroup.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.model.DepositFeeDetail;
import com.aig.advanceinnovationgroup.model.ProjectView;

import java.util.List;

/**
 * Created by admin on 3/21/2018.
 */

public class ProjectDetailAdapter extends RecyclerView.Adapter<ProjectDetailAdapter.DepositViewHolder> {

    private Context context;
    private List<ProjectView> projectViewList;

    public ProjectDetailAdapter(Context context, List<ProjectView> projectViewList) {
            this.context = context;
            this.projectViewList = projectViewList;
    }

    @Override
    public DepositViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_single_row, parent, false);

        return new DepositViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DepositViewHolder holder, int position) {

        ProjectView projectView = projectViewList.get(position);

        holder.titleTV.setText(projectView.getProjectName()+" "+projectView.getPstatus());
        holder.startDateTV.setText(projectView.getPStartDate());
        holder.endDateTV.setText(projectView.getPCloseDate());


    }

    @Override
    public int getItemCount() {
        return projectViewList.size();
    }

    public class DepositViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTV, startDateTV, endDateTV;

        public DepositViewHolder(View itemView) {
            super(itemView);

            titleTV = (TextView) itemView.findViewById(R.id.tv_title);
            startDateTV = (TextView) itemView.findViewById(R.id.tv_start_date);
            endDateTV = (TextView) itemView.findViewById(R.id.tv_end_date);

        }
    }
}
