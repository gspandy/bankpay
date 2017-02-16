package com.qiyu.bankpay.domain.result.kft;

import com.qiyu.bankpay.domain.result.CallBackRlt;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/24.
 */
public class KFTBaseRlt extends CallBackRlt implements Serializable {
    //返回状态码
    private String returnCode;
    //返回信息
    private String returnMsg;
    //签名
    private String sign;
    //业务结果
    private String resultCode;
    //错误代码
    private String errCode;
    //错误代码描述
    private String errCodeDes;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }
}
