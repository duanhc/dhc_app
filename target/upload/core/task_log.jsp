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
    
	
    <body>
<ol class="breadcrumb" style="background-color: #f9f9f9;padding:8px 0;margin-bottom:10px;">
    <li>
        <a href=""><i class="fa fa-cogs"></i>
            消息推送</a>
    </li>
    <li class="active">
        <a href="">发送历史</a>
    </li>
</ol>
<ul class="nav nav-tabs" role="tablist">
    <li ><a href="task_add_view.do">消息推送</a></li>
    <li ><a href="task_run_view.do">状态查询</a></li>
    <li class="active"><a href="#">发送历史</a></li>
</ul>
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
                </tr>
                </thead>
                <tbody>
				
			<c:forEach items="${logs}" var="log">
				<tr>
                    <td width="10%">${log.id}</td>
                    <td>${log.app_name}</td>
                    <td>${log.weixin_name}</td>
                    <td>${log.send_time}</td>
                    <td>完成</td>
					<td>${log.send_count}</td>
					<td style="${log.fail_count > 0 ? 'color:red;' : ''}">${log.fail_count}</td>
       			 </tr>
           </c:forEach>   
           
                </tbody>
            </table>
        </div>
    </div>
<ul class="pagination" style="float: right;">
	<c:if test="${thisPage > 1}">
    	<li><a href="task_history_view.do?wxId=${thisPage-1}">&laquo;</a></li>
    </c:if>

	 <c:if test="${thisPage > 3}">
				<c:forEach begin="1" end="3" var="i" step="1">
					<li><a href="task_history_view.do?wxId=${i}">${i}</a></li>
				</c:forEach>
				<c:if test="${thisPage > 6}">
        			<li><a>...</a></li>	  
        		</c:if>
        		<c:if test="${thisPage == 5}">
        			<li><a href="task_history_view.do?wxId=4">4</a></li>
        		</c:if>
        		<c:if test="${thisPage >= 6}">
        			<li><a href="task_history_view.do?wxId=${thisPage-2}">${thisPage-2}</a></li>
        			<li><a href="task_history_view.do?wxId=${thisPage-1}">${thisPage-1}</a></li>
        		</c:if>
	</c:if>
	
	<c:if test="${thisPage <= 3}">
				<c:forEach begin="1" end="${thisPage-1}" var="i" step="1">
					<li><a href="task_history_view.do?wxId=${i}">${i}</a></li>
				</c:forEach>
	</c:if>

    <li class="active"><a>${thisPage}</a></li>

	 <c:if test="${thisPage+1 <= pageCount}">
  			<c:forEach begin="${thisPage+1}" end="${thisPage+2}" var="i" step="1">
  				<c:if test="${i <= pageCount}">
						<li><a href="task_history_view.do?wxId=${i}">${i}</a></li>
				</c:if>
			</c:forEach>
	</c:if>
			
	<c:if test="${thisPage + 3 == pageCount}">
			<li><a href="task_history_view.do?wxId=${pageCount}">${pageCount}</a></li>		
	</c:if>
			
	<c:if test="${thisPage + 3 < pageCount}">
			<c:if test="${thisPage + 4 < pageCount}">
					<li><a>...</a></li>
			</c:if>
				<li><a href="task_history_view.do?wxId=${pageCount-1}">${pageCount-1}</a></li>	
				<li><a href="task_history_view.do?wxId=${pageCount}">${pageCount}</a></li>	
	</c:if>

    <c:if test="${thisPage+1 <= pageCount}">
   		 <li><a href="task_history_view.do?wxId=${thisPage+1}">&raquo;</a></li>
   	</c:if>
</ul>
</body>

</html>
    