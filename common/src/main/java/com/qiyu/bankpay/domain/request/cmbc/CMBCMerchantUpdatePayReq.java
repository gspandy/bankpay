package com.qiyu.bankpay.domain.request.cmbc;

import java.io.Serializable;

/**
 * Created by Qidi on 2016/11/19.
 */
public class CMBCMerchantUpdatePayReq extends CMBCBaseReq implements Serializable {
    //合作方商户编码
    private String merchantId;
    //商户简称
    private String shortName;
    //商户地址
    private String merchantAddress;
    //客服电话
    private String servicePhone;
    //联系人名称
    private String contactName;
    //收款人账户号
    private String accNo;
    //收款人账户名
    private String accName;
    //收款人账户联行号
    private String bankType;
    //收款人账户开户行名称
    private String bankName;
    //T0单笔提现手续费
    private String t0drawFee;
    //T0交易手续费扣率
    private String t0tradeRate;
    //T1单笔提现手续费
    private String t1drawFee;
    //T1交易手续费扣率
    private String t1tradeRate;
    //支付渠道商户编码
    private String channelMerchantCode;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getMerchantAddress() {
        return merchantAddress;
    }

    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getT0drawFee() {
        return t0drawFee;
    }

    public void setT0drawFee(String t0drawFee) {
        this.t0drawFee = t0drawFee;
    }

    public String getT0tradeRate() {
        return t0tradeRate;
    }

    public void setT0tradeRate(String t0tradeRate) {
        this.t0tradeRate = t0tradeRate;
    }

    public String getT1drawFee() {
        return t1drawFee;
    }

    public void setT1drawFee(String t1drawFee) {
        this.t1drawFee = t1drawFee;
    }

    public String getT1tradeRate() {
        return t1tradeRate;
    }

    public void setT1tradeRate(String t1tradeRate) {
        this.t1tradeRate = t1tradeRate;
    }

    public String getChannelMerchantCode() {
        return channelMerchantCode;
    }

    public void setChannelMerchantCode(String channelMerchantCode) {
        this.channelMerchantCode = channelMerchantCode;
    }
}
