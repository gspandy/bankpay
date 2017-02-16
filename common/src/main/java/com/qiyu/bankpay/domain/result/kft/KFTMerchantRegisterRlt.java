package com.qiyu.bankpay.domain.result.kft;

import java.io.Serializable;

/**
 * Created by Qidi on 2017/1/6.
 */
public class KFTMerchantRegisterRlt extends KFTBaseRlt implements Serializable {
    private String subMchId;

    public String getSubMchId() {
        return subMchId;
    }

    public void setSubMchId(String subMchId) {
        this.subMchId = subMchId;
    }
}
