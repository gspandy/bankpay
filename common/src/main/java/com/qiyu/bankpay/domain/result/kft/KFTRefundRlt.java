package com.qiyu.bankpay.domain.result.kft;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/29.
 */
public class KFTRefundRlt extends  KFTBaseRlt implements Serializable {
    /**
     * 商户退款单号
     */
    private String outRefundNo;
    /**
     * 商户订单号
     */
    private String outTradeNo;
    /**
     * 支付渠道退款单号
     */
    private String channelRefundNo;
    /**
     * 退款状态
     */
    private String refundStatus;

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getChannelRefundNo() {
        return channelRefundNo;
    }

    public void setChannelRefundNo(String channelRefundNo) {
        this.channelRefundNo = channelRefundNo;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }
}
