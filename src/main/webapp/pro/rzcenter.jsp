<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<link rel="stylesheet" href="http://dzez.v228.10000net.cn/css/weui.min.css">
		<link rel="stylesheet" href="http://dzez.v228.10000net.cn/css/jquery-weui.min.css">
		<script src="http://dzez.v228.10000net.cn/js/jquery.min.js"></script>
		<script src="http://dzez.v228.10000net.cn/js/jquery-weui.min.js"></script>
		<script src="http://dzez.v228.10000net.cn/js/jquery.form.js"  type="text/javascript"></script>
	</head>	
	<style type="text/css">
		*{padding: 0;margin: 0;}
		ul li{list-style: none;}
		::-webkit-input-placeholder { color: #AAAAAA; } :-moz-placeholder { color: #AAAAAA; } ::-moz-placeholder { color: #AAAAAA; } :-ms-input-placeholder { color: #AAAAAA; }  
		#top{width: 100vw;height: 13vw;background: #5F9BF1;}
		#top_pic{width: 100vw;height: 50vw;position: relative;}
		#top_pic .wcrz{width: 100vw;position: absolute;left: 0vw;top: 15vw;text-align: center;font-size: 4vw;color: white;height: 12vw;line-height: 12vw;}
		#top_pic .qtrz{width: 100vw;position: absolute;left: 0vw;top: 25vw;text-align: center;font-size: 3vw;color: white;height: 12vw;line-height: 12vw;}
		#content{width: 100vw;height: 78vw;}
		#content ul li{height: 35vw;width: 40vw;background: white;box-shadow:1px 1px 2px #DEDEDE;margin-left: 6vw;margin-bottom: 5vw;float: left;border-radius: 5vw;}
		#content ul li .pic{width: 40vw;height: 25vw;float: left;}
		#content ul li p{width: 40vw;height: 10vw;float: left;line-height: 10vw;font-size: 4vw;color: #949494;text-align: center;}
		#bottom{height: 12vw;width: 100vw;position: fixed;bottom: 0;left: 0;border-top: 1px solid #DEDEDE;background: white;}
		#bottom ul li{width: 25vw;float: left;height: 12vw;}
		#bottom ul li p{text-align: center;font-size: 3vw;margin-top: -1vw;height: 4vw;line-height: 4vw;color: #DEDEDE;}
		.regButton{width: 90vw;margin-left: 5vw;background: #56A4F6;height: 11vw;font-size: 5vw;text-align: center;line-height: 11vw;border-radius: 2vw;margin-top: 3vw;color: white;font-weight: 700;margin-bottom: 4vw;}
	</style>
	
	<body>
		<div id="top">
			<span style="width: 10vw;height: 12vw;display: block;float: left;"><img src="images/tuihou.png" onclick="window.open('myMess.html','_self');" style="width: 3vw;margin-left: 3vw;margin-top: 4vw;"/></span>
			<p style="color: #fff;height: 13vw;text-align: center;width: 90vw;line-height: 13vw;font-size: 4.8vw;">认证中心</p>
		</div>
		<div id="top_pic">
			<img src="images/rzCenter_top.png" style="width: 100vw;height: 50vw;z-index:-1;"/>
			<div class="wcrz">
					已完成 <span style="font-size: 6vw;">${count}</span> 项认证
			</div>
		</div>
		
		<div id="content" style="z-index:100;">
			<ul>
				<li onclick="step1${identify.step2 == 1 ? 's' : ''}();">
					<div class="pic">
						<img src="images/rz0${identify.step2 == 1 ? '1' : '2'}.png" style="width: 20vw;margin-left: 10vw;margin-top: 10vw;"/>
					</div>
					<p>身份证认证</p>
				</li>
				<li onclick="step2${identify.step4 == 1 ? 's' : ''}();">
					<div class="pic">
						<img src="images/tx0${identify.step4 == 1 ? '1' : '2'}.png" style="width: 15vw;margin-left: 12.5vw;margin-top: 10vw;"/>
					</div>
					<p>个人信息</p>
				</li>
				<li onclick="step3${identify.step5 == 1 ? 's' : ''}()">
					<div class="pic">
						<img src="images/bf0${identify.step5 == 1 ? '1' : '2'}.png" style="width: 15vw;margin-left: 12.5vw;margin-top: 10vw;"/>
					</div>
					<p>备份通讯录</p>
				</li>
				<li onclick="step4${identify.step6 == 1 ? 's' : ''}()">
					<div class="pic">
						<img src="images/xh0${identify.step6 == 1 ? '1' : '2'}.png" style="width: 15vw;margin-left: 12.5vw;margin-top: 10vw;"/>
					</div>
					<p>银行卡认证</p>
				</li>
				
			</ul>
		</div>	
		
		<!-- 
			<c:if test="${identify.pay == 0 && identify.put == 1}">
			<div class="regButton" id="bottomButton" onclick="expedite()">
				系统快审
			</div>
			</c:if>
		 -->
		<div class="regButton" id="bottomButton" onclick="${identify.put == 0 ? 'submit()' : (check == 0 ? 'submited()' : 'check()')}">
			${identify.put == 0 ? '提交审核' : (check == 0 ? '已认证' : '审核中')}
		</div>
		
	</body>
	<script>
		var check_status = ${check};
		function submited() {
			$.alert("认证已完成",function(){
					
			});
		}
		
		function check() {
				$.alert("提交审核中，请耐心等待",function(){
							window.location.href = "auth_center.action";
				});
		}
				
		function submit() {
			var s4 = ${identify.step6};
			if (s4 == 1) {
				$.ajax({
					url : "identify_submit.action",
					type : "GET",
					dataType : "json",
					success : function(data) {
						var hint = data.hint;
						if (hint == "success") {
							if (check_status == 1) {
								$.alert("提交审核中，请耐心等待",function(){
									window.location.href = "auth_center.action";
								});
							} else {
								$.confirm({
  								title: '提示',
  								text: '提交成功，前往借款',
  								onOK: function () {
  									window.location.href = "lendmoney.html"; 						
 				   				},
  								onCancel: function () {
									window.location.href = "auth_center.action";
  								
  								}					
  			    				});	
							}
							
						}else if(hint = "un_login") {
							$.alert("登录超时，请重新登录",function(){
								window.location.href = "../login.html";
							});
						}else if(hint == "step_one_by_one") {
							$.alert("请完成所有认证后提交",function(){
								window.location.href = "auth_center.action";
							});
						}
					}
					
				});
			} else {
				$.alert("请完成所有认证后提交",function(){
				
				});
			}
		}
		
		function step1() {
			window.open('id_card_view.action', '_self');
		}
		function step1s() {
			$.alert("身份证认证已完成",function(){
				
			});
		}
		
		function step2() {
			var s1 = ${identify.step2};
			if (s1 == 1) {
				window.open('user_detail_view.action?uuid=' + Math.random()  , '_self');
			} else {
				$.alert("请先完成身份证认证",function(){
				
				});
			}
		}
		function step2s() {
			$.alert("填写信息已完成",function(){
				
			});
		}
		
		function step3() {
			var s2 = ${identify.step4};
			if (s2 == 1) {
				window.open('user_contact_view.action?uuid=' + Math.random()  , '_self');
			} else {
				$.alert("请先填写个人信息",function(){
				
				});
			}
			
		}
		function step3s() {
			$.alert("备份联系人已完成",function(){
				
			});
		}
		
		function step4() {
			var s3 = ${identify.step5};
			if (s3 == 1) {	
				$.alert("银行卡认证请绑定本人实名银行卡",function(){
					$.ajax({
					url : "bank_auth.action?source=ios",
					type : "GET",
					dataType : "text",
					success : function(data) {
						if (data.indexOf("un_login") != -1) {
							$.alert("登录超时，请重新登录",function(){
								window.location.href = "../login.html";
							});
						} else {
							window.location.href = "template.do?title=" + "绑定银行卡"  + "&url=" + data;
						}
					}
					
					});
				});
				
			} else {
				$.alert("请先备份通讯录",function(){
				
				});
			}
		}
		
		function step4s() {
			$.alert("银行卡认证已完成",function(){
				
			});
		}
	</script>
</html>
    