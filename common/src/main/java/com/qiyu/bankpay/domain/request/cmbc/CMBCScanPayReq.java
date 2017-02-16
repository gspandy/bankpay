package com.qiyu.bankpay.domain.request.cmbc;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Qidi on 2016/11/17.
 * 扫码支付请求
 */
public class CMBCScanPayReq extends CMBCBaseReq implements Serializable {
    //银行商户编码
    private String merchantCode;

    //订单金额
    private String totalAmount;

    //订单标题
    private String subject;

    //子商户微信公众账号ID
    private String subAppid;
    //订单描述
    private String desc;
    //商户操作员编号
    private String operatorId;

    //商户门店编号
    private String storeId;
    //商户机具终端编号
    private String terminalId;
    //指定支付方式
    private String limitPay;
    //source信息
    private String source;

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubAppid() {
        return subAppid;
    }

    public void setSubAppid(String subAppid) {
        this.subAppid = subAppid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public String getLimitPay() {
        return limitPay;
    }

    public void setLimitPay(String limitPay) {
        this.limitPay = limitPay;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
