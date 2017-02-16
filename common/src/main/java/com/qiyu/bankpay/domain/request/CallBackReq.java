package com.qiyu.bankpay.domain.request;

import java.io.Serializable;

/**
 * Created by Qidi on 2016/11/18.
 */
public class CallBackReq implements Serializable {
    /**
     * 回调字符串
     */
    private String returnStr;

    public String getReturnStr() {
        return returnStr;
    }

    public void setReturnStr(String returnStr) {
        this.returnStr = returnStr;
    }
}
