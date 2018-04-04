package com.aig.advanceinnovationgroup.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.model.LanguageDetail;
import com.aig.advanceinnovationgroup.model.ViewSkillsDatum;

import java.util.List;

/**
 * Created by admin on 4/2/2018.
 */

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.MyHolder> {

    private Context context;
    private List<LanguageDetail> languageDetails;

    public LanguageAdapter(Context context, List<LanguageDetail> languageDetails){
        this.context = context;
        this.languageDetails = languageDetails;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.language_single_row, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        LanguageDetail languageDetail = languageDetails.get(position);

        holder.launguageTV.setText(languageDetail.getLanguage());
        holder.proficientLevelTV.setText(languageDetail.getProficiency());
        holder.readTV.setText(languageDetail.getRead());
        holder.writeTV.setText(languageDetail.getWrite());
        holder.speakTV.setText(languageDetail.getSpeak());

    }

    @Override
    public int getItemCount() {
        return languageDetails.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private TextView launguageTV, proficientLevelTV, readTV, writeTV, speakTV;
        public MyHolder(View itemView) {
            super(itemView);

            launguageTV = (TextView) itemView.findViewById(R.id.tv_language);
            proficientLevelTV = (TextView) itemView.findViewById(R.id.tv_proficient_level);
            readTV = (TextView) itemView.findViewById(R.id.tv_read);
            writeTV = (TextView) itemView.findViewById(R.id.tv_write);
            speakTV = (TextView) itemView.findViewById(R.id.tv_speak);
        }
    }
}
