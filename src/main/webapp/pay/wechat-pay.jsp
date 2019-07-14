<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>微信支付提交页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>

  <script>
  	  function onBridgeReady(){
  	   var timestamp = Date.parse(new Date());
  		WeixinJSBridge.invoke(
       'getBrandWCPayRequest',{
           "appId":"${order.appId}",     //公众号名称，由商户传入     
           "timeStamp":"${order.timeStamp}",         //时间戳，自1970年以来的秒数     
           "nonceStr":"${order.nonceStr}", //随机串     
           "package":"${order.package}",     
           "signType":"${order.signType}",         //微信签名方式：     
           "paySign":"${order.paySign}" //微信签名 
       },
       function(res){     
           if(res.err_msg == "get_brand_wcpay_request:ok" ) {
           		window.location.href = "user_collect_view.do";//支付完成
           }else{   
                //alert(res.err_code + res.err_desc + res.err_msg);
               	window.location.href = "user_collect_view.do";
           }    // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
       }
   ); 
}
if (typeof WeixinJSBridge == "undefined"){
   if( document.addEventListener ){
       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
   }else if (document.attachEvent){
       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
   }
}else{
   onBridgeReady();
}
  </script>
  <body>
  </body>
</html>
