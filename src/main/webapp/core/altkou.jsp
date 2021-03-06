<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet"  href="core/css/index.css" type="text/css" />
<script src="http://hkwj.v228.10000net.cn/js/jquery.min.js"></script>
<script src="core/js/jquery.form.js" type="text/javascript"></script> 
<title>添加产品</title>
<style>
/*右边css*/
.concrete_con{
	padding:15px 0px 20px 25px;
	}
.concrete_con table{
	width:100%;
	}	
.concrete_con table td{
	padding:5px 0;
	vertical-align:middle;
	}
.concrete_con table tr td:first-child{
	background-color:#fff;
	width:100px;
	transform:scale(0.8);
	font-size:16px;
	text-align:center;
	color:#999;
	}
.concrete_con table tr td:last-child{
	padding-left:5px;
	}
.concrete_con input[type="file"]{
	position:absolute;
	left:0;
	bottom:0;
	}
.concrete_con input[type="text"]{
	width:95%;
	padding:5px 0;
	border:#f0f0f0 1px solid;
	font-size:12px;
	outline:none;
	}
.concrete_con input[type="number"]{
	width:20%;
	padding:5px 0;
	border:#f0f0f0 1px solid;
	outline:none;
	}
.concrete_con select{
	width:20%;
	padding:5px 0;
	border:#f0f0f0 1px solid;
	outline:none;
	}
</style>
</head>
<script>
	function uploadPic() { 
        var options = {		  
                url: "image_upload.do",  
                type: "post",  
                dataType: "json",  
                success: function(data) {
                	if(data.hint == "file_too_large"){
                		alert("Logo不得超过100kb");
                	}else if(data.hint == "un_login"){
                		alert("登录超时，请重新登录！");
                	}else if (data.hint == "not_permission"){
                		alert("抱歉，你没有权限");
                	} else if (data.hint == "unknow_error"){
                		alert("错误-1，如多次出现，请联系管理员");
                	}else{
                    	$("#allUrl").attr("src", "upload/" + data.fileName);
                    	document.getElementById("logo").value = data.fileName; 
                    }
                } ,
                error: function (XMLHttpRequest, textStatus, errorThrown) {   
                     		console.log(errorThrown);   
               } 
        };  
 
        $("#jvForm").ajaxSubmit(options);
   
    }  
    
    function addkouzi(){
    	var logo = document.getElementById("logo").value.trim();
    	var name = document.getElementById("name").value.trim();
    	if(logo == ""){
    		alert("请上传logo");
    		return;
    	}
    	if(name == ""){
    		alert("请输入必填信息（口子名称）");
    		return;
    	}
    	var options = {
    		url: "app_alt.do",
    		type: "POST",
    		dataType: "json",
    		success: function(data){
    			if(data.hint == "success"){
    				alert("修改成功！");
    			}else if(data.hint == "already_exist"){
    				alert("口子已存在！");
    			}else if(data.hint == "not_permission"){
    				alert("你没有权限进行此操作！");
    			}else if(data.hint == "un_login"){
    				alert("登录超时，请重新登录!");
    			}else {
    				alert("服务器繁忙！");
    			}
    		}
    	};
    	$("#kouzi_form").ajaxSubmit(options);
    }
</script>

<body>
<div class="navcon">
    <div class="con_title"><a href="#">首页</a><span>/</span><a href="#">产品管理</a><span>/</span><a href="#">修改产品</a></div>
    <div class="concrete_con">
        <table>
        
            <form id="jvForm" action="o_save.shtml" method="post" accept="image/*" enctype="multipart/form-data">
            <tr>
                <td>口子logo</td>
                <td class="upload" style="position:relative;"><img src="upload/${app.logo}" id="allUrl" style="width:266px; height:200px;" /><input name="file" type="file" onChange="uploadPic()" style="opacity:0; height:200px;"/></td>
            </tr>
            </form>
            
             <form id="kouzi_form">
                <tr>
                    <td>口子名称</td>
                    <td><input type="text" maxlength="10" id="name" name="name" value="${app.name}" placeholder="请输入口子名称" /></td>
                </tr>
                        <input type="hidden" id="logo" name="logo" value="${app.logo}"/>
                        <input type="hidden" id="id" name="id" value="${app.id}"/>
                <tr>
                    <td>口子描述</td>
                    <td><input type="text" maxlength="50" name="descript" value="${app.descript}" placeholder="请输入口子描述 (芝麻分580+银行卡，就可下款3500元)" /></td>
                </tr>
                <tr>
                    <td>额度范围</td>
                    <td>
                    	<input type="number" value="500" name="min_limit" value="${app.min_limit}" placeholder="￥" />&nbsp;-&nbsp;
                    	<input type="number" value="3000" name="max_limit" value="${app.max_limit}" placeholder="￥" />
                    </td>
                </tr>
                <tr>
                    <td>每日费率</td>
                    <td><input type="text" name="feilv" value="${app.feilv}" placeholder="不要带%（输入0.03即为0.03%）" /></td>
                </tr>
                <tr>
                    <td>贷款期限</td>
                    <td><input type="text" maxlength="25" name="timelimit" value="${app.timelimit}" placeholder="请输入贷款期限（例：3-8天）" /></td>
                </tr>
                <tr>
                    <td>成功率</td>
                    <td><select name="rate">
                        <option value="${app.rate}">${app.rate}%</option>
                        <option value="99">99%</option>
                        <option value="98">98%</option>
                        <option value="97">97%</option>
                        <option value="96">96%</option>
                        <option value="95">95%</option>
                        <option value="94">94%</option>
                        <option value="93">93%</option>
                        <option value="92">92%</option>
                        <option value="91">91%</option>
                        <option value="90">90%</option>
                        <option value="89">89%</option>                      
                        <option value="88">88%</option>
                        <option value="87">87%</option>
                        <option value="86">86%</option>
                        <option value="85">85%</option>
                        <option value="84">84%</option>
                        <option value="83">83%</option>
                        <option value="82">82%</option>
                        <option value="81">81%</option>
                        <option value="80">80%</option>
                        <option value="79">79%</option>
                        <option value="78">78%</option>
                        <option value="77">77%</option>
                        <option value="76">76%</option>
                        <option value="75">75%</option>
                        <option value="74">74%</option>
                        <option value="73">73%</option>
                        <option value="72">72%</option>
                        <option value="71">71%</option>
                        <option value="70">70%</option>                                                                                  
                        
                    </select></td>
                </tr>
                <tr>
                    <td>链接地址</td>
                    <td><input type="text" maxlength="300" name="app_url" value="${app.app_url}" placeholder="请输入口子链接地址（带http://或https://）" /></td>
                </tr>
                <tr>
                    <td>申请条件</td>
                    <td><input type="text" maxlength="50" name="need_condition" value="${app.need_condition}" placeholder="如：芝麻信用分600以上,年龄18周岁以上" /></td>
                </tr>
                <tr>
                    <td>合作价格</td>
                    <td><input type="text" name="price" maxlength="15" value="${app.price}" placeholder="8a" /></td>
                </tr>
        </form> 
                <tr>
                    <td></td>
                    <td>
                        <input type="button" onClick="addkouzi()" value="确认修改" style="margin-right: 10px;width:70px;height:25px; cursor: pointer;"><input type="hidden" value="重置" style="margin-right: 10px;width:70px;height:25px; cursor: pointer;">
                    </td>
                </tr>
        </table>
    </div>
</div>
</body>
</html>
    