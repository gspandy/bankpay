<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>获取对账文件</title>
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/jquery-1.12.2.min.js"></script>
	<script type="text/javascript">
		function ajaxSumbit(){
			$.ajax({
				type: 'get',
				url: 'http://lemon613.ticp.net/bankpay/bank/balanceAccountPay',
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
对账日期: <input type="text" name="settleDate" value="20161118" id="settleDate" /></br>
	文件返回类型: <input type="text" name="fileType" value="1" id="fileType" /></br>
	<div id = "lastdiv"></div>
<input type="button" onclick="ajaxSumbit()" value="交易查询" id="button_pay" /></form>
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