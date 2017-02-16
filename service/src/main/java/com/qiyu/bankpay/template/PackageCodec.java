package com.qiyu.bankpay.template;

import com.qiyu.bankpay.util.digester.DigesterRuleLoader;
import com.qiyu.bankpay.util.digester.TemplateParserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PackageCodec {

    private static final Logger LOG = LoggerFactory.getLogger(PackageCodec.class);

    @Autowired
    private DigesterRuleLoader digesterRuleLoader;

    public String decode(String template, Object object) throws Exception {
        LOG.info("PackageCodecImpl.decode decode.template is {}", template);

        try {
            // 模板生成数据
            String data = TemplateParserUtil.instance().parse(template, object);

            if (LOG.isDebugEnabled()) {
                LOG.debug("PackageCodecImpl encode data {}.", data);
            }

            if (null == data) {
                throw new Exception("PackageCodecImpl encode data is null");
            }

            return data;
        } catch (Exception e) {
            LOG.error("PackageCodecImpl.decode is error", e);
            throw new Exception(e);
        }
    }

    public Object decodeXML(String template, String xml) throws Exception {
        LOG.info("PackageCodecImpl.decodeXML template is {}, xml is {}", template, xml);
        try {
            Object bean = digesterRuleLoader.parseXML(template, xml);

            if (null == bean) {
                throw new Exception("PackageCodecImpl.decodeXML bean is null");
            }
            return bean;
        } catch (Exception e) {
            LOG.error("PackageCodecImpl.decodeXML is error", e);
            throw new Exception(e);
        }
    }


}
