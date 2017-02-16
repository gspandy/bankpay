package com.qiyu.bankpay.domain.result.kft;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/11/28.
 */
public class KFTPayRlt extends  KFTBaseRlt implements Serializable {
    //二维码串
    private String codeUrl;
    //支付码信息
    private String payCode;
    //商户订单号
    private String outTradeNo;
    //支付渠道订单号
    private String outChannelNo;
    //交易金额
    private BigDecimal amount;
    //交易时间
    private String transTime;

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOutChannelNo() {
        return outChannelNo;
    }

    public void setOutChannelNo(String outChannelNo) {
        this.outChannelNo = outChannelNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }
}
