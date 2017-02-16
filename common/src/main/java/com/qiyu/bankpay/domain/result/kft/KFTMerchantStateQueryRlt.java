package com.qiyu.bankpay.domain.result.kft;

import java.io.Serializable;

/**
 * Created by Qidi on 2017/1/7.
 */
public class KFTMerchantStateQueryRlt extends KFTBaseRlt implements Serializable {
    //代理商号
    private String channelNo;
    //激活状态
    private String activationStatus;
    //商户名称
    private String merchantName;
    //商户号
    private String merchantNo;

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(String activationStatus) {
        this.activationStatus = activationStatus;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }
}
