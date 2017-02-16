package com.qiyu.bankpay.domain.result.cmbc;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/19.
 */
public class CMBCPublicNumPayRlt extends  CMBCBaseRlt implements Serializable {
    //微信jsapi字符串
    private String wxjsapiStr;
    //支付宝支付渠道流水
    private String channelNo;

    public String getWxjsapiStr() {
        return wxjsapiStr;
    }

    public void setWxjsapiStr(String wxjsapiStr) {
        this.wxjsapiStr = wxjsapiStr;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }
}
