<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	</head>	
	<style type="text/css">
		*{padding: 0;margin: 0;}
		ul li{list-style: none;}
		html{background: #f9f9f9;}
		::-webkit-input-placeholder { color: #EEEEEE; } :-moz-placeholder { color: #EEEEEE; } ::-moz-placeholder { color: #EEEEEE; } :-ms-input-placeholder { color: #EEEEEE; }  
		#top{width: 100vw;height: 12vw;background: #f9f9f9;text-align: center;line-height: 12vw;font-size: 4.5vw;}
		
		#content{width: 92vw;height: auto;margin-left: 3vw;overflow: auto;margin-bottom: 20vw;}
		#content ul li{width: 92vw;height: 19vw;background: #fff;position: relative;margin-top: 4vw;}

		#content ul li .con_pic{width: 15vw;height: 19vw;float: left;}
		#content ul li .con_num{width: 9.5vw;height: 4vw;position: absolute;left: 0;top: -1.5vw;}
		#content ul li .con_mid{width: 56vw;height: 19vw;float: left;}
		#content ul li .con_mid .con_mid_top{width: 56vw;height: 6vw;margin-top: 4.5vw;margin-left: 2vw;font-size: 4vw;}
		#content ul li .con_mid .con_mid_bottom p{border: 0.3vw solid #bfc1ce;border-radius: 3vw;height: 5vw;line-height: 5vw;width: auto;margin-left: 2vw;color: #bfc1ce;font-size: 3vw;text-indent: 2vw;padding-right: 2vw;}
		#content ul li .con_reg{background: #4367fb;color: #fff;width: 18vw;height: 8vw;line-height: 8vw;text-align: center;float: left;margin-top: 5.5vw;border-radius: 5vw;font-size: 3.5vw;}
		
		#bottom{height: 13vw;width: 100vw;position: fixed;bottom: 0;left: 0;border-top: 1px solid #DEDEDE;background: white;}
		#bottom ul li{width: 33.3vw;float: left;height: 13vw;}
		#bottom ul li p{text-align: center;font-size: 3vw;height: 4vw;line-height: 4vw;color: #DEDEDE;margin-top: -0.5vw;}
   		

	</style>
	<body>
		<div id="top">
			免费申请
		</div>
		
		<div id="content">
			<ul>
                <c:forEach items="${hidden}" var="app" varStatus="status">
                    <a href="app_redirect.do?id=${app.id}" target="_blank">
                        <li>
                            <c:if test="${status.count == 1}">
                                <div class="con_num">
                                    <img src="images/no1.png" style="width: 9.5vw;"/>
                                </div>
                            </c:if>
                            <c:if test="${status.count == 2}">
                                <div class="con_num">
                                    <img src="images/no2.png" style="width: 9.5vw;"/>
                                </div>
                            </c:if>
                            <c:if test="${status.count == 3}">
                                <div class="con_num">
                                    <img src="images/no3.png" style="width: 9.5vw;"/>
                                </div>
                            </c:if>
                            <div class="con_pic">
                                <img src="../upload/${app.logo}" style="width: 10.5vw;margin-left: 4.5vw;margin-top: 4.7vw;"/>
                            </div>
                            <div class="con_mid">
                                <div class="con_mid_top">
                                    <p style="float: left;">${app.name}</p><span><img src="images/vip.png" style="width: 9.3vw;margin-top: 0.7vw;margin-left: 1vw;"/></span>
                                </div>
                                <div class="con_mid_bottom">
                                    <p style="float: left;">极速下款/通过率高/秒审核</p>
                                </div>
                            </div>
                            <div class="con_reg">
                                立即申请
                            </div>
                        </li>
                    </a>
                </c:forEach>
			</ul>
		</div>
		
		<div id="bottom">
			<ul>
                <a href="jssh.html">
                    <li>
                        <img src="images/shouye2.png" style="width:6vw;margin-left: 13.5vw;margin-top: 1.5vw;"/>
                        <p>首页</p>
                    </li>
                </a>
                <a id="jkglPage" href="javascript:0">
                    <li>
                        <img src="images/mfsq.png" style="width:6vw;margin-left: 13.5vw;margin-top: 1.5vw;"/>
                        <p style="color: #0383fe;">免费申请</p>
                    </li>
                </a>
                <a href="myMess.html">
                    <li>
                        <img src="images/wode2.png" style="width:6vw;margin-left: 13.5vw;margin-top: 1.5vw;"/>
                        <p>我的</p>
                    </li>
                </a>
			</ul>
		</div>
	</body>
	
</html>
