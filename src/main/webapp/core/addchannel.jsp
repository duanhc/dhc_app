<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" id="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"  href="css/index.css" type="text/css" />
<script src="../js/jquery.min.js"></script>
<script src="js/jquery.form.js" type="text/javascript"></script> 
<script src="../layer/layer.js" type="text/javascript"></script>
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
    	<table width="100%">
        	<tr>
            	<td style="width:110px; font-size:15px; text-align:right;">渠道名称</td>
                <td colspan="2"><input type="text" maxlength="16" placeholder="请输入渠道名称" name="name" id="name"  style="width:100%; height:30px; outline:none;"/></td>
            </tr>
            <tr>
            	<td style="width:110px; font-size:15px; text-align:right;">合作价格</td>
            	<td><input type="number" id="price" name="price" maxlength="2" style="outline:none;"></td>
            </tr>
            <tr>
                <td style="width:110px; font-size:15px; text-align:right;">展示代超用户数</td>
                <td>
                    <input type="number" id="total_show_market" name="total_show_market" maxlength="5" style="outline:none;">
                    <input type="hidden" id="should_show_market" name="should_show_market" style="outline:none;">
                </td>
            </tr>
            <tr>
            	<td style="width:110px; font-size:15px; text-align:right; outline:none;">选择实时后台</td>
                <td style="width:200px;"><select name="viewer.id" style="width:200px;">				
                   
                	<c:forEach items="${admins}" var="item">
                		<option value="${item.id}">${item.real_name}-${item.name}</option>
                	</c:forEach>

                </select></td>
                <td align="left"><div class="upframe fl" style="margin-right:10px;" onclick="addChannel()">添加</div></td>
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

	function addChannel(){
		var name = document.getElementById("name").value.trim();
		var price = document.getElementById("price").value.trim();
        var total_show_market = document.getElementById("total_show_market").value.trim();

        if(name == "" || price == "" || total_show_market== ""){
			hintBox("请输入必填信息！");
			return;
		}

        if (!(/(^[1-9]\d*$)/.test(total_show_market))) {
            layer.tips("请输入正整数","#total_show_market");
            return false;
        }else {
            $("#should_show_market").val(total_show_market);
        }


        var options = {
			url: "channel_add.do",
			type: "POST",
			dataType: "json",
			success : function(data){
				if(data.hint == "success"){
					hintBox("添加成功QAQ");
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
    
