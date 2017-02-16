package com.qiyu.bankpay.domain.result.industrial;

import com.qiyu.bankpay.domain.result.cttic.CTTICBaseRlt;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/23.
 */
public class IndustrialQueryPayRlt extends IndustrialBaseRlt implements Serializable {
    //交易状态
    private String trade_state;
    //交易类型
    private String trade_type;
    //威富通订单
    private String transaction_id;
    //第三方订单号
    private String out_transaction_id;
    //商户订单号
    private String out_trade_no;
    //总金额
    private String total_fee;
    //现金券金额
    private String coupon_fee;
    //货币种类
    private String fee_type;
    //附加信息
    private String attach;
    //付款银行
    private String bank_type;
    //银行订单号
    private String bank_billno;
    //支付完成时间
    private String time_end;

    public String getTrade_state() {
        return trade_state;
    }

    public void setTrade_state(String trade_state) {
        this.trade_state = trade_state;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }


    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_transaction_id() {
        return out_transaction_id;
    }

    public void setOut_transaction_id(String out_transaction_id) {
        this.out_transaction_id = out_transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getCoupon_fee() {
        return coupon_fee;
    }

    public void setCoupon_fee(String coupon_fee) {
        this.coupon_fee = coupon_fee;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public String getBank_billno() {
        return bank_billno;
    }

    public void setBank_billno(String bank_billno) {
        this.bank_billno = bank_billno;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }
}