package com.qiyu.bankpay.service;

import com.qiyu.bankpay.domain.request.ali.AliPayReq;
import com.qiyu.common.result.ModelResult;

/**
 * Created by Qidi on 2017/1/4.
 * 支付宝支付接口
 */
public interface AliPayService {
    /**
     * 条码支付
     * @param aliPayReq
     * @return
     */
    ModelResult barCodePay(AliPayReq aliPayReq);
}
