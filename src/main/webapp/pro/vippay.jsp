<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page import="com.alipay.api.AlipayApiException" %>
<%@page import="com.alipay.api.AlipayClient" %>
<%@page import="com.alipay.api.DefaultAlipayClient" %>
<%@page import="com.alipay.api.domain.AlipayTradeWapPayModel" %>
<%@page import="com.alipay.api.request.AlipayTradeWapPayRequest" %>
<%@page import="com.fusibang.help.AlipayConfig" %>
<%
    /* *
     * 功能：支付宝手机网站支付接口(alipay.trade.wap.pay)接口调试入口页面
     * 版本：2.0
     * 修改日期：2016-11-01
     * 说明：
     * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
     请确保项目文件有可写权限，不然打印不了日志。
     */
%>
<%
    if (request.getParameter("WIDout_trade_no") != null) {
        // 商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"), "UTF-8");
        // 订单名称，必填
        String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(subject);
        // 付款金额，必填
        String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"), "UTF-8");
        // 商品描述，可空
        String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"), "UTF-8");
        // 超时时间 可空
        String timeout_express = "2m";
        // 销售产品码 必填
        String product_code = "QUICK_WAP_WAY";
        /**********************/
        // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
        //调用RSA签名方式
        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
        AlipayTradeWapPayRequest alipay_request = new AlipayTradeWapPayRequest();

        // 封装请求支付信息
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(out_trade_no);
        model.setSubject(subject);
        model.setTotalAmount(total_amount);
        model.setBody(body);
        model.setTimeoutExpress(timeout_express);
        model.setProductCode(product_code);
        alipay_request.setBizModel(model);
        // 设置异步通知地址
        alipay_request.setNotifyUrl(AlipayConfig.notify_url);
        // 设置同步地址
        alipay_request.setReturnUrl(AlipayConfig.return_url);

        // form表单生产
        String form = "";
        try {
            // 调用SDK生成表单
            form = client.pageExecute(alipay_request).getBody();
            response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
            response.getWriter().write(form);//直接将完整的表单html输出到页面
            response.getWriter().flush();
            response.getWriter().close();
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>会员模式</title>
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<link rel="stylesheet" href="http://hkwj.v228.10000net.cn/css/weui.min.css">
		<link rel="stylesheet" href="http://hkwj.v228.10000net.cn/css/jquery-weui.min.css">	
		<script src="http://hkwj.v228.10000net.cn/js/jquery.min.js"></script>
		<script src="http://hkwj.v228.10000net.cn/js/jquery-weui.min.js"></script>
		<script src="http://hkwj.v228.10000net.cn/js/swiper.min.js"></script>
		<script src="http://hkwj.v228.10000net.cn/js/city-picker.min.js"></script>
		<script src="http://hkwj.v228.10000net.cn/js/jquery.form.js" type="text/javascript"></script> 
	</head>	
	<style type="text/css">
		*{padding: 0;margin: 0;}
		ul li{list-style: none;}
		html{background: #faf6f3;}
		::-webkit-input-placeholder { color: #AAAAAA; } :-moz-placeholder { color: #AAAAAA; } ::-moz-placeholder { color: #AAAAAA; } :-ms-input-placeholder { color: #AAAAAA; }  
		#top{text-align: center;font-size: 4vw;background: #f77601;height: 10vw;color: #fff;line-height: 10vw;}
		#cont{width: 100vw;height: 25wv;background: #fff;}
		#content{width: 100vw;margin-top: 4vw;margin-bottom: 20vw;}
		#content ul li{width: 100vw;margin-top: 2vw;}
		#content ul li .c_header{width: 100vw;height: 7vw;}
		#content ul li  .c_main{width: 100vw;height: 17vw;background: #fff;}
		#content ul li  .c_main .pic{width: 30vw;float: left;height: 12vw;}
		#content ul li  .c_main .c_name{width: 30vw;float: left;height: 12vw;}
		#content ul li  .c_main .c_right{width: 30vw;float: left;height: 12vw;}
		#content ul li  .c_main .c_right .ljsq{width: 30vw;height: 8vw;background: #F77601;font-size: 3.5vw;color: #fff;text-align: center;line-height: 8vw;border-radius: 4vw;float: left;margin-top: 3.5vw;}
		#content ul li  .c_main .c_right .no_ljsq{width: 30vw;height: 8vw;background: #c1c1c1;font-size: 3.5vw;color: #fff;text-align: center;line-height: 8vw;border-radius: 4vw;float: left;margin-top: 3.5vw;}
		#content ul li  .c_main .c_right .synum{float: left;line-height: 3vw;text-align: center;font-size: 3vw;width: 30vw;margin-top: 1vw;}
		#content ul li  .c_pro{width: 100vw;background: #FFFFFF;border-top: 1px solid #f6f6f6;}
	</style>
	<body>
		<div id="top">
			会员
		</div>
		<div id="cont">
			<p style="text-align: center;font-weight: 700;line-height: 6vw;font-size: 4vw;">公告</p>
			<p style="padding-left: 3vw;padding-right: 3vw;font-size: 3.5vw;">响应国家 "地址砍头息政策"，为了给您带来更便捷的服务，平台调整为会员卡模式。支付完成后，5分钟后重新打开app即可提现，若未及时到账，则审核需要3-5个工作日，系统自动核查您的征信，有任何疑问请加客服qq，为您带来不便请谅解。</p>
			<p style="padding-left: 3vw;padding-right: 3vw;font-size: 3.5vw;">(若有疑问请联系客服12：00~21：00)</p>
			<p style="padding-left: 3vw;padding-right: 3vw;font-size: 3.5vw;padding-bottom: 3vw;">客服QQ：207023628</p>
		</div>
		<div id="content">
			<ul>
				<li>
					<div class="c_header">
						<p style="margin-left: 3vw;border-left: 1.5vw solid #F77601;text-indent: 2vw;font-size: 3.5vw;">红钻会员</p>
					</div>
					<div class="c_main">
						<div class="pic">
							<img src="images/hzhy.jpg" style="width: 12vw;margin-left: 11vw;margin-top: 3vw;"/>
						</div>
						<div class="c_name">
							<p style="font-size: 3.8vw;line-height: 12vw;">红钻会员</p>
							<p style="font-size: 3.5vw;line-height: 2vw;font-weight: 700;">￥88/年</p>
						</div>
						<div class="c_right">
							<div id="ljsq1" class="ljsq" onclick="ljsqClick1()">
								立即申请
							</div>
							<div class="synum">
								剩余：112
							</div>
						</div>
					</div>
					<div class="c_pro">
						<p style="padding-left: 3vw;padding-right: 3vw;font-size: 3.5vw;padding-top: 1vw;">下款率90%(可退款)</p>
						<p style="padding-left: 3vw;padding-right: 3vw;font-size: 3.5vw;padding-bottom: 1vw;">可以提现额度3000元，免人工审核，无回访，24小时内到账</p>
					</div>
				</li>
				<li>
					<div class="c_header">
						<p style="margin-left: 3vw;border-left: 1.5vw solid #F77601;text-indent: 2vw;font-size: 3.5vw;">金钻会员</p>
					</div>
					<div class="c_main">
						<div class="pic">
							<img src="images/hzhy2.jpg" style="width: 12vw;margin-left: 11vw;margin-top: 3vw;"/>
						</div>
						<div class="c_name">
							<p style="font-size: 3.8vw;line-height: 12vw;">金钻会员</p>
							<p style="font-size: 3.5vw;line-height: 2vw;font-weight: 700;">￥188/年</p>
						</div>
						<div class="c_right">
							<div id="ljsq2" class="ljsq" onclick="ljsqClick2()">
								立即申请
							</div>
							<div class="synum">
								剩余：63
							</div>
						</div>
					</div>
					<div class="c_pro">
						<p style="padding-left: 3vw;padding-right: 3vw;font-size: 3.5vw;padding-top: 1vw;">下款率100%(可退款)</p>
						<p style="padding-left: 3vw;padding-right: 3vw;font-size: 3.5vw;padding-bottom: 1vw;">可以提现9200元，平台免审核，2小时内到账</p>
					</div>
				</li>
				<li>
					<div class="c_header">
						<p style="margin-left: 3vw;border-left: 1.5vw solid #F77601;text-indent: 2vw;font-size: 3.5vw;">黑钻会员</p>
					</div>
					<div class="c_main">
						<div class="pic">
							<img src="images/hzhy3.jpg" style="width: 12vw;margin-left: 11vw;margin-top: 3vw;"/>
						</div>
						<div class="c_name">
							<p style="font-size: 3.8vw;line-height: 12vw;">黑钻会员</p>
							<p style="font-size: 3.5vw;line-height: 2vw;font-weight: 700;">￥288/年</p>
						</div>
						<div class="c_right">
							<div id="ljsq3" class="ljsq" onclick="ljsqClick3()">
								立即申请
							</div>
							<div class="synum">
								剩余：32
							</div>
						</div>
					</div>
					<div class="c_pro">
						<p style="padding-left: 3vw;padding-right: 3vw;font-size: 3.5vw;padding-top: 1vw;">大额专享，老用户受邀开通</p>
						<p style="padding-left: 3vw;padding-right: 3vw;font-size: 3.5vw;padding-bottom: 1vw;">可以提现额度15000元，免人工审核，1小时内到账</p>
					</div>
				</li>
			</ul>
		</div>

        <form name=alipayment action='' method=post target="_blank">
<%--                    商户订单号：--%>
                        <input type="hidden" id="WIDout_trade_no" name="WIDout_trade_no"/>
<%--                    订单名称：--%>
                        <input type="hidden" id="WIDsubject" name="WIDsubject"/>
<%--                    付款金额：--%>
                        <input type="hidden" id="WIDtotal_amount" name="WIDtotal_amount"/>
<%--                    商品描述：--%>
                        <input type="hidden" id="WIDbody" name="WIDbody"/>
                        <button id="qrdd" class="" type="submit" style="display:none;">确 认</button>
        </form>

        <script language="javascript">
            function GetDateNow() {
                var vNow = new Date();
                var sNow = "";
                sNow += String(vNow.getFullYear());
                sNow += String(vNow.getMonth() + 1);
                sNow += String(vNow.getDate());
                sNow += String(vNow.getHours());
                sNow += String(vNow.getMinutes());
                sNow += String(vNow.getSeconds());
                sNow += String(vNow.getMilliseconds());
                document.getElementById("WIDout_trade_no").value = sNow;
                // document.getElementById("WIDsubject").value = "会员商品";
                // document.getElementById("WIDtotal_amount").value = "88.00";
                // document.getElementById("WIDbody").value = "购买会员商品88.00元";
            }

            function ljsqClick1() {
                GetDateNow();
                document.getElementById("WIDsubject").value = "红钻会员";
                // document.getElementById("WIDtotal_amount").value = "88.00";
                document.getElementById("WIDtotal_amount").value = "0.01";
                document.getElementById("WIDbody").value = "购买红钻会员88.00元";
                document.getElementById("qrdd").click();
            }

            function ljsqClick2() {
                GetDateNow();
                document.getElementById("WIDsubject").value = "金钻会员";
                document.getElementById("WIDtotal_amount").value = "188.00";
                document.getElementById("WIDbody").value = "购买金钻会员188.00元";
                document.getElementById("qrdd").click();
            }

            function ljsqClick3() {
                GetDateNow();
                document.getElementById("WIDsubject").value = "黑钻会员";
                document.getElementById("WIDtotal_amount").value = "288.00";
                document.getElementById("WIDbody").value = "购买黑钻会员288.00元";
                document.getElementById("qrdd").click();
            }

        </script>
	</body>
</html>
