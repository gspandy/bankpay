<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>条码支付预下单</title>
	<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="js/jquery-1.12.2.min.js"></script>
	<script type="text/javascript">
		function ajaxSumbit(){
			$.ajax({
				type: 'get',
				url: 'http://lemon613.ticp.net/bankpay/bank/barCodePay',
				data:$("#form_query").serialize(),
				success: function (ret) {
					alert(ret.code);
					if (ret) {
						if (ret.code == 0) {
						    alert("渠道流水："+ret.data.channelNo);
							//$("#lastdiv").html("<img src="+ret.data.imageUrl+ ">");

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
<form id="form_query" method="post">
商户编号: <input type="text" name="merchantCode" value="2016111704678504" id="merchantCode" /></br>
支付场景:<input type="text" name="scene" id="scene" value="1" /></br>
支付授权码:<input type="text" name="authCode" id="authCode" value="" /></br>
商户唯一订单号:<input type="text" name="reqMsgId" id="reqMsgId" value="1000000000000000" /></br>
订单标题:<input type="text" name="subject" id="subject" value="用户购买话费0.01元" /></br>
交易金额:<input type="text" name="totalAmount" id="totalAmount" value="0.01" /></br>
服务器地址:<input type="text" name="serverUrl" id="serverUrl"
	value="http://localhost:8080/nbp-smzf-hzf" /></br>
支付回调地址:<input type="text" name="callBack" id="callBack"
	value="http://lemon613.ticp.net/bankpay/bank/scanPayCallBack" /></br>
<input type="button" onclick="ajaxSumbit()" value="生成预付订单" id="button_pay" /></form>
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