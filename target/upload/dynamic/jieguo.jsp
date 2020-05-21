<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>首页</title>
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	</head>	
	<style type="text/css">
		*{padding: 0;margin: 0;}
		ul li{list-style: none;}
		::-webkit-input-placeholder { color: #D3D3D6; } :-moz-placeholder { color: #D3D3D6; } ::-moz-placeholder { color: #D3D3D6; } :-ms-input-placeholder { color: #D3D3D6; }  
		.top{width: 100vw;height: 12vw;background: #3c89fd;}
		.top .tui{width: 12vw;height: 12vw;float: left;}
		.top .main{height: 12vw;color: #FFFFFF;line-height: 12vw;font-size: 4vw;text-align: center;}
		.top .home{width: 12vw;height: 12vw;float: left;}
		.mess{width: 100vw;height: 12vw;background: #89cdfa;margin-top: 3vw;}
		.mess p{color: #FFFFFF;font-size: 3.3vw;text-align: center;line-height: 5vw;}
		#content{width: 100vw;margin-top: 3vw;}
		#content ul li{width: 100vw;height: 24vw;border-bottom: 1px solid #efefef;}
		#content ul li .pic{width: 20vw;height: 24vw;float: left;}
		#content ul li .center{width: 50vw;height: 24vw;float: left;}
		#content ul li .center p{font-size: 3.5vw;line-height: 7vw;color: #676767;}
		#content ul li .right{width: 30vw;height: 24vw;float: left;}
		#content ul li .right .button{width: 25vw;color: #4389ff;height: 8vw;margin-top: 5vw;float: left;text-align: center;border: 1px solid #4389ff;border-radius: 2vw;line-height: 8vw;font-size: 3.8vw;}
		#content ul li .right p{font-size: 3vw;color: #cecece;float: left;text-align: left;width: 30vw;margin-top: 2vw;}
        #bottom{height: 12vw;width: 100vw;position: fixed;bottom: 0;left: 0;border-top: 1px solid #DEDEDE;background: white;}
        #bottom ul li{width: 33.3vw;float: left;height: 12vw;}
        #bottom ul li p{text-align: center;font-size: 3vw;margin-top: -1vw;height: 4vw;line-height: 4vw;color: #DEDEDE;}
    </style>
	<body>
		<div class="top">
			<%--<div class="tui">
				<img src="../images/fanhui.png" style="width: 6vw;margin-top: 3vw;margin-left: 3vw;"/>
			</div>--%>
			<div class="main">
				贷款超市
			</div>
			<%--<div class="home">
				<img src="../images/home.png" style="width: 5vw;margin-top: 3.5vw;margin-left: 3vw;"/>
			</div>--%>
		</div>
		<%--<div class="mess">
			<p>您的筹集已完成</p>
			<p>你的信息已报备给以下资金方，用同一手机申请100%下款！</p>
		</div>--%>
		<div id="content">
			<ul>
                <c:forEach items="${hidden}" var="app">
                        <a href="app_redirect.do?id=${app.id}" target="_blank">
                            <li>
                                <div class="pic">
                                    <img src="../upload/${app.logo}" style="width: 16vw;margin-top: 4vw;margin-left: 2vw;"/>
                                </div>
                                <div class="center">
                                    <p style="font-weight: 700;font-size: 4.5vw;margin-top: 2vw;">${app.name}</p>
                                    <p>最高可贷：<span style="color: #f3a553;">${app.min_limit}-${app.max_limit}</span></p>
                                    <p>${app.descript}</p>
                                </div>
                                <div class="right">
                                    <div class="button">
                                        一键申请
                                    </div>
                                    <p>${app.all_ua + 8768}人已申请</p>
                                </div>
                            </li>
                        </a>
                </c:forEach>
			</ul>
		</div>

        <div id="bottom">
            <ul>
                <a href="app_jieguo.do">
                    <li>
                        <img src="../pro/images/sy02.png" style="width:5vw;margin-left: 14vw;margin-top: 1.5vw;"/>
                        <p style="color: #5F9BF1;">首页</p>
                    </li>
                </a>
                <a href="../pro/repay.html">
                    <li>
                        <img src="../pro/images/hk01.png" style="width:5vw;margin-left: 14vw;margin-top: 1.5vw;"/>
                        <p>借款管理</p>
                    </li>
                </a>
                <!--<a href="faxian.do">
                <li>
                    <img src="images/fx01.png" style="width:5vw;margin-left: 10vw;margin-top: 1.5vw;"/>
                    <p>发现</p>
                </li>-->
                </a>
                <li>
                    <img src="../pro/images/wd01.png" style="width:5vw;margin-left: 14vw;margin-top: 1.5vw;"/>
                    <p>我的</p>
                </li>
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
 