package com.qiyu.bankpay.domain.request.cmbc;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/9.
 */
public class CMBCWithdrawPayReq extends CMBCBaseReq implements Serializable {
    //银行商户编码
    private String merchantCode;

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }
}
