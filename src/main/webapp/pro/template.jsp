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
		.scroll-wrapper {
  		-webkit-overflow-scrolling: touch;
 		 overflow-y: scroll;
  		/* 提示: 请在此处加上需要设置的大小(dimensions)或位置(positioning)信息! */
		}
		#top{width: 100vw;background: #5F9BF1;position: fixed;top: 0;}
		#select{width: 100vw;height: 13vw;background: #fff;position: fixed;top: 13vw;}
		#select span{width: 33.3vw;float: left;display: block;height: 12.2vw;text-align: center;line-height: 12.2vw;}
		#box{position: fixed;height: auto;width: 100%;bottom: 0vw;top:11vw;overflow: auto;}
		
	</style>
	<body>
		<div id="top" class="scroll-wrapper">
			<span style="width: 10vw;height: 12vw;display: block;float: left;"><img onclick="history.back();" src="images/tuihou.png" style="width: 3vw;margin-left: 3vw;margin-top: 4vw;"/></span>
			<p id="title" style="color: #fff;height: 13vw;text-align: center;width: 90vw;line-height: 13vw;font-size: 4.8vw;">${title}</p>
		</div>
		<div id="box">
			<iframe id="myiframe" src="${url}" frameborder=”no” border=”0″ marginwidth=”0″ marginheight=”0″ allowtransparency=”yes”  style="width:100%;height:100%"></iframe>		
		</div>
	</body>
	<script>
	function back() {
		window.location.href = "auth_center.action";
	}
	</script>
</html>
    