<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>中信交易查询</title>
    <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-1.12.2.min.js"></script>
    <script type="text/javascript">
        function ajaxSumbit() {
            $.ajax({
                type: 'get',
                url: 'http://lemon613.ticp.net/bankpay/bank/industrialWXQueryPay',
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
    </script>
</head>
<body>
<div>
    <form  id="form_query" method="post">
        商户订单号：<input name="out_trade_no" value="1000000004"/></br>
        威富通订单号： <input name="transaction_id" value="" /></br>
        商户编号: <input type="text" name="mch_id" value="7551000001" id="mch_id"/></br>
        随机字符串：<input name="nonce_str" value=""/></br>
        <input type="button" onclick="ajaxSumbit()" value="交易查询" id="button_pay"/></form>
</div>
</body>
</html>