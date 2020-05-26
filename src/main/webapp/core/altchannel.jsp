<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" id="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"  href="css/index.css" type="text/css" />
<script src="js/jquery.min.js"></script>
<script src="js/jquery.form.js" type="text/javascript"></script> 
<title>添加渠道</title>
<style>
.tjqdbox{
	padding:15px 15px 30px 50px;
	font-size:12px;
	border-bottom:#f0f0f0 1px solid;
	}
.tjqdbox td{
	padding:0 10px 5px 0;
	}
</style>
</head>
<body>


<div class="navcon">
    <div class="con_title">
        <a href="#">首页</a><span>/</span><a href="#">渠道管理</a><span>/</span><a href="#">添加渠道</a>
    </div>
    <div class="tjqdbox">
    <form id="channel_form">
    	<input name="id" value="${channel.id}" type="hidden"/>
    	<table width="100%">
        	<tr>
            	<td style="width:110px; font-size:15px; text-align:right;">渠道名称</td>
                <td colspan="2"><input type="text" maxlength="16" placeholder="请输入渠道名称" name="name" id="name" value="${channel.name}"  style="width:100%; height:30px; outline:none;"/></td>
            </tr>
            <tr>
            	<td style="width:110px; font-size:15px; text-align:right;">合作价格</td>
            	<td><input type="number" value="${channel.price}" id="price" name="price" maxlength="2" style="outline:none;"></td>
            </tr>
            <tr>
            	<td style="width:110px; font-size:15px; text-align:right; outline:none;">选择实时后台</td>
                <td style="width:200px;"><select name="viewer.id" style="width:200px;">				
                   	<option value="${channel.viewer.id}">${channel.viewer.name}</option>
                	<c:forEach items="${admins}" var="item">
                		<option value="${item.id}">${item.name}</option>
                	</c:forEach>

                </select></td>
                <td align="left"><div class="upframe fl" style="margin-right:10px;" onclick="altChannel()">修改</div></td>
            </tr>
      
        </table>
   </form>
    </div>
    <div class="jumpbox" id="hintBox" style="display:none">
    	<div class="tipsay" id="hint">添加成功</div>
        <div style="padding:12px 0;">
        	<div class="sure" onClick="hideHint()">确定</div>
        </div>
    </div>
</div>

<script>
	function hideHint(){
		var hintBox = document.getElementById("hintBox").style.display = "none";
	}

	function hintBox(tip){
			var hintBox = document.getElementById("hintBox");
			var hint = document.getElementById("hint");
			hint.innerHTML = tip;
			hintBox.style.display = "block";
			window.setTimeout(function(){
				hintBox.style.display = "none";
			},3000);
	}

	function altChannel(){
		var name = document.getElementById("name").value.trim();
		var price = document.getElementById("price").value.trim();
		if(name == "" || price == ""){
			hintBox("请输入必填信息！");
			return;
		}
		var options = {
			url: "channel_alt.do",
			type: "POST",
			dataType: "json",
			success : function(data){
				if(data.hint == "success"){
					hintBox("修改成功QAQ");
				}else if(data.hint == "already_exist"){
					hintBox("渠道名被占用！");
				}else if(data.hint == "not_permission"){
					hintBox("抱歉，你没有权限！");
				}else if(data.hint == "illegal_request"){
					hintBox("非法请求");
				}else if(data.hint == "un_login"){
					hintBox("登录超时WOW");
				}
			}
		};
		
		$("#channel_form").ajaxSubmit(options);
	}
</script>
</body>
</html>
    
