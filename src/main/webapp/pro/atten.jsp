<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		#content{width: 100vw;height: 60vw;}
		#content ul li{height: 12vw;width: 60vw;background: white;border-bottom: 1px solid #F5F5F5;margin: 0 auto;}
		#content ul li .pic{width: 14vw;height: 12vw;float: left;}
		#content ul li .item{width: 70vw;height: 12vw;float: left;line-height: 12vw;font-size: 4vw;color: #949494;}
		#content ul li .more{width: 10vw;float: right;height: 12vw;}
		#content ul li .is_rz{width: 16vw;height: 12vw;float: left;font-size: 3.0vw;text-align: right;line-height: 12vw;color: #FCCD2F;}
		.regButton{width: 90vw;margin-left: 3vw; background: #5F9BF1;height: 11vw;font-size: 4vw;text-align: center;line-height: 11vw;border-radius: 5vw;color: white;}
		#bottom{height: 12vw;width: 100vw;position: fixed;bottom: 0;left: 0;border-top: 1px solid #DEDEDE;background: white;}
		#bottom ul li{width: 25vw;float: left;height: 12vw;}
		#bottom ul li p{text-align: center;font-size: 3vw;height: 4vw;line-height: 4vw;color: #DEDEDE;}
		#hidebg { position:absolute;left:0px;top:0px;
	      background-color:#000;
	      width:100%;  
	      filter:alpha(opacity=40);  
	      opacity:0.4;  
	      display:none; 
	      z-Index:2;
	      height: 100%;}
      	#hidebox{width: 70vw;height: 48vw;background: white;position: fixed;top: 60vw;z-index: 100;left: 15vw;border-radius: 5vw;display: none;}
     	#hidebox .title{font-weight: 700;font-size: 4.5vw;border-bottom: 1px solid #DEDEDE;height: 12vw;text-align: center;line-height: 12vw;}
	  	#hidebox .item{width: 70vw;height: 18vw;}
	  	#hidebox .item .pic{width: 20vw;height: 18vw;float: left;}
  		#hidebox .item .mess{width: 45vw;height: 15vw;float: left;margin-top: 1.5vw;}
  		#hidebox .item .mess p{font-size: 3vw;height: 5vw;line-height: 5vw;color: #4A4A4A;}
		
	</style>
	<script>
		control.atten();
	</script>
	<body onload="addBackListener();">
		<div id="top">
			<span style="width: 10vw;height: 12vw;display: block;float: left;"><img onclick="window.open('myMess.html','_self');" src="images/tuihou.png" style="width: 3vw;margin-left: 3vw;margin-top: 4vw;"/></span>
			<p style="color: #fff;height: 13vw;text-align: center;width: 90vw;line-height: 13vw;font-size: 4.8vw;">关注公众号</p>
		</div>
		<div style="width: 55vw;margin-top: 13vw;margin: 15vw auto;height: 1vw">
			<img src="images/pay_wechat.png" style="width:11 vw;height: 11vw" />
			<img src="images/arrow_double.png"  style="width:11 vw;height: 11vw;margin-left: 4vw" />
			<img src="../tipimages/logo.jpg"  style="width:11 vw;height: 11vw;margin-left: 4vw" />
		</div>	
		<h3>1、扫码关注</h3>
		<div style="width: 100%;height:22vw;text-align: center">
			
			<img src="../tipimages/qrcode.jpg"  style="width:22 vw;height: 22vw;margin-left: 4vw" />
		</div>
		<div style="width: 100%;text-align: center;margin-bottom: 4vw">		
			<p style="font-size: 4vw" >长按保存图片，微信扫一扫关注公众号</p>
		</div>
		
		
		<h3>2、手动搜索"星光口袋"关注</h3>
		<div style="width: 100%">
			<img style="width: 49%;float: left;margin-left: 1%" src="../tipimages/tip1.png">
			<img style="width: 49%;float: left" src="../tipimages/tip2.png">
		</div>
		<div style="width: 100%;margin-top: 4vw">
			<img style="width: 49%;float: left;margin-left: 1%" src="../tipimages/tip3.png">
			<img style="width: 49%;float: left" src="../tipimages/tip4.png">
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
    