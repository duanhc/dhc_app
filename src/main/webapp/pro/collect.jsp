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
		a{text-decoration: none;}
		html{background: #F5F5F5;}
		ul li{list-style: none;}
		#top{width: 100vw;height: 12vw;background: #1283e3;}
		html{background: url(images/18.jpg) no-repeat;background-size: 100% auto;overflow: hidden;}
		#content{width: 92vw;height: 40vw;margin-top: 45vw;position: relative;background: url(images/repay_top.png) no-repeat;background-size:100%;margin-left: 4vw;margin-bottom: 2vw;}
		#content .header{width: 92vw;height: 12vw;line-height: 14vw;text-indent: 5vw;font-weight: 700;font-size: 5vw;color: #FFFFFF;}
		#content .xyed{width: 87vw;height: 6vw;line-height: 6vw;font-size: 3.5vw;color: #ffffff;margin-left: 5vw;}
		#content .xyed_sum{width: 87vw;height: 12vw;line-height: 12vw;font-size: 7vw;color: #ffffff;margin-left: 5vw;font-weight: 700;}
		#content .yd_item{width: 87vw;margin-left: 5vw;height: 10vw;}
		#content .yd_item ul li{float: left;width: 42vw;float: left;}
		#content .yd_item ul li .ed{font-size: 3.5vw;line-height: 8vw;color: #ffffff;float: left;}
		#content .yd_item ul li span{line-height: 8vw;font-size: 3.5vw;font-weight: 700;color: #ffffff ;}
		#liucheng{width: 92vw;margin-left: 4vw;height: 50vw;}
		#liucheng ul li{width: 92vw;height: 10vw;border-radius: 8vw;background: #0B56FF;}
		#liucheng ul .false{width: 92vw;height: 10vw;border-radius: 8vw;background: #a5a5a5;}
		#liucheng ul li .left{width: 7vw;height: 7vw;margin-top: 1.5vw;border-radius: 50%;background: white;margin-left: 2vw;float: left;text-align: center;line-height: 7vw;font-size: 4vw;color: #1381e2;}
		#liucheng ul .false .left{color: #a5a5a5;}
		#liucheng ul li .right{width: 80vw;height: 10vw;float: left;line-height: 10vw;color: #FFFFFF;text-align: center;}
		#liucheng ul .space{height: 10vw;width: 92vw;float: left;}
		.bottom{width: 100vw;height: 12vw;position: fixed;bottom: 0;left: 0;}
		.bottom .item{width: 50vw;height: 12vw;float: left;line-height: 12vw;text-align: center;color:#fff;font-size: 4vw;}
	</style>
	<body onload="addBackListener();">
		

		<div id="content">
			<div class="header">
				恭喜您申请成功
			</div>
			<div class="xyed">
				借款额度(元)
			</div>
			<div class="xyed_sum">
				${lend.lend_count}
			</div>
			<div class="yd_item">
				<ul>
					<li>
						<div class="ed">
							借款期限
						</div>
						<span>${lend.lend_time}</span>
					</li>
					
				</ul>
			</div>
		</div>
		
		<div id="liucheng">
			<ul>
				<li>
					<div class="left">
						1
					</div>
					<div class="right">
						借款审核成功
					</div>
				</li>
				<li style="background: none;height: 9vw;">
					<img src="images/next.png" style="width: 7vw;margin-top: 1.5vw;margin-left: 44vw;"/>
				</li>
				<li>
					<div class="left">
						2
					</div>
					
					<div class="right">
						筹集资金中（${radio}%）
					</div>
				</li>
				<li style="background: none;height: 9vw;">
					<img src="images/next.png" style="width: 7vw;margin-top: 1.5vw;margin-left: 44vw;"/>
				</li>
				<li class="false">
					<div class="left">
						3
					</div>
					
					<div class="right">
						借款成功
					</div>
				</li>
			</ul>
		</div>
		
		<c:if test="${identify.pay == 0}">
			<div class="bottom">
				<div class="item" style="background: #1283E3;width:${success?'100%':'50%'}" onclick="${success?'colectSuccess()':'commonCollect()'}">
					${success?'筹集完成':'资金筹集中'}
				</div>
			
		
				<c:if test="${!success}">
					<div class="item" style="background: #ffb516;" onclick="speedCollect()">
						加速筹集
					</div>
				</c:if>	
			</div>
		</c:if>	
		
		<c:if test="${identify.pay == 1}" >
			<div class="bottom">
				<div class="item" style="background: #ffb516;width:100%" onclick="${success?'colectSuccess()':'commonCollect2()'}">
					${success?'筹集完成':'加速筹集中'}
				</div>
			</div>
		</c:if>
			
		
		<script>
	
	
	function colectSuccess() {

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

		$.confirm({
  					title: '筹集成功',
  					text: '已为你完成筹集，查看筹集结果',
  					onOK: function () {
   						 window.location.href = "app_jieguo.do"; 
 				   },
  					onCancel: function () {				
 						window.location.href= window.location.href;
  					}
  			  });
	}
	
	function commonCollect2() {
	
      		$.confirm({
  					title: '资金筹集中',
  					text: '极速筹集中，平台为您推荐了一些低门槛口子，去看看吧！',
  					onOK: function () {
   						 window.location.href = "app_market.do"; 
 				   },
  					onCancel: function () {				
 						window.location.href= window.location.href;
  					}
  			  });
	}
	
	function commonCollect() {
	
      		$.confirm({
  					title: '资金筹集中',
  					text: '正在为您努力筹集资金，根据目前序列，预计3-7个工作日内获取筹集完成，平台同时为您推荐了一些低门槛口子，去看看吧！',
  					onOK: function () {
   						 window.location.href = "app_market.do"; 
 				   },
  					onCancel: function () {				
 						window.location.href= window.location.href;
  					}
  			  });
	}
	
	function speedCollect() {
		$.confirm({
  					title: '加速筹集',
  					text: '加速筹集资金一小时内完成，需要向相关平台支付必要的费用，如下款失败请七天内联系客服申请退款',
  					onOK: function () {
   						window.location.href = "pro/qrzf.html";
 				    },
  					onCancel: function () {				
 						window.location.href= window.location.href;
  					}
  		});
	}
</script>
	</body>
	
</html>
     