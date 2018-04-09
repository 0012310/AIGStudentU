package com.aig.advanceinnovationgroup.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.model.ExamDetail;

import java.util.List;

/**
 * Created by admin on 4/9/2018.
 */

public class PrepaExamAdapter extends RecyclerView.Adapter<PrepaExamAdapter.MyViewHolder> {

    private Context context;
    private List<ExamDetail> examDetailList;

    public PrepaExamAdapter(Context context, List<ExamDetail> examDetailList){
        this.context = context;
        this.examDetailList = examDetailList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prepa_exam_single_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ExamDetail examDetail = examDetailList.get(position);
        holder.prepaExamTV.setText(examDetail.getCourseName()+" ("+examDetail.getExamDate()+" )");
    }

    @Override
    public int getItemCount() {
        return examDetailList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView prepaExamTV;

        public MyViewHolder(View itemView) {
            super(itemView);

            prepaExamTV = (TextView) itemView.findViewById(R.id.tv_exam_name);
        }
    }
}
