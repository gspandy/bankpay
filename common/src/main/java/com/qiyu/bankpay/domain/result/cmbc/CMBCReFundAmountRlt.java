package com.qiyu.bankpay.domain.result.cmbc;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/18.
 */
public class CMBCReFundAmountRlt extends  CMBCBaseRlt implements Serializable {
    //退款金额
    private String refundAmount;
    //实际退款金额
    private String refundFee;
    //可退金额
    private String backFee;
    //退款支付时间
    private String refundTime;
    //对账日期
    private String settleDate;
    //买家编号
    private String buyerId;
    //清算撤销标识
    private String isClearOrCancel;

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(String refundFee) {
        this.refundFee = refundFee;
    }

    public String getBackFee() {
        return backFee;
    }

    public void setBackFee(String backFee) {
        this.backFee = backFee;
    }

    public String getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(String refundTime) {
        this.refundTime = refundTime;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getIsClearOrCancel() {
        return isClearOrCancel;
    }

    public void setIsClearOrCancel(String isClearOrCancel) {
        this.isClearOrCancel = isClearOrCancel;
    }
}
