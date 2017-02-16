package com.qiyu.bankpay.service;

import com.qiyu.bankpay.domain.result.CallBackRlt;
import com.qiyu.bankpay.domain.result.kft.*;
import com.qiyu.common.result.ModelResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.SortedMap;

/**
 * Created by Qidi on 2016/11/24.
 */
public interface KFTPayService  {

    Logger logger = LoggerFactory.getLogger(KFTPayService.class);
    /**
     * 生成收款二维码
     * @param map
     * @return
     * @throws Exception
     */
    ModelResult<CallBackRlt> generateQRcode(SortedMap<String,String> map)throws Exception ;



    /**
     * 下单
     * @param map
     * @return
     * @throws Exception
     */
    ModelResult<KFTPayRlt> kftPay(SortedMap<String,String> map)throws Exception ;

    /**
     * 交易查询
     * @param map
     * @return
     * @throws Exception
     */
    ModelResult kftQuery(SortedMap<String,String> map) throws  Exception;

    /**
     * 退款
     * @param map
     * @return
     * @throws Exception
     */
    ModelResult kftRefund(SortedMap<String,String> map) throws  Exception;
    /**
     * 获取对账文件
     * @param map
     * @return
     * @throws Exception
     */
    ModelResult kftDownload(SortedMap<String,String> map) throws  Exception;
    /**
     * 回调
     * @param map
     * @return
     * @throws Exception
     */
    ModelResult<KFTPayCallBackRlt> kftPayCallBack(Map<String,String> map,String key)throws Exception ;

    /**
     * 商户信息登记
     * @param map
     * @return
     * @throws Exception
     */
    ModelResult<KFTMerchantRegisterRlt> kftMerchantRegist(SortedMap<String,String> map)throws Exception;

    /**
     * 商户信息修改
     * @param map
     * @return
     * @throws Exception
     */
    ModelResult<KFTMerchantModifyRlt> kftMerchantModify(SortedMap<String,String> map)throws Exception;
    /**
     * 商户状态查询
     * @param map
     * @return
     * @throws Exception
     */
    ModelResult<KFTMerchantStateQueryRlt> kftMerchantStateQuery(SortedMap<String,String> map)throws Exception;
}
