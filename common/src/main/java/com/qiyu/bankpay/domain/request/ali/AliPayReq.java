package com.qiyu.bankpay.domain.request.ali;


import java.io.Serializable;

/**
 * Created by Qidi on 2017/1/4.
 */
public class AliPayReq implements Serializable{
    //商户号
    private Long merchantId;
    //餐馆id
    private Long restaurantId;

    private String outTradeNo;
    //订单标题
    private String subject;
    //订单金额
    private String totalAmount;
    //授权码
    private String authCode;
    //支付宝门店编号
    private String reputationStoreId;
    //口碑门店id
    private String aliStoreId;
    //appid （会放到缓存里）
    private String appid;
    //私钥（会放到缓存里）
    private String privateKey;
    //公钥（会放到缓存里）
    private String publicKey;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getReputationStoreId() {
        return reputationStoreId;
    }

    public void setReputationStoreId(String reputationStoreId) {
        this.reputationStoreId = reputationStoreId;
    }

    public String getAliStoreId() {
        return aliStoreId;
    }

    public void setAliStoreId(String aliStoreId) {
        this.aliStoreId = aliStoreId;
    }
}
