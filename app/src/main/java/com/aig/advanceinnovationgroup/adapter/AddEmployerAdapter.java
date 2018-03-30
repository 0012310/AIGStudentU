package com.aig.advanceinnovationgroup.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.activity.AddEmployerActivity;
import com.aig.advanceinnovationgroup.model.EmployerDatum;

import java.util.List;

/**
 * Created by admin on 3/30/2018.
 */

public class AddEmployerAdapter extends RecyclerView.Adapter<AddEmployerAdapter.MyHolder> {

    private Context context;
    private List<EmployerDatum> employerData;

    public AddEmployerAdapter(Context context, List<EmployerDatum> employerDataList) {
        this.context = context;
        this.employerData = employerDataList;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_employer, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        EmployerDatum datum = employerData.get(position);

        holder.employerNameTV.setText(datum.getEmployerName());
        holder.employerTypeTV.setText(datum.getEmployerType());
        holder.fromDateTV.setText(datum.getFromMonth()+"/"+datum.getFromYear());
        holder.toDateTV.setText(datum.getToMonth()+"/"+datum.getToYear());
        holder.designationTV.setText(datum.getDesignation());
        holder.jobTypeTV.setText(datum.getJobProfile());

    }

    @Override
    public int getItemCount() {
        return employerData.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private TextView employerNameTV, employerTypeTV, fromDateTV, toDateTV, designationTV, jobTypeTV;

        public MyHolder(View itemView) {
            super(itemView);

            employerNameTV = (TextView) itemView.findViewById(R.id.tv_employer_name);
            employerTypeTV = (TextView) itemView.findViewById(R.id.tv_employer_type);
            fromDateTV = (TextView) itemView.findViewById(R.id.tv_duration_from);
            toDateTV = (TextView) itemView.findViewById(R.id.tv_duration_to);
            designationTV = (TextView) itemView.findViewById(R.id.tv_designation);
            jobTypeTV = (TextView) itemView.findViewById(R.id.tv_job_profile);


        }
    }
}
