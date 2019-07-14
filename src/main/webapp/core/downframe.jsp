<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" id="viewport" content="width=device-width, initial-scale=1">
<script src="core/js/jquery.min.js"></script> 
<link rel="stylesheet"  href="core/css/index.css" type="text/css" />
<title>下架管理</title>
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
	function priorityApp(id, def) {
		var priority = prompt("请输入要修改的位置", def);
		$.ajax({ 
   			type: "POST",
  			url:  "app_priority.do",
  			data: 'id=' + id + "&priority=" + priority,
   			dataType: "json",
   			success: function(data){
       			if(data.hint == "success"){
       				$(location).attr("href", "app_down_view.do?id=${thisPage}&name=${name}&put_away=${put}");
       			}else if(data.hint == "not_permission"){
       				$(location).attr("href", "core/nopermission.html");
       			}else if(data.hint == "un_login"){
       				$(location).attr("href", "core/timeout.html");
       			}    			
  			}
   		});
	}
	
	function downApp(id) {
		
		$.ajax({ 
   			type: "POST",
  			url:  "app_put.do",
  			data: 'put_away=0&id=' + id,
   			dataType: "json",
   			success: function(data){
       			if(data.hint == "success"){
       				$(location).attr("href", "app_down_view.do?id=${thisPage}&name=${name}&put_away=${put}");
       			}else if(data.hint == "not_permission"){
       				$(location).attr("href", "core/nopermission.html");
       			}else if(data.hint == "un_login"){
       				$(location).attr("href", "core/timeout.html");
       			}else if(data.hint == "illegal_request"){
       				$(location).attr("href", "core/illegal_request.html");
       			}  			
  			}
   		});
	};
	
	function searchapp(){
		var name = $("#searchname").val();
		window.location.href = "app_down_view.do?name=" + name;	
	}
	
	function topage(){
	
		var page = $("#pageNumber").val();
		var pageCount = ${pageCount};
		if (page <= pageCount){
			window.location.href = "app_down_view.do?name=${name}&put_away=${put}&id=" + page;	
		} else {
			alert("一共" + pageCount + "页" );
		};
			
	}
	
</script>
	
<div class="navcon">
    <div class="con_title">
        <a href="#">首页</a><span>/</span><a href="#">产品管理</a><span>/</span><a href="#">下架管理</a>
        <div class="ssbox">
            <input type="text" placeholder="请输入产品名称" value="${name}" id="searchname" class="fl" style="height:31px; outline:none;"/>
            <a href="#" onClick="searchapp()"><div class="fl" style="background-color:#009786;width:60px; padding:0 0 0 15px;height:35px; line-height:35px; font-size:13px; color:#fff;"><img src="core/images/ss.png" style="vertical-align:middle; margin-right:5px;">搜索</div></a>
            <div class="clearfix"></div>
        </div>
    </div>
    <div class="listbox">
        <table>
            <tr>
                <td>ID</td>
                <td>产品图标</td>
                <td>产品名称</td>
                <td>合作价格</td>
                <td>用户总数（UV）</td>
                <td>今日新增</td>
                <td>昨日UV</td>
                <td>当前位置</td>
                <td>操作</td>
            </tr>
            
  	<c:forEach var="item" items="${apps}">
            <tr>
                <td>${item.id}</td>
                <td><img src="upload/${item.logo}" width="35" ></td>
                <td>${item.name}</td>
                <td>${item.price}</td>
                <td>${item.all_ua}</td>
                <td>${item.today_ua}</td>
                <td>${item.yester_ua}</td>
                <td>${item.priority}</td>
                <td>
                	<a class="upframe" href="app_getqr.do?id=${item.id}">QR Code</a>
                	<a class="upframe" onclick="downApp(${item.id});">下架</a>
                	<a class="upframe" onclick="priorityApp(${item.id}, ${item.priority});">排序</a>
                </td>
            </tr>   
	</c:forEach> 
	
                          
        </table>
    </div>
      
    
    <div class="jumpbox" style="display:none">
    	<div class="tipsay">上架成功</div>
        <div style="padding:12px 0;">
        	<div class="sure">确定</div>
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
  					<td><a href="app_down_view.do?id=${thisPage-1}&name=${name}&put_away=${put}">上一页</a></td>
			</c:if>
			
			<c:if test="${thisPage > 3}">
				<c:forEach begin="1" end="3" var="i" step="1">
					<td><a href="app_down_view.do?id=${i}&name=${name}&put_away=${put}">${i}</a></td>
				</c:forEach>
				<c:if test="${thisPage > 6}">
        			<td class="shenglue"><a href="#">...</a></td>
        		</c:if>
        		<c:if test="${thisPage == 5}">
        			<td><a href="app_down_view.do?id=4&name=${name}&put_away=${put}">4</a></td>
        		</c:if>
        		<c:if test="${thisPage >= 6}">
        			<td><a href="app_down_view.do?id=${thisPage-2}&name=${name}&put_away=${put}">${thisPage-2}</a></td>
        			<td><a href="app_down_view.do?id=${thisPage-1}&name=${name}&put_away=${put}">${thisPage-1}</a></td>
        		</c:if>
			</c:if>
			
			<c:if test="${thisPage <= 3}">
				<c:forEach begin="1" end="${thisPage-1}" var="i" step="1">
					<td><a href="app_down_view.do?id=${i}&name=${name}&put_away=${put}">${i}</a></td>
				</c:forEach>
			</c:if>
        	
		
     		<td class="addtd"><a href="#" style="color:#fff;">${thisPage}</a></td>
     		
 			<c:if test="${thisPage+1 <= pageCount}">
  				<c:forEach begin="${thisPage+1}" end="${thisPage+2}" var="i" step="1">
  					<c:if test="${i <= pageCount}">
						<td><a href="app_down_view.do?id=${i}&name=${name}&put_away=${put}">${i}</a></td>
					</c:if>
				</c:forEach>
			</c:if>
			
			<c:if test="${thisPage + 3 == pageCount}">
				<td><a href="app_down_view.do?id=${pageCount}&name=${name}&put_away=${put}">${pageCount}</a></td>
			</c:if>
			
			<c:if test="${thisPage + 3 < pageCount}">
				<c:if test="${thisPage + 4 < pageCount}">
					<td class="shenglue"><a href="#">...</a></td>
				</c:if>
				<td><a href="app_down_view.do?id=${pageCount-1}&name=${name}&put_away=${put}">${pageCount-1}</a></td>
				<td><a href="app_down_view.do?id=${pageCount}&name=${name}&put_away=${put}">${pageCount}</a></td>
			</c:if>
			
			
        	
 			<c:if test="${thisPage+1 <= pageCount}">
  					<td><a href="app_down_view.do?id=${thisPage+1}&name=${name}&put_away=${put}">下一页</a></td>
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
<script>
}}}
</script>

</body>
</html>
    