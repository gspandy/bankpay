package com.qiyu.bankpay.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.qiyu.bankpay.domain.request.ali.AliPayReq;
import com.qiyu.common.result.ModelResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Qidi on 2017/1/4.
 */
public class AliPayServiceImpl extends BasePayService implements AliPayService {
    Logger logger = LoggerFactory.getLogger(AliPayServiceImpl.class);

    @Override
    public ModelResult barCodePay(AliPayReq aliPayReq) {
        ModelResult modelResult = new ModelResult();
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        String bizContent = "{" +
                "    \"out_trade_no\":\"" + aliPayReq.getOutTradeNo() + "\"," +
                "    \"scene\":\"bar_code\"," +
                "    \"auth_code\":\"" + aliPayReq.getAuthCode() + "\"," +
                "    \"subject\":\"" + aliPayReq.getSubject() + "\"," +
                "    \"total_amount\":" + aliPayReq.getTotalAmount() + "," +
                "  }";
        if (aliPayReq.getReputationStoreId() != null && aliPayReq.getAliStoreId() != null) {
            bizContent = "{" +
                    "    \"out_trade_no\":\"" + aliPayReq.getOutTradeNo() + "\"," +
                    "    \"scene\":\"bar_code\"," +
                    "    \"auth_code\":\"" + aliPayReq.getAuthCode() + "\"," +
                    "    \"subject\":\"" + aliPayReq.getSubject() + "\"," +
                    "    \"total_amount\":" + aliPayReq.getTotalAmount() + "," +
                    "    \"store_id\":\"" + aliPayReq.getReputationStoreId() + "\"," +
                    "    \"alipay_store_id\":\"" + aliPayReq.getAliStoreId() + "\"" +
                    "  }";
        }
        request.setBizContent(bizContent);
        logger.info("链接支付宝支付配置信息|serverUrl={}|appid={}|privateKey={}|publicKey={}", aliServerUrl, aliAppId, aliPrivateKey, aliPublicKey);
        AlipayClient alipayClient = new DefaultAlipayClient(aliServerUrl, aliAppId,
                aliPrivateKey, aliFormat, aliCharset, aliPublicKey);
        logger.info(request.getBizContent());
        logger.info("alipayTradePayRequest={}", request);
        AlipayTradePayResponse response = null;
        try {
            response = alipayClient.execute(request);
            if (response.isSuccess()) {
                modelResult.setData(response);
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        logger.info("支付宝条码支付返回值response={}", response);
        return modelResult;
    }
}