package com.qiyu.bankpay.template;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
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
public class PackageTemplate implements  InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(PackageTemplate.class);

    private static final String TEMPALTE_FILE_SUFFIX = ".vm";

    private Map<String, String> cmbcCache = new HashMap<String, String>();

    private static final String CMBC_TEMPLATE_PATH_DIR = "template/";


    @Override
    public void afterPropertiesSet() throws Exception {
        LOG.info("PackageTemplate 加载模板...");

        File cmbcTemplateDirDir = getCMBCTemplateDir();
        loadTemplates(cmbcTemplateDirDir);

        File ctticTemplateDirDir = getCTTICTemplateDir();
        loadTemplates(ctticTemplateDirDir);
        LOG.info("PackageTemplate 加载模板完成");
    }

    public String getPackageTemplate(String type) {
        return cmbcCache.get(type);
    }

    /**
     * @param yinboDir
     * @throws IOException
     */
    private void loadTemplates(File yinboDir) throws IOException {
        Iterator<File> files = FileUtils.iterateFiles(yinboDir, new SuffixFileFilter(TEMPALTE_FILE_SUFFIX), null);

        while (files.hasNext()) {
            File templateFile = files.next();

            String content = FileUtils.readFileToString(templateFile);

            String name = org.apache.commons.lang3.StringUtils.removeEnd(templateFile.getName(), TEMPALTE_FILE_SUFFIX);

            cmbcCache.put(name, content);
        }
    }

    /**
     * @return
     * @throws IOException
     */
    protected File getCMBCTemplateDir() throws IOException {
        File cmbcDir = new ClassPathResource(CMBC_TEMPLATE_PATH_DIR + "cmbc").getFile();
        if (!cmbcDir.isDirectory()) {
            throw new FileNotFoundException("CMBC classpath directory " + CMBC_TEMPLATE_PATH_DIR + " not exist.");
        }

        return cmbcDir;
    }
    /**
     * @return
     * @throws IOException
     */
    protected File getCTTICTemplateDir() throws IOException {
        File cmbcDir = new ClassPathResource(CMBC_TEMPLATE_PATH_DIR + "cttic").getFile();
        if (!cmbcDir.isDirectory()) {
            throw new FileNotFoundException("CMBC classpath directory " + CMBC_TEMPLATE_PATH_DIR + " not exist.");
        }

        return cmbcDir;
    }


}
