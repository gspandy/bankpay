package com.qiyu.bankpay.domain.result;

import java.io.Serializable;

/**
 * Created by Qidi on 2016/11/18.
 */
public class CallBackRlt implements Serializable {
    private String returnStr;

    public String getReturnStr() {
        return returnStr;
    }

    public void setReturnStr(String returnStr) {
        this.returnStr = returnStr;
    }
}
