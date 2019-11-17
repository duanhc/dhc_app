<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		html{background: url(images/shenhe.jpeg) no-repeat;background-size:100% auto;}
		.regButton{width: 90vw;margin-left: 5vw; background: #5F9BF1;height: 11vw;font-size: 5vw;text-align: center;line-height: 11vw;border-radius: 2vw;margin-top: 8vw;color: white;}
	</style>
	<body>
		<div id="top">
			<span style="width: 10vw;height: 12vw;display: block;float: left;"><img src="images/tuihou.png" style="width: 3vw;margin-left: 3vw;margin-top: 4vw;"/></span>
			<p style="color: #fff;height: 13vw;text-align: center;width: 90vw;line-height: 13vw;font-size: 4.8vw;">审核</p>
		</div>

        <div class="regButton" id="bottomButton" onclick="${identify.put == 0 ? 'submit()' : (check == 0 ? 'submited()' : 'check()')}">
            ${identify.put == 0 ? '提交审核' : (check == 0 ? '查看结果' : '审核中')}
        </div>

        <script>
            function submited() {
                $.alert("您的申请正在审核中，根据你的当前额度平台还推荐给您了一些低门槛极速放款合作平台，去看看吧！",function(){
                    window.location.href = "app_jieguo.action";
                });
            }

            function check() {
                $.alert("提交审核中，请耐心等待",function(){
                });
            }
        </script>
	</body>
</html>
