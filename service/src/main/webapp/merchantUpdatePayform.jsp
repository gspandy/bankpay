<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商户查询</title>
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/jquery-1.12.2.min.js"></script>
	<script type="text/javascript">
		function ajaxSumbit(){
			$.ajax({
				type: 'get',
				url: 'http://lemon613.ticp.net/bankpay/bank/merchantUpdatePay',
				data:$("#form_query").serialize(),
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
<div>
<form id="form_query" method="post">
合作方商户编码: <input type="text" name="merchantId" value="10231212001" id="merchantId" /></br>
	商户简称: <input type="text" name="shortName" value="深圳旗鱼移动" id="shortName" /></br>
	商户地址: <input type="text" name="merchantAddress" value="深圳市南山科技园东方科技大厦" id="merchantAddress" /></br>
	客服电话: <input type="text" name="servicePhone" value="15817353351" id="servicePhone" /></br>
	<div id = "lastdiv"></div>
<input type="button" onclick="ajaxSumbit()" value="商户修改" id="button_pay" /></form>
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