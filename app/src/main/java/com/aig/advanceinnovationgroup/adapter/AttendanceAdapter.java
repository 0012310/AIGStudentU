package com.aig.advanceinnovationgroup.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.model.AttendanceData;
import com.aig.advanceinnovationgroup.model.AttendanceDetail;

import java.util.List;

/**
 * Created by admin on 3/21/2018.
 */

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.AttendViewHolder> {

    private Context context;
    private List<AttendanceDetail> attendanceDetailList;

    public AttendanceAdapter(Context context, List<AttendanceDetail> attendanceDetailList) {
        this.context = context;
        this.attendanceDetailList = attendanceDetailList;
    }

    @Override
    public AttendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_single_row, parent, false);
        return new AttendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AttendViewHolder holder, int position) {

        AttendanceDetail detail = attendanceDetailList.get(position);

        holder.dayDateTV.setText(detail.getClassDate());
        holder.timeTV.setText(detail.getTime());
        holder.trainerTV.setText(detail.getTrainerName());
        holder.statusTV.setText(detail.getAttandanceStatus());
        holder.courseContentTV.setText(detail.getCourseContent());

    }

    @Override
    public int getItemCount() {
        return attendanceDetailList.size();
    }

    public class AttendViewHolder extends RecyclerView.ViewHolder {

        private TextView dayDateTV, timeTV, trainerTV, statusTV, courseContentTV;

        public AttendViewHolder(View itemView) {
            super(itemView);

            dayDateTV = (TextView) itemView.findViewById(R.id.tv_day_date);
            timeTV = (TextView) itemView.findViewById(R.id.tv_time);
            trainerTV = (TextView) itemView.findViewById(R.id.tv_trainner);
            statusTV = (TextView) itemView.findViewById(R.id.tv_status);
            courseContentTV = (TextView) itemView.findViewById(R.id.tv_course_content);


        }
    }
}
