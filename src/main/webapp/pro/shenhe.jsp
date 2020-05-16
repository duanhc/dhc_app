<%@page import="com.alipay.api.internal.util.AlipaySignature"%>
<%
    /* *
     功能：支付宝页面跳转同步通知页面
     版本：3.2
     日期：2011-03-17
     说明：
     以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
     该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

     //***********页面功能说明***********
     该页面可在本机电脑测试
     可放入HTML等美化页面的代码、商户业务逻辑程序代码
     TRADE_FINISHED(表示交易已经成功结束，并不能再对该交易做后续操作);
     TRADE_SUCCESS(表示交易已经成功结束，可以对该交易做后续操作，如：分润、退款等);
     //********************************
     * */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.fusibang.help.AlipayConfig"%>
<%@ page import="com.alipay.api.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">

		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<link rel="stylesheet" href="https://cdn.bootcss.com/weui/1.1.2/style/weui.min.css">
		<link rel="stylesheet" href="https://cdn.bootcss.com/jquery-weui/1.2.0/css/jquery-weui.min.css">	
		<script src="https://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
		<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/jquery-weui.min.js"></script>
		<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/swiper.min.js"></script>
		<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/city-picker.min.js"></script>
	</head>	
	<style type="text/css">
		*{padding: 0;margin: 0;}
		ul li{list-style: none;}
		::-webkit-input-placeholder { color: #AAAAAA; } :-moz-placeholder { color: #AAAAAA; } ::-moz-placeholder { color: #AAAAAA; } :-ms-input-placeholder { color: #AAAAAA; }  
		#top{width: 100vw;height: 13vw;background: #5F9BF1;margin-bottom: 85vw;}
		html{background: url(images/shenhe.jpg) no-repeat;background-size:100% auto;}
		.regButton{width: 90vw;margin-left: 5vw; background: #5F9BF1;height: 11vw;font-size: 5vw;text-align: center;line-height: 11vw;border-radius: 2vw;margin-top: 8vw;color: white;}
	</style>
	<body>

        <%
            //获取支付宝GET过来反馈信息
            Map<String,String> params = new HashMap<String,String>();
            Map requestParams = request.getParameterMap();
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }

            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
            //商户订单号

            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号

            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
            //计算得出通知验证结果
            //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
            boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");

            if(verify_result){
                //验证成功

            }else{
                //验证失败

            }
        %>

		<div id="top">
			<span style="width: 10vw;height: 12vw;display: block;float: left;"><img src="images/tuihou.png" style="width: 3vw;margin-left: 3vw;margin-top: 4vw;"/></span>
			<p style="color: #fff;height: 13vw;text-align: center;width: 90vw;line-height: 13vw;font-size: 4.8vw;">审核</p>
		</div>
		
		<div class="regButton">
			审核中
		</div>
		
	</body>
</html>
