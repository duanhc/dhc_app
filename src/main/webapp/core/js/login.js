;
! function() {
	var layer = layui.layer,
		form = layui.form,
		carousel = layui.carousel;

	// 背景图片轮播
	carousel.render({
		elem: '#login_carousel',
		width: '100%',
		height: '100%',
		interval: 3000,
		arrow: 'none',
		anim: 'fade',
		indicator: 'none'
	});

	// 验证码值存储变量
	var vailCode;
	// 执行获取验证码
	refCode();

	// 点击刷新验证码
	$("#refCode_login_img").on("click", function() {
		refCode();
	});

	// 获取验证码
	function refCode() {
		
	}

	// 自定义验证规则
//	form.verify({
//		account: function(value) {
//			
//		},
//		code: function(value) {
//			if(value.toUpperCase() != vailCode) {
//				refCode();
//				return "验证码不正确";
//			}
//		}
//	});
	function flushImage() {
		$("#verify_image").click();
	}
	
	//监听提交  
	form.on("submit(login)", function() {
		var name = $("#account").val();
		var password = $("#password").val();
		var Pcode = $("#Pcode").val();
		
		 var options = {  
	                // 规定把请求发送到那个URL  
	                url: "../admin_login.do",  
	                // 请求方式  
	                type: "post",  
	                // 服务器响应的数据类型  
	                dataType: "json",  
	                // 请求成功时执行的回调函数  
	                success: function(data){  
	                    // 图片显示地址 
						if(data.hint == "success"){
							window.location.href = "admin_manage.do";
						}else if(data.hint == "account_not_found"){
							layer.alert("用户不存在");
							flushImage();
						}else if(data.hint == "password_error"){
							layer.alert("密码错误");	
							flushImage();
						}else if(data.hint == "server_error"){
							layer.alert("服务器繁忙");
							flushImage();
						}else if(data.hint == "account_forbid"){
							layer.alert("用户已被禁用，请联系管理员！");
							flushImage();
						}else if(data.hint="image_code_error"){
							layer.alert("图形验证码错误！");
						};
	                },
	                error: function (XMLHttpRequest, textStatus, errorThrown) {   
	                     		console.log(errorThrown);   
	               }
	        	};  
	        	
	        	$("#admin_login").ajaxSubmit(options);
		

	});
}();