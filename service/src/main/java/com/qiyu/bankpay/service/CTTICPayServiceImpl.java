package com.qiyu.bankpay.service;

import com.alibaba.fastjson.JSONObject;
import com.qiyu.bankpay.domain.result.cttic.*;
import com.qiyu.common.result.ModelResult;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.SortedMap;

/**
 * Created by Qidi on 2016/11/14.
 * 中兴银行
 */
@Service("ctticPayService")
public class CTTICPayServiceImpl extends BasePayService implements CTTICPayService{
    /**
     * 小额支付（扫码枪扫码）
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult microPay(SortedMap<String, String> map) throws Exception {
        String key = null;
       /* if (!map.containsKey("key")){
            key = "ed00c91049096e2a845da08519dfba24";
        }*/
       if (map.containsKey("key")){
            key = map.get("key");
            map.remove("key");
       }
        map.put("service", "unified.trade.micropay");
        Map<String, String> ctticPayMap = sendCtticPay(map, key, CTTICPayServiceImpl.class,"中信银行小额支付");
        ModelResult modelResult = new ModelResult();
        if (null != ctticPayMap) {
            if (ctticPayMap.containsKey("ctticMsg")){
                modelResult.setMsg(ctticPayMap.get("ctticMsg"));
                modelResult.setCode(200001);
                return modelResult;
            }
            if ("0".equals(ctticPayMap.get("status")) && "0".equals(ctticPayMap.get("result_code"))) {
                CTTICMicroPayRlt ctticMicroPayRlt = JSONObject.parseObject(JSONObject.toJSONString(ctticPayMap), CTTICMicroPayRlt.class);
                //此处设置code是为了在处理业务时便于判断交易类型
                modelResult.setCode(1001);
                modelResult.setData(ctticMicroPayRlt);
                return modelResult;
            } else if ("0".equals(ctticPayMap.get("status")) && !"0".equals(ctticPayMap.get("result_code"))) {
                if (ctticPayMap.get("need_query").equals("Y")) {
                    Map<String, String> returnQueryMap = null;
                    for (int i = 1; i <= 6; i++) {
                        if (map.containsKey("service")) {
                            map.remove("service");
                            map.put("service", "unified.trade.query");
                        }
                        map.put("op_user_id", map.get("mch_id"));
                        returnQueryMap = sendCtticPay(map, key, CTTICPayServiceImpl.class,"中信银行小额支付中轮循");
                        if (returnQueryMap.containsKey("ctticMsg")){
                            modelResult.setMsg(returnQueryMap.get("ctticMsg"));
                            modelResult.setCode(200001);
                            return modelResult;
                        }
                        if ("0".equals(returnQueryMap.get("status")) && "0".equals(returnQueryMap.get("result_code"))) {
                            if (returnQueryMap.get("trade_state").equalsIgnoreCase("success")) {
                                CTTICMicroQueryPayRlt ctticMicroQueryPayRlt = JSONObject.parseObject(JSONObject.toJSONString(ctticPayMap), CTTICMicroQueryPayRlt.class);
                                modelResult.setCode(1002);
                                modelResult.setData(ctticMicroQueryPayRlt);
                                break;
                            }
                        } else {
                            Thread.sleep(5000);
                        }

                    }
                    if (null != returnQueryMap && (!returnQueryMap.get("trade_state").equalsIgnoreCase("success"))) {
                        if (map.containsKey("service")) {
                            map.remove("service");
                            map.put("service", "unified.micropay.reverse");
                        }
                        Map<String, String> returnRollMap = sendCtticPay(map, key, CTTICPayServiceImpl.class,"中信银行小额支付中冲正");
                        if (returnRollMap.containsKey("ctticMsg")){
                            modelResult.setMsg(returnRollMap.get("ctticMsg"));
                            modelResult.setCode(200001);
                            return modelResult;
                        }
                        if (null != returnRollMap && returnRollMap.get("result_code").equals("0")){
                            CTTICBaseRlt ctticBaseRlt = JSONObject.parseObject(JSONObject.toJSONString(ctticPayMap), CTTICBaseRlt.class);
                            modelResult.setCode(1003);
                            modelResult.setData(ctticBaseRlt);
                            return modelResult;
                        }
                    }
                }
            } else {
                modelResult.setCode(1);
                modelResult.setMsg("扫码枪扫码失败");
                return modelResult;
            }
        }
        return modelResult;
    }

    /**
     * 小额支付退款
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult<CTTICMicroRefundPayRlt> microRefundPay(SortedMap<String,String> map)throws Exception{
      /*  String key = null;
        if (map.containsKey("key")){
            key = map.get("key");
            map.remove("key");
        }*/
        String key = "ed00c91049096e2a845da08519dfba24";
        map.put("service","unified.trade.refund");
        map.put("op_user_id",map.get("mch_id"));
        Map<String,String>  ctticPayMap =  sendCtticPay(map,key,CTTICPayServiceImpl.class,"中信退款");
        ModelResult modelResult = new ModelResult();
        if (null != ctticPayMap){
            CTTICMicroRefundPayRlt ctticMicroRefundPayRlt = JSONObject.parseObject(JSONObject.toJSONString(ctticPayMap), CTTICMicroRefundPayRlt.class);
            modelResult.setData(ctticMicroRefundPayRlt);
            return modelResult;
        }else{
            modelResult.setCode(1);
            modelResult.setMsg("退款失败");
            return modelResult;
        }
    }

    /**
     * 小额支付交易查询
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult microQueryPay(SortedMap<String,String> map)throws Exception{
        String key = null;
        if (map.containsKey("key")){
            key = map.get("key");
            map.remove("key");
        }
        //String key = "ed00c91049096e2a845da08519dfba24";
        map.put("service","unified.trade.query");
        Map<String,String>  ctticPayMap =  sendCtticPay(map,key,CTTICPayServiceImpl.class,"中信查询");
        ModelResult modelResult = new ModelResult();
        if (null != ctticPayMap){
            CTTICMicroQueryPayRlt ctticMicroQueryPayRlt = JSONObject.parseObject(JSONObject.toJSONString(ctticPayMap), CTTICMicroQueryPayRlt.class);
            modelResult.setData(ctticMicroQueryPayRlt);
            return modelResult;
        }else{
            modelResult.setCode(1);
            modelResult.setMsg("查询失败");
            return modelResult;
        }
    }

    /**
     * 冲正
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult microRollPay(SortedMap<String,String> map)throws Exception{
       // String key = "ed00c91049096e2a845da08519dfba24";
        String key = null;
        if (map.containsKey("key")){
            key = map.get("key");
            map.remove("key");
        }
        map.put("service","unified.micropay.reverse");
        Map<String,String>  ctticPayMap =  sendCtticPay(map,key,CTTICPayServiceImpl.class,"中信冲正");
        ModelResult modelResult = new ModelResult();
        if (null != ctticPayMap){
            CTTICBaseRlt ctticBaseRlt = JSONObject.parseObject(JSONObject.toJSONString(ctticPayMap), CTTICBaseRlt.class);
            modelResult.setData(ctticBaseRlt);
            return modelResult;
        }else{
            modelResult.setCode(1);
            modelResult.setMsg("冲正失败");
            return modelResult;
        }
    }

    /**
     * 退款查询
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public ModelResult microRefundQueryPay(SortedMap<String,String> map)throws Exception{
        //String key = "ed00c91049096e2a845da08519dfba24";
        String key = null;
        if (map.containsKey("key")){
            key = map.get("key");
            map.remove("key");
        }
        map.put("service","unified.trade.refundquery");
        Map<String,String>  ctticPayMap =  sendCtticPay(map,key,CTTICPayServiceImpl.class,"中信小额退款交易查询");
        ModelResult modelResult = new ModelResult();
        if (null != ctticPayMap){
            CTTICMicroRefundQueryPayRlt ctticMicroRefundQueryPayRlt = JSONObject.parseObject(JSONObject.toJSONString(ctticPayMap), CTTICMicroRefundQueryPayRlt.class);
            modelResult.setData(ctticMicroRefundQueryPayRlt);
            return modelResult;
        }else{
            modelResult.setCode(1);
            modelResult.setMsg("退款查询失败");
            return modelResult;
        }
    }
}