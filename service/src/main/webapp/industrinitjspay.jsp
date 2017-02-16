<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>兴业公众号支付</title>
    <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-1.12.2.min.js"></script>
    <script type="text/javascript">
        function ajaxSumbit() {
            $.ajax({
                type: 'get',
                url: 'https://union.qiyupay.com/bank/industrialInitReqPay',
                data: $("#form_query").serialize(),
                dataType: 'json',
                success: function (ret) {
                    alert(ret.code);
                    if (ret) {
                        if (ret.code == 0) {
                            alert("公众号支付");
                            window.open("https://pay.swiftpass.cn/pay/jspay?token_id=" + ret.data.token_id);
                        }
                    }
                }
            });
        }
    </script>
</head>
<body>
<div>
   <%-- <form id="form_query" method="post"> <!---101590019961--->
        service: <input type="text" name="service" value="pay.weixin.jspay" id="service"/></br>
        版本号：<input size="30" name="version" value="2.0" readonly="readonly" maxlength="8" placeholder="长度8"/></br>
        商户编号: <input type="text" name="mch_id" value="101510021361" id="mchId"/></br>
        秘钥: <input type="text" name="key" value="37f26e94ad7927977d0582b6f82fd20a" id="key"/></br>
        是否原生态: <input type="text" name="is_raw" value="0" id="is_raw"/></br>
        商户唯一订单号:<input type="text" name="out_trade_no" id="outTradeNo" value="45434343"/></br>
        订单标题:<input type="text" name="body" id="body" value="用户购买话费0.01元"/></br>
        用户openid:<input type="text" name="sub_openid" id="sub_openid" value="oF3LAwSh9Lty08_BkI0Hrssr4SS8"/></br>
        交易金额:<input type="text" name="total_fee" id="totalFee" value="1"/></br>
        随机字符串：<input name="nonce_str" value="" maxlength="32" size="30" placeholder="长度18"/></br>
        ip： <input name="mch_create_ip" value="127.0.0.1" maxlength="16" placeholder="长度16"/>
        通知地址：<input name="notify_url" value="https://union.qiyupay.com/bank/industrialJsCallBack"/>
        <div id="lastdiv"></div>
        <input type="button" onclick="ajaxSumbit()" value="生成预付订单" id="button_pay"/></form>--%>
       <form id="form_query" method="post"> <!---101590019961--->
           商户唯一订单号:<input type="text" name="out_trade_no" id="outTradeNo" value="45434343"/></br>
           订单标题:<input type="text" name="body" id="body" value="用户购买话费0.01元"/></br>
           用户openid:<input type="text" name="sub_openid" id="sub_openid" value="oF3LAwSh9Lty08_BkI0Hrssr4SS8"/></br>
           交易金额:<input type="text" name="total_fee" id="totalFee" value="1"/></br>
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