package com.qiyu.bankpay.domain.request.cmbc;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/18.
 */
public class CMBCReFundAmountReq extends CMBCBaseReq implements Serializable {
    //原支付交易流水号
    private String  oriReqMsgId;
    //退款金额
    private String  refundAmount;
    //退款原因
    private String refundReason;
    //商户操作员编号
    private String operatorId;
    //商户门店编号
    private String storeId;
    //商户机具终端编号
    private String terminalId;

    public String getOriReqMsgId() {
        return oriReqMsgId;
    }

    public void setOriReqMsgId(String oriReqMsgId) {
        this.oriReqMsgId = oriReqMsgId;
    }

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }
}
