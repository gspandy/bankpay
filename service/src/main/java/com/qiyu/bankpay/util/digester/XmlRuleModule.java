package com.qiyu.bankpay.util.digester;

import org.apache.commons.digester3.xmlrules.FromXmlRulesModule;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * xml模板类
 * @author think
 *
 */
public class XmlRuleModule extends FromXmlRulesModule {

    private static final Logger LOG = LoggerFactory.getLogger(XmlRuleModule.class);

    private String xml;

    public XmlRuleModule(String xml) {
        this.xml = xml;
    }

    @Override
    protected void loadRules() {
        loadXMLRules(IOUtils.toInputStream(xml));
    }

}
