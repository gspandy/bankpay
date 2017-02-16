package com.qiyu.bankpay.service;

import com.alibaba.fastjson.JSONObject;
import com.qiyu.bankpay.domain.constant.Constant;
import com.qiyu.bankpay.domain.request.CallBackReq;
import com.qiyu.bankpay.domain.request.cmbc.*;
import com.qiyu.bankpay.domain.result.*;
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
 */
@Service("cmbcPayService")
public class CMBCPayServiceImpl extends BasePayService implements CMBCPayService{

    private Logger logger = LoggerFactory.getLogger(CMBCPayServiceImpl.class);

    String nowDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

    /**
     * 商户入驻
     *
     * @param cmbcMerchantSignReq
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult<CMBCMerchantSignRlt> merchantSign(CMBCMerchantSignReq cmbcMerchantSignReq) throws Exception {

        cmbcMerchantSignReq.setReqDate(nowDate);
        cmbcMerchantSignReq.setVersion("1.0.0");
        String reqMsgId = UuidUtil.get32UUID();
        JSONObject jsonObject = sendCmbcPost(reqMsgId, cmbcMerchantSignReq, Constant.TRAN_CODE_SMZF_001, CMBCPayServiceImpl.class, null);
        Map<String, String> map = getReturnCallBackMap(CMBCPayServiceImpl.class, jsonObject);
        CMBCMerchantSignRlt cmbcMerchantSignRlt = (CMBCMerchantSignRlt) packageCodec.decodeXML(map.get("templateXml"), map.get("resXml"));
        ModelResult modelResult = new ModelResult();
        modelResult.setData(cmbcMerchantSignRlt);
        return modelResult;
    }

    /**
     * 扫码支付
     *
     * @param cmbcScanPayReq
     * @return
     */
    @Override
    public ModelResult<CMBCScanPayRlt> scanPay(CMBCScanPayReq cmbcScanPayReq) throws Exception {
        String callBack = cmbcScanPayReq.getCallBack();
        //商户订单id
        String reqMsgId = cmbcScanPayReq.getReqMsgId();
        if (null == reqMsgId) {
            reqMsgId = UuidUtil.get32UUID();
        }
        cmbcScanPayReq.setReqDate(nowDate);
        cmbcScanPayReq.setVersion("1.0.0");

        JSONObject jsonObject = sendCmbcPost(reqMsgId, cmbcScanPayReq, Constant.TRAN_CODE_SMZF_002, CMBCPayServiceImpl.class, callBack);
        Map<String, String> map = getReturnCallBackMap(CMBCPayServiceImpl.class, jsonObject);
        CMBCScanPayRlt cmbcScanPayRlt = (CMBCScanPayRlt) packageCodec.decodeXML(map.get("templateXml"), map.get("resXml"));
        ModelResult modelResult = new ModelResult();
        if (null != cmbcScanPayRlt ) {
            if (cmbcScanPayRlt.getRespType().equals("R")){
                String imageUrl = encodeQrcode(cmbcScanPayRlt.getQrCode(), "CMBCQRCODE", ".png", qrCodePath);
                cmbcScanPayRlt.setImageUrl(imageUrl);
                modelResult.setData(cmbcScanPayRlt);
                return modelResult;
            }else{
                modelResult.setCode(Integer.parseInt(cmbcScanPayRlt.getRespCode()));
                modelResult.setMsg(cmbcScanPayRlt.getRespMsg());
                return modelResult;
            }
        }
        modelResult.setCode(1);
        modelResult.setMsg("扫码支付失败");
        return modelResult;
    }

    /**
     * 扫码/条码支付回调
     *
     * @param callBackReq
     * @return
     */
    @Override
    public ModelResult<CallBackRlt> scanPayCallBack(CallBackReq callBackReq) throws Exception {
        ModelResult modelResult = new ModelResult();
        CallBackRlt callBackRlt = new CallBackRlt();
        callBackRlt.setReturnStr("000000");
        if (null != callBackReq && null != callBackReq.getReturnStr()) {
            logger.info("returnStr:"+callBackReq.getReturnStr());
            JSONObject jsonObject = JSONObject.parseObject(callBackReq.getReturnStr());
            if (null != jsonObject && jsonObject.size()>0) {
                Map<String, String> map = getReturnCallBackMap(CMBCPayServiceImpl.class, jsonObject);
                CMBCScanPayCallBackRlt cmbcScanPayCallBackRlt = (CMBCScanPayCallBackRlt) packageCodec.decodeXML(map.get("templateXml"), map.get("resXml"));
                if (null != cmbcScanPayCallBackRlt) {
                    logger.info("cmbcScanPayCallBackRlt.getReqMsgId =" + cmbcScanPayCallBackRlt.getReqMsgId());
                    logger.info("cmbcScanPayCallBackRlt.getRespType =" + cmbcScanPayCallBackRlt.getRespType());
                    modelResult.setData(cmbcScanPayCallBackRlt);
                    return modelResult;
                } else {
                    modelResult.setCode(1);
                    modelResult.setMsg("回调失败！");
                    return modelResult;
                }
            }
        }
        return null;
    }


    /**
     * 条码支付
     *
     * @param cmbcBarCodePayReq
     * @return
     */
    @Override
    public ModelResult<CMBCBarCodePayRlt> barCodePay(CMBCBarCodePayReq cmbcBarCodePayReq) throws Exception {
        cmbcBarCodePayReq.setReqDate(nowDate);
        cmbcBarCodePayReq.setVersion("1.0.0");
        String callBack = cmbcBarCodePayReq.getCallBack();;
        //商户订单id
        String reqMsgId = cmbcBarCodePayReq.getReqMsgId();
        JSONObject jsonObject = sendCmbcPost(reqMsgId, cmbcBarCodePayReq, Constant.TRAN_CODE_SMZF_003, CMBCPayServiceImpl.class, callBack);
        Map<String, String> map = getReturnCallBackMap(CMBCPayServiceImpl.class, jsonObject);
        CMBCBarCodePayRlt cmbcBarCodePayRlt = (CMBCBarCodePayRlt) packageCodec.decodeXML(map.get("templateXml"), map.get("resXml"));
        ModelResult modelResult = new ModelResult();
        if (null != cmbcBarCodePayRlt) {
            if (cmbcBarCodePayRlt.getRespType().equals("S")){
                modelResult.setData(cmbcBarCodePayRlt);
                return modelResult;
            }else{
                modelResult.setCode(Integer.parseInt(cmbcBarCodePayRlt.getRespCode()));
                modelResult.setMsg(cmbcBarCodePayRlt.getRespMsg());
                return modelResult;
            }
        }
        modelResult.setCode(1);
        modelResult.setMsg("民生条码支付失败");
        return modelResult;
    }


    /**
     * 申请退款
     *
     * @param cmbcReFundAmountReq
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult<CMBCReFundAmountRlt> reFundAmountPay(CMBCReFundAmountReq cmbcReFundAmountReq) throws Exception {
        cmbcReFundAmountReq.setReqDate(nowDate);
        cmbcReFundAmountReq.setVersion("1.0.0");
        String callBack = cmbcReFundAmountReq.getCallBack();
        //商户订单id
        String reqMsgId = cmbcReFundAmountReq.getReqMsgId();
        if (null == reqMsgId) {
            reqMsgId = UuidUtil.get32UUID();
        }
        JSONObject jsonObject = sendCmbcPost(reqMsgId, cmbcReFundAmountReq, Constant.TRAN_CODE_SMZF_004, CMBCPayServiceImpl.class, callBack);
        Map<String, String> map = getReturnCallBackMap(CMBCPayServiceImpl.class, jsonObject);
        CMBCReFundAmountRlt cmbcReFundAmountRlt = (CMBCReFundAmountRlt) packageCodec.decodeXML(map.get("templateXml"), map.get("resXml"));
        ModelResult modelResult = new ModelResult();
        if (null != cmbcReFundAmountRlt) {
            modelResult.setData(cmbcReFundAmountRlt);
            return modelResult;
        }
        modelResult.setCode(1);
        modelResult.setMsg("退款失败！");
        return modelResult;
    }

    /**
     * 撤销交易
     *
     * @param cancelPayReq
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult<CMBCCancelPayRlt> canclePay(CMBCCancelPayReq cancelPayReq) throws Exception {
        cancelPayReq.setReqDate(nowDate);
        cancelPayReq.setVersion("1.0.0");
        String callBack = cancelPayReq.getCallBack();
        //商户订单id
        String reqMsgId = cancelPayReq.getReqMsgId();
        if (null == reqMsgId) {
            reqMsgId = UuidUtil.get32UUID();
        }
        JSONObject jsonObject = sendCmbcPost(reqMsgId, cancelPayReq, Constant.TRAN_CODE_SMZF_005, CMBCPayServiceImpl.class, callBack);
        Map<String, String> map = getReturnCallBackMap(CMBCPayServiceImpl.class, jsonObject);
        CMBCCancelPayRlt cmbcCancelPayRlt = (CMBCCancelPayRlt) packageCodec.decodeXML(map.get("templateXml"), map.get("resXml"));
        ModelResult modelResult = new ModelResult();
        if (null != cmbcCancelPayRlt) {
            modelResult.setData(cmbcCancelPayRlt);
            return modelResult;
        }
        modelResult.setCode(1);
        modelResult.setMsg("撤销交易失败！");
        return modelResult;
    }

    /**
     * 交易查询（某一笔）
     *
     * @param cmbcTransSearchPayReq
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult<CMBCTransSearchPayRlt> transSearchPay(CMBCTransSearchPayReq cmbcTransSearchPayReq) throws Exception {
        cmbcTransSearchPayReq.setReqDate(nowDate);
        cmbcTransSearchPayReq.setVersion("1.0.0");
        String callBack = cmbcTransSearchPayReq.getCallBack();
        //商户订单id
        String reqMsgId = cmbcTransSearchPayReq.getReqMsgId();
        if (null == reqMsgId) {
            reqMsgId = UuidUtil.get32UUID();
        }
        JSONObject jsonObject = sendCmbcPost(reqMsgId, cmbcTransSearchPayReq, Constant.TRAN_CODE_SMZF_006, CMBCPayServiceImpl.class, callBack);
        Map<String, String> map = getReturnCallBackMap(CMBCPayServiceImpl.class, jsonObject);
        CMBCTransSearchPayRlt cmbcTransSearchPayRlt = (CMBCTransSearchPayRlt) packageCodec.decodeXML(map.get("templateXml"), map.get("resXml"));
        ModelResult modelResult = new ModelResult();
        if (null != cmbcTransSearchPayRlt) {
            modelResult.setData(cmbcTransSearchPayRlt);
            return modelResult;
        }
        modelResult.setCode(1);
        modelResult.setMsg("交易查询失败！");
        return modelResult;
    }

    /**
     * 商户查询
     *
     * @param cmbcMerchantSearchPayReq
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult<CMBCMerchantSearchPayRlt> merchantSearchPay(CMBCMerchantSearchPayReq cmbcMerchantSearchPayReq) throws Exception {
        cmbcMerchantSearchPayReq.setReqDate(nowDate);
        cmbcMerchantSearchPayReq.setVersion("1.0.0");
        String callBack = cmbcMerchantSearchPayReq.getCallBack();
        //商户订单id
        String reqMsgId = cmbcMerchantSearchPayReq.getReqMsgId();
        if (null == reqMsgId) {
            reqMsgId = UuidUtil.get32UUID();
        }
        JSONObject jsonObject = sendCmbcPost(reqMsgId, cmbcMerchantSearchPayReq, Constant.TRAN_CODE_SMZF_007, CMBCPayServiceImpl.class, callBack);
        Map<String, String> map = getReturnCallBackMap(CMBCPayServiceImpl.class, jsonObject);
        CMBCMerchantSearchPayRlt cmbcMerchantSearchPayRlt = (CMBCMerchantSearchPayRlt) packageCodec.decodeXML(map.get("templateXml"), map.get("resXml"));
        ModelResult modelResult = new ModelResult();
        if (null != cmbcMerchantSearchPayRlt) {
            modelResult.setData(cmbcMerchantSearchPayRlt);
            return modelResult;
        }
        modelResult.setCode(1);
        modelResult.setMsg("商户查询失败！");
        return modelResult;
    }

    /**
     * 商户修改
     *
     * @param cmbcMerchantUpdatePayReq
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult<CMBCMerchantUpdatePayRlt> merchantUpdatePay(CMBCMerchantUpdatePayReq cmbcMerchantUpdatePayReq) throws Exception {
        cmbcMerchantUpdatePayReq.setReqDate(nowDate);
        cmbcMerchantUpdatePayReq.setVersion("1.0.0");
        String callBack = cmbcMerchantUpdatePayReq.getCallBack();
        //商户订单id
        String reqMsgId = cmbcMerchantUpdatePayReq.getReqMsgId();
        if (null == reqMsgId) {
            reqMsgId = UuidUtil.get32UUID();
        }
        JSONObject jsonObject = sendCmbcPost(reqMsgId, cmbcMerchantUpdatePayReq, Constant.TRAN_CODE_SMZF_009, CMBCPayServiceImpl.class, callBack);
        Map<String, String> map = getReturnCallBackMap(CMBCPayServiceImpl.class, jsonObject);
        CMBCMerchantUpdatePayRlt cmbcMerchantUpdatePayRlt = (CMBCMerchantUpdatePayRlt) packageCodec.decodeXML(map.get("templateXml"), map.get("resXml"));
        ModelResult modelResult = new ModelResult();
        if (null != cmbcMerchantUpdatePayRlt) {
            modelResult.setData(cmbcMerchantUpdatePayRlt);
            return modelResult;
        }
        modelResult.setCode(1);
        modelResult.setMsg("商户查询失败！");
        return modelResult;
    }

    /**
     * 公众号、服务窗支付
     *
     * @param cmbcPublicNumPayReq
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult<CMBCPublicNumPayRlt> publicNumPay(CMBCPublicNumPayReq cmbcPublicNumPayReq) throws Exception {
        cmbcPublicNumPayReq.setReqDate(nowDate);
        cmbcPublicNumPayReq.setVersion("1.0.0");
        String callBack = cmbcPublicNumPayReq.getCallBack();
        //商户订单id
        String reqMsgId = cmbcPublicNumPayReq.getReqMsgId();
        if (null == reqMsgId) {
            reqMsgId = UuidUtil.get32UUID();
        }
        JSONObject jsonObject = sendCmbcPost(reqMsgId, cmbcPublicNumPayReq, Constant.TRAN_CODE_SMZF_010, CMBCPayServiceImpl.class, callBack);
        Map<String, String> map = getReturnCallBackMap(CMBCPayServiceImpl.class, jsonObject);
        CMBCPublicNumPayRlt cmbcPublicNumPayRlt = (CMBCPublicNumPayRlt) packageCodec.decodeXML(map.get("templateXml"), map.get("resXml"));
        ModelResult modelResult = new ModelResult();
        if (null != cmbcPublicNumPayRlt) {
            modelResult.setData(cmbcPublicNumPayRlt);
            return modelResult;
        }
        modelResult.setCode(1);
        modelResult.setMsg("公众号/服务窗支付失败！");
        return modelResult;
    }

    /**
     * 获取对账文件
     *
     * @param cmbcBalanceAccountPayReq
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult<CMBCBalanceAccountPayRlt> balanceAccountPay(CMBCBalanceAccountPayReq cmbcBalanceAccountPayReq) throws Exception {
        cmbcBalanceAccountPayReq.setReqDate(nowDate);
        cmbcBalanceAccountPayReq.setVersion("1.0.0");
        String callBack = cmbcBalanceAccountPayReq.getCallBack();
        //商户订单id
        String reqMsgId = cmbcBalanceAccountPayReq.getReqMsgId();
        if (null == reqMsgId) {
            reqMsgId = UuidUtil.get32UUID();
        }
        JSONObject jsonObject = sendCmbcPost(reqMsgId, cmbcBalanceAccountPayReq, Constant.TRAN_CODE_SMZF_020, CMBCPayServiceImpl.class, callBack);
        Map<String, String> map = getReturnCallBackMap(CMBCPayServiceImpl.class, jsonObject);
        CMBCBalanceAccountPayRlt cmbcBalanceAccountPayRlt = (CMBCBalanceAccountPayRlt) packageCodec.decodeXML(map.get("templateXml"), map.get("resXml"));
        ModelResult modelResult = new ModelResult();
        if (null != cmbcBalanceAccountPayRlt) {
            modelResult.setData(cmbcBalanceAccountPayRlt);
            return modelResult;
        }
        modelResult.setCode(1);
        modelResult.setMsg("获取对账文件失败！");
        return modelResult;
    }

    /**
     * 商户提现接口
     *
     * @param cmbcWithdrawPayReq
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult<CMBCWithdrawPayRlt> withdrawPay(CMBCWithdrawPayReq cmbcWithdrawPayReq) throws Exception {
        cmbcWithdrawPayReq.setReqDate(nowDate);
        cmbcWithdrawPayReq.setVersion("1.0.0");
        String callBack = cmbcWithdrawPayReq.getCallBack();
        //商户订单id
        String reqMsgId = cmbcWithdrawPayReq.getReqMsgId();
        if (null == reqMsgId) {
            reqMsgId = UuidUtil.get32UUID();
        }
        JSONObject jsonObject = sendCmbcPost(reqMsgId, cmbcWithdrawPayReq, Constant.TRAN_CODE_SMZF_021, CMBCPayServiceImpl.class, callBack);
        Map<String, String> map = getReturnCallBackMap(CMBCPayServiceImpl.class, jsonObject);
        CMBCWithdrawPayRlt cmbcWithdrawPayRlt = (CMBCWithdrawPayRlt) packageCodec.decodeXML(map.get("templateXml"), map.get("resXml"));
        ModelResult modelResult = new ModelResult();
        if (null != cmbcWithdrawPayRlt) {
            modelResult.setData(cmbcWithdrawPayRlt);
            return modelResult;
        }
        modelResult.setCode(1);
        modelResult.setMsg("商户提现失败！");
        return modelResult;
    }

    /**
     * 提现交易查询
     *
     * @param cmbcWithdrawSearchPayReq
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult<CMBCWithdrawSearchPayRlt> withdrawSearchPay(CMBCWithdrawSearchPayReq cmbcWithdrawSearchPayReq) throws Exception {
        cmbcWithdrawSearchPayReq.setReqDate(nowDate);
        cmbcWithdrawSearchPayReq.setVersion("1.0.0");
        //商户订单id
        String reqMsgId = cmbcWithdrawSearchPayReq.getReqMsgId();
        if (null == reqMsgId) {
            reqMsgId = UuidUtil.get32UUID();
        }
        JSONObject jsonObject = sendCmbcPost(reqMsgId, cmbcWithdrawSearchPayReq, Constant.TRAN_CODE_SMZF_022, CMBCPayServiceImpl.class, null);
        Map<String, String> map = getReturnCallBackMap(CMBCPayServiceImpl.class, jsonObject);
        CMBCWithdrawSearchPayRlt cmbcWithdrawSearchPayRlt = (CMBCWithdrawSearchPayRlt) packageCodec.decodeXML(map.get("templateXml"), map.get("resXml"));
        ModelResult modelResult = new ModelResult();
        if (null != cmbcWithdrawSearchPayRlt) {
            modelResult.setData(cmbcWithdrawSearchPayRlt);
            return modelResult;
        }

        modelResult.setCode(1);
        modelResult.setMsg("提现交易查询失败！");
        return modelResult;
    }

    /**
     * 商户提现异步通知接口
     *
     * @param callBackReq
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult<CMBCWithdrawPayCallBackRlt> withdrawPayCallBack(CallBackReq callBackReq) throws Exception {
        ModelResult modelResult = new ModelResult();
        CallBackRlt callBackRlt = new CallBackRlt();
        callBackRlt.setReturnStr("000000");
        if (null != callBackReq) {
            JSONObject jsonObject = JSONObject.parseObject(callBackReq.getReturnStr());
            if (null != jsonObject) {
                Map<String, String> map = getReturnCallBackMap(CMBCPayServiceImpl.class, jsonObject);
                CMBCWithdrawPayCallBackRlt cmbcWithdrawPayCallBackRlt = (CMBCWithdrawPayCallBackRlt) packageCodec.decodeXML(map.get("templateXml"), map.get("resXml"));
                if (null != cmbcWithdrawPayCallBackRlt) {
                    logger.info("cmbcScanPayCallBackRlt.getReqMsgId =" + cmbcWithdrawPayCallBackRlt.getReqMsgId());
                    logger.info("cmbcScanPayCallBackRlt.getRespType =" + cmbcWithdrawPayCallBackRlt.getRespType());
                    if (cmbcWithdrawPayCallBackRlt.getRespType().equals("S")){
                        modelResult.setData(cmbcWithdrawPayCallBackRlt);
                        return modelResult;
                    }else{
                        modelResult.setCode(Integer.parseInt(cmbcWithdrawPayCallBackRlt.getRespCode()));
                        modelResult.setMsg(cmbcWithdrawPayCallBackRlt.getRespMsg());
                        return modelResult;
                    }

                }
            }else{
                modelResult.setMsg("解析提现回调异常");
                modelResult.setCode(2000001);
                return modelResult;
            }
        }

        return modelResult;
    }

}