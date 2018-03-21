package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 3/21/2018.
 */

public class DepositFeeDetail {

    @SerializedName("fee_date")
    @Expose
    private String feeDate;
    @SerializedName("fee_mount")
    @Expose
    private String feeMount;
    @SerializedName("payment_mode")
    @Expose
    private String paymentMode;
    @SerializedName("cheque_no")
    @Expose
    private String chequeNo;
    @SerializedName("receipt_no")
    @Expose
    private String receiptNo;

    public String getFeeDate() {
        return feeDate;
    }

    public void setFeeDate(String feeDate) {
        this.feeDate = feeDate;
    }

    public String getFeeMount() {
        return feeMount;
    }

    public void setFeeMount(String feeMount) {
        this.feeMount = feeMount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }
}
