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
		#top{width: 100vw;height: 13vw;background: #5F9BF1;position: fixed;top: 0;}
		#select{width: 100vw;height: 13vw;background: #fff;position: fixed;top: 13vw;}
		#select span{width: 33.3vw;float: left;display: block;height: 12.2vw;text-align: center;line-height: 12.2vw;}
		#box{-webkit-overflow-scrolling:touch;position: fixed;height: 100%;width: 100%;bottom: 12vw;top:11vw;overflow: scroll;}
		#bottom{height: 12vw;width: 100vw;position: fixed;bottom: 0;left: 0;border-top: 1px solid #DEDEDE;background: white;}
		#bottom ul li{width: 25vw;float: left;height: 12vw;}
		#bottom ul li p{text-align: center;font-size: 3vw;height: 4vw;line-height: 4vw;color: #DEDEDE;}
	</style>
	<body onload="addBackListener();">
		<div id="top">
			<span style="width: 10vw;height: 12vw;display: block;float: left;"></span>
			<p style="color: #fff;height: 13vw;text-align: center;width: 90vw;line-height: 13vw;font-size: 4.8vw;">发现</p>
		</div>
		
		
		<div id="box">
			<iframe id="app_iframe" frameborder=”no” border=”0″ marginwidth=”0″ marginheight=”0″ allowtransparency=”yes” src="${url}" style="width:100%;height:100%"></iframe>
			
		</div>
		
		<div id="bottom">
			<ul>
				<a href="jssh.html">
				<li>
					<img src="images/sy01.png" style="width:5vw;margin-left: 10vw;margin-top: 1.5vw;"/>
					<p>首页</p>
				</li>
				</a>
				<a href="repay.html">
				<li>
					<img src="images/hk01.png" style="width:5vw;margin-left: 10vw;margin-top: 1.5vw;"/>
					<p>还款</p>
				</li>
				</a>
				<a href="productShop.html">
				<li>
					<img src="images/fx02.png" style="width:5vw;margin-left: 10vw;margin-top: 1.5vw;"/>
					<p style="color: #5F9BF1;">发现</p>
				</li>
				</a>
				<a href="myMess.html">
				<li>
					<img src="images/wd01.png" style="width:5vw;margin-left: 10vw;margin-top: 1.5vw;"/>
					<p>我的</p>
				</li>
				</a>
			</ul>
		</div>

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
		</script>
	</body>
</html>
    