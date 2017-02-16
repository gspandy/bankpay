<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>快付通查询</title>
	<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="js/jquery-1.12.2.min.js"></script>
	<script type="text/javascript">
		function ajaxSumbit(){
			$.ajax({
				type: 'get',
				url: 'http://lemon613.ticp.net/bankpay/bank/kftPayQuery',
				data:$("#form_query").serialize(),
				success: function (ret) {
					alert(ret.code);
					if (ret) {
						if (ret.code == 0) {
							alert(ret.data.status);
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
	<form method="post"  id="form_query">
				交易类型-tradeType:
				<input type="text"  name="tradeType" value="cs.trade.single.query" maxlength="32" /> <br/>
				
				接口版本-version:
				<input type="text"  name="version" value="1.0" maxlength="8" /><br/>
				代理商号-mchId:
				<input type="text" name="mchId" value="${mchId}" maxlength="32" /><br/>

				商户号-subMchId:
				<input type="text" name="subMchId" value="" maxlength="30" /><br/>
				
				商户订单号-outTradeNo:
				<input type="text"  name="outTradeNo" value="" placeholder="请输入商户订单号" maxlength="32" /><br/>
				
				商户原交易订单号-oriTradeNo:
				<input type="text"  name="oriTradeNo" value="" placeholder="请输入商户原交易订单号" maxlength="32" /><br/>

				查询类型:
				<select id="queryType" name="queryType">	
				    <option value="0">-请选择-</option>
					<option value="1">订单查询</option>
					<option value="3">退款查询</option>
				</select><br/>

		<input type="button" onclick="ajaxSumbit()" value="下单" id="showlayerButton" class="btn1">
	</form>
</body>
</html>
