package com.qiyu.bankpay.domain.result.kft;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/24.
 */
public class KFTGenQrCodeRlt extends  KFTBaseRlt implements Serializable{
    private String codeUrl;

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }
}
