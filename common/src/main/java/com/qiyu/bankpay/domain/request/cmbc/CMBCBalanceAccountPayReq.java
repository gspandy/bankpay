package com.qiyu.bankpay.domain.request.cmbc;

import java.io.Serializable;

/**
 * Created by Qidi on 2016/11/19.
 */
public class CMBCBalanceAccountPayReq extends  CMBCBaseReq implements Serializable{
    //对账日期
    private String settleDate;
    //文件返回类型
    private String fileType;

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
