package com.qiyu.bankpay.domain.result.kft;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/5.
 */
public class KFTBalanceAccountRlt extends  KFTBaseRlt implements Serializable {
    /**
     * 内容
     */
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
