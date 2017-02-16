package com.qiyu.bankpay.domain.result.cmbc;

import com.qiyu.bankpay.domain.request.cmbc.CMBCBaseReq;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/9.
 */
public class CMBCWithdrawSearchPayRlt extends CMBCBaseRlt implements Serializable {
    //原交易应答类型
    private String oriRespType;
    //原交易应答码
    private String oriRespCode;
    //原交易应答描述
    private String oriRespMsg;
    //实际提现金额
    private String drawAmount;
    //提现手续费
    private String drawFee;
    //交易手续费
    private String tradeFee;
    //对账日期
    private String settleDate;

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

    public String getTradeFee() {
        return tradeFee;
    }

    public void setTradeFee(String tradeFee) {
        this.tradeFee = tradeFee;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }
}