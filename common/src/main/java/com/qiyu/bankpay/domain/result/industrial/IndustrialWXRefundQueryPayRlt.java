package com.qiyu.bankpay.domain.result.industrial;

import com.qiyu.bankpay.domain.result.cttic.CTTICBaseRlt;

import java.io.Serializable;

/**
 * Created by Qidi on 2016/11/22.
 */
public class IndustrialWXRefundQueryPayRlt extends CTTICBaseRlt implements Serializable {
    //设备号
    private String device_info;
    //威富通订单号
    private String transaction_id;
    //商户订单号
    private String out_trade_no;
    //退款笔数
    private String refund_count;
    //商户退款单号
    private String out_refund_no_$n;
    //威富通退款单号
    private String refund_id_$n;
    //退款渠道
    private String refund_channel_$n;
    //退款金额
    private String refund_fee_$n;
    //现金券退款金额
    private String coupon_refund_fee_$n;
    //退款时间
    private String refund_time_$n;
    //退款状态
    private String refund_status_$n;

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

    public String getRefund_count() {
        return refund_count;
    }

    public void setRefund_count(String refund_count) {
        this.refund_count = refund_count;
    }

    public String getOut_refund_no_$n() {
        return out_refund_no_$n;
    }

    public void setOut_refund_no_$n(String out_refund_no_$n) {
        this.out_refund_no_$n = out_refund_no_$n;
    }

    public String getRefund_id_$n() {
        return refund_id_$n;
    }

    public void setRefund_id_$n(String refund_id_$n) {
        this.refund_id_$n = refund_id_$n;
    }

    public String getRefund_channel_$n() {
        return refund_channel_$n;
    }

    public void setRefund_channel_$n(String refund_channel_$n) {
        this.refund_channel_$n = refund_channel_$n;
    }

    public String getRefund_fee_$n() {
        return refund_fee_$n;
    }

    public void setRefund_fee_$n(String refund_fee_$n) {
        this.refund_fee_$n = refund_fee_$n;
    }

    public String getCoupon_refund_fee_$n() {
        return coupon_refund_fee_$n;
    }

    public void setCoupon_refund_fee_$n(String coupon_refund_fee_$n) {
        this.coupon_refund_fee_$n = coupon_refund_fee_$n;
    }

    public String getRefund_time_$n() {
        return refund_time_$n;
    }

    public void setRefund_time_$n(String refund_time_$n) {
        this.refund_time_$n = refund_time_$n;
    }

    public String getRefund_status_$n() {
        return refund_status_$n;
    }

    public void setRefund_status_$n(String refund_status_$n) {
        this.refund_status_$n = refund_status_$n;
    }
}