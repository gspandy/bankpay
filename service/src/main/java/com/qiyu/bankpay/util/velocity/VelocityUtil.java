/**
 * Copyright (c) 2003-2013 All Rights Reserved.
 */
package com.qiyu.bankpay.util.velocity;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.VelocityException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * velocity模板渲染工具类，模板是配置在数据库的字符串<br>
 * 底层工具类不消灭异常，由上层显式处理
 *
 */
public final class VelocityUtil {

    /** logger */
    private static final Logger logger    = Logger.getLogger("UTF-8");

    /**
     * 根据报文模板和<code>VelocityContext</code>，渲染出实际的业务报文
     *
     * @param context 业务上下文信息
     * @param template 模板
     * @return
     * @throws IOException
     * @throws VelocityException
     * @throws MethodInvocationException
     * @throws ParseErrorException
     */
    public static String evaluate(VelocityContext context, String template) throws IOException, ParseErrorException, MethodInvocationException, VelocityException {
        Writer writer = new StringWriter();
        try {
            Velocity.evaluate(context, writer, StringUtils.EMPTY, template);
            return writer.toString();
        } finally {
            if (null != writer)
                writer.close();
        }
    }

}
