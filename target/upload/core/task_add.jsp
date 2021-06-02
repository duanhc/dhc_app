<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<html>
<head>
	<meta charset="utf-8"> 
	<title>消息推送</title>
	<link rel="stylesheet" href="http://hkwj.v228.10000net.cn/css/bootstrap.min.css">
	<script src="http://hkwj.v228.10000net.cn/js/jquery.min.js"></script>
	<script src="http://hkwj.v228.10000net.cn/js/bootstrap.min.js"></script>
	<script src="http://dzez.v228.10000net.cn/js/jquery.form.js"  type="text/javascript"></script>
</head>
<body>



<ol class="breadcrumb" style="background-color: #f9f9f9;padding:8px 0;margin-bottom:10px;">
    <li>
        <a href=""><i class="fa fa-cogs"></i>
            消息推送</a>
    </li>
    <li class="active">
        <a href="task-view.jsp">发送消息</a>
    </li>
</ol>
<ul class="nav nav-tabs" role="tablist">
    <li class="active"><a href="#">消息推送</a></li>
    <li ><a href="task_run_view.do">状态查询</a></li>
    <li ><a href="task_history_view.do?wxId=1">发送历史</a></li>
</ul>
<form class="form-horizontal" id="form" target="_self"  action="../addTask.action" method="post">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">发送消息</h3>
        </div>
        <div class="panel-body">
            
            <div class="form-group">
                <label for="" class="col-sm-2 control-label">公众号选择</label>
                <div class="col-sm-9">
                    <select class="js-example-basic-single form-control" name="wxinfo">
            		<c:forEach items="${weixins}" var="w">
                        <option value="${w.id},${w.name}">${w.name}</option> 
                 	</c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
				<label for="" class="col-sm-2 control-label">产品选择</label>
				<div class="col-sm-9">				
				
			<c:forEach items="${apps}" var="a">
				
                <label class="checkbox-inline" style="margin-left:10px">
                        <input type="checkbox" name="storeId" value="${a.id},${a.name}" style="width: 15px;height: 15px;margin-top: 7px">${a.name}
						<img src="../upload/${a.logo}" style="width: 30px;height: 30px">
                </label>
			</c:forEach>
			
			</div>	
				 </div>
            </div>
            <div class="form-group">
                <label for="" class="col-sm-2 control-label">额度填写</label>
                <div class="col-sm-9">
                    <input type="text" name="edu"  class="form-control" placeholder="1000-8000元">
                </div>
            </div>
            <div class="form-group">
                <label for="" class="col-sm-2 control-label">期限填写</label>
                <div class="col-sm-9">
                    <input type="text" name="timeLimit"  class="form-control" placeholder="7-30天">
                </div>
            </div>
           
        </div>
    </div>
    
 <button type="button" id="submit" class="btn btn-primary btn-lg"  style="width: 10%;margin-left: 40%">
	推送
</button>
</form>
<!-- 按钮触发模态框 -->

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					模态框（Modal）标题
				</h4>
			</div>
			<div class="modal-body">
				在这里添加一些文本
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<script>
	$("#submit").click(function(){
		$("#myModalLabel").html("发送结果");
		var options = {
			url : "task_add.do",
			dataType : "json",
			type : "POST",
			success : function(data) {
				var hint = data.hint;
				if (hint == "success") {		
					alert("成功");
					window.location.href = "task_run_view.do";
				}else if (hint == "weixin_select") {
					alert("请选择公众号");
				}else if(hint == "app_select") {
					alert("至少选择一个产品");
				} else if (hint == "un_login") {
					alert("登录超时，请重新登录");
				} else if (hint == "illegal_request") {
					alert("非法请求");
				} else if (hint == "not_permission") {
					alert("抱歉，你没有权限！");
				}
				
			}
		};
		$("#form").ajaxSubmit(options);
		
	});
</script>
</body>
</html>