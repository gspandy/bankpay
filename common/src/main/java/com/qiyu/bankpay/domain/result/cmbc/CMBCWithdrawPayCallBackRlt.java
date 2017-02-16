package com.qiyu.bankpay.domain.result.cmbc;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/9.
 */
public class CMBCWithdrawPayCallBackRlt extends CMBCBaseRlt implements Serializable {
    //实际提现金额
    private String drawAmount;
    //提现手续费
    private String drawFee;
    //交易手续费
    private String tradeFee;
    //合作方请求流水
    private String reqMsgId;
    //对账日期
    private String settleDate;

    public String getTradeFee() {
        return tradeFee;
    }

    public void setTradeFee(String tradeFee) {
        this.tradeFee = tradeFee;
    }

    public String getDrawAmount() {
        return drawAmount;
    }

    public void setDrawAmount(String drawAmount) {
        this.drawAmount = drawAmount;
    }

    public String getDrawFee() {
        return drawFee;
    }

    public void setDrawFee(String drawFee) {
        this.drawFee = drawFee;
    }

    public String getReqMsgId() {
        return reqMsgId;
    }

    public void setReqMsgId(String reqMsgId) {
        this.reqMsgId = reqMsgId;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }
}
