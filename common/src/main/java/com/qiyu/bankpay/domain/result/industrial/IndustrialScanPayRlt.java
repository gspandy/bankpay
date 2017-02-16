package com.qiyu.bankpay.domain.result.industrial;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/6.
 */
public class IndustrialScanPayRlt extends IndustrialBaseRlt implements Serializable {
    //设备号
    private String device_info;
    //二维码链接
    private String code_url;
    //二维码图片
    private String code_img_url;

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getCode_url() {
        return code_url;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }

    public String getCode_img_url() {
        return code_img_url;
    }

    public void setCode_img_url(String code_img_url) {
        this.code_img_url = code_img_url;
    }
}
