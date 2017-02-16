/**
 * Pinganfu.com Inc.
 * Copyright (c) 2003-2013 All Rights Reserved.
 */
package com.qiyu.bankpay.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.BitSet;
import java.util.regex.Pattern;

/**
 * 增强 StringUtil的填充字符串的功能，专门处理一些包含中文的字符串
 *
 */
public class StringUtil {

    private static final Logger LOG = LoggerFactory.getLogger(StringUtil.class);

    private static final BitSet ALPHA = new BitSet(256);
    private static final BitSet ALPHANUM;
    private static final BitSet MARK;
    private static final BitSet RESERVED;
    private static final BitSet UNRESERVED;
    private static int[] HEXADECIMAL;

    public static final String EMPTY_STRING = "";

    static {
        for (int i = 97; i <= 122; i++) {
            ALPHA.set(i);
        }

        for (int i = 65; i <= 90; i++) {
            ALPHA.set(i);
        }

        ALPHANUM = new BitSet(256);

        ALPHANUM.or(ALPHA);

        for (int i = 48; i <= 57; i++) {
            ALPHANUM.set(i);
        }

        MARK = new BitSet(256);

        MARK.set(45);
        MARK.set(95);
        MARK.set(46);
        MARK.set(33);
        MARK.set(126);
        MARK.set(42);
        MARK.set(39);
        MARK.set(40);
        MARK.set(41);

        RESERVED = new BitSet(256);

        RESERVED.set(59);
        RESERVED.set(47);
        RESERVED.set(63);
        RESERVED.set(58);
        RESERVED.set(64);
        RESERVED.set(38);
        RESERVED.set(61);
        RESERVED.set(43);
        RESERVED.set(36);
        RESERVED.set(44);

        UNRESERVED = new BitSet(256);

        UNRESERVED.or(ALPHANUM);
        UNRESERVED.or(MARK);

        HEXADECIMAL = new int[] { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
    }

    /** 判断字符串是否为16进制数字 */
    public static final Pattern HAX_PATTERN = Pattern.compile("^[0-9a-fA-F]+$");

    /** 判断字符串是否为数字 */
    public static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]+$");

    /** 默认中文编码字符集 */
    public static final String DEFAULT_CHINESE_CHARSET = "GBK";

    public static boolean isEmpty(String str) {
        return (str == null) || (str.length() == 0);
    }

    public static String defaultIfBlank(String str) {
        return isBlank(str) ? "" : str;
    }

    public static String obj2String(Object obj) {
        return null == obj ? null : obj.toString();
    }

    public static boolean isBlank(String str) {
        int length;
        if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * 获取指定字符串按GBK编码转换成的byte长度 由于String.getByte方法依赖于操作系统编码，处理中文字符串时建议用此方法
     *
     * @param data
     * @return
     */
    public static byte[] getGBKByte(String data) {
        if (data == null) {
            return new byte[0];
        }
        try {
            return data.getBytes("GBK");
        } catch (Exception e) {
            LOG.info("不支持的Encoding: GBK");
            return data.getBytes();
        }
    }

    public static byte[] getBytes(String data, String encoding) {
        if (data == null) {
            return new byte[0];
        }
        try {
            return data.getBytes(encoding);
        } catch (Exception e) {
            LOG.info("不支持的Encoding: ", encoding);
            return data.getBytes();
        }
    }

    /**
     * 获取指定字符串按GBK编码转换成byte的长度 由于String.getByte方法依赖于操作系统编码，处理中文字符串时建议用此方法
     *
     * @param data
     * @return
     */
    public static int getGBKByteLength(String data) {
        return getGBKByte(data).length;
    }

    public static boolean isNotBlank(String str) {
        int length;
        if ((str == null) || ((length = str.length()) == 0)) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    /**
     * 左靠补位
     *
     * @param str
     *            左靠数据
     * @param size
     *            数据长度
     * @param padStr
     *            右补数据
     * @return
     */
    public static String alignLefts(String str, int size, String padStr) {
        if (str == null)
            return null;

        String padStringFinal = (isBlank(padStr)) ? EMPTY_STRING : padStr;
        int padLen = padStringFinal.length();
        int strLen = str.getBytes().length;
        int pads = size - strLen;

        if (pads <= 0)
            return str;

        if (pads == padLen) {
            return str.concat(padStringFinal);
        } else if (pads < padLen) {
            return str.concat(padStringFinal.substring(0, pads));
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStringFinal.toCharArray();

            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }

            return str.concat(new String(padding));
        }
    }

    /**
     * 右靠补位
     *
     * @param str
     * @param size
     * @param padStr
     * @return
     */
    public static String alignRight(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }

        String padStringFinal = (isEmpty(padStr)) ? EMPTY_STRING : padStr;
        int padLen = padStringFinal.length();
        int strLen = str.getBytes().length;
        int pads = size - strLen;
        if (pads <= 0) {
            return str;
        }

        if (pads == padLen) {
            return padStringFinal.concat(str);
        } else if (pads < padLen) {
            return padStringFinal.substring(0, pads).concat(str);
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStringFinal.toCharArray();

            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }

            return new String(padding).concat(str);
        }
    }

    /**
     * 截取返回报文的数据，得到xml字符串
     *
     * @param resDataStr
     * @return
     */
    public static String getResponseXmlData(String resDataStr){
        //String testXml = "xml=<?xml version=\"1.0\" encoding=\"UTF-8\"?><message><head><version>1.0.0</version><msgType>0002</msgType><chanId>99</chanId><merchantNo>1024</merchantNo><clientDate>20120628142356</clientDate><serverDate>20120626142356</serverDate><tranFlow>10012012062614235612345</tranFlow><tranCode>SC0001</tranCode><respCode>C000000000</respCode><respMsg>交易成功</respMsg></head><body><custId>1</custId><cardNum>1</cardNum><cardInfos><cardInfo><storableCardNo>1</storableCardNo><bankNo>1</bankNo><cardType>1</cardType><phoneNo>1</phoneNo></cardInfo><cardInfo><storableCardNo>2</storableCardNo><bankNo>2</bankNo><cardType>2</cardType><phoneNo>2</phoneNo></cardInfo><cardInfo><storableCardNo>3</storableCardNo><bankNo>3</bankNo><cardType>3</cardType><phoneNo>3</phoneNo></cardInfo></cardInfos></body></message>&mac=f788a734ea51b5a461804acb63038a32";
        String str1[]=resDataStr.split("xml=|&");
        String s = "";
        for(int i=0;i<str1.length;i++){
            s = str1[1];
        }
        return s;
    }

}
