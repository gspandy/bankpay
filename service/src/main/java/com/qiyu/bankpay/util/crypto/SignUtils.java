/**
 * Project Name:payment
 * File Name:SignUtils.java
 * Package Name:cn.swiftpass.utils.payment.sign
 * Date:2014-6-27下午3:22:33
 */

package com.qiyu.bankpay.util.crypto;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Sets;
import com.qiyu.common.utils.Digests;
import com.qiyu.common.utils.Encodes;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;


/**
 * ClassName:SignUtils
 * Function: 签名用的工具箱
 * Date:     2014-6-27 下午3:22:33
 * @author
 */
public class SignUtils {

    /** <一句话功能简述>
     * <功能详细描述>验证返回参数
     * @param params
     * @param key
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean checkParam(Map<String, String> params, String key) {
        boolean result = false;
        if (params.containsKey("sign")) {
            String sign = params.get("sign");
            //System.out.println("sign--------"+sign);
            params.remove("sign");

            Map<String,String> filterMap = paraFilter(params);
            StringBuilder buf = new StringBuilder((filterMap.size() + 1) * 10);
            SignUtils.buildPayParams(buf, filterMap, false);
            String preStr = buf.toString();
            //System.out.println("preStr-------"+preStr);
            String signRecieve = MD5.sign(preStr, "&key=" + key, "utf-8");
            //System.out.println("signRecieve------"+signRecieve);
            result = sign.equalsIgnoreCase(signRecieve);
        }
        return result;
    }

    /**
     * 过滤参数
     * @author
     * @param sArray
     * @return
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {
        Map<String, String> result = new HashMap<String, String>(sArray.size());
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        DecimalFormat formater = new DecimalFormat("###0.00");
        for (String key : sArray.keySet()) {
            Object value = sArray.get(key);
            String finalValue = null;
            if(value instanceof BigDecimal){
                finalValue = formater.format(value);
            }else {
                finalValue = (String) value;
            }
            if (value == null || value.equals("")
                    || key.equalsIgnoreCase("sign")) {
                continue;
            }
            result.put(key, finalValue);
        }

        return result;
    }

    /** <一句话功能简述>
     * <功能详细描述>将map转成String
     * @param payParams
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String payParamsToString(Map<String, String> payParams) {
        return payParamsToString(payParams, false);
    }

    public static String payParamsToString(Map<String, String> payParams, boolean encoding) {
        return payParamsToString(new StringBuilder(), payParams, encoding);
    }

    /**
     * @author
     * @param payParams
     * @return
     */
    public static String payParamsToString(StringBuilder sb, Map<String, String> payParams, boolean encoding) {
        buildPayParams(sb, payParams, encoding);
        return sb.toString();
    }

    /**
     * @author
     * @param payParams
     * @return
     */
    public static void buildPayParams(StringBuilder sb, Map<String, String> payParams, boolean encoding) {
        List<String> keys = new ArrayList<String>(payParams.keySet());
        Collections.sort(keys);
        for (String key : keys) {
            sb.append(key).append("=");
            if (encoding) {
                sb.append(urlEncode(String.valueOf(payParams.get(key))));
            } else {
                sb.append(String.valueOf(payParams.get(key)));
            }
            sb.append("&");
        }
        sb.setLength(sb.length() - 1);
    }

    public static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Throwable e) {
            return str;
        }
    }


    public static Element readerXml(String body, String encode) throws DocumentException {
        SAXReader reader = new SAXReader(false);
        InputSource source = new InputSource(new StringReader(body));
        source.setEncoding(encode);
        Document doc = reader.read(source);
        Element element = doc.getRootElement();
        return element;
    }

    public static String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

    public static String createSignPlainText(final Object dataInfo, final boolean isWithEmpty, final String... filters) {
        if (dataInfo instanceof String) {
            return (String) dataInfo;
        }
        if (dataInfo instanceof Map) {
            Map<String, Object> dataMap = (Map<String, Object>) dataInfo;
            return createMapSignPlainText(dataMap, isWithEmpty, filters);
        }
        String signJsonPlainText = JSONObject.toJSONString(dataInfo);
        JSONObject jsonObject = JSONObject.parseObject(signJsonPlainText);
        return createMapSignPlainText(jsonObject, isWithEmpty, filters);
    }

    private static String createMapSignPlainText(Map<String, Object> dataMap, final boolean isWithEmpty, final String... filters) {
        final StringBuilder sb = new StringBuilder();
        final Set<String> filterSet = Sets.newHashSet(filters);
        List<String> list = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            Object value = entry.getValue();
            if (!isWithEmpty) {
                if (value == null) {
                    continue;
                }
                if (value instanceof String && StringUtils.isBlank((String) value)) {
                    continue;
                }
            }
            String keyName = entry.getKey();
            if (filterSet.contains(keyName)) {
                continue;
            }
            if ("sign".equalsIgnoreCase(keyName.toLowerCase())) {
                continue;
            }
            list.add(keyName + "=" + entry.getValue());
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
            if (i < size - 1) {
                sb.append("&");
            }
        }
        return sb.toString();
    }


    public static String md5(String plainText) {
        try {
            return Encodes.encodeHex(Digests.md5(new ByteArrayInputStream(plainText.getBytes("utf-8"))));
        } catch (Exception ex) {
            return "";
        }
    }
    public static String genSign(String key,String str){
        return md5(str+"&key="+key).toUpperCase();
    }


}

