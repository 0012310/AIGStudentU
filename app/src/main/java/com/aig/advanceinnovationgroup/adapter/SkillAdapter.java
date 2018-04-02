package com.aig.advanceinnovationgroup.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.model.ViewSkillsDatum;

import java.util.List;

/**
 * Created by admin on 4/2/2018.
 */

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.MyHolder> {

    private Context context;
    private List<ViewSkillsDatum> viewSkillsData;

    public SkillAdapter(Context context, List<ViewSkillsDatum> viewSkillsData){
        this.context = context;
        this.viewSkillsData = viewSkillsData;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_skill_single_row, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        ViewSkillsDatum skillsDatum = viewSkillsData.get(position);

        holder.skillTV.setText(skillsDatum.getSkills());
        holder.versionTV.setText(skillsDatum.getVersionUsed());
        holder.lastUsedTV.setText(skillsDatum.getLastUsed());
        holder.experienceTV.setText(skillsDatum.getExpYear()+" Year"+" "+skillsDatum.getExpMonth()+" Month");

    }

    @Override
    public int getItemCount() {
        return viewSkillsData.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private TextView skillTV, versionTV, lastUsedTV, experienceTV;
        public MyHolder(View itemView) {
            super(itemView);

            skillTV = (TextView) itemView.findViewById(R.id.tv_skill);
            versionTV = (TextView) itemView.findViewById(R.id.tv_version);
            lastUsedTV = (TextView) itemView.findViewById(R.id.tv_last_used);
            experienceTV = (TextView) itemView.findViewById(R.id.tv_experience);
        }
    }
}
