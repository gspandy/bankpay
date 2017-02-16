package com.qiyu.bankpay.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class HttpClientUtil {

    private static final Logger LOG = LoggerFactory.getLogger(HttpClientUtil.class);

    private static final String A_D = "&";
    private static final String D_D = "=";

    /**
     * 获取httpclient
     *
     * @return
     */
    public static HttpClient createHttpClient() {
        return new HttpClient();
    }

    /**
     * 获取post
     * @param url
     * @return
     */
    public static PostMethod getPostMethod(String url) {
        return new PostMethod(url);
    }

    /**
     * 封装参数
     *
     * @param params
     * @return
     */
    public static NameValuePair[] setParams(Map<String, String> params) {
        if (null == params || params.size() == 0) {
            LOG.error("HttpClientUtil setParams is null!");
            return null;
        }

        List<NameValuePair> nameValues = new ArrayList<NameValuePair>();

        Set<String> set = params.keySet();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String key = it.next();
            nameValues.add(new NameValuePair(key, params.get(key)));
        }

        return nameValues.toArray(new NameValuePair[params.size()]);
    }

    /**
     * 封装返回值(UTF-8)
     *
     * @param data
     * @return
     */
    public static String getResponseBody(byte[] data) {
        return getResponseBody(data, "UTF-8");
    }

    public static JSONObject getJSONResponseBody(byte[] data) {
        return JSONObject.parseObject(getResponseBody(data, "UTF-8"));
    }

    /**
     * 封装返回值
     *
     * @param data
     * @param charset
     * @return
     */
    public static String getResponseBody(byte[] data, String charset) {
        if (null == data || data.length == 0) {
            return null;
        }

        try {
            return new String(data, charset);
        } catch (UnsupportedEncodingException e) {
            LOG.error("HttpClientUtil getResponseBody error : ", e);
            return null;
        }
    }

    /**
     * 解析键值对成map
     * @param body
     * @return
     */
    public static Map<String, String> getResponseMap(String body) {
        Map<String, String> map = new HashMap<String, String>();

        if (StringUtils.isBlank(body)) {
            return null;
        }
        String[] strs = body.split(A_D);
        if (null != strs && strs.length > 0) {
            for (String str : strs) {
                String[] ss = str.split(D_D, 2);
                if (null != ss && ss.length == 2) {
                    map.put(ss[0], ss[1]);
                }
            }
        }

        return map;
    }

}
