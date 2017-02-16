<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>快付通生成二维码</title>
    <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-1.12.2.min.js"></script>
    <script type="text/javascript">
        function ajaxSumbit() {
            $.ajax({
                type: 'get',
                url: 'http://lemon613.ticp.net/bankpay/bank/kftGenQrcode',
                data: $("#form_query").serialize(),
                success: function (ret) {
                    alert(ret.code);
                    if (ret) {
                        if (ret.code == 0) {
                            alert(ret.data.returnStr);
                            $("#lastdiv").html("<img src="+ret.data.returnStr+ ">");
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
<form method="post" action="http://lemon613.ticp.net/bankpay/bank/kftGenQrcode" id="form_query">
    交易类型-tradeType:
    <input type="text" name="tradeType" value="cs.pay.qrcode" maxlength="32"/> <br/>
    接口版本-version:
    <input type="text" name="version" value="1.3" maxlength="8"/><br/>
    <label>代理商号-mchId:</label>
    <input type="text" name="mchId" value="000010001" maxlength="32"/><br/>
    <label>商户号-subMchId:</label>
    <input type="text" name="subMchId" value="000010001000000159" maxlength="30"/><br/>
    <input type="button" onclick="ajaxSumbit()" value="生成" class="btn1"><br/>
</form>
<div id="lastdiv"></div>
</body>

</html>
