<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" id="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"  href="css/index.css" type="text/css" />
<script src="js/jquery.min.js"></script> 
<script src="js/jquery.form.js" type="text/javascript"></script> 
<title>修改后台</title>
<style>
.tjqdbox{
	padding:35px 15px 30px 50px;
	font-size:12px;
	position:relative;
	}
.tjqdbox td{
	padding:0 10px 5px 0;
	}
.tipbox{
	padding:5px 10px;
	border:red 1px solid;
	color:red;
	position:absolute;
	top:5px;
	left:174px;
	}
</style>
</head>

<body>
<div class="navcon">
    <div class="con_title">
        <a href="#">首页</a><span>/</span><a href="#">渠道管理</a><span>/</span><a href="#">修改后台</a>
    </div>
    <div class="tjqdbox">
    	<div class="tipbox" id="tipbox" style="display:none">必填项不能为空</div>
    	<table width="100%">
    <form id="rtform">
    	
    		<input name="id" type="hidden" value="${admin.id}"/>
        	<tr>
            	<td style="width:110px; font-size:15px; text-align:right;">后台名称</td>
                <td><input type="text" name="real_name" value="${admin.real_name}" id="realName" maxlength="15" placeholder="请输入渠道名称"  style="width:190px; height:30px; outline:none;"/></td>
            </tr>
            <tr>
            	<td style="width:110px; font-size:15px; text-align:right;">登录帐号</td>
                <td><input type="text" placeholder="请输入登录帐号" id="name" value="${admin.name}" name="name" maxlength="15" style="width:190px; height:30px; outline:none;"/></td>
            </tr>
            <tr>
            	<td style="width:110px; font-size:15px; text-align:right;">登录密码</td>
                <td><input type="password" id="pwd1" placeholder="请输入登录密码" value="${admin.password}"  style="width:190px; height:30px; outline:none;"/></td>
            </tr>
            <tr>
            	<td style="width:110px; font-size:15px; text-align:right;">请再次确认密码</td>
                <td><input type="password" id="pwd2" maxlength="32" value="${admin.password}" name="password" placeholder="请重复输入密码"  style="width:190px; height:30px; outline:none;"/></td>
            </tr>
    </form>    
            <tr>
            	<td></td>
                <td><div class="upframe fl" onClick="post_form()" style="margin-right:10px; height:34px; line-height:34px; background-color:#009688;">修改</div><div class="upframe fl" style="background-color:#fff; height:34px; line-height:34px; color:#000; border-color:#c9c9c9;">重置</div></td>
            </tr>
        </table>
    </div>
    <div class="jumpbox" style="display:none">
    	<div class="tipsay">修改成功</div>
        <div style="padding:12px 0;">
        	<div class="sure">确定</div>
        </div>
    </div>
</div>
<script>
var unSelected = "#999";
var selected = "#333";
$(function () {
$("select").css("color", unSelected);
$("option").css("color", selected);
$("select").change(function () {
var selItem = $(this).val();
if (selItem == $(this).find('option:first').val()) {
$(this).css("color", unSelected);
} else {
$(this).css("color", selected);
}
});
});

function hintbox(tip){
	var hint = document.getElementById("tipbox");
	hint.innerHTML = tip;
	hint.style.display = "block";
	window.setTimeout(function(){
		hint.style.display = "none";	
	},2000);
}

function post_form(){
	var realname =  document.getElementById("realName").value.trim();
	var name = document.getElementById("name").value.trim();
	var pwd1 = document.getElementById("pwd1").value.trim();
	var pwd2 = document.getElementById("pwd2").value.trim();
	if(realname=="" || name=="" || pwd1=="" || pwd2==""){
		hintbox("请输入必填信息！");
		return;
	}
	if(pwd1 != pwd2){
		hintbox("两次输入的密码不一致！");
		return;
	}
	
	var options = {
		url: "admin_alt.do",
		type: "POST",
		dataType: "json",
		success : function(data){
					if(data.hint == "success"){
						hintbox("修改成功！");
					}else if(data.hint == "already_exist"){
						hintbox("登录账号已被占用！");
					}else if(data.hint == "not_permission"){
						hintbox("你没有权限！");
					}else if(data.hint == "un_login"){
						hintbox("登录超时，请重新登录！");
					}else if(data.hint == "illegal_request"){
						hintbox("非法请求");
					}
				},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			console.log(errorThrown); 
		}
	};
	$("#rtform").ajaxSubmit(options);
		
}


</script>
</body>
</html>
   
