package com.qiyu.bankpay.service;

import com.alibaba.fastjson.JSONObject;
import com.qiyu.bankpay.domain.result.CallBackRlt;
import com.qiyu.bankpay.domain.result.kft.*;
import com.qiyu.bankpay.util.crypto.SignUtils;
import com.qiyu.common.result.ModelResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.SortedMap;

/**
 * Created by Administrator on 2016/11/24.
 */
@Service("kftPayService")
public class KFTPayServiceImpl extends BasePayService implements KFTPayService {

    Logger logger = LoggerFactory.getLogger(KFTPayServiceImpl.class);

    /**
     * 生成收款二维码
     *
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult<CallBackRlt> generateQRcode(SortedMap<String, String> map) throws Exception {
        String mchId = "000010001";
        String key = "b2ea60be9994425886681ed1fce5e70a";
        String tradeType = "cs.pay.qrcode";
        if (!map.containsKey("mchId")) {
            map.put("mchId", "00001000003");
        }
        if (!map.containsKey("tradeType")) {
            map.put("tradeType", "cs.pay.qrcode");
        }
        Map<String, String> returnMap = sendKFTPay(kftServerUrl, map, key, KFTPayServiceImpl.class);
        ModelResult modelResult = new ModelResult();
        if (null != returnMap) {
            KFTGenQrCodeRlt kftGenQrCodeRlt = JSONObject.parseObject(JSONObject.toJSONString(returnMap), KFTGenQrCodeRlt.class);
            String imageUrl = encodeQrcode(kftGenQrCodeRlt.getCodeUrl(), "KFTQRCODE", ".png", qrCodePath);
            kftGenQrCodeRlt.setReturnStr(imageUrl);
            modelResult.setData(kftGenQrCodeRlt);
            return modelResult;
        } else {
            modelResult.setCode(1);
            modelResult.setMsg("商户固定二维码生成失败");
            return modelResult;
        }
    }


    /**
     * 下单
     *
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult<KFTPayRlt> kftPay(SortedMap<String, String> map) throws Exception {
       /* String mchId = "00001000003";
        String key = "1c75e136c30745f29c74880dff6c32d7";
        String tradeType = "cs.pay.submit";
        if (!map.containsKey("mchId")){
            map.put("mchId","00001000003");
        }if (!map.containsKey("tradeType")){
            map.put("tradeType","cs.pay.qrcode");
        }*/
        String key = null;
        if (map.containsKey("key")) {
            key = map.get("key");
            map.remove("key");
        }
        key =  "b2ea60be9994425886681ed1fce5e70a";
        String channel = map.get("channel");
        Map<String, String> returnMap = requestKFTPay(kftServerUrl, map, key, KFTPayServiceImpl.class);
        ModelResult modelResult = new ModelResult();
        if (returnMap.containsKey("ctticMsg")) {
            modelResult.setMsg(returnMap.get("ctticMsg"));
            modelResult.setCode(200001);
            return modelResult;
        }
        //微信公众账号扫码支付或支付宝扫码支付
        if (channel.equals("wxPubQR") || channel.equals("alipayQR")) {
            KFTPayRlt kftPayRlt = JSONObject.parseObject(JSONObject.toJSONString(returnMap), KFTPayRlt.class);
            String imageUrl = encodeQrcode(kftPayRlt.getCodeUrl(), "KFTQRCODE", ".png", qrCodePath);
            kftPayRlt.setReturnStr(imageUrl);
            modelResult.setData(kftPayRlt);
            return  modelResult;
        } else if (channel.equals("wxApp")) {
            //微信app支付
            //请商户调用sdk控件发起支付
            KFTPayRlt kftPayRlt = JSONObject.parseObject(JSONObject.toJSONString(returnMap), KFTPayRlt.class);
            modelResult.setData(kftPayRlt);
            return  modelResult;
        } else if (channel.equals("wxMicro") || channel.equals("alipayMicro")) {
            //微信付款码支付或支付宝付款码支付
            KFTPayRlt kftPayRlt = JSONObject.parseObject(JSONObject.toJSONString(returnMap), KFTPayRlt.class);
            modelResult.setData(kftPayRlt);
            return  modelResult;
            //支付成功后的处理，更新订单状态等
        } else if (channel.equals("wxPub")) {
            KFTPayRlt kftPayRlt = JSONObject.parseObject(JSONObject.toJSONString(returnMap), KFTPayRlt.class);
            modelResult.setData(kftPayRlt);
            return  modelResult;
        }else{
            modelResult.setCode(200001);
            modelResult.setMsg("我想骂人");
            return modelResult;
        }

}

    /**
     * 交易查询
     *
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult kftQuery(SortedMap<String, String> map) throws Exception {
        String mchId = "00001000003";
        String key = "b2ea60be9994425886681ed1fce5e70a";
        String tradeType = "cs.pay.submit";
        if (!map.containsKey("mchId")) {
            map.put("mchId", "000010001");
        }
        if (!map.containsKey("tradeType")) {
            map.put("tradeType", "cs.trade.single.query");
        }
        Map<String, String> returnMap = sendKFTPay(kftServerUrl, map, key, KFTPayServiceImpl.class);
        ModelResult modelResult = new ModelResult();
        if (null != returnMap) {
            KFTQueryRlt kftQueryRlt = JSONObject.parseObject(JSONObject.toJSONString(returnMap), KFTQueryRlt.class);
            modelResult.setData(kftQueryRlt);
            return modelResult;
        } else {
            modelResult.setCode(1);
            modelResult.setMsg("交易查询失败");
            return modelResult;
        }
    }

    /**
     * 退款
     *
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult kftRefund(SortedMap<String, String> map) throws Exception {
        String mchId = "000010001";
        String key = "b2ea60be9994425886681ed1fce5e70a";
        String tradeType = "cs.refund.apply";
        if (!map.containsKey("mchId")) {
            map.put("mchId", "000010001");
        }
        if (!map.containsKey("tradeType")) {
            map.put("tradeType", "cs.refund.apply");
        }
        Map<String, String> returnMap = sendKFTPay(kftServerUrl, map, key, KFTPayServiceImpl.class);
        ModelResult modelResult = new ModelResult();
        if (null != returnMap) {
            KFTRefundRlt kftRefundRlt = JSONObject.parseObject(JSONObject.toJSONString(returnMap), KFTRefundRlt.class);
            modelResult.setData(kftRefundRlt);
            return modelResult;
        } else {
            modelResult.setCode(1);
            modelResult.setMsg("退款失败");
            return modelResult;
        }
    }

    /**
     * 获取对账文件
     *
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult kftDownload(SortedMap<String, String> map) throws Exception {
        String mchId = "00001000003";
        String key = "1c75e136c30745f29c74880dff6c32d7";
        String tradeType = "cs.bill.download";
        if (!map.containsKey("mchId")) {
            map.put("mchId", "00001000003");
        }
        if (!map.containsKey("tradeType")) {
            map.put("tradeType", tradeType);
        }
        Map<String, String> returnMap = sendKFTPay(kftServerUrl, map, key, KFTPayServiceImpl.class);
        ModelResult modelResult = new ModelResult();
        if (null != returnMap) {
            KFTBalanceAccountRlt kftBalanceAccountRlt = JSONObject.parseObject(JSONObject.toJSONString(returnMap), KFTBalanceAccountRlt.class);
            modelResult.setData(kftBalanceAccountRlt);
            return modelResult;
        } else {
            modelResult.setCode(1);
            modelResult.setMsg("获取对账文件失败");
            return modelResult;
        }
    }

    /**
     * 回调
     *
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult<KFTPayCallBackRlt> kftPayCallBack(Map<String, String> map,String key) throws Exception {
        ModelResult modelResult = new ModelResult();
        if (map.containsKey("sign")) {
            if (!SignUtils.checkParam(map, key)) {
                modelResult.setMsg("验签失败");
                modelResult.setCode(20001);
                logger.info("验签失败");
                return modelResult;
            } else {
                if ("0".equals(map.get("returnCode")) && "0".equals(map.get("resultCode"))) {
                    KFTPayCallBackRlt kftPayCallBackRlt = JSONObject.parseObject(JSONObject.toJSONString(map), KFTPayCallBackRlt.class);
                    modelResult.setData(kftPayCallBackRlt);
                    return modelResult;
                } else {
                    logger.info("请求失败！");
                    modelResult.setMsg(map.get("returnMsg") + "" + map.get("errCodeDes"));
                    modelResult.setCode(Integer.parseInt(map.get("returnCode")));
                    return modelResult;
                }
            }
        } else {
            logger.info("参数有误！没有sign");
            modelResult.setMsg("参数有误");
            modelResult.setCode(200001);
            return modelResult;
        }
    }

    @Override
    public ModelResult<KFTMerchantRegisterRlt> kftMerchantRegist(SortedMap<String, String> map) throws Exception {
        String mchId = "000010001";
        String key = null;
        if (!map.containsKey("key")){
             key = "b2ea60be9994425886681ed1fce5e70a";
        }
        if (!map.containsKey("mchId")) {
            map.put("mchId", "000010001");
        }
        if (!map.containsKey("tradeType")) {
            map.put("tradeType", "cs.merchant.register");
        }
        Map<String, String> returnMap = sendKFTPay(kftServerUrl, map, key, KFTPayServiceImpl.class);
        ModelResult modelResult = new ModelResult();
        if (null != returnMap) {
            KFTMerchantRegisterRlt kftMerchantRegisterRlt = JSONObject.parseObject(JSONObject.toJSONString(returnMap), KFTMerchantRegisterRlt.class);
            modelResult.setData(kftMerchantRegisterRlt);
            return modelResult;
        } else {
            modelResult.setCode(1);
            modelResult.setMsg("商户登记失败");
            return modelResult;
        }
    }

    @Override
    public ModelResult<KFTMerchantModifyRlt> kftMerchantModify(SortedMap<String, String> map) throws Exception {
        String mchId = "000010001";
        String key = null;
        if (!map.containsKey("key")){
            key = "b2ea60be9994425886681ed1fce5e70a";
        }
        if (!map.containsKey("mchId")) {
            map.put("mchId", "000010001");
        }
        if (!map.containsKey("tradeType")) {
            map.put("tradeType", "cs.merchant.modify");
        }
        Map<String, String> returnMap = sendKFTPay(kftServerUrl, map, key, KFTPayServiceImpl.class);
        ModelResult modelResult = new ModelResult();
        if (null != returnMap) {
            KFTMerchantModifyRlt kftMerchantModifyRlt = JSONObject.parseObject(JSONObject.toJSONString(returnMap), KFTMerchantModifyRlt.class);
            modelResult.setData(kftMerchantModifyRlt);
            return modelResult;
        } else {
            modelResult.setCode(1);
            modelResult.setMsg("商户修改失败");
            return modelResult;
        }
    }

    @Override
    public ModelResult<KFTMerchantStateQueryRlt> kftMerchantStateQuery(SortedMap<String, String> map) throws Exception {
        String mchId = "000010001";
        String key = null;
        if (!map.containsKey("key")){
            key = "b2ea60be9994425886681ed1fce5e70a";
        }
        if (!map.containsKey("mchId")) {
            map.put("mchId", "000010001");
        }
        if (!map.containsKey("tradeType")) {
            map.put("tradeType", "cs_merchant_querydetail");
        }
        Map<String, String> returnMap = sendKFTPay(kftServerUrl, map, key, KFTPayServiceImpl.class);
        ModelResult modelResult = new ModelResult();
        if (null != returnMap) {
            KFTMerchantStateQueryRlt kftMerchantStateQueryRlt = JSONObject.parseObject(JSONObject.toJSONString(returnMap), KFTMerchantStateQueryRlt.class);
            modelResult.setData(kftMerchantStateQueryRlt);
            return modelResult;
        } else {
            modelResult.setCode(1);
            modelResult.setMsg("商户状态查询失败");
            return modelResult;
        }
    }
}
