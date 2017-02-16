package com.qiyu.bankpay.domain.result.cmbc;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/17.
 */
public class CMBCScanPayRlt extends CMBCBaseRlt implements Serializable {

    //二维码链接
    private String qrCode;

    //图片路径
    private String imageUrl;

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
