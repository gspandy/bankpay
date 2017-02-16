package com.qiyu.bankpay.domain.request.cmbc;

import com.qiyu.bankpay.domain.result.cmbc.CMBCBaseRlt;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/9.
 */
public class CMBCWithdrawSearchPayReq extends CMBCBaseReq implements Serializable {
    //原交易流水号
    private String oriReqMsgId;

    public String getOriReqMsgId() {
        return oriReqMsgId;
    }

    public void setOriReqMsgId(String oriReqMsgId) {
        this.oriReqMsgId = oriReqMsgId;
    }
}
