package com.qiyu.bankpay.domain.result.industrial;

import com.qiyu.bankpay.domain.result.cttic.CTTICBaseRlt;

import java.io.Serializable;

/**
 * Created by Qidi on 2016/11/22.
 */
public class IndustrialRefundPayRlt extends IndustrialBaseRlt implements Serializable {
    //设备
    private String device_info;
    //威富通订单
    private String transaction_id;
    //商户订单号
    private String out_trade_no;
    //商户退款单
    private String out_refund_no;
    //退款单号
    private String refund_id;
    //退款渠道
    private String refund_channel;
    //退款金
    private String refund_fee;
    //现金券退款金额
    private String coupon_refund_fee;

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

    public String getRefund_channel() {
        return refund_channel;
    }

    public void setRefund_channel(String refund_channel) {
        this.refund_channel = refund_channel;
    }

    public String getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(String refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getCoupon_refund_fee() {
        return coupon_refund_fee;
    }

    public void setCoupon_refund_fee(String coupon_refund_fee) {
        this.coupon_refund_fee = coupon_refund_fee;
    }
}
