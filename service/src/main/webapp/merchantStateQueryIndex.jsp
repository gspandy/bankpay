<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>商户查询</title>
    <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-1.12.2.min.js"></script>
    <script type="text/javascript">
        function ajaxSumbit() {
            $.ajax({
                type: 'get',
                url: 'http://lemon613.ticp.net/bankpay/bank/kftMerchantStateQuery',
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
    <input type="text" name="tradeType" value="cs_merchant_querydetail" maxlength="32"/> <br/>

    代理商编号-mchId:
    <input type="text" name="mchId" value="${mchId}" placeholder="请输入代理商编号" maxlength="32"/><br/>

    商户编号-subMchId:
    <input type="text" name="subMchId" value="" placeholder="请输入商户编号" maxlength="32"/><br/>

    <input type="button" onclick="ajaxSumbit()" value="商户状态查询" class="btn1">

</form>
</body>

</html>
