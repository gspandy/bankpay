<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>快付通退款</title>
	<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="js/jquery-1.12.2.min.js"></script>
	<script type="text/javascript">
		function ajaxSumbit(){
			$.ajax({
				type: 'get',
				url: 'http://lemon613.ticp.net/bankpay/bank/kftPayRefund',
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
	<form method="post" id="form_query">
					交易类型-tradeType:
					<input type="text" name="tradeType" value="cs.refund.apply" maxlength="32" /> <br/>
			
					接口版本-version:
					<input type="text" name="version" value="1.0" maxlength="8" /><br/>

					代理商号-mchId:
					<input type="text" name="mchId" value="" maxlength="32" /><br/>
					
					商户号-subMchId:
					<input type="text" name="subMchId" value="" maxlength="30" /><br/>

					渠道类型-channel:
					<select id="channel" onchange="changeChannel(this)" name="channel">	
					    <option value="0">---请选择---</option>
						<option value="weixin">微信</option>
						<!-- <option value="jd">京东</option> -->
						<option value="alipay">支付宝</option>
						<!-- <option value="baidu">百度</option> -->
					</select><br/>

					商户订单号-outTradeNo:
					<input type="text" name="outTradeNo" value="" placeholder="请输入商户订单号" maxlength="32" /><br/>

					商户退款单号-outRefundNo:
					<input type="text" name="outRefundNo" value=""  placeholder="请输入商户退款单号" maxlength="32" /><br/>

					退款金额-amount:
					<input type="text" name="amount" value="0.01"  placeholder="请输入退款金额" /><br/>

					退款详情-description:
					<input type="text" name="description" value="测试"  placeholder="请输入退款详情" /><br/>

					<input type="button"  onclick="ajaxSumbit()" value="退款申请" class="btn1">
				
			</div>
		</div>
	</form>
</body>
 <script type="text/javascript">
 	function changeChannel(chan){
	    for(var i=1;i<chan.length;i++)
	    {
	    	var div = document.getElementById(chan.options[i].value);
	    	div.style.display="none";
	    } 
 		if(chan.value != 0){
 			document.getElementById(chan.value).style.display="";
 		}
	}
 </script>

</html>
