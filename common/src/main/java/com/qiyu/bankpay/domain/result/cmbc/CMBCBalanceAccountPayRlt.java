package com.qiyu.bankpay.domain.result.cmbc;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/19.
 */
public class CMBCBalanceAccountPayRlt extends CMBCBaseRlt implements Serializable {
    //对账日期
    private String settleDate;
    //文件返回类型
    private String fileType;
    //内容
    private String content;
    //备注说明
    private String remark;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
