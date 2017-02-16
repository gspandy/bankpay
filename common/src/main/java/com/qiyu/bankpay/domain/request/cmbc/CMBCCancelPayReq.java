package com.qiyu.bankpay.domain.request.cmbc;

import java.io.Serializable;

/**
 * Created by Qidi on 2016/11/18.
 */
public class CMBCCancelPayReq extends  CMBCBaseReq implements Serializable {
    //原交易流水号
    private String oriReqMsgId;

    public String getOriReqMsgId() {
        return oriReqMsgId;
    }

    public void setOriReqMsgId(String oriReqMsgId) {
        this.oriReqMsgId = oriReqMsgId;
    }
}
