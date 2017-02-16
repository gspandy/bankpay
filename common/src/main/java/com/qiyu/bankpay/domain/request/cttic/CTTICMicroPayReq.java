package com.qiyu.bankpay.domain.request.cttic;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/22.
 */
public class CTTICMicroPayReq extends  CTTICBaseReq implements Serializable{
    //大商户编号
    private String group_no;
    //设备号
    private String device_info;
    //商品描述
    private String body;
    //附加信息
    private String attach;
    //总金额
    private String total_fee;
    //终端IP
    private String mch_create_ip;
    //授权码
    private String auth_code;
    //订单生成时间
    private String time_start;
    //订单超时时
    private String ime_expire;
    //操作员
    private String op_user_id;
    //门店编号
    private String op_shop_id;
    //设备编号
    private String op_device_id;
    //商品标
    private String goods_tag;
    //随机字符串
    private String nonce_str;

    public String getGroup_no() {
        return group_no;
    }

    public void setGroup_no(String group_no) {
        this.group_no = group_no;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getMch_create_ip() {
        return mch_create_ip;
    }

    public void setMch_create_ip(String mch_create_ip) {
        this.mch_create_ip = mch_create_ip;
    }

    public String getAuth_code() {
        return auth_code;
    }

    public void setAuth_code(String auth_code) {
        this.auth_code = auth_code;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getIme_expire() {
        return ime_expire;
    }

    public void setIme_expire(String ime_expire) {
        this.ime_expire = ime_expire;
    }

    public String getOp_user_id() {
        return op_user_id;
    }

    public void setOp_user_id(String op_user_id) {
        this.op_user_id = op_user_id;
    }

    public String getOp_shop_id() {
        return op_shop_id;
    }

    public void setOp_shop_id(String op_shop_id) {
        this.op_shop_id = op_shop_id;
    }

    public String getOp_device_id() {
        return op_device_id;
    }

    public void setOp_device_id(String op_device_id) {
        this.op_device_id = op_device_id;
    }

    public String getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }
}
