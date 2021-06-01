<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<link rel="stylesheet" href="./css/weui.min.css">
		<link rel="stylesheet" href="./css/jquery-weui.min.css">
		<script src="./js/jquery.min.js"></script>
		<script src="./js/jquery-weui.min.js"></script>
		<script src="./js/swiper.min.js"></script>
		<script src="./js/city-picker.min.js"></script>
		<script src="./js/jquery.form.js"  type="text/javascript"></script>
	</head>	
	<style type="text/css">
		*{padding: 0;margin: 0;}
		ul li{list-style: none;}
		html{background: #FAFAFA;}
		::-webkit-input-placeholder { color: #EEEEEE; } :-moz-placeholder { color: #EEEEEE; } ::-moz-placeholder { color: #EEEEEE; } :-ms-input-placeholder { color: #EEEEEE; }  
		#top{width: 100vw;height: 18vw;background: #33F49E;position: relative;z-index: 5;
		background: -webkit-linear-gradient(left, #33F89C , #339BCB);
        background: -o-linear-gradient(right, #33F89C, #339BCB);
        background: -moz-linear-gradient(right, #33F89C, #339BCB);
        background: linear-gradient(to right, #33F89C , #339BCB);}
		
		.tips{width: 100vw;height: 8vw;background: #fef1e8;font-size: 3vw;text-indent: 3vw;line-height: 8vw;}
		
		#content{width: 92vw;margin-left: 4vw;margin-bottom: 15vw;}
		#content .zxkz{width: 92vw;height: 30vw;}
		#content .zxkz ul li{width: 46vw;float: left;height: 25vw;position: relative;}
		#content .zxkz ul li .zximg{width: 10vw;height: 10vw;position: absolute;top: 2vw;left: 8vw;border-radius: 50%;}
		#content .zxkz ul li .zxname{width: 20vw;height: 10vw;position: absolute;top: 4vw;left: 22vw;font-size: 4vw;}
		#content .zxkz ul li .ednum{width: 20vw;height: 10vw;position: absolute;top: 14vw;left: 15vw;font-size: 6vw;color: #00c6cc;font-weight: 700;}
		#content .zxkz ul li .maxed{width: 30vw;height: 10vw;position: absolute;top: 23vw;left: 10vw;font-size: 3.5vw;color: #afafaf;}
		
		#content .rmtj{width: 92vw;}
		#content .header{font-size: 4vw;}
		#content .rmtj ul li{width: 92vw;height: 35vw;}
		#content .rmtj ul li .tj_top{width: 92vw;height: 12vw;position: relative;}
		#content .rmtj ul li .tj_top .tjname{position: absolute;top: 0vw;left: 13vw;width: 20vw;font-size: 4vw;}
		#content .rmtj ul li .tj_top .nqnum{position: absolute;top: 6vw;left: 13vw;width: 30vw;font-size: 3vw;color: #bfbfbf;}
		#content .rmtj ul li .tj_top .qnq{position: absolute;width: 25vw;background: #00c6cc;height: 10vw;top: 0;right: 3vw;border-radius: 5vw;color: #fff;text-align: center;line-height: 10vw;}
		#content .rmtj ul li .tj_top .new{position: absolute;width: 16vw;height: 5vw;top: 6vw;right: 37vw;text-align: center;background: red;color: #FFFFFF;line-height: 5vw;font-size: 3.5vw;}

		#content .rmtj ul li .tj_bottom{width: 92wv;height: 15vw;border-bottom: 3px solid #ebebeb;}
		#content .rmtj ul li .tj_bottom ul li{width: 30vw;height: 13vw;float: left;}
		#content .rmtj ul li .tj_bottom .maxnumb{line-height: 7vw;font-size: 5vw;color: #5734C6;text-align: center;font-weight: 700;}
		#content .rmtj ul li .tj_bottom .maxloan{line-height: 5vw;font-size: 3.5vw;color: #bfbfbf;text-align: center;font-weight: 400;}

		#bottom{height: 12vw;width: 100vw;position: fixed;bottom: 0;left: 0;border-top: 1px solid #DEDEDE;background: white;}
		#bottom ul li{width: 33.3vw;float: left;height: 12vw;}
		#bottom ul li p{text-align: center;font-size: 3vw;height: 4vw;line-height: 4vw;color: #DEDEDE;margin-top: -0.5vw;}
   		

	</style>
	<body>
		<div id="loadDiv" style="display: none;" class="weui-toast weui_loading_toast weui-toast--visible"><div class="weui_loading"><i class="weui-loading weui-icon_toast"></i></div><p class="weui-toast_content">数据加载中</p></div>
		<div id="top">
			<img src="images/daikuan.png" style="width: 30vw;margin-top: 5vw;margin-left: 5vw;"/>
		</div>
		
		<div class="tips">
			建议同时申请3-5款产品，通过率99%
		</div>
		
		<div id="content">
			
			
			<div class="header" style="line-height: 12vw;font-weight: 700;">
				热门推荐
			</div>
			
			<div class="rmtj">
				<ul>
                    <c:forEach items="${asorder}" var="app">
                        <li>
                            <div class="tj_top">
                                <div class="tjimg">
                                    <img src="../upload/${app.logo}" style="width: 10vw;border-radius: 50%;"/>
                                </div>
                                <div class="tjname">
                                        ${app.name}
                                </div>
                                <div class="nqnum">
                                        ${app.descript}
                                </div>
                                <div class="qnq" appURL="${app.app_url}" appId="${app.id}">
                                    去拿钱
                                </div>
                                <div class="new">
                                    新户秒过
                                </div>
                            </div>
                            <div class="tj_bottom">
                                <ul>
                                    <li style="border-right: 3px solid #f1f1f1;">
                                        <div class="maxnumb">
                                                ${app.max_limit}
                                        </div>
                                        <div class="maxloan">
                                            最高可借(元)
                                        </div>
                                    </li>
                                    <li style="border-right: 3px solid #f1f1f1;">
                                        <div class="maxnumb">
                                                ${fn:substringAfter(app.timelimit, "-")}
                                        </div>
                                        <div class="maxloan">
                                            最高借款期限
                                        </div>
                                    </li>
                                    <li style="">
                                        <div class="maxnumb">
                                                ${app.feilv}%
                                        </div>
                                        <div class="maxloan">
                                            日利率
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    </c:forEach>
					
				</ul>
			</div>
			
		</div>
		
		<div id="bottom">
			<ul>
				<a href="app_shouye.do">
				<li>
					<img src="images/i1b.png" style="width:5vw;margin-left: 14vw;margin-top: 1.5vw;"/>
					<p>首页</p>
				</li>
				</a>
				<a href="app_daikuan.do">
				<li>
					<img src="images/i2a.png" style="width:5vw;margin-left: 14vw;margin-top: 1.5vw;"/>
					<p style="color: #00c6cc;">贷款</p>
				</li>
				</a>
				<a href="wode.html">
				<li>
					<img src="images/i3b.png" style="width:5vw;margin-left: 14vw;margin-top: 1.5vw;"/>
					<p>我的</p>
				</li>
				</a>
				
			</ul>
		</div>
		
	</body>
	<script type="text/javascript">
		// $('#loadDiv').show();

        //是否未登录
        $.ajax({
            url : "need_login.do",
            type : "GET",
            dataType : "json",
            success : function(data) {
                if (data.hint == "unlogin") {
                    $.alert("尚未登录,是否登录", function (){
                        window.location.href = "../login.html";
                    });
                }
            }
        });

        $('.qnq').click(function(){
            var appId = $(this).attr("appId");
            $.ajax({
                url : "qnq.do",
                type : "GET",
                data : "appId="+appId,
                dataType : "json",
                success : function(data) {
                    if (data.hint == "send") {
                        //已认证
                        let url = $(".qnq").attr("appURL");
                        // window.location.href=url;
                        window.open(appUrl);
                    }else if(data.hint == "un_send"){
                        window.location.href="./ziliao.html?appId="+appId;
                    }else if(data.hint == "un_login") {
                        window.location.href="../login.html";
                    }else if(data.hint == "no_data") {
                        // 无数据
                        $.alert("无数据", function (){
                        });
                    }else if(data.hint == "put_away") {
                        // app已下架
                        $.alert("数据已更新", function (){
                            window.location.href = "app_daikuan.do";
                        });
                    }
                }
            });
        })

	</script>
</html>
