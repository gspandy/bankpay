<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>快付通商户修改</title>
    <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-1.12.2.min.js"></script>
    <script type="text/javascript">
        function ajaxSumbit() {
            $.ajax({
                type: 'get',
                url: 'http://lemon613.ticp.net/bankpay/bank/kftMerchantModify',
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
    <input type="text" name="tradeType" value="cs.merchant.modify" maxlength="32"/> <br/>

    接口版本-version:
    <input type="text" name="version" value="1.3" maxlength="8"/><br/>

    代理商编码-mchId:
    <input type="text" name="mchId" value="000010001" maxlength="32"/><br/>

    商户编号-subMchId:
    <input type="text" name="subMchId" value="" maxlength="32"/><br/>


    商户名称-merchantName
    <input type="text" name="merchantName" value="深圳旗鱼移动科技有限公司 " placeholder="请输入商户名称" maxlength="128"/><br/>

    商户简称-merchantShortName:
    <input type="text" name="merchantShortName" value="深圳旗鱼" placeholder="请输入商户简称" maxlength="20"/><br/>

    负责人-opName:
    <input type="text" name="opName" value="卢俊芳" placeholder="请输入负责人" maxlength="20"/><br/>

    负责人电话-opPhone:
    <input type="text" name="opPhone" value="18682350817" placeholder="请输入负责人电话" maxlength="20"/><br/>

    客服电话-customerTelephone:
    <input type="text" name="customerTelephone" value="4000000000" placeholder="请输入客服电话" maxlength="20"/><br/>

    省份-proName:
    <input type="text" name="proName" value="广东省" placeholder="请输入省份" maxlength="20"/><br/>

    城市-cityName:
    <input type="text" name="cityName" value="深圳市" placeholder="请输入城市" maxlength="20"/><br/>

    地址-address:
    <input type="text" name="address" value="深圳市南山区科苑路16号东方科技大厦1705" placeholder="请输入地址" maxlength="240"/><br/>

    电话-telephone:
    <input type="text" name="telephone" value="0755-36607372" placeholder="请输入电话" maxlength="32"/><br/>

    邮箱-email:
    <input type="text" name="email" value="51@qiyudc.com" placeholder="请输入邮箱" maxlength="50"/><br/>

    商户经营类型-merchantOperationType:
    <input type="text" name="merchantOperationType" value="51@qiyudc.com" placeholder="请输入商户经营类型" maxlength="50"/><br/>

    备注说明-rmk:
    <input type="text" name="rmk" value="备注" placeholder="请输入备注说明（可空）" maxlength="128"/><br/>


    <!-- 支付类型 -->
    <input type="text" name="payTypes"
           value='[{"payTypeCode":"alipayMicro","rate":10,"rateType":"1","rmk":"支付宝付款码"},{"payTypeCode":"alipayQR","rate":10,"rateType":"1","rmk":"支付宝扫码"},{"payTypeCode":"wxMicro","rate":10,"rateType":"1","rmk":"微信付款码"},{"payTypeCode":"wxPub","rate":10,"rateType":"1","rmk":"微信公众号"},{"payTypeCode":"wxPubQR","rate":10,"rateType":"1","rmk":"微信扫码"}]'
           maxlength="32" style="width: 300px;"/><br/>

    <input type="button" onclick="ajaxSumbit()" value="商户信息登记"/>

</form>
</body>

</html>
