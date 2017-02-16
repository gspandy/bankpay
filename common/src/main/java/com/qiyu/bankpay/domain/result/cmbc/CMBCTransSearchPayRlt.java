package com.qiyu.bankpay.domain.result.cmbc;

import java.io.Serializable;

/**
 * Created by Qidi on 2016/11/18.
 */
public class CMBCTransSearchPayRlt extends  CMBCBaseRlt implements Serializable {
    //原交易应答类型
    private String oriRespType;
    //原交易应答码
    private String oriRespCode;
    //原交易应答描述
    private String oriRespMsg;
    //订单金额
    private String totalAmount;
    //买家实付金额
    private String buyerPayAmount;
    //积分支付金额
    private String pointAmount;
    //商户门店编号
    private String storeId;
    //商户机具终端编号
    private String terminalId;
    //买家编号
    private String buyerId;
    //买家帐号
    private String buyerAccount;
    //对账日期
    private String settleDate;
    //清算撤销标识
    private String isClearOrCancel;
    //支付渠道流水
    private String channelNo;
    //交易支付时间
    private String payTime;

    public String getOriRespType() {
        return oriRespType;
    }

    public void setOriRespType(String oriRespType) {
        this.oriRespType = oriRespType;
    }

    public String getOriRespCode() {
        return oriRespCode;
    }

    public void setOriRespCode(String oriRespCode) {
        this.oriRespCode = oriRespCode;
    }

    public String getOriRespMsg() {
        return oriRespMsg;
    }

    public void setOriRespMsg(String oriRespMsg) {
        this.oriRespMsg = oriRespMsg;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPointAmount() {
        return pointAmount;
    }

    public void setPointAmount(String pointAmount) {
        this.pointAmount = pointAmount;
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

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerAccount() {
        return buyerAccount;
    }

    public void setBuyerAccount(String buyerAccount) {
        this.buyerAccount = buyerAccount;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    public String getIsClearOrCancel() {
        return isClearOrCancel;
    }

    public void setIsClearOrCancel(String isClearOrCancel) {
        this.isClearOrCancel = isClearOrCancel;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getBuyerPayAmount() {
        return buyerPayAmount;
    }

    public void setBuyerPayAmount(String buyerPayAmount) {
        this.buyerPayAmount = buyerPayAmount;
    }
}
