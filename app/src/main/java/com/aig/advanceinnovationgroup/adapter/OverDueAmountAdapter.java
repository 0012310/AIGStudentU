package com.aig.advanceinnovationgroup.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.model.OverdueFeeDetail;

import java.util.List;

/**
 * Created by admin on 3/21/2018.
 */

public class OverDueAmountAdapter extends RecyclerView.Adapter<OverDueAmountAdapter.OverViewHolder> {

    private Context context;
    private List<OverdueFeeDetail> overdueFeeDetailList;

    public OverDueAmountAdapter(Context context, List<OverdueFeeDetail> overDueAmountList) {
        this.context = context;
        this.overdueFeeDetailList = overDueAmountList;

    }

    @Override
    public OverViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.amount_single_row, parent, false);

        return new OverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OverViewHolder holder, int position) {

        OverdueFeeDetail overdueFeeDetail = overdueFeeDetailList.get(position);

        holder.dateTV.setText(overdueFeeDetail.getInstallmentDate());
        holder.amountTV.setText(context.getString(R.string.Rs)+" "+overdueFeeDetail.getNetAmount());

    }

    @Override
    public int getItemCount() {
        return overdueFeeDetailList.size();
    }

    public class OverViewHolder extends RecyclerView.ViewHolder {

        private TextView dateTV, amountTV;

        public OverViewHolder(View itemView) {
            super(itemView);

            dateTV = (TextView) itemView.findViewById(R.id.tv_date);
            amountTV = (TextView) itemView.findViewById(R.id.tv_amount);
        }
    }
}
