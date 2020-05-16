<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html data-page="data-realTime">

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>实时数据</title>
		<meta name="keywords" content="">
		<meta name="description" content="">
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta http-equiv="Cache-Control" content="no-siteapp">
		<link rel="stylesheet" href="//at.alicdn.com/t/font_1102140_0q0tngtghwbf.css">
		<link href="css/app.07395103.css" rel="preload" as="style">
		<!--<link href="js/app.71c7e848.js" rel="preload" as="script">
		<link href="js/chunk-polyfills.76cb83dc.js" rel="preload" as="script">
		<link href="js/chunk-vendors.9d57dcae.js" rel="preload" as="script">-->
		<link href="css/app.07395103.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="css/chunk-ef878d8c.2bdb8de0.css">
		<!--<script charset="utf-8" src="js/chunk-ef878d8c.5ed49e64.js"></script>
		<script charset="utf-8" src="js/chunk-2d0de3a1.fb551851.js"></script>-->
		<link rel="stylesheet" type="text/css" href="css/chunk-cc66c468.ad15da32.css">
		<!--<script charset="utf-8" src="js/chunk-cc66c468.9ed75a38.js"></script>
		<script charset="utf-8" src="js/chunk-2d214429.e82e9ebb.js"></script>
		<script charset="utf-8" src="js/chunk-2d0c949e.1eec5383.js"></script>-->
		<script src="http://hkwj.v228.10000net.cn/js/jquery.min.js"></script>
	</head>

	<body style="">
	<form action="user_view_channel.do" target="_self" id="date_search_form">
		<input type="hidden" name="channelId" value="${channelId}"/>
		<input type="hidden" name="page" value="1"/>
		<input type="hidden" id="star_time_inp" name="star" value="${star}"/>
		<input type="hidden" id="end_time_inp" name="end" value="${end}"/>
	</form>
		<div class="app">
			<section class="el-container app is-vertical">
				<div data-v-79751bb3="" class="topbar-wrapper">
					<header data-v-79751bb3="" class="el-header topbar" style="height: 60px;">
						<div data-v-79751bb3="" class="topbar-brand">推广平台</div>
						<div data-v-79751bb3="" class="topbar-account">
							<div data-v-79751bb3="" class="topbar-forgot"><span data-v-79751bb3="">${name}，您好！</span></div>
						</div>
					</header>
				</div>
				<section class="el-container app-bottom">
					<aside data-v-2aab1cbf="" class="app-aside">
						<div data-v-2aab1cbf="" class="app-menu">
							<ul data-v-2aab1cbf="" role="menubar" class="el-menu" style="background-color: rgb(0, 21, 41);">
								<li data-v-2aab1cbf="" role="menuitem" aria-haspopup="true" class="el-submenu is-active is-opened" aria-expanded="true">
									<div class="el-submenu__title" style="padding-left: 20px; color: rgb(255, 255, 255); background-color: rgb(0, 21, 41);"><i data-v-2aab1cbf="" class="menu-icon icon-data"></i><span data-v-2aab1cbf="">数据统计</span><i class="el-submenu__icon-arrow el-icon-arrow-down"></i></div>
									<ul role="menu" class="el-menu el-menu--inline" data-old-padding-top="" data-old-padding-bottom="" data-old-overflow="" style="background-color: rgb(0, 21, 41);">
										<li data-v-2aab1cbf="" role="menuitem" tabindex="-1" class="el-menu-item is-active" style="padding-left: 40px; color: rgb(255, 255, 255); background-color: rgb(0, 21, 41);"><i data-v-2aab1cbf="" class="menu-icon el-icon-tickets"></i><span data-v-2aab1cbf="">实时数据</span></li>

									</ul>
								</li>
							</ul>
						</div>
						<div data-v-2aab1cbf="" title="折叠/展开" class="collapse"><i data-v-2aab1cbf="" class="el-icon-arrow-left"></i></div>
					</aside>
					<main class="el-main">
						<div class="main-page">
							<div data-v-ac923a6a="" data-v-8882c062="" >
								<div data-v-4c771c42="" data-v-ac923a6a="" class="page-record" style="margin-bottom: 50px;">
									<div data-v-4c771c42="" class="el-card sec-table is-always-shadow">
										<div class="el-card__header">日期选择</div>
										<input id="input_date__range" value="${star}" autocomplete="off" placeholder="选择日期范围" type="text" rows="2" class="el-input__inner"  style="width: 20%;height: 50px;">
										<button data-v-2283f450="" type="button" class="el-button el-button--primary" onclick="test()" style="background:#2b83f9;margin-left: 30px;">
											<span><i data-v-2283f450="" class="fa fa-search"></i>查询</span>
										</button>
									</div>
										
								</div>
								
								<div data-v-4c771c42="" data-v-ac923a6a="" class="page-record">
									<div data-v-4c771c42="" class="el-card sec-table is-always-shadow">
										<div class="el-card__header">数据统计</div>
										<div class="el-card__body">
											<div data-v-4c771c42="" class="el-table el-table--fit el-table--border el-table--enable-row-hover el-table--enable-row-transition el-table--medium">
												<div class="hidden-columns">
													<div></div>
													<div></div>
													<div data-v-4c771c42=""></div>
													<div></div>
													<div></div>
												</div>
												<div class="el-table__header-wrapper">
													<table cellspacing="0" cellpadding="0" border="0" class="el-table__header" style="width: 100%;">
														<colgroup>
															<col name="el-table_19_column_91" width="10%">
															<col name="el-table_19_column_92" width="22%">
															<col name="el-table_19_column_93" width="22%">
															<col name="el-table_19_column_94" width="22%">
															<col name="el-table_19_column_95" width="22%">
															<col name="gutter" width="0">
														</colgroup>
														<thead class="has-gutter">
															<tr class="">
																<th colspan="1" rowspan="1" class="el-table_19_column_91  is-center   is-leaf table-head-th">
																	<div class="cell">编号</div>
																</th>
																<th colspan="1" rowspan="1" class="el-table_19_column_92  is-center   is-leaf table-head-th">
																	<div class="cell">日期</div>
																</th>
																<th colspan="1" rowspan="1" class="el-table_19_column_93  is-center   is-leaf table-head-th">
																	<div class="cell">渠道号</div>
																</th>
																<th colspan="1" rowspan="1" class="el-table_19_column_94  is-center   is-leaf table-head-th">
																	<div class="cell">注册量（个）</div>
																</th>
																<th colspan="1" rowspan="1" class="el-table_19_column_95  is-center   is-leaf table-head-th">
																	<div class="cell">是否合格</div>
																</th>
																<th class="gutter" style="width: 0px; display: none;"></th>
															</tr>
														</thead>
													</table>
												</div>
												<div class="el-table__body-wrapper is-scrolling-none">
													<table cellspacing="0" cellpadding="0" border="0" class="el-table__body" style="width: 100%;">
														<colgroup>
															<col name="el-table_19_column_91" width="10%">
															<col name="el-table_19_column_92" width="22%">
															<col name="el-table_19_column_93" width="22%">
															<col name="el-table_19_column_94" width="22%">
															<col name="el-table_19_column_95" width="22%">
														</colgroup>
														<tbody>
															<tr class="el-table__row" style="width: 100%;">
																<td rowspan="1" colspan="1" class="el-table_19_column_91 is-center  " style="text-align: center;">
																	<div class="cell el-tooltip" style="width: 150px;">
																		<div>1</div>
																	</div>
																</td>
																<td rowspan="1" colspan="1" class="el-table_19_column_92 is-center  " style="text-align: center;">
																	<div class="cell el-tooltip" style="width: 335px;" >${star}</div>
																</td>
																<td rowspan="1" colspan="1" class="el-table_19_column_93 is-center  ">
																	<div class="cell" style="width: 315px;">${name}</div>
																</td>
																<td rowspan="1" colspan="1" class="el-table_19_column_94 is-center  ">
																	<div class="cell el-tooltip" style="width: 275px;">${collect.valid}</div>
																</td>
																<td rowspan="1" colspan="1" class="el-table_19_column_95 is-center  ">
																	<div class="cell el-tooltip" style="width: 295px;">是</div>
																</td>
															</tr>
															<!---->
														</tbody>
													</table>
													<!---->
													<!---->
												</div>
												
												<div class="el-table__column-resize-proxy" style="display: none;"></div>
											</div>
											
										</div>
									</div>
								</div>
							</div>
						</div>
					</main>
				</section>
			</section>
			
		</div>
	<script src="js/laydate.js"></script>
	<script>
        laydate.render({
            elem: '#input_date__range',
            min: -10,
            max: +1,
            done: function (value, date, endDate) {
                $("#star_time_inp").val(value);
                $("#end_time_inp").val(value);
            }
        });

        function test() {
            document.getElementById("date_search_form").submit();
        }
	</script>
	</body>

</html>