<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<link rel="stylesheet" href="../css/weui.min.css">
		<link rel="stylesheet" href="../css/jquery-weui.min.css">
		<script src="../js/jquery.min.js"></script>
		<script src="js/localResizeIMG.js" type="text/javascript"></script>
   		<script src="js/mobileBUGFix.mini.js" type="text/javascript"></script>
		<script src="../js/jquery-weui.min.js"></script>
		<script src="../js/jquery.form.js"  type="text/javascript"></script>
	</head>	
	<style type="text/css">
		*{padding: 0;margin: 0;}
		ul li{list-style: none;}
		::-webkit-input-placeholder { color: #AAAAAA; } :-moz-placeholder { color: #AAAAAA; } ::-moz-placeholder { color: #AAAAAA; } :-ms-input-placeholder { color: #AAAAAA; }  
		#top{width: 100vw;height: 13vw;background: #5F9BF1;}
		#content{width: 100vw;height: 120vw;}
		#content ul li{width: 100vw;background: white;border-bottom: 1px solid #F6F6F6;float: left;}
		#content ul li .more{width: 10vw;float: right;height: 6vw;font-size: 2vw;line-height: 6vw;text-align: center;margin-top: 3vw;}
		#content ul li input{width: 80vw;float: left;height: 5vw;border: none;background: none;padding: 4vw 0 4vw 0;font-size: 3.5vw;text-indent: 5vw;float: left;}
		.regButton{width: 90vw;margin-left: 5vw; background: #5F9BF1;height: 11vw;font-size: 5vw;text-align: center;line-height: 11vw;border-radius: 5vw;margin-top: 7vw;color: white;}
		.bottom{width: 90vw;height: 5vw;margin-left: 5vw;font-size: 3.5vw;color: black;margin-top: 3vw;font-weight: 700;}
	</style>
	<body onload="addBackListener();">
		<div id="top">
			<span style="width: 10vw;height: 12vw;display: block;float: left;"><img onclick="history.back();" src="images/tuihou.png" style="width: 3vw;margin-left: 3vw;margin-top: 4vw;"/></span>
			<p style="color: #fff;height: 13vw;text-align: center;width: 90vw;line-height: 13vw;font-size: 4.8vw;">上传身份证</p>
		</div>
		
		<div id="content">
			<ul>
				<form id="front_form"  action="id_card_auth_font.action" method="post" accept="image/*" enctype="multipart/form-data">			
					<input name="file"  type="file" id="front_file" style="opacity:0;width:90vw;height:50vw;margin-top:5vw"/>
				</form>
				<li style="margin-top:-55vw">
				
					<img  id="front_image" src="${identify.step1 == 0 ? 'images/sfz1.png' : 'id_card_image_font.action'}" style="width: 90vw;height:55vw;margin-left: 5vw;margin-top: 5vw;"/>
				</li>
				<form id="back_form" action="id_card_auth_back.action" method="post" accept="image/*" enctype="multipart/form-data">
					<input name="file" type="file" id="back_file" style="opacity:0;width:90vw;height:50vw;margin-top:5vw"/>
				</form>
				<li style="margin-top:-55vw">
					<img id="back_image" src="${identify.step2 == 0 ? 'images/sfz2.png' : 'id_card_image_back.action'}" style="width: 90vw;height:55vw;margin-left: 5vw;margin-top: 5vw;"/>
				</li>
			</ul>
		</div>
		<div class="regButton" onclick="submit()">
			提交审核
		</div>
		
	</body>
	
	 <script type="text/javascript">
         //返回键处理
         function addBackListener() {
             document.addEventListener('plusready', function() {
                 var webview = plus.webview.currentWebview();
                 plus.key.addEventListener('backbutton', function() {
                     webview.canBack(function(e) {
                         if(e.canBack) {
                             webview.back();
                         } else {
                             //webview.close(); //hide,quit
                             //plus.runtime.quit();
                             //首页返回键处理
                             //处理逻辑：1秒内，连续两次按返回键，则退出应用；
                             var first = null;
                             plus.key.addEventListener('backbutton', function() {
                                 //首次按键，提示‘再按一次退出应用’
                                 if (!first) {
                                     first = new Date().getTime();
                                     //console.log('再按一次退出应用');
                                     setTimeout(function() {
                                         first = null;
                                     }, 1000);
                                 } else {
                                     if (new Date().getTime() - first < 1500) {
                                         plus.runtime.quit();
                                     }
                                 }
                             }, false);
                         }
                     })
                 });
             });
         }

	 	var front_success = 0;
		var back_success = 0;
		var step1 = ${identify.step1};
		var step2 = ${identify.step2};

        $('#front_file').localResizeIMG({
             width: 800,

             quality: 0.5,

             success: function (result) {
             
             $("#front_image").attr("src","images/loading.gif");
             
             if (front_success == 1) {
				$.alert("上传中请等待片刻！", function (){
                			return;
                });
                return;
			 }

             var img = new Image();

             img.src = result.base64;



             //$('body').append(img);
             //console.log(result);

             var form=document.forms[0];

            var formData = new FormData(form);   //这里连带form里的其他参数也一起提交了,如果不需要提交其他参数可以直接FormData无参数的构造函数

             //convertBase64UrlToBlob函数是将base64编码转换为Blob  
             formData.append("file",convertBase64UrlToBlob(result.base64));  //append函数的第一个参数是后台获取数据的参数名,和html标签的input的name属性功能相同


             //ajax提交form表单

             $.ajax({
                    url : '../id_card_auth_font.action',
                    type : "POST",
                    data : formData,
                    dataType:"json",
                    processData : false,         // 告诉jQuery不要去处理发送的数据
                    contentType : false,        // 告诉jQuery不要去设置Content-Type请求头

                   success: function(data) {
                	front_success = 0;
                	if (data.hint == "success") {  
                		step1 = 1;     
                		$.alert("识别成功，请继续上传国徽面", function (){      			
                		});
                		$("#front_image").attr("src","id_card_image_font.action");
                	}else if (data.hint == "already_exist") {
                		$.alert("该身份证已在另一个帐号申请过借款！", function (){

                        });
                	}else if(data.hint == "file_too_large"){
                		$.alert("图片不能超过20M", function (){
                			
                		});
                	}else if(data.hint == "un_login"){
                		$.alert("登录超时,请重新登录", function (){
                			window.location.href = "../login.html";
                		});
                	} else {
                		$.alert("识别失败:" + data.hint , function (){
                			
                		});
                	}
                } ,
                error: function (XMLHttpRequest, textStatus, errorThrown) {   
                     		console.log(errorThrown);   
                     		front_success = 0;
                     		$.alert("上传失败，请稍后再试:" + data.hint , function (){
                			
                			});
               }

                });
            }

        
		

        });
         /**
         * 将以base64的图片url数据转换为Blob 可以直接上传文件
         * @param urlData
         *            用url方式表示的base64图片数据
         */
        function convertBase64UrlToBlob(urlData){

            var bytes=window.atob(urlData.split(',')[1]);        //去掉url的头，并转换为byte

            //处理异常,将ascii码小于0的转换为大于0
            var ab = new ArrayBuffer(bytes.length);
            var ia = new Uint8Array(ab);
            for (var i = 0; i < bytes.length; i++) {
                ia[i] = bytes.charCodeAt(i);
            }

            return new Blob( [ab] , {type : 'image/png'});
        }
        
        
        $('#back_file').localResizeIMG({

             width: 800,

             quality: 0.5,

             success: function (result) {
             $("#back_image").attr("src","images/loading.gif");
            if (step1 == 0) {
				$.alert("请先上传正面", function (){
                	return;
                });   
                return;        
			}
			if (back_success == 1) {
				$.alert("上传中请等待片刻！", function (){
                			return;
                });
                return;
			}
			back_success = 1;

             var img = new Image();

             img.src = result.base64;



             //$('body').append(img);
             //console.log(result);

             var form=document.forms[0];

            var formData = new FormData(form);   //这里连带form里的其他参数也一起提交了,如果不需要提交其他参数可以直接FormData无参数的构造函数

             //convertBase64UrlToBlob函数是将base64编码转换为Blob  
             formData.append("file",convertBase64UrlToBlob(result.base64));  //append函数的第一个参数是后台获取数据的参数名,和html标签的input的name属性功能相同


             //ajax提交form表单

             $.ajax({
                    url : '../id_card_auth_back.action',
                    type : "POST",
                    data : formData,
                    dataType:"json",
                    processData : false,         // 告诉jQuery不要去处理发送的数据
                    contentType : false,        // 告诉jQuery不要去设置Content-Type请求头

                    success: function(data) {
                	var hint = data.hint;
                	back_success = 0;      
                	if (hint == "success") { 
                		step2 = 1;          			
                		$.alert("识别成功，请提交审核", function (){ 
                		});
                			$("#back_image").attr("src","id_card_image_back.action");
                	}else if(hint == "file_too_large"){
                		$.alert("图片不能超过20M", function (){
                			
                		});
                	}else if(hint == "un_login"){
                		$.alert("登录超时,请重新登录", function (){
                			window.location.href = "../login.html";
                		});
                	} else {
                		$.alert("识别失败:" + hint, function (){
                			
                		});
                	}
                } ,
                error: function (XMLHttpRequest, textStatus, errorThrown) {   
                     		console.log(errorThrown); 
                     		back_success = 0;
                     		$.alert("上传失败，请稍后再试:" + data.hint , function (){
                			
                			});  
               }});
            }

        
		

        });
         /**
         * 将以base64的图片url数据转换为Blob 可以直接上传文件
         * @param urlData
         *            用url方式表示的base64图片数据
         */
        function convertBase64UrlToBlob(urlData){

            var bytes=window.atob(urlData.split(',')[1]);        //去掉url的头，并转换为byte

            //处理异常,将ascii码小于0的转换为大于0
            var ab = new ArrayBuffer(bytes.length);
            var ia = new Uint8Array(ab);
            for (var i = 0; i < bytes.length; i++) {
                ia[i] = bytes.charCodeAt(i);
            }

            return new Blob( [ab] , {type : 'image/png'});
        }
        
        
        function submit() {
			if (step1 == 1 && step2 == 1) {
				window.location.href = "auth_center.action";
			} else  if (step2 == 0) {
				$.alert("请上传身份证反面", function (){
                			
                });
			} else if (step1 == 0) {
				$.alert("请上传身份证正面", function (){
                			
                });
             }
		}

    </script>
	
	
		
		

</html>
    