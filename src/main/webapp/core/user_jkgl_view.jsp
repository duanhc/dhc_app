<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" id="viewport" content="width=device-width, initial-scale=1">
    <script src="js/jquery.min.js"></script>
    <script src="../layer/layer.js"></script>
    <link rel="stylesheet" href="css/index.css" type="text/css"/>
    <title>借款管理</title>
    <style>
        .upframe {
            background-color: rgb(6, 148, 134);
            border-bottom-color: rgb(6, 148, 134);
            float: left;
            margin-right: 5px;
        }

        .tjqdbox{
            font-size:12px;
            position:relative;
        }
        .tjqdbox td{
            padding:0 40px 10px 0;
        }
    </style>
</head>
<body>
<script>
    function searchannel() {
        var name = $("#searchname").val();
        window.location.href = "user_jkgl_view.do?salt=" + name;
    }

    function topage() {
        var page = $("#pageNumber").val();
        var pageCount = ${pageCount};
        if (page <= pageCount) {
            window.location.href = "user_jkgl_view.do?salt=${name}&id=" + page;
        } else {
            alert("一共" + pageCount + "页");
        }
        ;

    }

    var layerIndex;
    //转账说明
    function zzsm(id) {

        var zzsmHtml = "<div class=\"navcon\">" +
            "    <div class=\"tjqdbox\">" +
            "    <table width=\"100%\">" +
            "      <tr>" +
            "            <td><input type=\"text\" placeholder=\"可自己修改\" id=\"realName\" name=\"realName\" style=\"width:480px; height:30px; outline:none;\"/></td>" +
            "      </tr>" +
            "      <tr>" +
            "           <td style='float: left;'><div class=\"upframe fl\" onClick=\"changeCard()\" style=\"margin-right:10px; height:34px; line-height:34px; background-color:#f2f2f2;color: #009688;\">说明1</div></td>" +
            "           <td style='float: left;'><div class=\"upframe fl\" onClick=\"changeCard1()\" style=\"margin-right:10px; height:34px; line-height:34px; background-color:#f2f2f2;color: #009688;\">说明2</div></td>" +
            "           <td style='float: left;'><div class=\"upframe fl\" onClick=\"changeCard2()\" style=\"margin-right:10px; height:34px; line-height:34px; background-color:#f2f2f2;color: #009688;\">说明3</div></td>" +
            "       </tr>" +
            "       <tr>" +
            "           <td><div class=\"upframe fl\" onClick=\"addZzsm("+id+")\" style=\"margin-right:10px; height:34px; line-height:34px; background-color:#009688;\">确认修改</div></td>" +
            "        </tr>" +
            "      </table>" +
            "    </div>" +
            "</div>";

        //页面层-自定义
        layerIndex = layer.open({
            type: 1,
            title: "转账说明",
            closeBtn: 0,
            shadeClose: true,
            skin: 'yourclass',
            area: ['500px', '250px'],
            content: zzsmHtml
        });

    };

    function changeCard(){
        var realName = document.getElementById("realName").value="因借款人账户信息不符，根据国家法规《借款人资金安全法》存在骗贷风险。";
    }
    function changeCard1(){
        var realName = document.getElementById("realName").value="借款人账户风险评估过低，根据国家法规《借款人资金安全法》暂停放款转账。";
    }
    function changeCard2(){
        var realName = document.getElementById("realName").value="借款人账户资金流水不足，根据国家法规《借款人资金安全法》存在多头借贷。";
    }

    function addZzsm(userId){
        var zzsm = document.getElementById("realName").value.trim();
        if(zzsm == ""){
            layer.msg("请输入必填信息",{time:2000})
            return;
        }

        //增加转账说明
        $.ajax({
            url : "addIdentifyZzsm.action",
            type : "POST",
            dataType : "json",
            data: "id=" + userId + "&zzsm=" + zzsm,
            success: function (data) {
                if (data.hint == "success") {
                    layer.msg("修改成功");
                    layer.close(layerIndex);
                } else if (data.hint == "illegal_request") {
                    $(location).attr("href", "illegal_request.html");
                } else if (data.hint == "un_login") {
                    $(location).attr("href", "timeout.html");
                } else if (data.hine == "not_permission") {
                    $(location).attr("href", "nopermission.html");
                }
            }
        });

    }

    //打开转账截图页面
    function toZzjtPage(userId){
        //要发送的参数
        var params = {
            "userId": userId
        };
        window["filter"] = params;
        window.open('loan.html')
    }


</script>

<div class="navcon">
    <div class="con_title">
        <a href="#">首页</a><span>/</span><a href="#">借款管理</a><span>/</span><a href="#">用户管理</a>
        <div class="ssbox">
            <input type="text" placeholder="请输入手机号" id="searchname" value="${name}" class="fl" style="height:31px; outline:none;"/>
            <a href="#" onClick="searchannel()">
                <div class="fl" style="background-color:#009786;width:60px; padding:0 0 0 15px;height:35px; line-height:35px; font-size:13px; color:#fff;"><img src="images/ss.png"
                                                                                                                                                                style="vertical-align:middle; margin-right:5px;">搜索
                </div>
            </a>
            <div class="clearfix"></div>
        </div>
    </div>
    <div class="listbox">
        <table>
            <tr>
                <td>订单号</td>
                <td>手机号</td>
                <td>用户名</td>
                <td>金额</td>
                <td>期限</td>
                <td>提现时间</td>
                <td>转账记录</td>
                <td>保险截图</td>
                <td>订单状态</td>
                <td>操作</td>
            </tr>

            <c:forEach items="${userInfoList}" var="userInfo">
                <tr>
                    <td>${userInfo.identify.order_no}</td>
                    <td>${userInfo.identify.user.phone_number}</td>
                    <td>${userInfo.userDetail.name}</td>
                    <td>${userInfo.identify.lend_count == 0 ? "":userInfo.identify.lend_count}</td>
                    <td>${userInfo.identify.lend_time}</td>
                    <td>${userInfo.identify.cash_time}</td>
                    <td><span onClick="zzsm(${userInfo.identify.user.id})">转账说明</span>
                        <br/>
                        <span onClick="toZzjtPage(${userInfo.identify.user.id})">转账截图</span></td>
                    <td>保险截图</td>
                    <td><a onClick="userDetail(${userInfo.identify.user.id})">资金冻结</a></td>
                    <td>
                        <a class="upframe" onClick="agree(${userInfo.identify.user.id})">合同</a>
                        <a class="upframe" onClick="disagree(${userInfo.identify.user.id})">资料</a>
                        <a class="upframe" onClick="disagree(${userInfo.identify.user.id})">改卡</a>
                    </td>
                </tr>
            </c:forEach>


        </table>
    </div>

</div>

<!-- 分页 -->
<div class="layui-footer">
    <div class="pagesite">
    	<span class="inpage">
            <div class="allcount">共${count}条</div>
            <table class="fl" cellspacing="0">
        <tr>
  			<c:if test="${thisPage > 1}">
                <td><a href="user_jkgl_view.do?id=${thisPage-1}&salt=${name}">上一页</a></td>
            </c:if>
			
			<c:if test="${thisPage > 3}">
                <c:forEach begin="1" end="3" var="i" step="1">
                    <td><a href="user_jkgl_view.do?id=${i}&salt=${name}">${i}</a></td>
                </c:forEach>
                <c:if test="${thisPage > 6}">
                    <td class="shenglue"><a href="#">...</a></td>
                </c:if>
                <c:if test="${thisPage == 5}">
                    <td><a href="user_jkgl_view.do?id=4&salt=${name}">4</a></td>
                </c:if>
                <c:if test="${thisPage >= 6}">
                    <td><a href="user_jkgl_view.do?id=${thisPage-2}&salt=${name}">${thisPage-2}</a></td>
                    <td><a href="user_jkgl_view.do?id=${thisPage-1}&salt=${name}">${thisPage-1}</a></td>
                </c:if>
            </c:if>
			
			<c:if test="${thisPage <= 3}">
                <c:forEach begin="1" end="${thisPage-1}" var="i" step="1">
                    <td><a href="user_jkgl_view.do?id=${i}&salt=${name}">${i}</a></td>
                </c:forEach>
            </c:if>
        	
		
     		<td class="addtd"><a href="#" style="color:#fff;">${thisPage}</a></td>
     		
 			<c:if test="${thisPage+1 <= pageCount}">
                <c:forEach begin="${thisPage+1}" end="${thisPage+2}" var="i" step="1">
                    <c:if test="${i <= pageCount}">
                        <td><a href="user_jkgl_view.do?id=${i}&salt=${name}">${i}</a></td>
                    </c:if>
                </c:forEach>
            </c:if>
			
			<c:if test="${thisPage + 3 == pageCount}">
                <td><a href="user_jkgl_view.do?id=${pageCount}&salt=${name}">${pageCount}</a></td>
            </c:if>
			
			<c:if test="${thisPage + 3 < pageCount}">
                <c:if test="${thisPage + 4 < pageCount}">
                    <td class="shenglue"><a href="#">...</a></td>
                </c:if>
                <td><a href="user_jkgl_view.do?id=${pageCount-1}&salt=${name}">${pageCount-1}</a></td>
                <td><a href="user_jkgl_view.do?id=${pageCount}&salt=${name}">${pageCount}</a></td>
            </c:if>
			
			
        	
 			<c:if test="${thisPage+1 <= pageCount}">
                <td><a href="user_jkgl_view.do?id=${thisPage+1}&salt=${name}">下一页</a></td>
            </c:if>
         </tr>
            </table>
            <div style="color:#999;margin-left:15px;">到第</div>
            <div class="page"><input type="number" id="pageNumber" style="background-color:#fff; position:absolute; top:0; left:0;"></div>
            <div style="color:#999;">页</div>
            <div class="page" onClick="topage()" style="cursor:pointer;">确定</div>
        </span>
    </div>
</div>

<script>
    $(".navcon").height($(window).height() - 44);
    $(window).resize(function () {          //当浏览器大小变化时
        $(".navcon").height($(window).height() - 44);
    });
</script>
<script>

</script>
</body>
</html>
    