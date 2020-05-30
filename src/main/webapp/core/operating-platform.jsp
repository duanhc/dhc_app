<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" id="viewport" content="width=device-width, initial-scale=1">
    <script src="js/jquery.min.js"></script>
    <script src="../layer/layer.js"></script>
    <link rel="stylesheet" href="css/index.css" type="text/css"/>
    <title>工作台</title>
    <link rel="stylesheet" href="css/vendors.13cc6c1a.chunk.css" />
    <link rel="stylesheet" href="css/antd.317558af.chunk.css" />
    <link rel="stylesheet" href="css/umi.acd00da0.chunk.css" />
    <link rel="stylesheet" type="text/css" href="css/layouts__index.7ea1c9f0.chunk.css" />
    <script charset="utf-8" src="js/layouts__index.81149523.async.js"></script>
    <script charset="utf-8" src="js/p__channelCustomer__index.6cbfdaea.async.js"></script>
    <script src="../js/jquery.min.js"></script>
    <style>
        .tjqdbox td{
            padding:0 40px 10px 0;
        }
    </style>
</head>
<body>

<div class="navcon">
    <div class="con_title">
        <a href="#">首页</a><span>/</span><a href="#">工作台</a><span>/</span><a href="#">用户统计</a>
    </div>

    <form action="operating_platform.do" target="_self" id="date_search_form">
        <input type="hidden" id="star_time_inp" name="star" value="${star}" />
        <input type="hidden" id="end_time_inp" name="end" value="${end}" />
    </form>

    <div class="ant-layout-content" style="margin: 24px; padding: 24px; background: rgb(255, 255, 255); min-height: 280px;">
        <div>
            <div class="content___2eDcp">
                <div class="ant-card ant-card-wider-padding ant-card-padding-transition">
                    <div class="ant-card-body">
                        <div class="tableList">
                            <div class="tableListForm">

                                <div class="ant-row" style="margin-left: -24px; margin-right: -24px;">
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
                                                            <input id="input_date__range" class="ant-calendar-range-picker-input" value="${star} - ${end}" style="width:240px" lay-key="1">
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
                                                    <span class="ant-form-item-children"><span class="submitButtons" onclick="test()"><button type="button" class="ant-btn ant-btn-primary"><span>查 询</span></button><button type="button" class="ant-btn" style="margin-left: 8px;"><span>重 置</span></button></span></span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>

                            </div>
                            <h4>用户统计</h4>
                            <div class="standardTable___3DIwF">
                                <div class="ant-table-wrapper">
                                    <div class="ant-spin-nested-loading">
                                        <div class="ant-spin-container">
                                            <div class="ant-table ant-table-default ant-table-bordered ant-table-scroll-position-left">
                                                <div class="ant-table-content">
                                                    <div class="ant-table-body">
                                                        <table class="">
                                                            <colgroup>
                                                                <col style="width: 80px; min-width: 80px;" />
                                                                <col style="width: 80px; min-width: 80px;" />
                                                                <col style="width: 110px; min-width: 110px;" />
                                                                <col style="width: 180px; min-width: 180px;" />
                                                            </colgroup>
                                                            <thead class="ant-table-thead">
                                                                <tr>
                                                                    <th class=""><span>注册数</span></th>
                                                                    <th class=""><span>申请数</span></th>
                                                                </tr>
                                                            </thead>
                                                            <tbody class="ant-table-tbody">
                                                                <tr>
                                                                    <td class="">${registerCount}</td>
                                                                    <td class="">${signCount}</td>
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

<script src="js/laydate.js"></script>
<script>
    var dataStr = "${star} - ${end}";
    laydate.render({
        elem: '#input_date__range'
        ,range: true,
        min: -60,
        max: +60,
        change: function(value, date, endDate){
            $("#star_time_inp").val(value.split(" - ")[0]);
            $("#end_time_inp").val(value.split(" - ")[1]);
            //dataStr = value;
            //console.log(value); //得到日期生成的值，如：2017-08-18
            //console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
            //console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
        }
    });

    function test(){
        document.getElementById("date_search_form").submit();
    }

    $(".navcon").height($(window).height() - 44);
    $(window).resize(function () {          //当浏览器大小变化时
        $(".navcon").height($(window).height() - 44);
    });
</script>
<script>

</script>
</body>
</html>
