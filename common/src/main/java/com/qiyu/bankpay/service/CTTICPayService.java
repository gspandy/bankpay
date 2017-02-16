package com.qiyu.bankpay.service;

import com.qiyu.bankpay.domain.result.cttic.*;
import com.qiyu.common.result.ModelResult;

import java.util.SortedMap;

/**
 * Created by Qidi on 2016/11/14.
 * 中兴银行
 */
public interface CTTICPayService {
    /**
     * 小额支付（扫码枪扫码）
     * @param map
     * @return
     * @throws Exception
     */
    ModelResult microPay(SortedMap<String, String> map) throws Exception ;

    /**
     * 小额支付退款
     * @param map
     * @return
     * @throws Exception
     */
    ModelResult<CTTICMicroRefundPayRlt> microRefundPay(SortedMap<String,String> map)throws Exception;

    /**
     * 小额支付交易查询
     * @param map
     * @return
     * @throws Exception
     */
    ModelResult microQueryPay(SortedMap<String,String> map)throws Exception;
    /**
     * 冲正
     * @param map
     * @return
     * @throws Exception
     */
    ModelResult microRollPay(SortedMap<String,String> map)throws Exception;

    /**
     * 退款查询
     * @param map
     * @return
     * @throws Exception
     */
    ModelResult microRefundQueryPay(SortedMap<String,String> map)throws Exception;
}