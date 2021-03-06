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
    <link rel="stylesheet" href="css/index.css" type="text/css"/>
    <title>实时注册</title>
    <style>
        .upframe {
            background-color: rgb(6, 148, 134);
            border-bottom-color: rgb(6, 148, 134);
            float: left;
            margin-right: 5px;
        }
    </style>
</head>
<body>
<script>
    function searchannel() {
        var name = $("#searchname").val();
        window.location.href = "user_view.do?salt=" + name;
    }

    function topage() {
        var page = $("#pageNumber").val();
        var pageCount = ${pageCount};
        if (page <= pageCount) {
            window.location.href = "user_view.do?salt=${name}&id=" + page;
        } else {
            alert("一共" + pageCount + "页");
        }
        ;

    }

    function setValid(id) {
        if (confirm('确定标记用户:' + id + '为有效？') == false) {
            return false;
        }
        ;
        $.ajax({
            type: "POST",
            url: "user_valid.do",
            data: 'id=' + id,
            dataType: "json",
            success: function (data) {
                if (data.hint == "success") {
                    $(location).attr("href", "user_view.do?salt=${name}&id=${thisPage}");
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

    function delUser(id) {
        if (confirm('确定删除用户:' + id + '？') == false) {
            return false;
        }
        ;
        $.ajax({
            type: "POST",
            url: "user_del.do",
            data: 'id=' + id,
            dataType: "json",
            success: function (data) {
                if (data.hint == "success") {
                    $(location).attr("href", "user_view.do?salt=${name}&id=${thisPage}");
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


</script>

<div class="navcon">
    <div class="con_title">
        <a href="#">首页</a><span>/</span><a href="#">实时注册</a><span>/</span><a href="#">用户管理</a>
        <div class="ssbox">
            <input type="text" placeholder="请输入手机号或渠道名" id="searchname" value="${name}" class="fl" style="height:31px; outline:none;"/>
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
                <td>用户ID</td>
                <td>手机号码</td>
                <td>来源渠道</td>
                <td>渠道来源</td>
                <td>注册时间</td>
                <td>是否有效</td>
                <td>操作</td>
            </tr>

            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${fn:substring(user.phone_number, 0, 3)}****${fn:substring(user.phone_number, 8, 11)}</td>
                    <td>${user.channel.name}</td>
                    <td>${user.channel.creater.real_name}</td>
                    <td>${user.registe_time}</td>
                    <td>${user.valid==1?"是":"否"}</td>
                    <td>
                        <a class="upframe" onClick="delUser(${user.id})">注销</a>
                        <c:if test="${user.valid == 0}">
                            <a class="upframe" onClick="setValid(${user.id})">标记有效</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>


        </table>
    </div>


    <div class="jumpbox" id="jumpbox" style="display:none">
        <div class="tipsay">复制成功</div>
        <div style="padding:12px 0;">
            <div class="sure" onClick="document.getElementById('jumpbox').style.display='none';">确定</div>
        </div>
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
                <td><a href="user_view.do?id=${thisPage-1}&salt=${name}">上一页</a></td>
            </c:if>
			
			<c:if test="${thisPage > 3}">
                <c:forEach begin="1" end="3" var="i" step="1">
                    <td><a href="user_view.do?id=${i}&salt=${name}">${i}</a></td>
                </c:forEach>
                <c:if test="${thisPage > 6}">
                    <td class="shenglue"><a href="#">...</a></td>
                </c:if>
                <c:if test="${thisPage == 5}">
                    <td><a href="user_view.do?id=4&salt=${name}">4</a></td>
                </c:if>
                <c:if test="${thisPage >= 6}">
                    <td><a href="user_view.do?id=${thisPage-2}&salt=${name}">${thisPage-2}</a></td>
                    <td><a href="user_view.do?id=${thisPage-1}&salt=${name}">${thisPage-1}</a></td>
                </c:if>
            </c:if>
			
			<c:if test="${thisPage <= 3}">
                <c:forEach begin="1" end="${thisPage-1}" var="i" step="1">
                    <td><a href="user_view.do?id=${i}&salt=${name}">${i}</a></td>
                </c:forEach>
            </c:if>
        	
		
     		<td class="addtd"><a href="#" style="color:#fff;">${thisPage}</a></td>
     		
 			<c:if test="${thisPage+1 <= pageCount}">
                <c:forEach begin="${thisPage+1}" end="${thisPage+2}" var="i" step="1">
                    <c:if test="${i <= pageCount}">
                        <td><a href="user_view.do?id=${i}&salt=${name}">${i}</a></td>
                    </c:if>
                </c:forEach>
            </c:if>
			
			<c:if test="${thisPage + 3 == pageCount}">
                <td><a href="user_view.do?id=${pageCount}&salt=${name}">${pageCount}</a></td>
            </c:if>
			
			<c:if test="${thisPage + 3 < pageCount}">
                <c:if test="${thisPage + 4 < pageCount}">
                    <td class="shenglue"><a href="#">...</a></td>
                </c:if>
                <td><a href="user_view.do?id=${pageCount-1}&salt=${name}">${pageCount-1}</a></td>
                <td><a href="user_view.do?id=${pageCount}&salt=${name}">${pageCount}</a></td>
            </c:if>
			
			
        	
 			<c:if test="${thisPage+1 <= pageCount}">
                <td><a href="user_view.do?id=${thisPage+1}&salt=${name}">下一页</a></td>
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
    