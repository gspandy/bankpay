<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>快付通下单</title>
	<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="js/jquery-1.12.2.min.js"></script>
	<script type="text/javascript">
		function ajaxSumbit(){
			$.ajax({
				type: 'get',
				url: 'http://lemon613.ticp.net/bankpay/bank/kftPay',
				data:$("#form_query").serialize(),
				success: function (ret) {
					alert(ret.code);
					if (ret) {
						if (ret.code == 0) {
							if (null != ret.data.returnStr){
								$("#lastdiv").html("<img src="+ret.data.returnStr+ ">");
							}
							if(null != ret.data.payCode){
								window.open(ret.data.payCode)
							}
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
	<form id="form_query" method="post" >
		<div class="content">
			<div class="content_0">
				<label>交易类型-tradeType:</label>
				<input type="text" name="tradeType" value="cs.pay.submit"> <br/>
				
				<label>版本-version:</label>
				<input type="text" name="version" value="1.3"> <br/>
				
				<label>支付渠道-channel:</label>
				<select id="channel" onchange="changeChannel(this)" name="channel">
					<option value="wxPub">微信公众账号支付</option>
					<option value="wxPubQR">微信扫码支付</option>
					<option value="wxMicro">微信付款码支付</option>
					<option value="wxApp">微信 APP 支付</option>
					<option value="alipayQR">支付宝扫码支付</option>
					<option value="alipayMicro">支付宝付款码支付</option>
				</select><br/>


				<label>代理商号-mchId:</label>
				<input type="text" name="mchId" value="000010001"> <br/>

				<label>商户号-subMchId:</label>
				<input type="text" name="subMchId" value="000010001000000159"> <br/>

				<label>商品描述-body:</label>
				<input type="text" name="body" value=""> <br/>
				
				<label>商户订单号-outTradeNo:</label>
				<input type="text" name="outTradeNo" value=""> <br/>
				
				<label>交易金额-amount:</label>
				<input type="text" name="amount" value="0.01"> <br/>
				
				<label>附加数据-description:</label>
				<input type="text" name="description" value=""> <br/>
				
				<label>货币类型-currency:</label>
				<input type="text" name="currency" value="CNY"> <br/>
				
				<label>订单支付时间-timePaid:</label>
				<input type="text" name="timePaid" value=""> <br/>
				
				<label>订单失效时间-timeExpire:</label>
				<input type="text" name="timeExpire" value=""> <br/>
				
				<label>商品的标题-subject:</label>
				<input type="text" name="subject" value=""> <br/>
				<div id="extral">

				</div>

				<div id="lastdiv"></div>

				<input type="button" onclick="ajaxSumbit()" value="下单" id="showlayerButton" class="btn1">
			</div>
		</div>
	</form>
	<div>
		<div id="wxPub"  style="display:none">
			<label>指定支付方式-limitPay:</label>
			<input type="text" name="limitPay" value=""> <br/>

			<label>openId:</label>
			<input type="text" name="openId" value=""> <br/>

			<label>成功跳转url-callbackUrl:</label>
			<input type="text" name="callbackUrl" value="${notifyUrl}"> <br/>

			<label>结果通知url-notifyUrl:</label>
			<input type="text" name="notifyUrl" value="${notifyUrl}"> <br/>
		</div>

		<div id="wxPubQR"  style="display:none">
			<label>指定支付方式-limitPay:</label>
			<input type="text" name="limitPay" value=""> <br/>

			<!-- <label>商品id-productId:</label>
            <input type="text" name="productId" value=""> <br/> -->

			<label>结果通知url-notifyUrl:</label>
			<input type="text" name="notifyUrl" value=""> <br/>

			<!-- <label>商品标记-goodsTag:</label>
            <input type="text" name="goodsTag" value=""> <br/> -->
		</div>


		<div id="alipayQR"  style="display:none">
			<label>指定支付方式-limitPay:</label>
			<input type="text" name="limitPay" value=""> <br/>

			<!-- <label>商品id-productId:</label>
            <input type="text" name="productId" value=""> <br/> -->

			<label>结果通知url-notifyUrl:</label>
			<input type="text" name="notifyUrl" value=""> <br/>

			<!-- <label>商品标记-goodsTag:</label>
            <input type="text" name="goodsTag" value=""> <br/> -->
		</div>
		<div id="wxApp" style="display:none">
			<label>指定支付方式-limitPay:</label>
			<input type="text" name="limitPay" value=""> <br/>

			<label>结果通知url-notifyUrl:</label>
			<input type="text" name="notifyUrl" value=""> <br/>
			<!--
            <label>商品标记-goodsTag:</label>
            <input type="text" name="goodsTag" value=""> <br/> -->
			<label>微信开放平台上创建应用所生成的AppID:</label>
			<input type="text" name="mobileAppId" value=""> <br/>
		</div>

		<div id="wxMicro" style="display:none">
			<label>授权码-authCode:</label>
			<input type="text" name="authCode" value=""> <br/>

			<label>结果通知url-notifyUrl:</label>
			<input type="text" name="notifyUrl" value=""> <br/>

			<!-- <label>商品标记-goodsTag:</label>
            <input type="text" name="goodsTag" value=""> <br/> -->
		</div>

		<div id="jdPay" style="display:none">
			<label>支付成功跳转路径url-callbackUrl:</label>
			<input type="text" name="callbackUrl" value=""> <br/>

			<label>支付完成后结果通知url-notifyUrl:</label>
			<input type="text" name="notifyUrl" value=""> <br/>
		</div>

		<div id="jdPayGate" style="display:none">
			<label>支付成功跳转路径url-callbackUrl:</label>
			<input type="text" name="callbackUrl" value=""> <br/>

			<label>支付完成后结果通知url-notifyUrl:</label>
			<input type="text" name="notifyUrl" value=""> <br/>
		</div>
		<div id="jdQR" style="display:none">
			<label>支付完成后结果通知url-notifyUrl:</label>
			<input type="text" name="notifyUrl" value=""> <br/>
		</div>
		<div id="jdMicro" style="display:none">
			<label>支付完成后结果通知url-notifyUrl:</label>
			<input type="text" name="notifyUrl" value=""> <br/>
		</div>
		<div id="alipayMicro" style="display:none">
			<label>支付完成后结果通知url-notifyUrl:</label>
			<input type="text" name="notifyUrl" value=""> <br/>
			<label>授权码-authCode:</label>
			<input type="text" name="authCode" value=""> <br/>
		</div>
	</div>

</body>
<script type="text/javascript">
	jQuery(function(){
		changeChannel($('#channel').get(0));
	})
	function changeChannel(chan){
		$('#extral').html($('#' + chan.value).html());
	}
</script>

</html>

