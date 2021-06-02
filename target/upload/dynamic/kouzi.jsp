<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title> 贷款超市</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
<meta http-equiv="Cache-Control" content="no-transform">
<meta http-equiv="Cache-Control" content="no-siteapp">
<meta name="mobile-agent" content="format=html5;url=">
<meta http-equiv="mobile-agent" content="format=xhtml; url=">
<link rel="stylesheet" type="text/css" media="all" href="http://hkwj.v228.10000net.cn/pengbin/css/base.css">
<link rel="stylesheet" type="text/css" media="all" href="http://hkwj.v228.10000net.cn/pengbin/css/style.css">
<meta name="keywords" content="网贷,贷款超市,贷款口子,下款">
<meta name="description" content="贷款超市">
<link rel="stylesheet" type="text/css" href="http://hkwj.v228.10000net.cn/pengbin/css/dropload.css">
<link rel="stylesheet" type="text/css" media="all" href="http://hkwj.v228.10000net.cn/pengbin/css/swiper.min.css">
<script type="text/javascript" src="http://hkwj.v228.10000net.cn/pengbin/js/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="http://hkwj.v228.10000net.cn/pengbin/js/dropload.min.js"></script>
</head>
<body>
<script type="text/javascript" src="http://hkwj.v228.10000net.cn/pengbin/js/swiper.jquery.min.js"></script>
<div class="touch-slide">
<div class="swiper-container">
<div class="swiper-wrapper">
</div>
<div class="swiper-pagination">
</div>
</div>
<script>
		    var swiper = new Swiper('.swiper-container', {
		        pagination: '.swiper-pagination',
		        nextButton: '.swiper-button-next',
		        prevButton: '.swiper-button-prev',
		        paginationClickable: true,
		        spaceBetween: 30,
		        centeredSlides: true,
		        autoplay: 2500,
		        autoplayDisableOnInteraction: true,
		    });

		    $(function () {
		        var swiper_h = Math.ceil($('.touch-slide').width() * 0.5);
		    });
		</script>
</div>
<div class="swiper-channels">

<div class="swiper-pagination-channel">
</div>
<script>
		    var swiper = new Swiper('.swiper-channels', {
		        pagination: '.swiper-pagination-channel',
		        slidesPerView: 4,
		        slidesPerGroup: 8,
		        slidesPerColumn: 2,
		        slidesPerColumnFill: 'column',
		        paginationClickable: true,
		        spaceBetween: 15,

		    });
		</script>
</div>
<style type="text/css">
		.swiper-container2{
			height: 32px;
			line-height: 32px;
			overflow: hidden;
		}
		.commend{
			background: #fff;
		}

		.dropload-down{
			width: 100%;
		}
		.clearfix dd{
			width: 70%;
		}
		.search_head{
			width: 100%;
		}
		@media only screen and (max-width: 384px){
			.width{
				/*width: 521px !important;*/
			}
		}
		
		@media screen and (min-width: 414) {
    		 #width111{
				/*width: 521px !important;*/
				width: 50%;

			}
		}

		.daikuan_list dd p.title{
			
		}

	</style>
<div class="search_head">
<div class="clearfix">
<dt style="width:88px !important;float:left;">
<img alt="最热产品" src="http://hkwj.v228.10000net.cn/pengbin/images/hot.png">
</dt>
<div class="swiper-container2">
<div class="swiper-wrapper">



<c:forEach items="${asorder}" var="app">
	<div class="swiper-slide commend">
		<dd style="width:90% !important;float:right;margin:0 10% 0 0;">
			<a href="app_redirect.do?id=${app.id}" target="_blank">
				<p class="title" style="margin-left:20%;">${app.name}<span class="fr">成功率：<i>${app.rate}%</i></span></p>
			</a>
		</dd>
	</div>
</c:forEach>
 	
<!--  -->

</div>
<script src="http://hkwj.v228.10000net.cn/pengbin/js/swiper.min.js"></script>
<script>
	                var swiper = new Swiper('.swiper-container2', {
	                    spaceBetween: 30,
	                    centeredSlides: true,
	                    loop: true,
	                    direction: 'vertical',
	                    autoplay: {
	                        delay: 3000,
	                        disableOnInteraction: false,
	                    },
	                });
			    </script>
</div>
</div>
<div style="height: 10px;">
	<marquee style="color:red;">内部消息：同时申请五个产品以上，能提高100%的下款率！</marquee>
</div>
</div>
<div class="block_box">
<ul class="daikuan_types clearfix">
<li class="categoryClass cur" classid='is_top'><a>推荐口子</a></li>
<li class="categoryClass" classid='is_msg'><a>最新口子</a></li>
<li class="categoryClass " classid='is_red'><a>热门口子</a></li>
</ul>

<div id="trendCont_c_is_top" class="daikuan_list" style="display:block;">

<c:forEach items="${asorder}" var="app">
	<a href="app_redirect.do?id=${app.id}" target="_blank">
		<dl class="clearfix">
			<dt class="icon" style="width:30%">
				<img alt="${app.name}" src="upload/${app.logo}">
			</dt>
			
			<dd class="width width_two">
				<p class="title">${app.name}<span class="fr">成功率：<i>${app.rate}%</i></span></p>
				<p>额度：<i>${app.min_limit}-${app.max_limit}元</i><span class="fr">费用：<i>${app.feilv}%</i></span></p>
				<p>${app.need_condition}</p>
			</dd>
		</dl>
	</a>
</c:forEach>

</div>



<div id="trendCont_c_is_msg" class="daikuan_list" style="display:none;">

<c:forEach items="${astime}" var="app">
	<a href="app_redirect.do?id=${app.id}" target="_blank">
		<dl class="clearfix">
			<dt class="icon" style="width:30%">
				<img alt="${app.name}" src="upload/${app.logo}">
			</dt>
			
			<dd class="width width_two">
				<p class="title">${app.name}<span class="fr">成功率：<i>${app.rate}%</i></span></p>
				<p>额度：<i>${app.min_limit}-${app.max_limit}元</i><span class="fr">费用：<i>${app.feilv}%</i></span></p>
				<p>${app.need_condition}</p>
			</dd>
		</dl>
	</a>
</c:forEach>

</div>


<div id="trendCont_c_is_red" class="daikuan_list" style="display:none;">

<c:forEach items="${asua}" var="app">
	<a href="app_redirect.do?id=${app.id}" target="_blank">
		<dl class="clearfix">
			<dt class="icon" style="width:30%">
				<img alt="${app.name}" src="upload/${app.logo}">
			</dt>
			
			<dd class="width width_two">
				<p class="title">${app.name}<span class="fr">成功率：<i>${app.rate}%</i></span></p>
				<p>额度：<i>${app.min_limit}-${app.max_limit}元</i><span class="fr">费用：<i>${app.feilv}%</i></span></p>
				<p>${app.need_condition}</p>
			</dd>
		</dl>
	</a>
</c:forEach>

</div>

<script>
	
				    //界面初始化后，为防止商品量过多，本应执行下拉加载效果，考虑现实产品数量不大，暂不做此效果，代码注释存放
				    $(function () {
				        // //dropload
				        //// 页数
				        // var page = 1;
				        //// // 每页展示20个
				        // var size = 10;
				        // classid=$(".cur").attr('classid');
				        // console.log($(".cur").attr('classid'));
				        //$('.block_box').dropload({
				        //     scrollArea : window,
				        //      loadDownFn : function(me){
				        //          $.ajax({
				        //              type: 'GET',
				        //              url: '/tools/submit_ajax.ashx?action=get_pro&page=' + page + '&size=' + size + '&type=' + classid,
				        //              dataType: "json",
				        //              success: function(data){
				        //                  //	console.log(data);
				        //                  var strHtml = '';
				        //                  var length = data.length;
				        //                  if (length == 0) {
				        //                      me.lock();
				        //                      // 无数据
				        //                      me.noData();
				        //                      me.resetload();
				        //                      if (page == 1) {
				        //                          $(".dropload-down").css("display", "none");//没有更多数据的文字
				        //                      }
				        //                  }
				        //                  else {
				        //                      $(".dropload-load").show();
				        //                      page++;
				        //                      for (var i = 0; i < length; i++) {
				        //                          strHtml += '<a href=' + '"' + data[i].link_url + '"' + '> <dl class="clearfix" ><dt class="icon" style="width:22%">'

                        //     + '	<img alt="' + data[i].title + '" src="' + data[i].img_url + '"> </dt>'

                        //     + '<dd style="width:78%;background:url(/templates/mobile/images/e23deb9312a569a0f4c427f937000fab.png) no-repeat 100% 0;background-size:30%;" class="width width_two">'
                        //     + '	<p class="title"> ' + data[i].title + '<span class="fr">成功率：<i>99%</i></span></p>'
                        //      + '  <p>	额度：<i>1000-5000</i>   费用：<i>日利率0.04%</i> </p> <p>致力于为短期内急需现金的用户提...</p> </dd> </dl> </a>';
				        //                         if (length < 10 && i == (length - 1)) { //表示一页未满已经没有数据
				        //                              // 锁定
				        //                              me.lock();
				        //                              // 无数据
				        //                              me.noData();
				        //                              break;
				        //                          }
				        //                      }
				        //                       setTimeout(function(){
				        //                         // 插入数据到页面，放到最后面
				        //                        $('#trendCont_c_'+classid).append(strHtml);
				        //                        // 每次数据插入，必须重置
				        //                         me.resetload();
				        //                        },50);
				        //                  }
				        //               },
				        //              error: function(xhr, type){
				        //                  //alert('Ajax error!');
				        //                   // 即使加载出错，也得重置
				        //                   me.resetload();
				        //               },
				        //           });
				        //       }
				        //  });

				        $(".categoryClass").click(function () {
				            $(".dropload-down").remove();
				          //  $(".daikuan_list").html("");
				            var classid = $(this).attr('classid');
				            console.log(classid);
				            $(".daikuan_list").hide(400);
				            $("#trendCont_c_" + classid).show(400);
				            $(this).addClass('cur').siblings().removeClass('cur');

				            // $.ajax({
				           //  	url : '/index/goods/goodsclassten.html',
				            // 	type : 'POST',
				             //	data : 'classid='+classid,
				            // 	async : false,
				             //	success : function(data){
				             //		console.log(data);
				             //		$("#trendCont_c_1").append(data);
				           //  	},
				            // 	error : function(data){
				             //		console.log('连接错误');
				             //		//alert('连接错误');
				            // 	}
				         //    });

				            // 页数
				           //    var page = 1;
				             //  每页展示10个
				           //    var size = 10;

				            //   dropload
				               //$('.block_box').dropload({
				               //    scrollArea: window,
				               //    loadDownFn: function (me) {
				               //        $.ajax({
				               //            type: 'GET',
				               //            url: '/tools/submit_ajax.ashx?action=get_pro&page=' + page + '&size=' + size + '&type=' + classid,
				               //            dataType: "json",
				               //            success: function (data) {
				               //                //	console.log(data);
				               //                var strHtml = '';
				               //                var length = data.length;
				               //                if (length == 0) {
				               //                    me.lock();
				               //                    // 无数据
				               //                    me.noData();
				               //                    me.resetload();
				               //                    if (page == 1) {
				               //                        $(".dropload-down").css("display", "none");//没有更多数据的文字
				               //                    }
				               //                }
				               //                else {
				               //                    $(".dropload-load").show();
				               //                    page++;
				               //                    for (var i = 0; i < length; i++) {
				               //                        strHtml += '<a href=' + '"' + data[i].link_url + '"' + '> <dl class="clearfix" ><dt class="icon" style="width:22%">'

                               //   + '	<img alt="' + data[i].title + '" src="' + data[i].img_url + '"> </dt>'

                               //   + '<dd style="width:78%;background:url(/templates/mobile/images/e23deb9312a569a0f4c427f937000fab.png) no-repeat 100% 0;background-size:30%;" class="width width_two">'
                               //   + '	<p class="title"> ' + data[i].title + '<span class="fr">成功率：<i>99%</i></span></p>'
                               //    + '  <p>	额度：<i>1000-5000</i>   费用：<i>日利率0.04%</i> </p> <p>致力于为短期内急需现金的用户提...</p> </dd> </dl> </a>';
				               //                        if (length < 10 && i == (length - 1)) {//表示一页未满已经没有数据
				               //                            // 锁定
				               //                            me.lock();
				               //                            // 无数据
				               //                            me.noData();
				               //                            break;
				               //                        }
				               //                    }
				               //                    setTimeout(function () {
				               //                        // 插入数据到页面，放到最后面
				               //                        $('#trendCont_c_' + classid).append(strHtml);
				               //                        // 每次数据插入，必须重置
				               //                        me.resetload();
				               //                    }, 50);
				               //                }
				               //            },
				               //            error: function (xhr, type) {
				               //                //alert('Ajax error!');
				               //                // 即使加载出错，也得重置
				               //                me.resetload();
				               //            },
				               //        });
				               //    }
				               //});
				        });
				    })

		</script>
</div>
<footer class="footer">
<div class="container">
</div>
</footer>
<div class="footer_nav">
</div>
<script type="text/javascript">
	    $(function () {
	        if ($('.daikuan_list').length > 0) {
	            var daikuan_node_w = $('.daikuan_list dl').width();
	            $('.daikuan_list dl dd').width(daikuan_node_w - $('.daikuan_list dt').width());
	        }
	    });

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
	</script>
<div class="hidden">
</div>
</body>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?e50417764aa19394199a8180cafe9f3d";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
</html>
<script type="text/javascript">
    $(function () {
        var len = $(".width_two").length;
        for (var i = 0; i <= len; i++) {
            $(".width_two").eq(i).width('65%');
        }
    })

</script>
    