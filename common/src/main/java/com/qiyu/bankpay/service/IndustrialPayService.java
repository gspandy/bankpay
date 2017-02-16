package com.qiyu.bankpay.service;

import com.alibaba.fastjson.JSONObject;
import com.qiyu.bankpay.domain.result.industrial.*;
import com.qiyu.common.result.ModelResult;
import com.qiyu.common.utils.UuidUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.SortedMap;

/**
 * Created by Qidi on 2016/11/14.
 * 兴业银行支付接口
 */
public interface IndustrialPayService  {

    /**
     * 支付宝扫码支付
     *
     * @param map
     * @return
     * @throws Exception
     */
    ModelResult scanZFBPay(SortedMap<String, String> map) throws Exception ;

    /**
     * 扫码支付回调
     *
     * @param resString
     * @param key
     * @return
     * @throws Exception
     */
    ModelResult scanZFBPayCallBack(String resString,String key) throws Exception ;


    /**
     * 退款(同中信小额支付退款)
     * @param map
     * @return
     * @throws Exception
     */
    ModelResult<IndustrialRefundPayRlt> industrialZFBRefundPay(SortedMap<String,String> map)throws Exception;

    /**
     *交易查询 (同中信小额支付交易查询）
     * @param map
     * @return
     * @throws Exception
     */
    ModelResult<IndustrialQueryPayRlt>  industrialZFBQueryPay(SortedMap<String,String> map)throws Exception;



    /**
     * 微信扫码支付
     *
     * @param map
     * @return
     * @throws Exception
     */
    ModelResult scanWXPay(SortedMap<String, String> map) throws Exception ;


    /**
     * 扫码支付回调
     *
     * @param resString
     * @param key
     * @return
     * @throws Exception
     */
    ModelResult scanWXPayCallBack(String resString,String key) throws Exception ;

    /**
     * 兴业微信交易查询
     * @param map
     * @return
     * @throws Exception
     */
    ModelResult<IndustrialWXQueryPayRlt>  industrialWXQueryPay(SortedMap<String,String> map)throws Exception;
    /**
     * 兴业微信退款
     * @param map
     * @return
     * @throws Exception
     */
    ModelResult<IndustrialRefundPayRlt> industrialWXRefundPay(SortedMap<String,String> map)throws Exception;

    /**
     * 退款查询
     * @param map
     * @return
     * @throws Exception
     */
    ModelResult<IndustrialWXRefundQueryPayRlt> industrialRefundQueryPay(SortedMap<String,String> map)throws Exception;

    /**
     * 关闭订单
     * @param map
     * @return
     * @throws Exception
     */
    ModelResult industrialClosePay(SortedMap<String,String> map)throws Exception;

    /**
     * 初始化请求接口
     * @param map
     * @return
     * @throws Exception
     */
    ModelResult industrialInitRequestPay(SortedMap<String,String> map,String key)throws Exception;

    /**
     * js支付通知
     * @param resString
     * @param key
     * @return
     * @throws Exception
     */
    ModelResult industrialJsCallBack(String resString,String key)throws Exception;


    /**
     * 扫码支付
     *
     * @param map
     * @param key
     * @return
     * @throws Exception
     */
    ModelResult scanPay(SortedMap<String, String> map,String key) throws Exception ;

    /**
     * 支付回调
     * @param resString
     * @param key
     * @return
     * @throws Exception
     */
    ModelResult industrialCallBack(String resString,String key)throws Exception;
}
