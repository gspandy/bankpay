package com.qiyu.bankpay.service;

import com.alibaba.fastjson.JSONObject;
import com.qiyu.bankpay.domain.constant.Constant;
import com.qiyu.bankpay.domain.request.CallBackReq;
import com.qiyu.bankpay.domain.request.cmbc.*;
import com.qiyu.bankpay.domain.result.CallBackRlt;
import com.qiyu.bankpay.domain.result.cmbc.*;
import com.qiyu.common.result.ModelResult;
import com.qiyu.common.utils.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


/**
 * Created by Qidi on 2016/11/14.
 * 民生支付接口
 */

public interface CMBCPayService {

    ModelResult<CMBCMerchantSignRlt> merchantSign(CMBCMerchantSignReq cmbcMerchantSignReq) throws Exception ;

    /**
     * 扫码支付
     *
     * @param cmbcScanPayReq
     * @return
     */
    ModelResult<CMBCScanPayRlt> scanPay(CMBCScanPayReq cmbcScanPayReq) throws Exception ;

    /**
     * 扫码/条码支付回调
     *
     * @param callBackReq
     * @return
     */
    ModelResult<CallBackRlt> scanPayCallBack(CallBackReq callBackReq) throws Exception ;


    /**
     * 条码支付
     *
     * @param cmbcBarCodePayReq
     * @return
     */
    ModelResult<CMBCBarCodePayRlt> barCodePay(CMBCBarCodePayReq cmbcBarCodePayReq) throws Exception ;


    /**
     * 申请退款
     *
     * @param cmbcReFundAmountReq
     * @return
     * @throws Exception
     */
    ModelResult<CMBCReFundAmountRlt> reFundAmountPay(CMBCReFundAmountReq cmbcReFundAmountReq) throws Exception ;

    /**
     * 撤销交易
     *
     * @param cancelPayReq
     * @return
     * @throws Exception
     */
    ModelResult<CMBCCancelPayRlt> canclePay(CMBCCancelPayReq cancelPayReq) throws Exception ;

    /**
     * 交易查询（某一笔）
     *
     * @param cmbcTransSearchPayReq
     * @return
     * @throws Exception
     */
    ModelResult<CMBCTransSearchPayRlt> transSearchPay(CMBCTransSearchPayReq cmbcTransSearchPayReq) throws Exception ;

    /**
     * 商户查询
     *
     * @param cmbcMerchantSearchPayReq
     * @return
     * @throws Exception
     */
    ModelResult<CMBCMerchantSearchPayRlt> merchantSearchPay(CMBCMerchantSearchPayReq cmbcMerchantSearchPayReq) throws Exception ;

    /**
     * 商户修改
     *
     * @param cmbcMerchantUpdatePayReq
     * @return
     * @throws Exception
     */
    ModelResult<CMBCMerchantUpdatePayRlt> merchantUpdatePay(CMBCMerchantUpdatePayReq cmbcMerchantUpdatePayReq) throws Exception ;

    /**
     * 公众号、服务窗支付
     *
     * @param cmbcPublicNumPayReq
     * @return
     * @throws Exception
     */
    ModelResult<CMBCPublicNumPayRlt> publicNumPay(CMBCPublicNumPayReq cmbcPublicNumPayReq) throws Exception ;

    /**
     * 获取对账文件
     *
     * @param cmbcBalanceAccountPayReq
     * @return
     * @throws Exception
     */
    ModelResult<CMBCBalanceAccountPayRlt> balanceAccountPay(CMBCBalanceAccountPayReq cmbcBalanceAccountPayReq) throws Exception ;

    /**
     * 商户提现接口
     *
     * @param cmbcWithdrawPayReq
     * @return
     * @throws Exception
     */
    ModelResult<CMBCWithdrawPayRlt> withdrawPay(CMBCWithdrawPayReq cmbcWithdrawPayReq) throws Exception ;

    /**
     * 提现交易查询
     *
     * @param cmbcWithdrawSearchPayReq
     * @return
     * @throws Exception
     */
    ModelResult<CMBCWithdrawSearchPayRlt> withdrawSearchPay(CMBCWithdrawSearchPayReq cmbcWithdrawSearchPayReq) throws Exception;

    /**
     * 商户提现异步通知接口
     *
     * @param callBackReq
     * @return
     * @throws Exception
     */
    ModelResult<CMBCWithdrawPayCallBackRlt> withdrawPayCallBack(CallBackReq callBackReq) throws Exception ;

}