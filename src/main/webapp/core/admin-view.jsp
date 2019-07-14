<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" id="viewport" content="width=device-width, initial-scale=1">
<script src="js/jquery.min.js"></script> 
<link rel="stylesheet"  href="css/index.css" type="text/css" />
<title>渠道管理</title>
<style>
.upframe{
background-color:rgb(6, 148, 134);
border-bottom-color:rgb(6, 148, 134);
float:left;
margin-right:5px;
}	
</style>
</head>
<body>
<script>
	function searchannel(){
		var name = $("#searchname").val();
		window.location.href = "admin_detail_view.do?name=" + name;	
	}
	
	function topage(){
		var page = $("#pageNumber").val();
		var pageCount = ${pageCount};
		if (page <= pageCount){
			window.location.href = "admin_detail_view.do?name=${name}&id=" + page;	
		} else {
			alert("一共" + pageCount + "页" );
		};	
		
	}
	
	function forbid(id) {
		$.ajax({ 
   			type: "POST",
  			url:  "admin_forbid.do",
  			data: 'forbid=1&id=' + id,
   			dataType: "json",
   			success: function(data) {
       			if(data.hint == "success"){
       				$(location).attr("href", "admin_detail_view.do?name=${name}&id=${thisPage}");
       			}else if(data.hint == "illegal_request"){
       				$(location).attr("href", "core/illegal_request.html");
       			}else if(data.hint == "un_login"){
       				$(location).attr("href", "core/timeout.html");
       			}    			
  			}
   		});
	}
</script>
	
<div class="navcon">
    <div class="con_title">
        <a href="#">首页</a><span>/</span><a href="#">管理员列表</a><span>/</span><a href="#">详情</a>
        <div class="ssbox">
            <input type="text" placeholder="请输入管理员真实姓名" id="searchname" value="${name}" class="fl" style="height:31px; outline:none;"/>
            <a href="#" onClick="searchannel()"><div class="fl" style="background-color:#009786;width:60px; padding:0 0 0 15px;height:35px; line-height:35px; font-size:13px; color:#fff;"><img src="images/ss.png" style="vertical-align:middle; margin-right:5px;">搜索</div></a>
            <div class="clearfix"></div>
        </div>
    </div>
    <div class="listbox">
        <table>
            <tr>
                <td>ID</td>
                <td>真实姓名</td>
                <td>登录帐号</td>
                <td>今日注册</td>
                <td>昨日注册</td>
                <td>总注册</td>
           <c:forEach items="${days}" var="day">    
  				<td>${day}</td>
  		  </c:forEach>
  		    	<td>本月结算</td>
  		    	<td>上月结算</td>
  				<td>总结算</td>                        
                <td>今日收入</td>
                <td>昨日收入</td>
                <td>总收入</td>
                <td>操作</td>
            </tr>
   		<c:forEach items="${admins}" var="admin">
   			<tr>
                <td>${admin.id}</td>
                <td>${admin.real_name}</td>
                <td>${admin.name}</td>
                <td>${admin.today_ua}</td>
                <td>${admin.yester_ua}</td>
                <td>${admin.all_ua}</td>
  				<td>${admin.pay1}</td>
  				<td>${admin.pay2}</td>
  				<td>${admin.pay3}</td>
  				<td>${admin.pay4}</td>
  				<td>${admin.month1}</td>
  				<td>${admin.month2}</td>
  				<td>${admin.pay}</td>
  				<td>${admin.today_income}</td>
  				<td>${admin.yester_income}</td>
  				<td>${admin.all_income}</td>
                <td>          
                	<a class="upframe" href="admin_alt_view.do?id=${admin.id}">修改</a>
                	<a class="upframe" onClick="forbid(${admin.id})">禁用</a>             	         	
                </td>
            </tr>
        </c:forEach>
   
              
        </table>
    </div>
      
    
    <div class="jumpbox" id="jumpbox" style="display:none">
    	<div class="tipsay" >复制成功</div>
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
            <table  class="fl" cellspacing="0">
        <tr>
  			<c:if test="${thisPage > 1}">
  					<td><a href="admin_detail_view.do?id=${thisPage-1}&name=${name}">上一页</a></td>
			</c:if>
			
			<c:if test="${thisPage > 3}">
				<c:forEach begin="1" end="3" var="i" step="1">
					<td><a href="admin_detail_view.do?id=${i}&name=${name}">${i}</a></td>
				</c:forEach>
				<c:if test="${thisPage > 6}">
        			<td class="shenglue"><a href="#">...</a></td>
        		</c:if>
        		<c:if test="${thisPage == 5}">
        			<td><a href="admin_detail_view.do?id=4&name=${name}">4</a></td>
        		</c:if>
        		<c:if test="${thisPage >= 6}">
        			<td><a href="admin_detail_view.do?id=${thisPage-2}&name=${name}">${thisPage-2}</a></td>
        			<td><a href="admin_detail_view.do?id=${thisPage-1}&name=${name}">${thisPage-1}</a></td>
        		</c:if>
			</c:if>
			
			<c:if test="${thisPage <= 3}">
				<c:forEach begin="1" end="${thisPage-1}" var="i" step="1">
					<td><a href="admin_detail_view.do?id=${i}&name=${name}">${i}</a></td>
				</c:forEach>
			</c:if>
        	
		
     		<td class="addtd"><a href="#" style="color:#fff;">${thisPage}</a></td>
     		
 			<c:if test="${thisPage+1 <= pageCount}">
  				<c:forEach begin="${thisPage+1}" end="${thisPage+2}" var="i" step="1">
  					<c:if test="${i <= pageCount}">
						<td><a href="admin_detail_view.do?id=${i}&name=${name}">${i}</a></td>
					</c:if>
				</c:forEach>
			</c:if>
			
			<c:if test="${thisPage + 3 == pageCount}">
				<td><a href="admin_detail_view.do?id=${pageCount}&name=${name}">${pageCount}</a></td>
			</c:if>
			
			<c:if test="${thisPage + 3 < pageCount}">
				<c:if test="${thisPage + 4 < pageCount}">
					<td class="shenglue"><a href="#">...</a></td>
				</c:if>
				<td><a href="admin_detail_view.do?id=${pageCount-1}&name=${name}">${pageCount-1}</a></td>
				<td><a href="admin_detail_view.do?id=${pageCount}&name=${name}">${pageCount}</a></td>
			</c:if>
			
			
        	
 			<c:if test="${thisPage+1 <= pageCount}">
  					<td><a href="admin_detail_view.do?id=${thisPage+1}&name=${name}">下一页</a></td>
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
$(".navcon").height($(window).height()-44);
$(window).resize(function () {          //当浏览器大小变化时
   $(".navcon").height($(window).height()-44);
});
</script>

</body>
</html>
    