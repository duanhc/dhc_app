<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>免费申请</title>
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	</head>	
	<style type="text/css">
		*{padding: 0;margin: 0;}
		ul li{list-style: none;}
		::-webkit-input-placeholder { color: #D3D3D6; } :-moz-placeholder { color: #D3D3D6; } ::-moz-placeholder { color: #D3D3D6; } :-ms-input-placeholder { color: #D3D3D6; }  
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
    </style>
	<body>
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

	</body>

</html>
