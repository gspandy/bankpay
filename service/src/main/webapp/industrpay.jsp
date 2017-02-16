<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>兴业扫码下单</title>
    <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-1.12.2.min.js"></script>
    <script type="text/javascript">
        function ajaxSumbit() {
            $.ajax({
                type: 'get',
                url: 'http://lemon613.ticp.net/bankpay/bank/industrialScanPay',
                data: $("#form_query").serialize(),
                dataType: 'json',
                success: function (ret) {
                    alert(ret.code);
                    if (ret) {
                        if (ret.code == 0) {
                            alert("扫码成功");
                            $("#lastdiv").html("<img src="+ret.data.code_img_url+ ">");
                            //alert(ret.data.imageUrl);
                        } else if (ret.code == 2) {
                            if (ret.msg == "USERPAYING") {
                                $.ajax({
                                    type: 'get',
                                    url: 'http://lemon613.ticp.net/bankpay/bank/industrialScanPay',
                                    data: $("#form_query").serialize(),
                                    success: function (ret) {
                                        alert(ret.code);
                                        if (ret) {
                                            if (ret.code == 0) {
                                                alert("查询成功");
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
                        } else {
                            alert("交易失败");
                        }
                    }
                }
            });
        }
    </script>
</head>
<body>
<div>
    <form id="form_query" method="post">
        service: <input type="text" name="service" value="pay.alipay.native" id="service"/></br>
        版本号：<input size="30" name="version" value="1.0" readonly="readonly" maxlength="8"  placeholder="长度8"/></br>
        商户编号: <input type="text" name="mch_id" value="7551000001" id="mchId"/></br>
        商户唯一订单号:<input type="text" name="out_trade_no" id="outTradeNo" value="45434343"/></br>
        订单标题:<input type="text" name="body" id="body" value="用户购买话费0.01元"/></br>
        交易金额:<input type="text" name="total_fee" id="totalFee" value="1"/></br>
        随机字符串：<input name="nonce_str" value="" maxlength="32" size="30" placeholder="长度18"/></br>
        ip： <input name="mch_create_ip" value="127.0.0.1" maxlength="16" placeholder="长度16"/>
        通知地址：<input name="notify_url" value="http://lemon613.ticp.net/bankpay/bank/industrialScanPayCallBack"/>
        <div id="lastdiv"></div>
        <input type="button" onclick="ajaxSumbit()" value="生成预付订单" id="button_pay"/></form>
</div>
</body>
<script>

    //提示到服务器
    $(function () {
        var current = CurentTime();
        $("#reqMsgId").val(current);
    })
</script>
</html>