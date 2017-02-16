package com.qiyu.bankpay.template;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class XmlTemplate implements  InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(XmlTemplate.class);

    private static final String TEMPALTE_FILE_SUFFIX = ".xml";

    private Map<String, String> cmbcXMLCache = new HashMap<String, String>();

    private static final String CMBC_XML_PATH_DIR = "template/";


    @Override
    public void afterPropertiesSet() throws Exception {
        LOG.info("XmlTemplateImpl 加载模板...");
        File cmbcDir = getCMBCXmlDir();
        loadCMBCXml(cmbcDir);

        LOG.info("XmlTemplateImpl 加载模板完成");
    }

    public String getCMBCXmlTemplate(String type) {
        return cmbcXMLCache.get(type);
    }

    /**
     * 加载快捷模板缓存
     * @param yinboDir
     * @throws IOException
     */
    private void loadCMBCXml(File yinboDir) throws IOException {
        Iterator<File> files = FileUtils.iterateFiles(yinboDir, new SuffixFileFilter(TEMPALTE_FILE_SUFFIX), null);

        while (files.hasNext()) {
            File templateFile = files.next();

            String oldContent = FileUtils.readFileToString(templateFile);
            String content = new String(oldContent.getBytes("GBK"),"UTF-8");
            String name = StringUtils.removeEnd(templateFile.getName(), TEMPALTE_FILE_SUFFIX);

            cmbcXMLCache.put(name, content);

            LOG.info("loadCMBCXml name is {}, content is {}", name, content);
        }
    }

    /**
     * @return
     * @throws IOException
     */
    protected File getCMBCXmlDir() throws IOException {
        File yinboDir = new ClassPathResource(CMBC_XML_PATH_DIR + "cmbc").getFile();
        if (!yinboDir.isDirectory()) {
            throw new FileNotFoundException("CMBC XML classpath directory " + CMBC_XML_PATH_DIR + " not exist.");
        }

        return yinboDir;
    }

}
