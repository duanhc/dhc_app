<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="css/vendors.13cc6c1a.chunk.css"/>
    <link rel="stylesheet" href="css/antd.317558af.chunk.css"/>
    <link rel="stylesheet" href="css/umi.acd00da0.chunk.css"/>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no"/>
    <script>window.routerBase = "/";</script>
    <link rel="stylesheet" type="text/css" href="css/layouts__index.7ea1c9f0.chunk.css"/>
    <script charset="utf-8" src="js/layouts__index.81149523.async.js"></script>
    <script charset="utf-8" src="js/p__channelCustomer__index.6cbfdaea.async.js"></script>
    <script src="http://dzez.v228.10000net.cn/js/jquery.min.js"></script>
    <style>
        #down_context {
            display: none;
        }

        .ant-dropdown-trigger:hover #down_context {
            display: block;
        }
    </style>
</head>


<body>
<form action="user_view_channel.do" target="_self" id="date_search_form">
    <input type="hidden" name="channelId" value="${channelId}"/>
    <input type="hidden" name="page" value="1"/>
    <input type="hidden" id="star_time_inp" name="star" value="${star}"/>
    <input type="hidden" id="end_time_inp" name="end" value="${end}"/>
</form>
<div id="root">
    <div class="ant-layout ant-layout-has-sider">
        <div class="sider___rHZmy ant-layout-sider ant-layout-sider-dark" style="flex: 0 0 200px; max-width: 200px; min-width: 200px; width: 200px;">
            <div class="ant-layout-sider-children">
                <div class="logo___2ajOE">
                    <p class="logo_name___1R-S0">小花钱包</p>
                    <p class="logo_name___1R-S0">渠道管理后台</p>
                </div>
                <ul class="ant-menu ant-menu-dark ant-menu-root ant-menu-inline" role="menu">
                    <li class="ant-menu-item ant-menu-item-selected" role="menuitem" style="padding-left: 24px;"><span>渠道客户管理</span></li>
                </ul>
            </div>
        </div>
        <div class="ant-layout">
            <div class="header___tDjU- ant-layout-header">
                <i class="anticon anticon-menu-fold trigger___8tHlg"></i>
                <span style="float: right;">
		<span class="ant-dropdown-trigger" style="margin-right: 8px;">${name}
			<i class="anticon anticon-down"></i>
			 <div class="ant-dropdown ant-dropdown-placement-bottomLeft" id="down_context" style="left: 1818.61px; top: 45px;">
	<ul class="ant-dropdown-menu ant-dropdown-menu-light ant-dropdown-menu-root ant-dropdown-menu-vertical" role="menu">
		<li class="ant-dropdown-menu-item ant-dropdown-menu-item-active" role="menuitem"><a>修改密码</ a></li>
		<li class="ant-dropdown-menu-item" role="menuitem"><a>退出登录</a></li>
	</ul>
</div>
		</span>
	</span>
            </div>
            <div class="ant-layout-content" style="margin: 24px; padding: 24px; background: rgb(255, 255, 255); min-height: 280px;">
                <div>
                    <div class="antd-pro-page-header-pageHeader">
                        <div class="antd-pro-page-header-detail">
                            <div class="antd-pro-page-header-main">
                                <div class="antd-pro-page-header-row">
                                    <h1 class="antd-pro-page-header-title">渠道客户管理</h1>
                                </div>
                                <div class="antd-pro-page-header-row"></div>
                            </div>
                        </div>
                    </div>
                    <div class="content___2eDcp">
                        <div class="ant-card ant-card-wider-padding ant-card-padding-transition">
                            <div class="ant-card-body">
                                <div class="tableList">
                                    <div class="tableListForm">

                                        <div class="ant-row" style="margin-left: -24px; margin-right: -24px;">
                                            <div class="ant-col-sm-24 ant-col-md-8" style="padding-left: 24px; padding-right: 24px;">
                                                <div class="ant-row ant-form-item">
                                                    <div class="ant-form-item-label">
                                                        <label class="" title="渠道" for="channelId">渠道</label>
                                                    </div>
                                                    <div class="ant-form-item-control-wrapper">
                                                        <div class="ant-form-item-control has-success">
                  <span class="ant-form-item-children">
                   <div class="ant-select ant-select-enabled" style="width: 100%;">
                  
                  
						<select class="ant-input ant-input-lg" style="display: block;margin-left: 11px;margin-top:-5px; margin-right: 11px;position: relative;height: 40px;line-height: 40px;"
                                name="channelId">
							<option value="0" selected="selected">${name}</option>					
						</select>
                    
            
                   </div></span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="ant-col-sm-24 ant-col-md-8" style="padding-left: 24px; padding-right: 24px;">
                                                <div class="ant-row ant-form-item">
                                                    <div class="ant-form-item-label">
                                                        <label for="range-picker" class="" title="注册时间">注册时间</label>
                                                    </div>
                                                    <div class="ant-form-item-control-wrapper">
                                                        <div class="ant-form-item-control has-success">
                  <span class="ant-form-item-children">
				  <span id="range-picker" class="ant-calendar-picker" tabindex="0">
					<span class="ant-calendar-picker-input ant-input" style="height:40px;line-height:40px;margin-top:-5px">
                        <input id="input_date__range" class="ant-calendar-range-picker-input" value="${star}" placeholder="选择日期" style="width:240px" lay-key="1">
					</span>
				  </span>
				  </span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="ant-col-sm-24 ant-col-md-4" style="text-align: right; padding-left: 24px; padding-right: 24px;">
                                                <div class="ant-row ant-form-item">
                                                    <div class="ant-form-item-control-wrapper">
                                                        <div class="ant-form-item-control">
                                                            <span class="ant-form-item-children"><span class="submitButtons" onclick="test()"><button type="button"
                                                                                                                                                      class="ant-btn ant-btn-primary"><span>查 询</span></button><button
                                                                    type="button" class="ant-btn" style="margin-left: 8px;"><span>重 置</span></button></span></span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>

                                    </div>
                                    <div style="margin-bottom: 25px;">
                                        <h4>数据统计</h4>
                                        <p>备注：申请指完成认证。</p>
                                        <div class="standardTable___3DIwF">
                                            <div class="ant-table-wrapper">
                                                <div class="ant-spin-nested-loading">
                                                    <div class="ant-spin-container">
                                                        <div class="ant-table ant-table-default ant-table-bordered ant-table-scroll-position-left">
                                                            <div class="ant-table-content">
                                                                <div class="ant-table-body">
                                                                    <table class="">
                                                                        <colgroup>
                                                                            <col/>
                                                                            <col/>
                                                                            <col/>
                                                                            <col/>
                                                                            <col/>
                                                                        </colgroup>
                                                                        <thead class="ant-table-thead">
                                                                        <tr>
                                                                            <th class="" style="text-align: center;"><span>累计注册人数</span></th>
                                                                            <th class="" style="text-align: center;"><span>累计申请人数</span></th>
                                                                            <%--<th class="" style="text-align: center;"><span><span>累计放款金额</span></span></th>--%>
                                                                            <th class="" style="text-align: center;"><span><span>逾期</span></span></th>
                                                                        </tr>
                                                                        </thead>
                                                                        <tbody class="ant-table-tbody">
                                                                        <tr class="ant-table-row  ant-table-row-level-0" data-row-key="979">
                                                                            <td class="" style="text-align: center;"><span class="ant-table-row-indent indent-level-0"
                                                                                                                           style="padding-left: 0px;"></span>${collect.valid}</td>
                                                                            <td class="" style="text-align: center;">${collect.ljsqrs}</td>
                                                                            <%--<td class="" style="text-align: center;">0</td>--%>
                                                                            <td class="" style="text-align: center;">--</td>
                                                                        </tr>
                                                                        </tbody>
                                                                    </table>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
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

    function copyURL() {

        var Url2 = document.getElementById("url_copy").innerText;
        var oInput = document.createElement('input');
        oInput.value = Url2;
        document.body.appendChild(oInput);
        oInput.select(); // 选择对象
        document.execCommand("Copy"); // 执行浏览器复制命令
        oInput.className = 'oInput';
        oInput.style.display = 'none';
        document.getElementById("jumpbox").style.display = "block";
        window.setTimeout(function () {
            document.getElementById("jumpbox").style.display = "none";
        }, 1000);

    }
</script>


</body>
</html>