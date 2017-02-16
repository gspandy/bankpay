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
 */
@Service("industrialPayService")
public class IndustrialPayServiceImpl extends BasePayService implements IndustrialPayService {


    /**
     * 支付宝扫码支付
     *
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult scanZFBPay(SortedMap<String, String> map) throws Exception {
        String key = null;
       /* String key = "9d101c97133837e13dde2d32a5054abb";
        String mchId = "7551000001";*/
       /* map.put("mch_id", mchId);*/
       /* if (!map.containsKey("nonce_str")) {
            map.put("nonce_str", UuidUtil.get32UUID());
        }
        if (map.get("nonce_str").equals(null) || map.get("nonce_str").equals("")) {
            map.put("nonce_str", UuidUtil.get32UUID());
        }*/
        if (map.containsKey("key")) {
            key = map.get("key");
            map.remove("key");
        }
        map.put("service", "pay.alipay.native");
        Map<String, String> industrialScanPay = sendIndustrailPay(map, key, IndustrialPayServiceImpl.class, "兴业支付宝");
        ModelResult modelResult = new ModelResult();
        if (industrialScanPay.containsKey("ctticMsg")) {
            modelResult.setMsg(industrialScanPay.get("ctticMsg"));
            modelResult.setCode(200001);
            return modelResult;
        } else if ("0".equals(industrialScanPay.get("status"))) {
            if ("0".equals(industrialScanPay.get("result_code"))) {
                IndustrialScanPayRlt industrialScanPayRlt = JSONObject.parseObject(JSONObject.toJSONString(industrialScanPay), IndustrialScanPayRlt.class);
                String imageUrl = encodeQrcode(industrialScanPayRlt.getCode_url(), "CMBCQRCODE", ".png", qrCodePath);
                industrialScanPayRlt.setCode_img_url(imageUrl);
                modelResult.setData(industrialScanPayRlt);
                return modelResult;
            } else {
                modelResult.setCode(1);
                modelResult.setMsg(industrialScanPay.get("err_msg"));
                return modelResult;
            }

        } else {
            modelResult.setCode(1);
            modelResult.setMsg("兴业支付宝扫码失败");
            return modelResult;

        }
    }

    /**
     * 扫码支付回调
     *
     * @param resString
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult scanZFBPayCallBack(String resString, String key) throws Exception {
        ModelResult modelResult = new ModelResult();
        // String key = "9d101c97133837e13dde2d32a5054abb";
        Map<String, String> returnMap = returnIndustrialScanCallBack(IndustrialPayServiceImpl.class, resString, key);
        if (returnMap.containsKey("ctticMsg")) {
            modelResult.setMsg(returnMap.get("ctticMsg"));
            modelResult.setCode(200001);
            return modelResult;
        } else if ("0".equals(returnMap.get("status")) && "0".equals(returnMap.get("result_code"))) {
            IndustrialNotifyCallBackRlt industrialNotifyCallBackRlt = JSONObject.parseObject(JSONObject.toJSONString(returnMap), IndustrialNotifyCallBackRlt.class);
            modelResult.setData(industrialNotifyCallBackRlt);
            return modelResult;
        } else {
            modelResult.setMsg("兴业支付宝扫码支付回调失败");
            modelResult.setCode(1);
            return modelResult;
        }
    }


    /**
     * 退款(同中信小额支付退款)
     *
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult<IndustrialRefundPayRlt> industrialZFBRefundPay(SortedMap<String, String> map) throws Exception {
        String key = "9d101c97133837e13dde2d32a5054abb";
        map.put("service", "unified.trade.refund");
        map.put("op_user_id", map.get("mch_id"));
        Map<String, String> ctticPayMap = sendIndustrailPay(map, key, IndustrialPayServiceImpl.class, "兴业支付宝退款");
        ModelResult modelResult = new ModelResult();
        if (null != ctticPayMap) {
            IndustrialRefundPayRlt industrialRefundPayRlt = JSONObject.parseObject(JSONObject.toJSONString(ctticPayMap), IndustrialRefundPayRlt.class);
            modelResult.setData(industrialRefundPayRlt);
            return modelResult;
        } else {
            modelResult.setCode(1);
            modelResult.setMsg("支付宝退款失败");
            return modelResult;
        }
    }

    /**
     * 交易查询 (同中信小额支付交易查询）
     *
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult<IndustrialQueryPayRlt> industrialZFBQueryPay(SortedMap<String, String> map) throws Exception {
        String key = "9d101c97133837e13dde2d32a5054abb";
        map.put("service", "unified.trade.query");
        Map<String, String> ctticPayMap = sendIndustrailPay(map, key, IndustrialPayServiceImpl.class, "兴业交易查询");
        ModelResult modelResult = new ModelResult();
        if (null != ctticPayMap) {
            IndustrialQueryPayRlt industrailQueryPayRlt = JSONObject.parseObject(JSONObject.toJSONString(ctticPayMap), IndustrialQueryPayRlt.class);
            modelResult.setData(industrailQueryPayRlt);
            return modelResult;
        } else {
            modelResult.setCode(1);
            modelResult.setMsg("支付宝查询失败");
            return modelResult;
        }
    }


    /**
     * 微信扫码支付
     *
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult scanWXPay(SortedMap<String, String> map) throws Exception {
        /*String key = "9d101c97133837e13dde2d32a5054abb";
        String mchId = "7551000001";
        map.put("service", "pay.weixin.native");
        map.put("mch_id", mchId);
        if (!map.containsKey("nonce_str")) {
            map.put("nonce_str", UuidUtil.get32UUID());
        }
        if (map.get("nonce_str").equals(null) || map.get("nonce_str").equals("")) {
            map.put("nonce_str", UuidUtil.get32UUID());
        }
        Map<String, String> industrialScanPay = sendIndustrailPay(map, key, IndustrialPayServiceImpl.class, "兴业微信支付扫码");
        ModelResult modelResult = new ModelResult();
        if (null != industrialScanPay) {
            if ("0".equals(industrialScanPay.get("status")) && "0".equals(industrialScanPay.get("result_code"))) {
                IndustrialScanPayRlt industrialScanPayRlt = JSONObject.parseObject(JSONObject.toJSONString(industrialScanPay), IndustrialScanPayRlt.class);
                String imageUrl = encodeQrcode(industrialScanPayRlt.getCode_url(), "CMBCQRCODE", ".png", qrCodePath);
                industrialScanPayRlt.setCode_img_url(imageUrl);
                modelResult.setData(industrialScanPayRlt);
                return modelResult;
            } else {
                modelResult.setCode(1);
                modelResult.setMsg("微信扫码失败");
                return modelResult;
            }
        }
        return modelResult;


*/
        String key = null;
        if (map.containsKey("key")) {
            key = map.get("key");
            map.remove("key");
        }
        map.put("service", "pay.weixin.native");
        Map<String, String> industrialScanPay = sendIndustrailPay(map, key, IndustrialPayServiceImpl.class, "兴业微信支付扫码");
        ModelResult modelResult = new ModelResult();
        if (industrialScanPay.containsKey("ctticMsg")) {
            modelResult.setMsg(industrialScanPay.get("ctticMsg"));
            modelResult.setCode(200001);
            return modelResult;
        } else if ("0".equals(industrialScanPay.get("status"))) {
            if ("0".equals(industrialScanPay.get("result_code"))) {
                IndustrialScanPayRlt industrialScanPayRlt = JSONObject.parseObject(JSONObject.toJSONString(industrialScanPay), IndustrialScanPayRlt.class);
                String imageUrl = encodeQrcode(industrialScanPayRlt.getCode_url(), "CMBCQRCODE", ".png", qrCodePath);
                industrialScanPayRlt.setCode_img_url(imageUrl);
                modelResult.setData(industrialScanPayRlt);
                return modelResult;
            } else {
                modelResult.setCode(1);
                modelResult.setMsg(industrialScanPay.get("err_msg"));
                return modelResult;
            }

        } else {
            modelResult.setCode(1);
            modelResult.setMsg("兴业微信扫码失败");
            return modelResult;

        }

    }


    /**
     * 扫码支付回调
     *
     * @param resString
     * @param key
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult scanWXPayCallBack(String resString, String key) throws Exception {
        ModelResult modelResult = new ModelResult();
        // key = "9d101c97133837e13dde2d32a5054abb";
       /* Map<String, String> returnMap = returnIndustrialScanCallBack(IndustrialPayServiceImpl.class, resString, key);
        if (null != returnMap) {
            if ("0".equals(returnMap.get("status")) && "0".equals(returnMap.get("result_code"))) {
                IndustrialWXNotifyCallBackRlt industrialWXNotifyCallBackRlt = JSONObject.parseObject(JSONObject.toJSONString(returnMap), IndustrialWXNotifyCallBackRlt.class);
                modelResult.setData(industrialWXNotifyCallBackRlt);
                modelResult.setMsg("success");
                return modelResult;
            } else {
                modelResult.setMsg("fail");
                modelResult.setCode(1);
                return modelResult;
            }

        }
        modelResult.setMsg("fail");
        modelResult.setCode(1);
        return modelResult;

*/
        Map<String, String> returnMap = returnIndustrialScanCallBack(IndustrialPayServiceImpl.class, resString, key);
        if (returnMap.containsKey("ctticMsg")) {
            modelResult.setMsg(returnMap.get("ctticMsg"));
            modelResult.setCode(200001);
            return modelResult;
        } else if ("0".equals(returnMap.get("status")) && "0".equals(returnMap.get("result_code"))) {
            IndustrialWXNotifyCallBackRlt industrialWXNotifyCallBackRlt = JSONObject.parseObject(JSONObject.toJSONString(returnMap), IndustrialWXNotifyCallBackRlt.class);
            modelResult.setData(industrialWXNotifyCallBackRlt);
            return modelResult;
        } else {
            modelResult.setMsg("兴业微信扫码支付回调失败");
            modelResult.setCode(1);
            return modelResult;
        }
    }

    /**
     * 兴业微信交易查询
     *
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult<IndustrialWXQueryPayRlt> industrialWXQueryPay(SortedMap<String, String> map) throws Exception {
        String key = "9d101c97133837e13dde2d32a5054abb";
        map.put("service", "unified.trade.query");
        if (!map.containsKey("nonce_str")) {
            map.put("nonce_str", UuidUtil.get32UUID());
        }
        if (map.get("nonce_str").equals(null) || map.get("nonce_str").equals("")) {
            map.put("nonce_str", UuidUtil.get32UUID());
        }
        Map<String, String> ctticPayMap = sendIndustrailPay(map, key, IndustrialPayServiceImpl.class, "兴业微信查询");
        ModelResult modelResult = new ModelResult();
        if (null != ctticPayMap) {
            IndustrialWXQueryPayRlt industrialWXQueryPayRlt = JSONObject.parseObject(JSONObject.toJSONString(ctticPayMap), IndustrialWXQueryPayRlt.class);
            modelResult.setData(industrialWXQueryPayRlt);
            return modelResult;
        } else {
            modelResult.setCode(1);
            modelResult.setMsg("微信查询失败");
            return modelResult;
        }
    }

    /**
     * 兴业微信退款
     *
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult<IndustrialRefundPayRlt> industrialWXRefundPay(SortedMap<String, String> map) throws Exception {
        String key = "37f26e94ad7927977d0582b6f82fd20a";
        map.put("service", "unified.trade.refund");
        map.put("op_user_id", map.get("mch_id"));
        if (!map.containsKey("nonce_str")) {
            map.put("nonce_str", UuidUtil.get32UUID());
        }
        if (map.get("nonce_str").equals(null) || map.get("nonce_str").equals("")) {
            map.put("nonce_str", UuidUtil.get32UUID());
        }
        Map<String, String> ctticPayMap = sendIndustrailPay(map, key, IndustrialPayServiceImpl.class, "兴业微信退款");
        ModelResult modelResult = new ModelResult();
        if (null != ctticPayMap) {
            IndustrialWXRefundPayRlt industrialRefundPayRlt = JSONObject.parseObject(JSONObject.toJSONString(ctticPayMap), IndustrialWXRefundPayRlt.class);
            modelResult.setData(industrialRefundPayRlt);
            return modelResult;
        } else {
            modelResult.setCode(1);
            modelResult.setMsg("支付宝退款失败");
            return modelResult;
        }
    }

    /**
     * 退款查询
     *
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult<IndustrialWXRefundQueryPayRlt> industrialRefundQueryPay(SortedMap<String, String> map) throws Exception {
        String key = "9d101c97133837e13dde2d32a5054abb";
        map.put("service", "unified.trade.refundquery");
        if (!map.containsKey("nonce_str")) {
            map.put("nonce_str", UuidUtil.get32UUID());
        }
        if (map.get("nonce_str").equals(null) || map.get("nonce_str").equals("")) {
            map.put("nonce_str", UuidUtil.get32UUID());
        }
        Map<String, String> ctticPayMap = sendIndustrailPay(map, key, IndustrialPayServiceImpl.class, "兴业退款查询");
        ModelResult modelResult = new ModelResult();
        if (null != ctticPayMap) {
            IndustrialWXRefundQueryPayRlt industrailWXRefundQueryPayRlt = JSONObject.parseObject(JSONObject.toJSONString(ctticPayMap), IndustrialWXRefundQueryPayRlt.class);
            modelResult.setData(industrailWXRefundQueryPayRlt);
            return modelResult;
        } else {
            modelResult.setCode(1);
            modelResult.setMsg("退款查询失败");
            return modelResult;
        }
    }

    /**
     * 关闭订单
     *
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult industrialClosePay(SortedMap<String, String> map) throws Exception {
        String key = "9d101c97133837e13dde2d32a5054abb";
        map.put("service", "unified.micropay.reverse");
        if (!map.containsKey("nonce_str")) {
            map.put("nonce_str", UuidUtil.get32UUID());
        }
        if (map.get("nonce_str").equals(null) || map.get("nonce_str").equals("")) {
            map.put("nonce_str", UuidUtil.get32UUID());
        }
        Map<String, String> ctticPayMap = sendIndustrailPay(map, key, IndustrialPayServiceImpl.class, "兴业关闭订单");
        ModelResult modelResult = new ModelResult();
        if (null != ctticPayMap) {
            IndustrialBaseRlt industrialBaseRlt = JSONObject.parseObject(JSONObject.toJSONString(ctticPayMap), IndustrialBaseRlt.class);
            modelResult.setData(industrialBaseRlt);
            return modelResult;
        } else {
            modelResult.setCode(1);
            modelResult.setMsg("关闭订单失败");
            return modelResult;
        }
    }

    /**
     * 初始化请求接口
     *
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult industrialInitRequestPay(SortedMap<String, String> map, String key) throws Exception {
        map.put("service", "pay.weixin.jspay");
        map.put("nonce_str", UuidUtil.get32UUID());
        map.put("version", "2.0");
//        map.put("is_raw", "0");// 改为从用户端获取，如果不传值，则微信那边默认“0”
        map.put("mch_create_ip", "127.0.0.1");
        map.put("notify_url", "https://union.qiyupay.com/bank/industrialJsCallBack");
        if (map.containsKey("mchId")) {
            map.remove("mchId");
        }
        map.put("mch_id", "101510021361");
        Map<String, String> ctticPayMap = sendIndustrailPay(map, key, IndustrialPayServiceImpl.class, "兴业初始化请求");
        ModelResult modelResult = new ModelResult();

        if (null != ctticPayMap) {
            if (ctticPayMap.containsKey("ctticMsg")) {
                modelResult.setCode(1);
                modelResult.setMsg(ctticPayMap.get("ctticMsg"));
                return modelResult;
            } else {
                IndustrialInitReqPayRlt industrialInitReqPayRlt = JSONObject.parseObject(JSONObject.toJSONString(ctticPayMap), IndustrialInitReqPayRlt.class);
                // String str =  httpClientService.sendRequest("http://lemon613.ticp.net/bankpay/bank/industrialWXScanCallBack",JSONObject.toJSONString(ctticPayMap));
                modelResult.setData(industrialInitReqPayRlt);
                return modelResult;
            }

        } else {
            modelResult.setCode(1);
            modelResult.setMsg("初始化请求失败");
            return modelResult;
        }
    }

    /**
     * js支付通知
     *
     * @param resString
     * @param key
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult industrialJsCallBack(String resString, String key) throws Exception {
        ModelResult modelResult = new ModelResult();
        // String key = "9d101c97133837e13dde2d32a5054abb";
        Map<String, String> returnMap = returnIndustrialScanCallBack(IndustrialPayServiceImpl.class, resString, key);
        if (null != returnMap) {
            if (returnMap.containsKey("ctticMsg")) {
                modelResult.setMsg(returnMap.get("ctticMsg"));
                modelResult.setCode(1);
                return modelResult;
            } else {
                IndustrialWXNotifyCallBackRlt industrialWXNotifyCallBackRlt = JSONObject.parseObject(JSONObject.toJSONString(returnMap), IndustrialWXNotifyCallBackRlt.class);
                modelResult.setData(industrialWXNotifyCallBackRlt);
                return modelResult;
            }

        } else {
            modelResult.setMsg("fail");
            modelResult.setCode(1);
            return modelResult;
        }

}

    @Override
    public ModelResult scanPay(SortedMap<String, String> map, String key) throws Exception {
        String str = null;
        if (map.containsKey("service")) {
            if (map.get("service").contains("weixin")) {
                str = "微信";
            }
            if (map.get("service").contains("alipay")) {
                str = "支付宝";
            }
        }
        Map<String, String> industrialScanPay = sendIndustrailPay(map, key, IndustrialPayServiceImpl.class, "兴业" + str);
        ModelResult modelResult = new ModelResult();
        if (industrialScanPay.containsKey("ctticMsg")) {
            modelResult.setMsg(industrialScanPay.get("ctticMsg"));
            modelResult.setCode(200001);
            return modelResult;
        } else if ("0".equals(industrialScanPay.get("status"))) {
            if ("0".equals(industrialScanPay.get("result_code"))) {
                IndustrialScanPayRlt industrialScanPayRlt = JSONObject.parseObject(JSONObject.toJSONString(industrialScanPay), IndustrialScanPayRlt.class);
                String imageUrl = encodeQrcode(industrialScanPayRlt.getCode_url(), "CMBCQRCODE", ".png", qrCodePath);
                industrialScanPayRlt.setCode_img_url(imageUrl);
                modelResult.setData(industrialScanPayRlt);
                return modelResult;
            } else {
                modelResult.setCode(1);
                modelResult.setMsg(industrialScanPay.get("err_msg"));
                return modelResult;
            }

        } else {
            modelResult.setCode(1);
            modelResult.setMsg("兴业扫码失败");
            return modelResult;

        }
    }

    @Override
    public ModelResult industrialCallBack(String resString, String key) throws Exception {
        ModelResult modelResult = new ModelResult();
        Map<String, String> returnMap = returnIndustrialScanCallBack(IndustrialPayServiceImpl.class, resString, key);
        if (null != returnMap) {
            if ("0".equals(returnMap.get("status")) && "0".equals(returnMap.get("result_code"))) {
                IndustrialWXNotifyCallBackRlt industrialWXNotifyCallBackRlt = JSONObject.parseObject(JSONObject.toJSONString(returnMap), IndustrialWXNotifyCallBackRlt.class);
                modelResult.setData(industrialWXNotifyCallBackRlt);
                modelResult.setMsg("success");
                return modelResult;
            } else {
                modelResult.setMsg(returnMap.get("err_msg"));
                modelResult.setCode(1);
                return modelResult;
            }

        }
        modelResult.setMsg("fail");
        modelResult.setCode(1);
        return modelResult;
    }

}
