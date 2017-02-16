package com.qiyu.bankpay.domain.request.cmbc;

import java.io.Serializable;

/**
 * Created by Qidi on 2016/11/18.
 */
public class CMBCMerchantSearchPayReq extends  CMBCBaseReq implements Serializable {
    //合作方商户编码
    private String merchantId;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
}
