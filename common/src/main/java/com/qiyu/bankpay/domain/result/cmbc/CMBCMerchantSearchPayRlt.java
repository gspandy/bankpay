package com.qiyu.bankpay.domain.result.cmbc;

import java.io.Serializable;

/**
 * Created by Qidi on 2016/11/16.
 */
public class CMBCMerchantSearchPayRlt extends CMBCBaseRlt implements Serializable{
    //合作方商户编码
    private String merchantId;
    //银行商户编码
    private String merchantCode;
    //原交易应答类型
    private String oriRespType;
    //原交易应答码
    private String oriRespCode;
    //原交易应答描述
    private String oriRespMsg;
    //支付通道
    private String payWay;

    //商户名称
    private String merchantName;
    //商户简称
    private String shortName;
    //商户地址
    private String merchantAddress;
    //客服电话
    private String servicePhone;
    //组织机构代码
    private String orgCode;
    //联系人名称
    private String contactName;
    //联系人电话
    private String contactPhone;
    //联系人手机号
    private String contactMobile;
    //联系人邮箱
    private String contactEmail;
    //经营类目
    private String category;
    //商户身份证信息
    private String idCard;
    //商户营业执照号
    private String merchantLicense;
    //备注说明
    private String remark;
    //状态
    private String status;
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

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

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

    public void setOriRespMsg(String oriRespsg) {
        this.oriRespMsg = oriRespMsg;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
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

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getMerchantLicense() {
        return merchantLicense;
    }

    public void setMerchantLicense(String merchantLicense) {
        this.merchantLicense = merchantLicense;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
