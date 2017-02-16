package com.qiyu.bankpay.domain.request.cttic;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/22.
 */
public class CTTICBaseReq implements Serializable {
    //接口类型
    private String service;
    //版本
    private String version;
    //字符集
    private String charset;
    //签名方式
    private String sign_type;
    //上游商户号
    private String mch_id;
    //商户订单号
    private String out_trade_no;
    //签名
    private String sign;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
}
