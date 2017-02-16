<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>兴业微信退款</title>
    <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-1.12.2.min.js"></script>
    <script type="text/javascript">
        function ajaxSumbit() {
            $.ajax({
                type: 'get',
                url: 'http://lemon613.ticp.net/bankpay/bank/industrialWXRefundPay',
                data: $("#form_query").serialize(),
                success: function (ret) {
                    alert(ret.code);
                    if (ret) {
                        if (ret.code == 0) {
                            alert("退款成功");

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
<div>
    <form  id="form_query" method="post">
        商户订单号：<input name="out_trade_no" value="323232323"/>
        商户编号: <input type="text" name="mch_id" value="7551000001" id="mch_id"/></br>
        随机字符串：<input name="nonce_str" value=""/></br>
        商户退款单号：<input name="out_refund_no" value=""/></br>
        总金额：<input name="total_fee" value="1" maxlength="32" size="30"/></br>
        退款金额： <input name="refund_fee" value="1" maxlength="32" size="30"/></br>
        <input type="button" onclick="ajaxSumbit()" value="生成退款订单" id="button_pay"/></form>
</div>
</body>
</html>