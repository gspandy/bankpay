/**
 * Pinganfu.com Inc.
 * Copyright (c) 2003-2013 All Rights Reserved.
 */
package com.qiyu.bankpay.util.velocity;

import com.qiyu.bankpay.util.StringUtil;
import org.apache.velocity.VelocityContext;

/**
 * VelocityContext相关操作，初始化上下文的工具类和报文数据
 *
 */
public class VelocityContextHelper {

    public static VelocityContextHelper instance() {
        return new VelocityContextHelper();
    }

    /**
     * 填充<code>VelocityContext</code>，包括业务数据对象和相关的工具类
     *
     * @param template
     * @param obj
     * @return
     */
    public VelocityContext fillContext(String template, Object obj) {

        VelocityContext context = new VelocityContext();
        fillMessageContext(context, obj);
        fillUtilContext(context);
        return context;
    }

    /**
     * 报文数据上下文
     *
     * @param context
     * @param
     */
    private void fillMessageContext(VelocityContext context, Object obj) {
        context.put("obj", obj);
    }

    /**
     * 填充工具类上下文
     *
     * @param context
     */
    private void fillUtilContext(VelocityContext context) {
        context.put("stringUtil", create("com.qiyu.bankpay.util.StringUtil"));
    }

    public static StringUtil create(String fieldType) {
        try {
            Class<?> clazz = Class.forName(fieldType);
            return (StringUtil) clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
