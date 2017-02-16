package com.qiyu.bankpay.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by Administrator on 2016/11/16.
 */
public class Test {

    public static void main(String[] args) {
        String str = "\"returnCode\":\"0\",\"resultCode\":\"0\",\"sign\":\"E8EDA563722DDBBF46FC36DA62510E69\",\"outChannelNo\":\"00001902201612260000000007\",\"status\":\"02\",\"channel\":\"wxPubQR\",\"body\":\"etyeyertty\",\"outTradeNo\":\"9666666666666\",\"amount\":0.01,\"currency\":\"CNY\",\"transTime\":\"20161226155807\"";
        Map map =  transStringToMap(str);
        System.out.print("---"+map.size());

        String str1 = "12242321311娴嬭瘯001";
        try {
            str1 = new String(str1.getBytes("gbk"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.print("str1---"+str1);


    }
    public static Map transStringToMap(String mapString){
        Map map = new HashMap();
        java.util.StringTokenizer items;
        for(StringTokenizer entrys = new StringTokenizer(mapString, "^"); entrys.hasMoreTokens();
            map.put(items.nextToken(), items.hasMoreTokens() ? ((Object) (items.nextToken())) : null))
            items = new StringTokenizer(entrys.nextToken(), "'");
        return map;
    }
}
