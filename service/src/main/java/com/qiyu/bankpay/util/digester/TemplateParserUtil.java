package com.qiyu.bankpay.util.digester;

import com.qiyu.bankpay.util.velocity.VelocityContextHelper;
import com.qiyu.bankpay.util.velocity.VelocityUtil;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 模板加载工具类
 *
 * @author think
 *
 */
public class TemplateParserUtil {

    private static final Logger LOG = LoggerFactory.getLogger(TemplateParserUtil.class);

    public static TemplateParserUtil instance() {
        return new TemplateParserUtil();
    }

    /**
     * 模板加载参数
     *
     * @param template
     * @param object
     * @return
     * @throws Exception
     */
    public String parse(String template, Object object) {
        try {

            Velocity.setProperty(VelocityEngine.RUNTIME_LOG_LOGSYSTEM_CLASS,
                    "org.apache.velocity.runtime.log.NullLogChute");
            Velocity.init();


            VelocityContext context = VelocityContextHelper.instance().fillContext(template, object);
            String content = VelocityUtil.evaluate(context, template);

            return content;
        } catch (Exception e) {
            LOG.error("TemplateParserUtil.parse error", e);
        }
        return null;
    }
}
