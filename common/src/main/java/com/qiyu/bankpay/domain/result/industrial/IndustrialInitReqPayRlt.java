package com.qiyu.bankpay.domain.result.industrial;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/8.
 */
public class IndustrialInitReqPayRlt extends IndustrialBaseRlt implements Serializable{
    //公众账号ID
    private String appid;
    //商户号
    private String mch_id;
    //设备号
    private String device_info;
    //动态口令
    private String token_id;
    //原生态js 支付信息
    private String pay_info;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    @Override
    public String getMch_id() {
        return mch_id;
    }

    @Override
    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getToken_id() {
        return token_id;
    }

    public void setToken_id(String token_id) {
        this.token_id = token_id;
    }

    public String getPay_info() {
        return pay_info;
    }

    public void setPay_info(String pay_info) {
        this.pay_info = pay_info;
    }
}
