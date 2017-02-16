package com.qiyu.bankpay.util.security;


public class EncodeChanger {

    public static String unicode2UnicodeEsc(String uniStr) {
        StringBuffer ret = new StringBuffer();
        if (uniStr == null) {
            return null;
        }
        int maxLoop = uniStr.length();
        for (int i = 0; i < maxLoop; ++i) {
            char character = uniStr.charAt(i);
            if (character <= '') {
                ret.append(character);
            } else {
                ret.append("\\u");
                String hexStr = Integer.toHexString(character);
                int zeroCount = 4 - hexStr.length();
                for (int j = 0; j < zeroCount; ++j) {
                    ret.append('0');
                }
                ret.append(hexStr);
            }
        }
        return ret.toString();
    }

    public static String unicodeEsc2Unicode(String unicodeStr) {
        if (unicodeStr == null) {
            return null;
        }

        StringBuffer retBuf = new StringBuffer();
        int maxLoop = unicodeStr.length();
        for (int i = 0; i < maxLoop; ++i) {
            if (unicodeStr.charAt(i) == '\\') {
                if ((i < maxLoop - 5)
                        && (((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr
                        .charAt(i + 1) == 'U'))))
                    try {
                        retBuf.append((char) Integer.parseInt(
                                unicodeStr.substring(i + 2, i + 6), 16));
                        i += 5;
                    } catch (NumberFormatException e) {
                        retBuf.append(unicodeStr.charAt(i));
                    }
                else
                    retBuf.append(unicodeStr.charAt(i));
            } else {
                retBuf.append(unicodeStr.charAt(i));
            }
        }

        return retBuf.toString();
    }
}
