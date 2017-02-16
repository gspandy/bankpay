package com.qiyu.bankpay.util.digester;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.binder.DigesterLoader;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.InputStream;


@Component
public class DigesterRuleLoader {

    private static final Logger LOG = LoggerFactory.getLogger(DigesterRuleLoader.class);

    private Digester digester;
    /**
     * 解析xml
     * @param
     * @param xml
     * @return
     */
    public Object parseXML(String template, String xml) {

        try {
            DigesterLoader loader = DigesterLoader.newLoader(new XmlRuleModule(template));
            this.digester = loader.newDigester();
            InputStream in = IOUtils.toInputStream(xml, "UTF8");
            return this.digester.parse(in);
        } catch (Exception e) {
            LOG.error("DigesterRuleLoader parseXML is error", e);
            return null;
        }
    }


}
