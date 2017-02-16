package com.qiyu.bankpay.controller;

import com.alibaba.fastjson.JSONObject;
import com.qiyu.bankpay.domain.request.CallBackReq;
import com.qiyu.bankpay.domain.request.cmbc.*;
import com.qiyu.bankpay.service.*;
import com.qiyu.bankpay.util.crypto.XmlUtils;
import com.qiyu.common.result.ModelResult;
import com.qiyu.common.utils.MediaTypes;
import com.qiyu.common.utils.UuidUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

/**
 * Created by Administrator on 2016/11/17.
 */
@Controller
@RequestMapping("bank")
public class BankPayController {

    @Autowired
    private CMBCPayService cmbcPayService;

    @Autowired
    private CTTICPayService ctticPayService;

    @Autowired
    private KFTPayService kftPayService;

    @Autowired
    private IndustrialPayService industrialPayService;

    @Autowired
    private HttpClientService httpClientService;

    Logger logger = LoggerFactory.getLogger(BankPayController.class);

    /**
     * 民生银行商户入驻
     *
     * @param request
     * @param response
     * @param cmbcMerchantSignReq
     * @return
     */
    @RequestMapping(value = "merchantSign", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object merchantSign(HttpServletRequest request,
                               HttpServletResponse response, CMBCMerchantSignReq cmbcMerchantSignReq) {
        try {
            return cmbcPayService.merchantSign(cmbcMerchantSignReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 民生银行扫码支付
     *
     * @param request
     * @param response
     * @param cmbcScanPayReq
     * @return
     */
    @RequestMapping(value = "scanPay", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object scanPay(HttpServletRequest request,
                          HttpServletResponse response, CMBCScanPayReq cmbcScanPayReq) {
        try {
            return cmbcPayService.scanPay(cmbcScanPayReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 民生银行扫码/条码支付回调
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "scanPayCallBack", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object scanPayCallBack(HttpServletRequest request,
                                  HttpServletResponse response) {
        CallBackReq callBackReq = getCallBackReq(request);
        try {
            response.getWriter().write("000000");
            response.getWriter().flush();
            ModelResult modelResult =  cmbcPayService.scanPayCallBack(callBackReq);
            return modelResult;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    /**
     * 民生银行条码支付
     *
     * @param request
     * @param response
     * @param cmbcBarCodePayReq
     * @return
     */
    @RequestMapping(value = "barCodePay", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object barCodePay(HttpServletRequest request,
                             HttpServletResponse response, CMBCBarCodePayReq cmbcBarCodePayReq) {
        try {
            return cmbcPayService.barCodePay(cmbcBarCodePayReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




    /**
     * 退款
     * @param request
     * @param response
     * @param cmbcReFundAmountReq
     * @return
     */
    @RequestMapping(value = "reFundAmountPay", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object reFundAmountPay(HttpServletRequest request,
                                  HttpServletResponse response, CMBCReFundAmountReq cmbcReFundAmountReq) {
        try {
            return cmbcPayService.reFundAmountPay(cmbcReFundAmountReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 撤销交易（微信渠道条码支付）
     *
     * @param request
     * @param response
     * @param cmbcCancelPayReq
     * @return
     */
    @RequestMapping(value = "cancelPay", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object reFundAmountPay(HttpServletRequest request,
                                  HttpServletResponse response, CMBCCancelPayReq cmbcCancelPayReq) {
        try {
            return cmbcPayService.canclePay(cmbcCancelPayReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 交易查询（某一笔）
     *
     * @param request
     * @param response
     * @param cmbcTransSearchPayReq
     * @return
     */
    @RequestMapping(value = "transSearchPay", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object transSearchPay(HttpServletRequest request,
                                 HttpServletResponse response, CMBCTransSearchPayReq cmbcTransSearchPayReq) {
        try {
            return cmbcPayService.transSearchPay(cmbcTransSearchPayReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 商户查询
     *
     * @param request
     * @param response
     * @param cmbcMerchantSearchPayReq
     * @return
     */
    @RequestMapping(value = "merchantSearchPay", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object merchantSearchPay(HttpServletRequest request,
                                    HttpServletResponse response, CMBCMerchantSearchPayReq cmbcMerchantSearchPayReq) {
        try {
            return cmbcPayService.merchantSearchPay(cmbcMerchantSearchPayReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 商户修改
     *
     * @param request
     * @param response
     * @param cmbcMerchantUpdatePayReq
     * @return
     */
    @RequestMapping(value = "merchantUpdatePay", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object merchantUpdatePay(HttpServletRequest request,
                                    HttpServletResponse response, CMBCMerchantUpdatePayReq cmbcMerchantUpdatePayReq) {
        try {
            return cmbcPayService.merchantUpdatePay(cmbcMerchantUpdatePayReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 民生公众号/服务窗支付
     *
     * @param request
     * @param response
     * @param cmbcPublicNumPayReq
     * @return
     */
    @RequestMapping(value = "publicNumPay", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object publicNumPay(HttpServletRequest request,
                               HttpServletResponse response, CMBCPublicNumPayReq cmbcPublicNumPayReq) {
        try {
            // TODO: 2016/11/19  don't test
            return cmbcPayService.publicNumPay(cmbcPublicNumPayReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取对账文件
     *
     * @param request
     * @param response
     * @param cmbcBalanceAccountPayReq
     * @return
     */
    @RequestMapping(value = "balanceAccountPay", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object balanceAccountPay(HttpServletRequest request,
                                    HttpServletResponse response, CMBCBalanceAccountPayReq cmbcBalanceAccountPayReq) {
        try {
            return cmbcPayService.balanceAccountPay(cmbcBalanceAccountPayReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 商户提现
     * @param request
     * @param response
     * @param cmbcWithdrawPayReq
     * @return
     */
    @RequestMapping(value = "withdrawPay", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object withdrawPay(HttpServletRequest request,
                              HttpServletResponse response, CMBCWithdrawPayReq cmbcWithdrawPayReq) {
        try {
            return cmbcPayService.withdrawPay(cmbcWithdrawPayReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 商户提现回调
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "withdrawPayCallBack", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object withdrawPayCallBack(HttpServletRequest request,
                                      HttpServletResponse response) {
        try {
            CallBackReq callBackReq = getCallBackReq(request);
            return cmbcPayService.withdrawPayCallBack(callBackReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 提现交易查询
     * @param request
     * @param response
     * @param cmbcWithdrawSearchPayReq
     * @return
     */
    @RequestMapping(value = "withdrawSearchPay",produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object withdrawSearchPay(HttpServletRequest request,
                                    HttpServletResponse response, CMBCWithdrawSearchPayReq cmbcWithdrawSearchPayReq){
        try {
            return cmbcPayService.withdrawSearchPay(cmbcWithdrawSearchPayReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }





    /**
     * 中信扫码支付
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "microPay", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object microPay(HttpServletRequest request,
                           HttpServletResponse response) {

        SortedMap<String, String> map = XmlUtils.getParameterMap(request);
        logger.info("map:{}", map.size());
       /* if (null != ctticMicroPayReq) {
            ctticMicroPayReq.setMch_create_ip(getIp(request));
            try {
                return ctticPayService.microPay(ctticMicroPayReq);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
        try {
            if (map.containsKey("nonce_str")) {
                map.remove("nonce_str");
                map.put("nonce_str", UuidUtil.get32UUID());
            } else {
                map.put("nonce_str", UuidUtil.get32UUID());
            }
            return ctticPayService.microPay(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 小额支付退款
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "microRefundPay", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object microRefundPay(HttpServletRequest request,
                                 HttpServletResponse response) {
        SortedMap<String, String> map = XmlUtils.getParameterMap(request);
        try {
            if (map.containsKey("nonce_str")) {
                map.remove("nonce_str");
                map.put("nonce_str", UuidUtil.get32UUID());
            } else {
                map.put("nonce_str", UuidUtil.get32UUID());
            }
            return ctticPayService.microRefundPay(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 小额支付交易查询
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "microQueryPay", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object microQueryPay(HttpServletRequest request,
                                HttpServletResponse response) {
        SortedMap<String, String> map = XmlUtils.getParameterMap(request);
        try {
            if (map.containsKey("nonce_str")) {
                map.remove("nonce_str");
                map.put("nonce_str", UuidUtil.get32UUID());
            } else {
                map.put("nonce_str", UuidUtil.get32UUID());
            }
            return ctticPayService.microQueryPay(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 小额支付冲正
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "microRollPay", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object microRollPay(HttpServletRequest request,
                               HttpServletResponse response) {
        SortedMap<String, String> map = XmlUtils.getParameterMap(request);
        try {
            if (map.containsKey("nonce_str")) {
                map.remove("nonce_str");
                map.put("nonce_str", UuidUtil.get32UUID());
            } else {
                map.put("nonce_str", UuidUtil.get32UUID());
            }
            return ctticPayService.microRollPay(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 小额支付退款查询
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "microRefundQueryPay", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object microRefundQueryPay(HttpServletRequest request,
                                      HttpServletResponse response) {
        SortedMap<String, String> map = XmlUtils.getParameterMap(request);
        try {
            if (map.containsKey("nonce_str")) {
                map.remove("nonce_str");
                map.put("nonce_str", UuidUtil.get32UUID());
            } else {
                map.put("nonce_str", UuidUtil.get32UUID());
            }
            return ctticPayService.microRefundQueryPay(map);
        } catch (Exception e) {
            e.printStackTrace();


        }
        return null;
    }

    /**
     * 二维码
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "kftGenQrcode", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object kftGenQrcode(HttpServletRequest request,
                               HttpServletResponse response) {
        SortedMap<String, String> map = XmlUtils.getParameterMap(request);
        try {
            return kftPayService.generateQRcode(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 下单
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "kftPay", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object kftPay(HttpServletRequest request,
                         HttpServletResponse response) {
        SortedMap<String, String> map = XmlUtils.getParameterMap(request);
        try {
            return kftPayService.kftPay(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 下单回调
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "kftPayCallBack", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object kftPayCallBack(HttpServletRequest request,
                                 HttpServletResponse response) {
        String returnStr  = XmlUtils.parseRequst(request);
        try {
            returnStr = new String(returnStr.getBytes("gbk"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSONObject.parseObject(returnStr);
        Map map = (Map)jsonObject;
        logger.info("returnStr:{}",returnStr);
        try {
          String key = "b2ea60be9994425886681ed1fce5e70a";
            return kftPayService.kftPayCallBack(map,key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping(value = "kftPayNotify")
    @ResponseBody
    public String notify(HttpServletRequest request) {
        StringBuffer html = new StringBuffer();
        html.append("<div>");
        Map<String, String[]> params = request.getParameterMap();
        for(String key : params.keySet()) {
            html.append(key + "：");
            String[] values = params.get(key);
            for(String value : values) {
                html.append(value);
            }
            html.append("</br />");
        }
        html.append("</div>");

        request.setAttribute("htmlCode", html);
        return "notifyResult";
    }

    /**
     * 交易查询
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "kftPayQuery", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object kftQuery(HttpServletRequest request,
                           HttpServletResponse response) {
        SortedMap<String, String> map = XmlUtils.getParameterMap(request);
        try {
            return kftPayService.kftQuery(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 交易查询
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "kftPayRefund", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object kftRefundQuery(HttpServletRequest request,
                                 HttpServletResponse response) {
        SortedMap<String, String> map = XmlUtils.getParameterMap(request);
        try {
            return kftPayService.kftRefund(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 快付通对账单下载
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "kftDownloadBalance", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object kftDownloadBalance(HttpServletRequest request,
                                     HttpServletResponse response) {
        SortedMap<String, String> map = XmlUtils.getParameterMap(request);
        try {
            return kftPayService.kftDownload(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 兴业支付宝扫码
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "industrialZFBScanPay", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object industrialZFBScanPay(HttpServletRequest request, HttpServletResponse response) {
        SortedMap<String, String> map = XmlUtils.getParameterMap(request);
        try {
            return industrialPayService.scanZFBPay(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 兴业支付宝扫码回调
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "industrialZFBScanPayCallBack", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object industrialZFBScanPayCallBack(HttpServletRequest request, HttpServletResponse response) {
        try {
            String resString = XmlUtils.parseRequst(request);
            String key = "9d101c97133837e13dde2d32a5054abb";
            ModelResult modelResult = industrialPayService.scanZFBPayCallBack(resString,key);
            response.getWriter().print(modelResult.getMsg());
            return modelResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 兴业支付宝订单查询
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "industrialZFBPayQuery", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object industrialZFBPayQuery(HttpServletRequest request, HttpServletResponse response) {
        try {
            SortedMap<String, String> map = XmlUtils.getParameterMap(request);
            ModelResult modelResult = industrialPayService.industrialZFBQueryPay(map);
            return modelResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 兴业支付宝退款
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "industrialRefundPay", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object industrialRefundPay(HttpServletRequest request, HttpServletResponse response) {
        try {
            SortedMap<String, String> map = XmlUtils.getParameterMap(request);
            ModelResult modelResult = industrialPayService.industrialZFBRefundPay(map);
            return modelResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 兴业微信扫码支付
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "industrialWXScanPay", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object industrialWXScanPay(HttpServletRequest request, HttpServletResponse response){
        SortedMap<String, String> map = XmlUtils.getParameterMap(request);
        try {
            return industrialPayService.scanWXPay(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 兴业微信扫码回调
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "industrialWXScanCallBack", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object industrialWXScanCallBack(HttpServletRequest request, HttpServletResponse response){
        try {
            String resString = XmlUtils.parseRequst(request);
            String key = "37f26e94ad7927977d0582b6f82fd20a";
            logger.info("回调resString："+resString);
            ModelResult modelResult = industrialPayService.scanWXPayCallBack(resString,key);
            response.getWriter().print(modelResult.getMsg());
            return modelResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 兴业微信支付查询
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "industrialWXQueryPay", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object  industrialWXQueryPay(HttpServletRequest request, HttpServletResponse response){
        SortedMap<String, String> map = XmlUtils.getParameterMap(request);
        try {
            return industrialPayService.industrialWXQueryPay(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 兴业微信支付退款
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "industrialWXRefundPay",produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object industrialWXRefundPay(HttpServletRequest request,HttpServletResponse response){
        SortedMap<String, String> map = XmlUtils.getParameterMap(request);
        try {
            return industrialPayService.industrialWXRefundPay(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 兴业微信退款查询
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "industrialRefundQueryPay",produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object industrialRefundQueryPay(HttpServletRequest request,HttpServletResponse response){
        SortedMap<String, String> map = XmlUtils.getParameterMap(request);
        try {
            return industrialPayService.industrialRefundQueryPay(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 兴业微信关单
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "industrialClosePay",produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object industrialClosePay(HttpServletRequest request,HttpServletResponse response){
        SortedMap<String, String> map = XmlUtils.getParameterMap(request);
        try {
            return industrialPayService.industrialClosePay(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 兴业初始化请求接口
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "industrialInitReqPay",produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object industrialInitReqPay(HttpServletRequest request,HttpServletResponse response){
        SortedMap<String, String> map = XmlUtils.getParameterMap(request);
        try {
            String key = "37f26e94ad7927977d0582b6f82fd20a";
            return industrialPayService.industrialInitRequestPay(map,key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * js公众号支付回调
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "industrialJsCallBack",produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object industrialJsCallBack(HttpServletRequest request,HttpServletResponse response){
        try {
            String resString = XmlUtils.parseRequst(request);
            String key = "37f26e94ad7927977d0582b6f82fd20a";
            ModelResult modelResult = industrialPayService.industrialJsCallBack(resString,key);

            if (modelResult.getCode() == 0){
            	Map map = XmlUtils.toMap(resString.getBytes(), "utf-8");
            	String json =  JSONObject.toJSONString(map);
            	logger.info("attach:",map.get("attach"));
            	String str =  httpClientService.sendRequest(map.get("attach").toString(), json);
            	logger.info("str:",str);
            	if ("success".equalsIgnoreCase(str)) {
            		response.getWriter().write("success");
            	} else {
            		response.getWriter().write("fail");
            	}
            }else{
                response.getWriter().write("fail");
            }
            response.getWriter().flush();
            response.getWriter().close();
            return modelResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 商户登记
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "kftMerchantRegist",produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object kftMerchantRegist(HttpServletRequest request,HttpServletResponse response){
        SortedMap<String, String> map = XmlUtils.getParameterMap(request);
        try {
            return kftPayService.kftMerchantRegist(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 商户修改
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "kftMerchantModify",produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object kftMerchantModify(HttpServletRequest request,HttpServletResponse response){
        SortedMap<String, String> map = XmlUtils.getParameterMap(request);
        try {
            return kftPayService.kftMerchantModify(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 商户状态查询
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "kftMerchantStateQuery",produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object kftMerchantStateQuery(HttpServletRequest request,HttpServletResponse response){
        SortedMap<String, String> map = XmlUtils.getParameterMap(request);
        try {
            return kftPayService.kftMerchantStateQuery(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private CallBackReq getCallBackReq(HttpServletRequest request) {
        Map map = new HashMap();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    map.put(paramName, paramValue);
                }
            }
        }
        String returnStr = JSONObject.toJSONString(map);
        CallBackReq callBackReq = new CallBackReq();
        callBackReq.setReturnStr(returnStr);
        return callBackReq;
    }

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }
    @RequestMapping(value = "xingyeTest",produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public Object xingyeTest(HttpServletRequest request,HttpServletResponse response){
        String resString = "<xml><bank_type><![CDATA[CFT]]></bank_type><charset><![CDATA[UTF-8]]></charset><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[N]]></is_subscribe><mch_id><![CDATA[101510021361]]></mch_id><nonce_str><![CDATA[1485064718984]]></nonce_str><openid><![CDATA[oMDLljtXAo2POys8vjGxNzle3G3g]]></openid><out_trade_no><![CDATA[10000003]]></out_trade_no><out_transaction_id><![CDATA[4010162001201701227184844011]]></out_transaction_id><pay_result><![CDATA[0]]></pay_result><result_code><![CDATA[0]]></result_code><sign><![CDATA[9D48F2125406C66F9C1FA74BCEBC0F41]]></sign><sign_type><![CDATA[MD5]]></sign_type><status><![CDATA[0]]></status><sub_appid><![CDATA[wxfc81a8219ffeed55]]></sub_appid><sub_is_subscribe><![CDATA[Y]]></sub_is_subscribe><sub_openid><![CDATA[oF3LAwSh9Lty08_BkI0Hrssr4SS8]]></sub_openid><time_end><![CDATA[20170122135838]]></time_end><total_fee><![CDATA[1]]></total_fee><trade_type><![CDATA[pay.weixin.jspay]]></trade_type><transaction_id><![CDATA[101510021361201701227039383397]]></transaction_id><version><![CDATA[2.0]]></version></xml>";
        try {
            Map map = XmlUtils.toMap(resString.getBytes(), "utf-8");
           String json =  JSONObject.toJSONString(map);
           String str =  httpClientService.sendRequest("http://lemon613.ticp.net/bankpay/bank/industrialWXScanCallBack",json);
            logger.info("str:",str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
