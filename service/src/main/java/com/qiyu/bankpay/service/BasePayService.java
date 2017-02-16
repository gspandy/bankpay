package com.qiyu.bankpay.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.internal.util.codec.Base64;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.qiyu.bankpay.domain.constant.Constant;
import com.qiyu.bankpay.template.PackageCodec;
import com.qiyu.bankpay.template.PackageTemplate;
import com.qiyu.bankpay.template.XmlTemplate;
import com.qiyu.bankpay.util.crypto.CryptoUtil;
import com.qiyu.bankpay.util.crypto.MD5;
import com.qiyu.bankpay.util.crypto.SignUtils;
import com.qiyu.bankpay.util.crypto.XmlUtils;
import com.qiyu.common.result.ModelResult;
import com.qiyu.upload.domain.request.UploadBase64Req;
import com.qiyu.upload.domain.result.UploadBase64Rlt;
import com.qiyu.upload.service.UploadService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

/**
 * Created by Administrator on 2016/11/16.
 */

@Service
public class BasePayService {
    @Autowired
    public PackageTemplate packageTemplate;
    @Autowired
    public PackageCodec packageCodec;
    @Autowired
    public HttpClientService httpClientService;
    @Autowired
    public UploadService uploadService;
    @Autowired
    public XmlTemplate xmlTemplate;

    @Value("${cmbc.cooperator}")
    public String cooperator;
    @Value("${cmbc.cmbc_server_url}")
    public String cmbcServerUrl;
    @Value("${cmbc.cmbc_qrCodePath}")
    public String qrCodePath;
    @Value("${cttic.cttic_server_url}")
    public String ctticServerUrl;
    @Value("${kft.kft_server_url}")
    public String kftServerUrl;
    @Value("${industrail.industrail_server_url}")
    public String industrailServerUrl;
    @Value("${ali.ali_server_url}")
    public String aliServerUrl;
    @Value("${ali.ali_app_id}")
    public String aliAppId;
    @Value("${ali.ali_private_key}")
    public String aliPrivateKey;
    @Value("${ali.ali_public_key}")
    public String aliPublicKey;
    @Value("${ali.ali_format}")
    public String aliFormat;
    @Value("${ali.ali_charset}")
    public String aliCharset;
    @Value("${ali.ali_signType}")
    public String aliSignType;
    @Value("${ali.ali_partner}")
    public String aliPartner;

    private static final int BLACK = 0x00000000;
    private static final int WHITE = 0xFFFFFFFF;
    private PublicKey cmbcPublicKey;
    private PrivateKey cooperatorPrivateKey;

    @PostConstruct
    public void init() {
        try {
            String CMBC_PUBLIC_KEY_FILE_PATH = this.getClass().getResource("/").getPath() + File.separator + "template/cmbc/" + "ms_rsa_public_key_2048.pem";
            String COOPERATOR_PRIVATE_KEY_FILE_PATH = this.getClass().getResource("/").getPath() + File.separator + "template/cmbc/" + "pkcs8_rsa_private_key_2048.pem";
            PublicKey cmbcPublicKey = CryptoUtil.getRSAPublicKeyByFileSuffix(
                    CMBC_PUBLIC_KEY_FILE_PATH, "pem", "RSA");
            PrivateKey cooperatePrivateKey = CryptoUtil
                    .getRSAPrivateKeyByFileSuffix(COOPERATOR_PRIVATE_KEY_FILE_PATH,
                            "pem", null, "RSA");
            this.cmbcPublicKey = cmbcPublicKey;
            this.cooperatorPrivateKey = cooperatePrivateKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param reqMsgId 合作方流水id
     * @param object   传来的对象
     * @param tranCode 服务码
     * @param clazz    来自个类调用
     * @param callBack 回调地址
     * @return
     */
    public JSONObject sendCmbcPost(String reqMsgId, Object object, String tranCode, Class clazz, String callBack) {
        Logger logger = LoggerFactory.getLogger(clazz);
        String template = packageTemplate.getPackageTemplate(tranCode);
        try {
            logger.info("-------------" + reqMsgId);
            String data = packageCodec.decode(template, object);
            logger.info("data = {}", data);
            byte[] plainBytes = data.getBytes(Constant.CMBC_CHAR_SET);
            String cooperatorAESKey = "1234567887654321";
            byte[] cooperatorKeyBytes = cooperatorAESKey.getBytes(Constant.CMBC_CHAR_SET);
            String encryptData = new String(org.apache.commons.codec.binary.Base64.encodeBase64((CryptoUtil
                    .AESEncrypt(plainBytes, cooperatorKeyBytes, "AES",
                            "AES/ECB/PKCS5Padding", null))), Constant.CMBC_CHAR_SET);
            String signData = new String(Base64.encodeBase64(CryptoUtil
                    .digitalSign(plainBytes, getCooperatorPrivateKey(), "SHA1WithRSA")),
                    Constant.CMBC_CHAR_SET);
            String encrtptKey = new String(Base64.encodeBase64(CryptoUtil
                    .RSAEncrypt(cooperatorKeyBytes, getCmbcPublicKey(), 2048, 11,
                            "RSA/ECB/PKCS1Padding")), Constant.CMBC_CHAR_SET);
            Map<String, String> parm = new HashMap<>();
            parm.put("encryptData", encryptData);
            parm.put("encryptKey", encrtptKey);
            parm.put("cooperator", cooperator);
            parm.put("signData", signData);
            parm.put("tranCode", tranCode);
            parm.put("reqMsgId", reqMsgId);
            if (null != callBack && !"".equals(callBack)) {
                parm.put("callBack", callBack);
            }
            logger.info("向SMZF服务器发出请求encryptData:[{}]",
                    new Object[]{encryptData});
            logger.info("向SMZF服务器发出请求encrtptKey:[{}]",
                    new Object[]{encrtptKey});
            logger.info("向SMZF服务器发出请求cooperator:[{}]",
                    new Object[]{cooperator});
            logger.info("向SMZF服务器发出请求signData:[{}]", new Object[]{signData});
            logger.info("向SMZF服务器发出请求tranCode:[{}]", new Object[]{tranCode});
            logger.info("向SMZF服务器发出请求reqMsgId:[{}]", new Object[]{reqMsgId});
            JSONObject jsonObject = httpClientService.postGetJSONObject(cmbcServerUrl, parm);
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取回调或者同步返回的参数值并返回map
     *
     * @param clazz
     * @param jsonObject
     * @return
     */
    public Map<String, String> getReturnCallBackMap(Class clazz, JSONObject jsonObject) {
        Logger logger = LoggerFactory.getLogger(clazz);
        try {
            String resEncryptData = jsonObject.getString("encryptData");
            String resEncryptKey = jsonObject.getString("encryptKey");
            String tranCode = jsonObject.getString("tranCode");
            byte[] decodeBase64KeyBytes = Base64.decodeBase64(resEncryptKey
                    .getBytes(Constant.CMBC_CHAR_SET));
            // 解密encryptKey得到merchantAESKey
            byte[] merchantAESKeyBytes = CryptoUtil.RSADecrypt(
                    decodeBase64KeyBytes, getCooperatorPrivateKey(), 2048, 11,
                    "RSA/ECB/PKCS1Padding");
            // 使用base64解码商户请求报文
            byte[] decodeBase64DataBytes = Base64.decodeBase64(resEncryptData
                    .getBytes(Constant.CMBC_CHAR_SET));
            // 用解密得到的merchantAESKey解密encryptData
            byte[] merchantXmlDataBytes = CryptoUtil.AESDecrypt(decodeBase64DataBytes,
                    merchantAESKeyBytes, "AES", "AES/ECB/PKCS5Padding", null);
            String resXml = new String(merchantXmlDataBytes, Constant.CMBC_CHAR_SET);
            String templateXml = xmlTemplate.getCMBCXmlTemplate(tranCode);
            Map<String, String> map = new HashMap<>();
            map.put("resXml", resXml);
            map.put("templateXml", templateXml);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("验签失败");
        }
        return null;
    }


    /**
     * 将二维码转成Base64并上传到七牛服务器中
     *
     * @param content
     * @param prefix
     * @param suffix
     * @param tempPath
     * @return
     */
    public String encodeQrcode(String content, String prefix, String suffix, String tempPath) {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Map hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); //设置字符集编码类型
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 300, 300, hints);
            BufferedImage image = toBufferedImage(bitMatrix);
            File file = new File(tempPath + "/" + new Date().getTime() + suffix);
            ImageIO.write(image, suffix.replace(".", ""), file);

            InputStream fis = new FileInputStream(file);
            //获取后缀名
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            byte[] data = null;
            data = bos.toByteArray();
            org.apache.commons.codec.binary.Base64 base64Util = new org.apache.commons.codec.binary.Base64();
            String base64 = base64Util.encodeToString(data);
            base64 = new String(base64.getBytes(), "utf-8");

            //调用上传服务
            UploadBase64Req uploadBase64Req = new UploadBase64Req();
            uploadBase64Req.setBase64(base64);
            uploadBase64Req.setPrefix(prefix);
            uploadBase64Req.setSuffix(suffix);
            ModelResult<UploadBase64Rlt> uploadRltModelResult = uploadService.uploadBase64(uploadBase64Req);
            if (uploadRltModelResult.isSuccess()) {
                file.delete();
                return uploadRltModelResult.getData().getImgUrl();
            } else {
                return "";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) == true ? BLACK : WHITE);
            }
        }
        return image;
    }

    public Map<String, String> sendCTTICPost(Class clazz, String tranCode, Object object, String key) {
        Logger logger = LoggerFactory.getLogger(clazz);
        Map<String, String> map = new HashMap<>();
        Map<String, String> parMap = (Map<String, String>) JSONObject.toJSON(object);
        logger.info("---获得的参数map" + parMap);
        Map<String, String> paraFilter = SignUtils.paraFilter(parMap);
        logger.info("---过滤sign后的的参数map" + paraFilter);
        StringBuilder buf = new StringBuilder((paraFilter.size() + 1) * 10);
        SignUtils.buildPayParams(buf, paraFilter, false);
        logger.info("排序后的值为:{}", buf.toString());
        String preStr = buf.toString();
        String sign = MD5.sign(preStr, "&key=" + key, "UTF-8").toUpperCase();
        logger.info("sign:" + sign);
        if (parMap.containsKey("sign")) {
            parMap.remove("sign");
            parMap.put("sign", sign);
        } else {
            parMap.put("sign", sign);
        }
        String template = packageTemplate.getPackageTemplate(tranCode);
        try {
            object = JSONObject.parseObject(JSONObject.toJSONString(parMap));
            String data = packageCodec.decode(template, object);
            logger.info("请求xml为：{}", data);
            map = httpClientService.postGetMapByStr(ctticServerUrl, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public Map<String, String> returnCtticCallBack(Class clazz, Map<String, String> param, String tranCode, String key) {
        Logger logger = LoggerFactory.getLogger(clazz);
        Map<String, String> map = new HashMap<>();
        if (null != param && param.size() > 0) {
            if (param.containsKey("sign")) {
                if (!SignUtils.checkParam(param, key)) {
                    logger.info("签名验证失败");
                } else {
                    if ("0".equals(param.get("status")) && "0".equals(param.get("result_code"))) {
                        String templateXml = xmlTemplate.getCMBCXmlTemplate(tranCode);
                        String xml = XmlUtils.toXml(param);
                        map.put("templateXml", templateXml);
                        map.put("xml", xml);
                        return map;
                    } else {
                        logger.info("验签失败！");
                        return null;
                    }

                }
            }
        } else {
            logger.info("参数有误！");
            return null;
        }
        return null;
    }

    /**
     * 兴业银行支付宝扫码回调
     * @param clazz
     * @param resString
     * @param key
     * @return
     */
    public Map<String, String> returnIndustrialScanCallBack(Class clazz, String resString, String key) {
       // String resString = XmlUtils.parseRequst(request);
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.info("通知内容={}", resString);
        Map<String,String> returnMap = new HashMap<>();
        if (resString != null && !"".equals(resString)) {
            Map<String, String> map = null;
            try {
                map = XmlUtils.toMap(resString.getBytes(), "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
                returnMap.put("ctticMsg","回调参数转换异常");
                return returnMap;
            }
            String res = XmlUtils.toXml(map);
            if (map.containsKey("sign")) {
                if (!SignUtils.checkParam(map, key)) {
                    returnMap.put("ctticMsg","验签失败");
                    return returnMap;
                } else {
                    String status = map.get("status");
                    if (status != null && "0".equals(status)) {
                        String result_code = map.get("result_code");
                        if (result_code != null && "0".equals(result_code)) {
                            return map;
                        }else{
                            returnMap.put("ctticMsg","兴业支付宝扫码失败,"+map.get("err_msg"));
                            return returnMap;
                        }
                    }

                }
            } else{
                logger.info("参数有误！");
                map.put("ctticMsg","兴业支付宝参数有误"+map.get("err_msg"));
                return map;
            }
        }
        logger.info("参数有误！");
        returnMap.put("ctticMsg","兴业支付宝参数有误");
        return returnMap;
    }

    /**
     * 请求中信支付接口(兴业通用)
     *
     * @param map
     * @param key
     * @param clazz
     * @return
     * @throws Exception
     */
    public Map<String, String> sendCtticPay(SortedMap<String, String> map, String key, Class clazz,String source) throws Exception {
        Logger logger = LoggerFactory.getLogger(clazz);
        Map<String,String> returnMap = new HashMap<>();
        Map<String, String> params = SignUtils.paraFilter(map);
        StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
        SignUtils.buildPayParams(buf, params, false);
        String preStr = buf.toString();
        String sign = MD5.sign(preStr, "&key=" + key, "utf-8");
        map.put("sign", sign);
        logger.info("请求参数:{}", XmlUtils.parseXML(map));
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        String res = null;
        HttpPost httpPost = new HttpPost(ctticServerUrl);
        StringEntity entityParams = new StringEntity(XmlUtils.parseXML(map), "utf-8");
        httpPost.setEntity(entityParams);
        httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
        client = HttpClients.createDefault();
        response = client.execute(httpPost);
        if (response != null && response.getEntity() != null) {
            Map<String, String> resultMap = XmlUtils.toMap(EntityUtils.toByteArray(response.getEntity()), "UTF-8");
            res = XmlUtils.toXml(resultMap);
            logger.info("支付请求返回参数{}", res);
            if (resultMap.containsKey("sign")) {
                if (!SignUtils.checkParam(resultMap, key)) {
                    logger.info(source+"验签失败");
                    returnMap.put("ctticMsg",source+"验签失败");
                    return returnMap;
                } else {
                    if ("0".equals(resultMap.get("status")) && "0".equals(resultMap.get("result_code"))) {
                        logger.info("resultMap:{}", resultMap);
                        return resultMap;
                    } else {
                        returnMap.put("ctticMsg",source+returnMap.get("err_msg"));
                        return returnMap;
                    }
                }
            } else {
                logger.info("参数有误！");
                returnMap.put("ctticMsg",source+"参数有误"+returnMap.get("err_msg"));
                return returnMap;
            }
        } else {
            logger.info("没有返回任何数据");
            returnMap.put("ctticMsg",source+"无任何数据返回");
            return returnMap;
        }

    }

    /**
     * 请求快付通支付
     *
     * @param requestUrl 请求url 若为弄默认为去ctticServerUrl
     * @param map
     * @param key
     * @param clazz
     * @return
     * @throws Exception
     */
    public Map<String, String> sendKFTPay(String requestUrl, SortedMap<String, String> map, String key, Class clazz) throws Exception {
        Logger logger = LoggerFactory.getLogger(clazz);
        Map<String, String> params = SignUtils.paraFilter(map);
        //拼接
        String toSign = SignUtils.createLinkString(params);

        //生成签名sign
        String sign = SignUtils.genSign(key, toSign);
        params.put("sign", sign);

        //转为json串
        String postStr = JSON.toJSONString(params);
        logger.info("请求参数:{}", postStr);
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        String res = null;
        HttpPost httpPost = null;
        if (null != requestUrl) {
            //生成收款二维码时的请求地址不同
            httpPost = new HttpPost(requestUrl);
        } else {
            httpPost = new HttpPost(ctticServerUrl);
        }
        StringEntity entityParams = new StringEntity(postStr, "utf-8");
        httpPost.setEntity(entityParams);
        httpPost.setHeader("Content-Type", "application/json");
        client = HttpClients.createDefault();
        response = client.execute(httpPost);
        if (response != null && response.getEntity() != null) {
            String resultContent = EntityUtils.toString(response.getEntity());
            Map<String, String> returnMap = (Map<String, String>) JSONObject.parse(resultContent);
            logger.info("支付请求返回参数{}", returnMap);
            if (returnMap.containsKey("sign")) {
                if (!SignUtils.checkParam(returnMap, key)) {
                    logger.info("验签失败");
                    returnMap.put("ctticMsg","快付通验签失败");
                    return returnMap;
                } else {
                    if ("0".equals(returnMap.get("returnCode")) && "0".equals(returnMap.get("resultCode"))) {
                        logger.info("resultMap:{}", returnMap);
                        return returnMap;
                    } else {
                        logger.info(returnMap.get("errCodeDes"));
                        returnMap.put("ctticMsg",returnMap.get("errCodeDes"));
                        return returnMap;
                    }
                }
            } else {
                logger.info("参数有误！");
                returnMap.put("ctticMsg","参数有误");
                return returnMap;
            }
        } else {
            logger.info("没有返回任何数据");
            map.put("ctticMsg","没有返回任何数据");
            return map;
        }

    }

    /**
     *
     * 快付通下单接口调用
     * @param requestUrl 请求url 若为弄默认为去ctticServerUrl
     * @param map
     * @param key
     * @param clazz
     * @return
     * @throws Exception
     */
    public Map<String, String> requestKFTPay(String requestUrl, SortedMap<String, String> map, String key, Class clazz) throws Exception {
        Logger logger = LoggerFactory.getLogger(clazz);
        Map<String, String> params = SignUtils.paraFilter(map);
        //拼接
        String toSign = SignUtils.createSignPlainText(params,true);

        //生成签名sign
        String sign = SignUtils.genSign(key, toSign);
        params.put("sign", sign);

        //转为json串
        String postStr = JSON.toJSONString(params);
        logger.info("请求参数:{}", postStr);
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        String res = null;
        HttpPost httpPost = null;
        if (null != requestUrl) {
            //生成收款二维码时的请求地址不同
            httpPost = new HttpPost(requestUrl);
        } else {
            httpPost = new HttpPost(ctticServerUrl);
        }
        StringEntity entityParams = new StringEntity(postStr, "utf-8");
        httpPost.setEntity(entityParams);
        httpPost.setHeader("Content-Type", "application/json");
        client = HttpClients.createDefault();
        response = client.execute(httpPost);
        if (response != null && response.getEntity() != null) {
            String resultContent = EntityUtils.toString(response.getEntity());
            Map<String, String> returnMap = (Map<String, String>) JSONObject.parse(resultContent);
            logger.info("支付请求返回参数{}", returnMap);
            if (returnMap.containsKey("sign")) {
                if (!SignUtils.checkParam(returnMap, key)) {
                    logger.info("验签失败");
                    returnMap.put("ctticMsg","快付通验签失败");
                    return returnMap;
                } else {
                    if ("0".equals(returnMap.get("returnCode")) && "0".equals(returnMap.get("resultCode"))) {
                        logger.info("resultMap:{}", returnMap);
                        return returnMap;
                    } else {
                        logger.info(returnMap.get("errCodeDes"));
                        returnMap.put("ctticMsg",returnMap.get("errCodeDes"));
                        return returnMap;
                    }
                }
            } else {
                logger.info("参数有误！");
                returnMap.put("ctticMsg","参数有误");
                return returnMap;
            }
        } else {
            logger.info("没有返回任何数据");
            map.put("ctticMsg","没有返回任何数据");
            return map;
        }

    }


    /**
     * 请求中信支付接口(兴业通用)
     *
     * @param map
     * @param key
     * @param clazz
     * @return
     * @throws Exception
     */
    public Map<String, String> sendIndustrailPay(SortedMap<String, String> map, String key, Class clazz,String source) throws Exception {
        Logger logger = LoggerFactory.getLogger(clazz);
        Map<String,String> returnMap = new HashMap<>();
        Map<String, String> params = SignUtils.paraFilter(map);
        StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
        SignUtils.buildPayParams(buf, params, false);
        String preStr = buf.toString();
        String sign = MD5.sign(preStr, "&key=" + key, "utf-8");
        map.put("sign", sign);
        logger.info("请求参数:{}", XmlUtils.parseXML(map));
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        String res = null;
        HttpPost httpPost = new HttpPost(industrailServerUrl);
        StringEntity entityParams = new StringEntity(XmlUtils.parseXML(map), "utf-8");
        httpPost.setEntity(entityParams);
        httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
        client = HttpClients.createDefault();
        response = client.execute(httpPost);
        if (response != null && response.getEntity() != null) {
            Map<String, String> resultMap = XmlUtils.toMap(EntityUtils.toByteArray(response.getEntity()), "UTF-8");
            res = XmlUtils.toXml(resultMap);
            logger.info("支付请求返回参数{}", res);
            if (resultMap.containsKey("sign")) {
                if (!SignUtils.checkParam(resultMap, key)) {
                    logger.info(source+"验签失败");
                    returnMap.put("ctticMsg",source+"验签失败");
                    return returnMap;
                } else {
                    if ("0".equals(resultMap.get("status")) && "0".equals(resultMap.get("result_code"))) {
                        logger.info("resultMap:{}", resultMap);
                        return resultMap;
                    } else {
                        returnMap.put("ctticMsg",source+returnMap.get("err_msg")+source+returnMap.get("result_code"));
                        returnMap.put("resultCode",source+returnMap.get("result_code"));
                        return returnMap;
                    }
                }
            } else {
                logger.info("参数有误！");
                returnMap.put("ctticMsg",source+"参数有误");
                return returnMap;
            }
        } else {
            logger.info("没有返回任何数据");
            returnMap.put("ctticMsg",source+"无任何数据返回");
            return returnMap;
        }

    }

    public PublicKey getCmbcPublicKey() {
        return cmbcPublicKey;
    }

    public PrivateKey getCooperatorPrivateKey() {
        return cooperatorPrivateKey;
    }
}
