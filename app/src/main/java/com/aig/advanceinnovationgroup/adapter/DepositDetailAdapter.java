package com.aig.advanceinnovationgroup.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.model.DepositFeeDetail;

import java.util.List;

/**
 * Created by admin on 3/21/2018.
 */

public class DepositDetailAdapter extends RecyclerView.Adapter<DepositDetailAdapter.DepositViewHolder> {

    private Context context;
    private List<DepositFeeDetail> depositFeeDetailList;

    public DepositDetailAdapter(Context context, List<DepositFeeDetail> depositFeeDetailList) {
            this.context = context;
            this.depositFeeDetailList = depositFeeDetailList;
    }

    @Override
    public DepositViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deposit_detail_single_row, parent, false);

        return new DepositViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DepositViewHolder holder, int position) {

        DepositFeeDetail feeDetail = depositFeeDetailList.get(position);

        holder.dateTV.setText(feeDetail.getFeeDate());
        holder.amountTV.setText(context.getString(R.string.Rs)+" "+feeDetail.getFeeMount());
        holder.paymentTV.setText(feeDetail.getPaymentMode());
        holder.chequeTV.setText(feeDetail.getChequeNo());
        holder.rctNoTV.setText(feeDetail.getReceiptNo());

    }

    @Override
    public int getItemCount() {
        return depositFeeDetailList.size();
    }

    public class DepositViewHolder extends RecyclerView.ViewHolder {

        private TextView dateTV, amountTV, paymentTV, chequeTV, rctNoTV;

        public DepositViewHolder(View itemView) {
            super(itemView);

            dateTV = (TextView) itemView.findViewById(R.id.tv_date);
            amountTV = (TextView) itemView.findViewById(R.id.tv_amount);
            paymentTV = (TextView) itemView.findViewById(R.id.tv_payment_mode);
            chequeTV = (TextView) itemView.findViewById(R.id.tv_cheque_no);
            rctNoTV = (TextView) itemView.findViewById(R.id.tv_rct_no);
        }
    }
}
