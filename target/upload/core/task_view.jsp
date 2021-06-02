<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
        <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
        <link rel="stylesheet" href="css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        
        <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
        <link rel="stylesheet" href="css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        
        <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
        <script src="js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <script src="js/jquery.min.js"></script>
    </head>
    <script type="text/javascript">
    	function cancleTask(wx, app) {
    		if (confirm("确认取消？") == false){  
                return;  
            }
    		$.ajax({
    			url : "task_cancle.do",
    			data : "storeId=" + app + "&wxId=" + wx,
    			type : "post",
    			dataType : "json",
    			success : function(data){
    				var hint = data.hint;
    				if (hint == "success") {
    					window.location.href = "task_run_view.do";
    				} else if (hint == "not_permission") {
    					alert("没有权限");
    				} else if (hint == "un_login") {
    					alert("登录超时");
    				}
    			}
    		});
    	}
    </script>
	
    <body>
<ol class="breadcrumb" style="background-color: #f9f9f9;padding:8px 0;margin-bottom:10px;">
    <li>
        <a href=""><i class="fa fa-cogs"></i>
            消息推送</a>
    </li>
    <li class="active">
        <a href="">状态查询</a>
    </li>
</ol>
<ul class="nav nav-tabs" role="tablist">
    <li ><a href="task_add_view.do">消息推送</a></li>
    <li  class="active"><a href="#">状态查询</a></li>
    <li ><a href="task_history_view.do?wxId=1">发送历史</a></li>
</ul>
<form action="" method="post">
    <div class="panel panel-default">
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="10%">编号</th>
                    <th>产品名称</th>
                    <th>公众号</th>
                    <th>发送时间</th>
                    <th>发送状态</th>
					<th>发送成功</th>
					<th>发送失败</th>
					<th>操作</th>
                </tr>
                </thead>
                <tbody>
                
         <c:forEach items="${runs}" var="run">
         
                <tr>
                    <td>0</td>
                    <td>${run.name}</td>
                    <td>${run.wexinName}</td>
                    <td>现在</td> 
                    <td>发送中</td>
					<td>${run.hasSend}</td>
					<td style="${run.failSend > 0 ? 'color:red;' : ''}">${run.failSend}</td>
					<td><button onclick="cancleTask(${run.wxId}, ${run.storeId})">取消发送</button></td>
                </tr>
                
		</c:forEach>
		
		<c:forEach items="${waits}" var="ws">
			<c:forEach items="${ws}" var="w">
				<tr>
                    <td style="color:gray">0</td>
                    <td style="color:gray">${w.name}</td>
                    <td style="color:gray">${w.wexinName}</td>
                    <td style="color:gray">now</td> 
                    <td style="color:gray">等待发送</td>
					<td style="color:gray">0</td>
					<td style="color:gray">0</td>
					<td><button onclick="cancleTask(${w.wxId}, ${w.storeId})">取消发送</button></td>
                </tr>
        	</c:forEach>
        </c:forEach>	
	             
                </tbody>
            </table>
        </div>
    </div>
</form>
</body>

</html>
    