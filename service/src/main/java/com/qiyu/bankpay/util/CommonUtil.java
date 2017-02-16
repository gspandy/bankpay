package com.qiyu.bankpay.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <strong>Title : CommonUtil</strong><br>
 * <strong>Description : 通用工具类</strong><br>
 */
public final class CommonUtil {

    private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);


    /**
     * 生成指定长度的随机字符串
     *
     * @param length
     *            指定字符串长度
     * @return
     */
    public static String generateLenString(int length) {
        char[] cResult = new char[length];
        int[] flag = { 0, 0, 0 }; // A-Z, a-z, 0-9
        int i = 0;
        while (flag[0] == 0 || flag[1] == 0 || flag[2] == 0 || i < length) {
            i = i % length;
            int f = (int) (Math.random() * 3 % 3);
            if (f == 0)
                cResult[i] = (char) ('A' + Math.random() * 26);
            else if (f == 1)
                cResult[i] = (char) ('a' + Math.random() * 26);
            else
                cResult[i] = (char) ('0' + Math.random() * 10);
            flag[f] = 1;
            i++;
        }
        return new String(cResult);
    }
    /**
     * 读取request流
     * @return
     * @author guoyx
     */
    public static String readReqStr(HttpServletRequest request)
    {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try
        {
            reader = new BufferedReader(new InputStreamReader(request
                    .getInputStream(), "utf-8"));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                sb.append(line);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (null != reader)
                {
                    reader.close();
                }
            } catch (IOException e)
            {

            }
        }
        return sb.toString();
    }
    /**
     * str空判断
     * @param str
     * @return
     * @author guoyx
     */
    public static boolean isnull(String str)
    {
        if (null == str || str.equalsIgnoreCase("null") || str.trim().equals(""))
        {
            return true;
        } else
            return false;
    }
    public static void main(String[] args) {
        String a="null";
        System.out.println(isnull(a));
    }

    /**
     * 生成待签名串
     * @return
     * @author guoyx
     */
    public static String getPlainText(Map<String, Object> params)
    {
        StringBuffer content = new StringBuffer();

        // 按照key做首字母升序排列
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < keys.size(); i++)
        {
            String key = (String) keys.get(i);
            if ("sign".equals(key))
            {
                continue;
            }
            String value = (String)params.get(key);
            // 空串不参与签名
            if (isnull(value))
            {
                continue;
            }
            content.append((i == 0 ? "" : "&") + key + "=" + value);

        }
        String signSrc = content.toString();
        if (signSrc.startsWith("&"))
        {
            signSrc = signSrc.replaceFirst("&", "");
        }
        return signSrc;
    }

    /**
     * 生成待签名串
     * @return
     * @author guoyx
     * @throws UnsupportedEncodingException
     */
    public static String genSignData(Map<String, String> params) throws UnsupportedEncodingException
    {
        StringBuffer content = new StringBuffer();
        String charset = "UTF-8";
        // 按照key做首字母升序排列
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < keys.size(); i++)
        {
            String key = (String) keys.get(i);
            if ("sign".equals(key))
            {
                continue;
            }
            String value = params.get(key);
            // 空串不参与签名
            if (CommonUtil.isnull(value))
            {
                continue;
            }
            content.append((i == 0 ? "" : "&") + key + "=" + value);

        }
        String signSrc = content.toString();
        if (signSrc.startsWith("&"))
        {
            signSrc = signSrc.replaceFirst("&", "");
        }
        return signSrc;
    }
//	public static JSONObject formStr2Json(String content){
//		JSONObject reqObj = new JSONObject();
//		String splitStr = "&";
//		String data[] = content.split(splitStr);
//		for(int i=0;i<data.length;i++){
//			String value = data[i];
//			String[] temp = value.split("=");
//			if(temp.length>=2){
//				reqObj.put(temp[0], value.substring(value.indexOf("=")+1, value.length()));
//			}else if(temp.length==1){
//				reqObj.put(temp[0], "");
//			}
//		}
//		return reqObj;
//	}
}