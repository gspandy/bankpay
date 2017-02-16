package com.qiyu.bankpay.domain.constant;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * Created by Administrator on 2016/11/16.
 */
public class Constant {

    public static String CMBC_CHAR_SET = "utf-8";

    //商户入驻
    public static String TRAN_CODE_SMZF_001 = "SMZF001";

    //扫码支付
    public static String TRAN_CODE_SMZF_002 = "SMZF002";
    //条码支付
    public static String TRAN_CODE_SMZF_003 = "SMZF003";
    //申请退款
    public static String TRAN_CODE_SMZF_004 = "SMZF004";
    //撤销交易
    public static String TRAN_CODE_SMZF_005 = "SMZF005";
    //交易查询
    public static String TRAN_CODE_SMZF_006 = "SMZF006";
    //商户查询
    public static String TRAN_CODE_SMZF_007 = "SMZF007";
    //支付回调
    public static String TRAN_CODE_SMZF_008 = "SMZF008";
    //商户修改
    public static String TRAN_CODE_SMZF_009 = "SMZF009";
    //公众号服务窗支付
    public static String TRAN_CODE_SMZF_010 = "SMZF010";
    //获取对账文件
    public static String TRAN_CODE_SMZF_020 = "SMZF020";
    //商户提现接口
    public static String TRAN_CODE_SMZF_021 = "SMZF021";
    //提现交易查询
    public static String TRAN_CODE_SMZF_022 = "SMZF022";
    //提现异步通知
    public static String TRAN_CODE_SMZF_023 = "SMZF023";
    //提现对账获取
    public static String TRAN_CODE_SMZF_024 = "SMZF024";

    //支付宝
    public static String CMBC_PAY_WAY_ZFBZF = "ZFBZF";

    //微信支付
    public static String CMBC_PAY_WAY_WXZF = "WXZF";

    //民生paymentChannel 支付宝
    public static String CMBC_101 = "101";
    //中信paymentChannel 支付宝
    public static String CTTIC_102 = "102";
    //兴业paymentChannel 支付宝
    public static String INDUSTRAIL_103 = "103";
    //快付通paymentChannel 支付宝
    public static String KFT_104 = "104";
    //民生paymentChannel 微信
    public static String CMBC_201 = "201";
    //中信paymentChannel 微信
    public static String CTTIC_202 = "202";
    //兴业paymentChannel微信
    public static String INDUSTRAIL_203 = "203";
    //快付通paymentChannel微信
    public static String KFT_204 = "204";

    //快付通微信条码
    public static String KFT_WXMICRO = "wxMicro";
    //快付通支付宝条码
    public static String KFT_ALIPAYMICRO = "alipayMicro";
    //快付通微信扫码
    public static String KFT_WXPUBQR = "wxPubQR";
    //快付通支付宝扫码
    public static String KFT_ALIPAYQR = "alipayQR";


}