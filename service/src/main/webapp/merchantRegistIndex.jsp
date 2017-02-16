<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>快付通商户登记</title>
    <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-1.12.2.min.js"></script>
    <script type="text/javascript">
        function ajaxSumbit() {
            $.ajax({
                type: 'get',
                url: 'http://lemon613.ticp.net/bankpay/bank/kftMerchantRegist',
                data: $("#form_query").serialize(),
                success: function (ret) {
                    alert(ret.code);
                    if (ret) {
                        if (ret.code == 0) {
                            alert(ret.data.respType);
                        } else {
                            alert('操作失败，' + ret.msg);
                        }
                    } else {
                        alert('请求失败,请稍后重试！');
                    }
                },
                dataType: 'json'
            });
        }
    </script>
</head>
<body>
<form method="post" id="form_query">
    交易类型-tradeType:
    <input type="text" name="tradeType" value="cs.merchant.register" maxlength="32"/> <br/>

    接口版本-version:
    <input type="text" name="version" value="1.3" maxlength="8"/><br/>

    代理商编码-mchId:
    <input type="text" name="mchId" value="000010001" maxlength="32"/><br/>

    商户名称-merchantName
    <input type="text" name="merchantName" value="深圳旗鱼移动科技" placeholder="请输入商户名称" maxlength="128"/><br/>
    商户简称-merchantShortName:
    <input type="text" name="merchantShortName" value="深圳旗鱼" placeholder="请输入商户简称" maxlength="20"/><br/>


    负责人-opName:
    <input type="text" name="opName" value="卢俊芳" placeholder="请输入负责人" maxlength="20"/><br/>

    负责人电话-opPhone:
    <input type="text" name="opPhone" value="18682350817" placeholder="请输入负责人电话" maxlength="20"/><br/>

    客服电话-customerTelephone:
    <input type="text" name="customerTelephone" value="4007180718" placeholder="请输入客服电话" maxlength="20"/><br/>

    省份-proName:
    <input type="text" name="proName" value="广东省" placeholder="请输入省份" maxlength="20"/><br/>

    城市-cityName:
    <input type="text" name="cityName" value="深圳市" placeholder="请输入城市" maxlength="20"/><br/>

    地址-address:
    <input type="text" name="address" value="深圳市南山区科苑路16号东方科技大厦1705" placeholder="请输入地址" maxlength="240"/><br/>

    电话-telephone:
    <input type="text" name="telephone" value="0755-36607372" placeholder="请输入电话" maxlength="32"/><br/>

    邮箱-email:
    <input type="text" name="email" value="106@qiyudc.com" placeholder="请输入邮箱" maxlength="50"/><br/>

    微信经营类目-categoryWx:
    <input type="text" name="categoryWx" value="90" placeholder="微信经营类目" maxlength="32"/><br/>

    支付宝经营类目-categoryZfb:
    <input type="text" name="categoryZfb" value="2015050700000010" placeholder="支付宝经营类目" maxlength="32"/><br/>

    银行名称-bankName:
    <input type="text" name="bankName" value="招商银行" placeholder="请输入银行名称" maxlength="100"/><br/>

    银行卡号-bankCardNo:
    <input type="text" name="bankCardNo" value="6228480128497513878" placeholder="请输入银行卡号" maxlength="20"/><br/>

    户名-bankAccName:
    <input type="text" name="bankAccName" value="郭启迪" placeholder="请输入户名" maxlength="100"/><br/>

    银行类型-bankType:
    <input type="text" name="bankType" value="9009" placeholder="请输入银行类型（举例：招商银行为 9004）" maxlength="100"/><br/>

    账户类型-accountType:
    <input type="text" name="accountType" value="1" placeholder="账户类型（个人为 1，企业为 2）" maxlength="100"/><br/>

    证件类型-certificateType:
    <input type="text" name="certificateType" value="1" placeholder="证件类型；(1-身份证；2-营业执照)" maxlength="100"/><br/>

    证件号码-certificateNo:
    <input type="text" name="certificateNo" value="420621199109201564" placeholder="" maxlength="100"/><br/>
    备注说明-rmk:
    <input type="text" name="rmk" value="备注" placeholder="请输入备注说明（可空）" maxlength="128"/><br/>

    <!-- 支付类型 -->
    <input type="text" name="payTypes"
           value='[{"payTypeCode":"alipayMicro","rate":0.6,"rateType":"1","rmk":"支付宝付款码"},{"payTypeCode":"alipayQR","rate":0.6,"rateType":"1","rmk":"支付宝扫码"},{"payTypeCode":"wxMicro","rate":0.6,"rateType":"1","rmk":"微信付款码"},{"payTypeCode":"wxPub","rate":0.6,"rateType":"1","rmk":"微信公众号"},{"payTypeCode":"wxPubQR","rate":0.6,"rateType":"1","rmk":"微信扫码"}]'
           maxlength="32" style="width: 300px;"/><br/>

    <input type="button" onclick="ajaxSumbit()" value="商户信息登记"/>
</form>
</body>
</html>