package com.qiyu.bankpay.service;

import com.alibaba.fastjson.JSONObject;
import com.qiyu.bankpay.util.HttpClientUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class HttpClientService {

    private static Logger logger = org.slf4j.LoggerFactory.getLogger(HttpClientService.class);
    private HttpClient client;

    private int status = 0;

    public Map<String, String> postGetMap(String url, Map<String, String> params) throws Exception {
        logger.info("HttpClientServiceImpl.post start...");
        client = HttpClientUtil.createHttpClient();
        HttpClientParams httpParam = client.getParams();
        httpParam.setContentCharset("UTF-8");
        PostMethod postMethod = HttpClientUtil.getPostMethod(url);
        postMethod.addParameters(HttpClientUtil.setParams(params));
        status = client.executeMethod(postMethod);
        if (status == 200) {
            logger.info("HttpClientServiceImpl.post response success!");
        } else {
            logger.warn("HttpClientServiceImpl.post response failure, status = [" + status + "]");
        }
        return HttpClientUtil.getResponseMap(HttpClientUtil.getResponseBody(postMethod.getResponseBody()));
    }


    public JSONObject postGetJSONObject(String url, Map<String, String> params) throws Exception {
        logger.info("HttpClientServiceImpl.post start...");
        client = HttpClientUtil.createHttpClient();
        HttpClientParams httpParam = client.getParams();
        httpParam.setContentCharset("UTF-8");
        PostMethod postMethod = HttpClientUtil.getPostMethod(url);
        postMethod.addParameters(HttpClientUtil.setParams(params));
        status = client.executeMethod(postMethod);
        if (status == 200) {
            logger.info("HttpClientServiceImpl.post response success!");
        } else {
            logger.warn("HttpClientServiceImpl.post response failure, status = [" + status + "]");
        }
        return HttpClientUtil.getJSONResponseBody(postMethod.getResponseBody());
    }

    public byte[] postGetBytes(String url, byte[] data) throws Exception {
        logger.info("HttpClientServiceImpl.post start...");

        client = HttpClientUtil.createHttpClient();

        PostMethod postMethod = HttpClientUtil.getPostMethod(url);

        RequestEntity entity = new ByteArrayRequestEntity(data, "UTF-8");

        postMethod.setRequestEntity(entity);

        status = client.executeMethod(postMethod);

        if (status == 200) {
            logger.info("HttpClientServiceImpl.post response success!");
        } else {
            logger.warn("HttpClientServiceImpl.post response failure, status = [" + status + "]");
        }

        return postMethod.getResponseBody();
    }


    public Map<String, String> postGetMapByStr(String url, String data) throws Exception {
        client = HttpClientUtil.createHttpClient();

        PostMethod postMethod = HttpClientUtil.getPostMethod(url);

        RequestEntity entity = new ByteArrayRequestEntity(data.getBytes(), "UTF-8");

        postMethod.setRequestEntity(entity);

        status = client.executeMethod(postMethod);

        if (status == 200) {
            logger.info("HttpClientServiceImpl.post response success!");
        } else {
            logger.warn("HttpClientServiceImpl.post response failure, status = [" + status + "]");
        }

        return HttpClientUtil.getResponseMap(HttpClientUtil.getResponseBody(postMethod.getResponseBody()));
    }

    public  String sendRequest(String url, String json) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            StringEntity stringEntity = new StringEntity(json, "UTF-8");//解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            long startTime = System.currentTimeMillis();
            response = httpClient.execute(httpPost);
            long endTime = System.currentTimeMillis();
            int statusCode = response.getStatusLine().getStatusCode();
            logger.info("statusCode:" + statusCode);
            logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
            if (statusCode != org.apache.http.HttpStatus.SC_OK) {
                logger.error("Method failed:" + response.getStatusLine());
            }
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  httpStr;
    }


}
