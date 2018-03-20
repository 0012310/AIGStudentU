package com.aig.advanceinnovationgroup.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.model.DueFeeDetail;
import com.aig.advanceinnovationgroup.util.DownloadTask;

import java.util.List;

/**
 * Created by admin on 3/19/2018.
 */

public class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.DownloadViewHolder> {

    private Context context;
    private List<DueFeeDetail> dueFeeDetailList ;

    public DownloadAdapter(Context context, List<DueFeeDetail> dueFeeDetailList){
        this.context = context;
        this.dueFeeDetailList = dueFeeDetailList;
    }


    class DownloadViewHolder extends RecyclerView.ViewHolder{

        private TextView courseNameTV, startUpTV;
        private ImageView downloadIV;

        public DownloadViewHolder(View itemView) {
            super(itemView);

            courseNameTV = (TextView) itemView.findViewById(R.id.tv_course_name);
            startUpTV = (TextView) itemView.findViewById(R.id.tv_startup_pack);
            downloadIV = (ImageView) itemView.findViewById(R.id.iv_download);
        }
    }

    @Override
    public DownloadAdapter.DownloadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.download_single_row, parent, false);

        return new DownloadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DownloadAdapter.DownloadViewHolder holder, int position) {

        final DueFeeDetail feeDetail = dueFeeDetailList.get(position);

        holder.courseNameTV.setText(feeDetail.getCourseName());
        holder.startUpTV.setText(feeDetail.getStartupPack());

        holder.downloadIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadTask( context, feeDetail.getUrl().replaceAll(" ", "%20"));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dueFeeDetailList.size();
    }
}
