<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title></title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="">
  <meta name="description" content="">
  <link rel="stylesheet" href="res/layui/css/layui.css">
  <link rel="stylesheet" href="res/css/global.css">
<script src="res/layui/layui.js"></script>
</head>


<body>
<div class="header" >
  <div class="main">
    <a  href="#"  style="margin-left: 30px;line-height: 65px; color: white;font-size: 18px;">花蝴蝶后台管理</a>
    <div class="nav">
    
    </div>
    
    <div class="nav-user">      
      <!-- 登入后的状态 -->
      
      <a class="avatar" href="#">
        <img src="res/images/8.jpg">
        <cite style="color: white;">${admin.real_name}</cite>
        <i style="color: white;"><a href="../admin_logout.do" target="_self">退出</a></i>
      </a>
      <div class="nav">
        <a href="set.html"><i class="iconfont icon-shezhi"></i>设置</a>
        <a href="login.html"><i class="iconfont icon-tuichu" style="top: 0; font-size: 22px;"></i>退了</a>
      </div>
    </div>
  </div>
</div>


	<div class="main fly-user-main layui-clear">
		<ul class="layui-nav layui-nav-tree layui-inline" lay-filter="user">
 	
 		<c:if test="${permission eq '11111' or permission eq '00010'}">
        
            <li class="layui-nav-item layui-this" >
				<a href="../app_market.do" target="right"><i class="layui-icon">&#xe609;</i>超市视图 </a>			
			</li> 
			
			 <li class="layui-nav-item layui-this" >
				<a href="../app_jieguo.do" target="right"><i class="layui-icon">&#xe609;</i>审核结果 </a>			
			</li>      
                
            <li class="layui-nav-item layui-this" >
				<a href="../app_put_view.do" target="right"><i class="layui-icon">&#xe609;</i>上架管理 </a>			
			</li>
			
			<li class="layui-nav-item layui-this" >
				<a href="../app_down_view.do" target="right"><i class="layui-icon">&#xe609;</i>下架管理 </a>			
			</li>
						
			<li class="layui-nav-item layui-this" >
				<a href="addkou.html" target="right"><i class="layui-icon">&#xe609;</i>添加产品 </a>			
			</li>
			
			<li class="layui-nav-item layui-this" >
				<a href="task_add_view.do" target="right"><i class="layui-icon">&#xe609;</i>消息推送</a>			
			</li> 
                      
		</c:if>
	
 
 		<c:if test="${permission eq '11111' or permission eq '00001'}">
             
            <li class="layui-nav-item layui-this" >
				<a href="channel_view.do?name=" target="right"><i class="layui-icon">&#xe609;</i>渠道视图 </a>			
			</li>
			
			<li class="layui-nav-item layui-this" >
				<a href="channel_add_view.do" target="right"><i class="layui-icon">&#xe609;</i>添加渠道 </a>			
			</li>
			
			<!-- 
			<li class="layui-nav-item layui-this" >
				<a href="addrt.html" target="right"><i class="layui-icon">&#xe609;</i>添加后台 </a>			
			</li>
			
			<li class="layui-nav-item layui-this" >
				<a href="admin_rt_forbid_view.do" target="right"><i class="layui-icon">&#xe609;</i>后台列表</a>			
			</li> 
			
			<li class="layui-nav-item layui-this" >
				<a href="admin_rt_forbided_view.do" target="right"><i class="layui-icon">&#xe609;</i>禁用列表 </a>			
			</li> 
				
			 -->
          
        </c:if>     
        
        <c:if test="${permission eq '11111'}">  
 
           <li class="layui-nav-item layui-this" >
				<a href="admin_add.html" target="right"><i class="layui-icon">&#xe609;</i>添加管理员 </a>			
			</li>
			
			<li class="layui-nav-item layui-this" >
				<a href="admin_detail_view.do" target="right"><i class="layui-icon">&#xe609;</i>渠道管理员 </a>			
			</li>
			
			<li class="layui-nav-item layui-this" >
				<a href="admin_app_view.do" target="right"><i class="layui-icon">&#xe609;</i>产品管理员 </a>			
			</li>
					
			
			<li class="layui-nav-item layui-this" >
				<a href="admin_all_view.do" target="right"><i class="layui-icon">&#xe609;</i>禁用列表 </a>			
			</li>  
		</c:if>
			
		 <c:if test="${permission ne '00010'}">  	
			<li class="layui-nav-item layui-this" >
				<a href="user_view.do" target="right"><i class="layui-icon">&#xe609;</i>实时注册 </a>			
			</li> 
		</c:if>			                                                				
		</ul>

		<div class="site-tree-mobile layui-hide">
			<i class="layui-icon">&#xe602;</i>
		</div>
		<div class="site-mobile-shade"></div>

		<div class="fly-panel fly-panel-user">
			<div class="layui-tab layui-tab-brief" lay-filter="user">
				
				<div class="layui-tab-content" style="padding: 20px;padding-top: 0px;">
					
    					<iframe src="channel-view.jsp" width="88%" style="border: none;" height="800" border="none" name="right"></iframe>
					</div>					
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery-1.11.3.min.js" ></script>
	<script type="text/javascript" src="myplugs/js/plugs.js" ></script>
<script>
		layui.use('element', function() {
			var element = layui.element();
		});
</script>
<script>

layui.cache.page = '';
layui.cache.user = {
  username: '游客'
  ,uid: -1
  ,avatar: '../res/images/avatar/00.jpg'
  ,experience: 83
  ,sex: '男'
};
layui.config({
  version: "2.0.0"
  ,base: '../res/mods/'
}).extend({
  fly: 'index'
}).use('fly');
	//添加编辑弹出层
			function updatePwd(title, id) {
				$.jq_Panel({
					title: title,
					iframeWidth: 500,
					iframeHeight: 300,
					url: "updatePwd.html"
				});
			}
</script>


</body>
</html>
    