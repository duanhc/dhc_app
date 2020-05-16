<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<title>借款额度</title>
<link rel="stylesheet" href="/css/weui.min.css">
<link rel="stylesheet" href="/css/jquery-weui.min.css">
<script src="/js/jquery.min.js"></script>
<script src="/js/jquery-weui.min.js"></script>
<script src="/js/jquery.form.js"  type="text/javascript"></script>
<style>
	html,body,div,span,applet,object,iframe,h1,h2,h3,h4,h5,h6,p,blockquote,pre,a,abbr,acronym,
	address,big,cite,code,del,dfn,em,img,ins,kbd,q,s,samp,small,strike,strong,sub,sup,tt,var,
	b,u,i,center,dl,dt,dd,ol,ul,li,fieldset,form,label,legend,table,caption,tbody,tfoot,thead,
	tr,th,td,article,aside,canvas,details,embed,figure,figcaption,footer,header,hgroup,menu,nav,
	output,ruby,section,summary,time,mark,audio,video{
		margin:0;
		padding:0;
		border:0;
		vertical-align:baseline;
	}
	article,aside,details,figcaption,figure,footer,header,hgroup,menu,nav,section{
		display:block;
	}
	body,html{
		color:#333; 
		font-family: "Microsoft-Yahei";
		margin:0 auto; 
		max-width:640px;
		height:100%;
	}
	.loan_bd{
		background:#fff url("images/bg.jpg") no-repeat center 0;
		background-size:100% 100%;
		width:100%;
		height:100%;
	}
	.loan_bd .loan_form{
		width:80%;
		margin:0 auto;
		position: relative;
		top: 14.2rem;
	}
	.weui-slider-box{
	}
	.weui-slider__handler{
		width: 2.8rem;
		height: 1rem;
		border-radius: 0.3rem;
		background-color: #5ABEFF;
		position:absolute;
		top:200%;
	}
	.weui-slider-box__value{
		margin-left:0;
		color:#ffffff;
	}
	.weui-slider__track{
		background-color:#965504;
	}
	.loan_font{
		font-weight:500;
		color:#FDCD61;
	}
	.loan_min,.loan_rule{
		display:flex;
		display:-webkit-flex;
		justify-content:center;
		font-size:0.1rem;
		color:#8D4E04;
	}
	.loan_money,.loan_time,.loan_poundage{
		padding:0.5rem 0.1rem;
		height:1.8rem;
	}
	.loan_time,.loan_poundage{
		display:flex;
		display:-webkit-flex;
		justify-content:flex-start;
	}
	.loan_time_lt,.loan_pf{
		display:flex;
		display-webkit-flex;
		align-items:flex-end;
		flex-wrap:nowrap;
		padding-left:0.8rem;
		padding-bottom:0.325rem;
		font-size:0.63rem;
		color:#8D4E04;
	}
	a:link{text-decoration:none;}
	a:visited{text-decoration:none; }
	a:hover{text-decoration:none;}
	a:active{text-decoration:none;}
	.time_lt{
		font-weight:500;
		color:#FDCD61;
		font-size:0.9rem;
		padding-right:0.3rem;
	}
	.hide {
		display: none;
	}
	.button {
		position: relative;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		width: 12.5rem;
		margin: 0 auto;
		margin-top:1.6rem;
		padding: 1.1rem 3.125rem;
		background-color: #5ABEFF;
		border: none;
		border-radius: 0.3125rem;
		box-shadow: 0 12px 24px 0 rgba(0, 0, 0, 0.2);
		color: white;
		font-weight: 300;
		text-transform: uppercase;
		overflow: hidden;
	}
	.button:before {
		position: absolute;
		content: '';
		bottom: 0;
		left: 0;
		width: 0%;
		height: 100%;
		background-color: #5ABEFF;
	}
	.button span {
		position: absolute;
		line-height: 0;
	}
	.button span i {
		transform-origin: center center;
	}
	.button span:nth-of-type(1) {
		top: 50%;
		transform: translateY(-50%);
	}
	.button span:nth-of-type(2) {
		top: 100%;
		transform: translateY(0%);
		font-size: 13.333333px;
	}
	.button span:nth-of-type(3) {
		display: none;
	}
	.active {
		background-color: #b78c52;
	}
	.active:before {
		width: 100%;
		transition: width 60s linear;
	}
	.active span:nth-of-type(1) {
		top: -100%;
		transform: translateY(-50%);
	}
	.active span:nth-of-type(2) {
		top: 50%;
		transform: translateY(-50%);
	}
	.active span:nth-of-type(2) i {
		animation: loading 500ms linear infinite;
	}
	.active span:nth-of-type(3) {
		display: none;
	}
	.finished {
		background-color: #54d98c;
	}
	.finished .submit {
		display: none;
	}
	.finished .loading {
		display: none;
	}
	.finished .check {
		display: block !important;
		font-size: 24px;
		animation: scale 0.5s linear;
	}
	.finished .check i {
		transform-origin: center center;
	}
	@keyframes loading {
		100% {
			transform: rotate(360deg);
		}
	}
	@keyframes scale {
		0% {
			transform: scale(10);
		}
		50% {
			transform: scale(0.2);
		}
		70% {
			transform: scale(1.2);
		}
		90% {
			transform: scale(0.7);
		}
		100% {
			transform: scale(1);
		}
	}
	@media screen and (min-width:320px) and (max-width:320px){
		.loan_bd .loan_form {
			position: relative;
			top: 10.2rem;
		}
	}
	@media screen and (min-height:812px) and (max-height:812px){
		.loan_bd .loan_form {
			position: relative;
			top: 15.2rem;
		}
	}
	@media screen and (min-width:768px) and (max-width:768px){
		.loan_bd .loan_form {
			position: relative;
			top: 15.2rem;
		}
	}
	@media screen and (min-width:1024px) and (max-width:1024px){
		.loan_bd .loan_form {
			position: relative;
			top: 19.2rem;
		}
	}
</style>
</head>

<body ontouchstart="" onload="addBackListener();">
	<div class="loan_bd">
		<form class="loan_form" id="loan_form_id">
			<div class="loan_money">
				<div class="weui-slider-box" id="slider1">
					<font class="loan_font">借款金额</font>
					<div class="weui-slider">
						<div id="sliderInner" class="weui-slider__inner">
							<div id="sliderTrack" style="width: 0%;" class="weui-slider__track"></div>
							<div id="sliderHandler" style="left: 40%;" class="weui-slider__handler">
								<div id="sliderValue" class="weui-slider-box__value">￥${lend.lend_count}</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="loan_min">最低借款额1000元</div>
			</div>
			<div class="loan_time">
				<font class="loan_font">借款期限</font>
				<div class="loan_time_lt"><font class="time_lt">${lend.lend_time}</font>随借随还，按日计费</div>
			</div>
			<div class="loan_poundage">
				<font class="loan_font">借款费用</font>
				<div class="loan_pf">手续费${lixi}元每天</div>
			</div>
			<button class="button" id="bt" type="button">
				<span class="submit" id="sub">提交</span>
				<span class="loading" id="sub_1"><i class="fa fa-refresh"></i></span>
				<span class="check"><i class="fa fa-check"></i></span>
			</button>
			<script>
                //返回键处理
                function addBackListener() {
                    document.addEventListener('plusready', function() {
                        var webview = plus.webview.currentWebview();
                        plus.key.addEventListener('backbutton', function() {
                            webview.canBack(function(e) {
                                if(e.canBack) {
                                    webview.back();
                                } else {
                                    //webview.close(); //hide,quit
                                    //plus.runtime.quit();
                                    //首页返回键处理
                                    //处理逻辑：1秒内，连续两次按返回键，则退出应用；
                                    var first = null;
                                    plus.key.addEventListener('backbutton', function() {
                                        //首次按键，提示‘再按一次退出应用’
                                        if (!first) {
                                            first = new Date().getTime();
                                            //console.log('再按一次退出应用');
                                            setTimeout(function() {
                                                first = null;
                                            }, 1000);
                                        } else {
                                            if (new Date().getTime() - first < 1500) {
                                                plus.runtime.quit();
                                            }
                                        }
                                    }, false);
                                }
                            })
                        });
                    });
                }
	
				function verify() {
					$.ajax({
    				url: "user_lend_verify.action",
    				type: "POST",
    				dataType: "JSON",
    				success: function(data) {
    					var hint = data.hint;
    					if(hint == "success") {
    						$.alert("申请成功，请关注微信公众号查看借款进度",function(){
    							window.location.href = "atten.html";
    						});
    					} else if(hint == "un_login") {
    						$.alert("登录超时，请重新登录",function(){
    							window.location.href = "../login.html";
    						});
    					} else if(hint == "sstep_one_by_one") {
    							window.location.href = "lendmoney.html";
    					}
    				  }
    				});
				}
			
				const button = document.querySelector('.button');
				const submit = document.querySelector('.submit');
				function toggleClass() {
					this.classList.toggle('active');
					document.getElementById("sub_1").innerHTML = "请稍等……";
					document.getElementById("bt").disabled=true;
				}
				function addClass() {
					this.classList.add('finished');
					verify();
				}
				button.addEventListener('click', toggleClass);
				button.addEventListener('transitionend', toggleClass);
				button.addEventListener('transitionend', addClass);
			</script>
			<span class="loan_rule">不向学生提供服务</span>
		</form>
	</div>
	<script type="text/javascript">
//		$(function(){
//			var $sliderTrack = $('#sliderTrack'),
//				$sliderHandler = $('#sliderHandler'),
//				$sliderValue = $('#sliderValue');
	 
//			var totalLen = $('#sliderInner').width(),
//				startLeft = 0,
//				startX = 0;
	 
//			$sliderHandler
//				.on('touchstart', function (e) {
//					startLeft = parseInt($sliderHandler.css('left')) ;
//					startX = e.originalEvent.changedTouches[0].clientX; //移动端
				   //pc端：e.originalEvent.clientX;
//			   })
//				.on('touchmove', function(e){
//					var dist = startLeft + e.originalEvent.changedTouches[0].clientX - startX,
//					//pc端：e.originalEvent.clientX;               
//					percent;
//					dist = dist < 0 ? 0 : dist > totalLen ? totalLen : dist;
//					percent =  parseInt(dist / totalLen * 40);
//					$sliderTrack.css('width', percent*2.5 + '%');
//					$sliderHandler.css('left', percent*2.5 + '%');
//					$sliderValue.text('￥'+(percent*100+1000));
//	 
//					e.preventDefault();
//				})
//			   .on('touchend',function(e){
//				   //代码
//				});
//		});
	</script>
</body>
</html>
 