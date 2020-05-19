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
    <title>借款管理</title>
    <style>
        .upframe {
            background-color: rgb(6, 148, 134);
            border-bottom-color: rgb(6, 148, 134);
            float: left;
            margin-right: 5px;
        }

        .tjqdbox{
            font-size:12px;
            position:relative;
        }
        .tjqdbox td{
            padding:0 40px 10px 0;
        }

        .clickHover:hover{color:red;cursor:pointer}
    </style>
</head>
<body>
<script>
    function searchannel() {
        var name = $("#searchname").val();
        window.location.href = "user_jkgl_view.do?salt=" + name;
    }

    function topage() {
        var page = $("#pageNumber").val();
        var pageCount = ${pageCount};
        if (page <= pageCount) {
            window.location.href = "user_jkgl_view.do?salt=${name}&id=" + page;
        } else {
            alert("一共" + pageCount + "页");
        }
        ;

    }

    var layerIndex;
    //转账说明
    function zzsm(id) {

        var zzsmHtml = "<div class=\"navcon\">" +
            "    <div class=\"tjqdbox\">" +
            "    <table width=\"100%\">" +
            "      <tr>" +
            "            <td><input type=\"text\" placeholder=\"可自己修改\" id=\"realName\" name=\"realName\" style=\"width:480px; height:30px; outline:none;\"/></td>" +
            "      </tr>" +
            "      <tr>" +
            "           <td style='float: left;'><div class=\"upframe fl\" onClick=\"changeCard()\" style=\"margin-right:10px; height:34px; line-height:34px; background-color:#f2f2f2;color: #009688;\">说明1</div></td>" +
            "           <td style='float: left;'><div class=\"upframe fl\" onClick=\"changeCard1()\" style=\"margin-right:10px; height:34px; line-height:34px; background-color:#f2f2f2;color: #009688;\">说明2</div></td>" +
            "           <td style='float: left;'><div class=\"upframe fl\" onClick=\"changeCard2()\" style=\"margin-right:10px; height:34px; line-height:34px; background-color:#f2f2f2;color: #009688;\">说明3</div></td>" +
            "       </tr>" +
            "      </table>" +
            "    </div>" +
            "</div>";

        //页面层-自定义
        layerIndex = layer.open({
            type: 1,
            title: "转账说明",
            closeBtn: 1,
            shadeClose: true,
            skin: 'yourclass',
            area: ['500px', '250px'],
            btn: ['确定',"取消"],
            yes: function(index, layero){
                //按钮【按钮一】的回调
                addZzsm(id);
            },
            btn2: function(index, layero){
                //按钮【按钮二】的回调
                layer.close(index);
            },
            content: zzsmHtml
        });

    };

    //修改订单状态
    function changeOrderStatus(id) {

        var orderStatusHtml = "<div class=\"tjqdbox\">" +
            "    <table width=\"100%\">" +
            "    <tr>" +
            "            <td style=\"width:110px; font-size:15px; text-align:right;\">RGB颜色代码</td>" +
            "                <td><span style=\"color: #E53333;margin-right: 30px;\">E53333</span><span style=\"color: #00D5FF;margin-right: 30px;\">00D5FF</span><span style=\"color: #E56000;margin-right: 30px;\">E56000</span><span style=\"color: #666666;margin-right: 30px;\">666666</span><span style=\"color: #3ED050;margin-right: 30px;\">3ED050</span></td>" +
            "            </tr>" +
            "        <tr>" +
            "            <td style=\"width:110px; font-size:15px; text-align:right;\">修改订单颜色</td>" +
            "                <td><input type=\"text\" placeholder=\"将RGB代码输入到框内\" id=\"orderColor\" name=\"orderColor\" style=\"width:300px; height:30px; outline:none;\"/></td>" +
            "            </tr>" +
            "            <tr>" +
            "            <td style=\"width:110px; font-size:15px; text-align:right;\">修改订单状态</td>" +
            "                <td><input type=\"text\" placeholder=\"可自己修改\" id=\"orderStatus\" name=\"orderStatus\"  style=\"width:300px; height:30px; outline:none;\"/></td>" +
            "            </tr>" +
            "            <tr>" +
            "            <td style=\"width:110px; font-size:15px; text-align:right;\">修改订单说明</td>" +
            "                <td><input type=\"text\" placeholder=\"可自己修改\" id=\"orderExplain\"  style=\"width:300px; height:30px; outline:none;\"/></td>" +
            "            </tr>" +
            "      <tr>" +
            "            <td></td>" +
            "                <td style=\"width: 70px;\">" +
            "                   <div class=\"upframe fl\" onClick=\"orderChangeCard()\" style=\"margin-right:10px; height:34px; line-height:34px; background-color:#f2f2f2;color: #009688;\">改卡</div>" +
                                "<div class=\"upframe fl\" onClick=\"orderChangeCard1()\" style=\"margin-right:10px; height:34px; line-height:34px; background-color:#f2f2f2;color: #009688;\">保险</div>" +
                                "<div class=\"upframe fl\" onClick=\"orderChangeCard2()\" style=\"margin-right:10px; height:34px; line-height:34px; background-color:#f2f2f2;color: #009688;\">信用</div>" +
                                "<div class=\"upframe fl\" onClick=\"orderChangeCard3()\" style=\"margin-right:10px; height:34px; line-height:34px; background-color:#f2f2f2;color: #009688;\">流水</div>" +
                                "<div class=\"upframe fl\" onClick=\"orderChangeCard5()\" style=\"margin-right:10px; height:34px; line-height:34px; background-color:#f2f2f2;color: #009688;\">放款</div>"+
            "                </td>" +
            "      </tr>" +
            "        </table>" +
            "    </div>";

        //页面层-自定义
        layerIndex = layer.open({
            type: 1,
            title: "订单修改",
            closeBtn: 1,
            shadeClose: true,
            skin: 'yourclass',
            area: ['700px', '350px'],
            btn: ['确定',"取消"],
            yes: function(index, layero){
                //按钮【按钮一】的回调
                addOrderInfo(id);
            },
            btn2: function(index, layero){
                //按钮【按钮二】的回调
                layer.close(index);
            },
            content: orderStatusHtml
        });

    };

    function orderChangeCard(){
        document.getElementById("orderStatus").value="资金冻结";
        document.getElementById("orderColor").value="E53333";
        document.getElementById("orderExplain").value="提现银行卡信息不符，导致资金冻结";

    }

    function orderChangeCard1(){
        document.getElementById("orderStatus").value="资金冻结";
        document.getElementById("orderColor").value="E53333";
        document.getElementById("orderExplain").value="风险评估过低，订单需绑定金融商业险。";

    }

    function orderChangeCard2(){
        document.getElementById("orderStatus").value="资金冻结";
        document.getElementById("orderColor").value="E53333";
        document.getElementById("orderExplain").value="信用分不足，请求未完成，请预缴首**期解冻";

    }
    function orderChangeCard3(){
        document.getElementById("orderStatus").value="资金冻结";
        document.getElementById("orderColor").value="E53333";
        document.getElementById("orderExplain").value="流水不足，导致放款失败，请补充流水。";

    }

    function orderChangeCard5(){
        document.getElementById("orderStatus").value="放款中";
        document.getElementById("orderColor").value="3ED050";
        document.getElementById("orderExplain").value="已预约从新打款，请留意到账信息。";

    }

    function changeCard(){
        document.getElementById("realName").value="因借款人账户信息不符，根据国家法规《借款人资金安全法》存在骗贷风险。";
    }
    function changeCard1(){
        document.getElementById("realName").value="借款人账户风险评估过低，根据国家法规《借款人资金安全法》暂停放款转账。";
    }
    function changeCard2(){
        document.getElementById("realName").value="借款人账户资金流水不足，根据国家法规《借款人资金安全法》存在多头借贷。";
    }

    function addZzsm(userId){
        var zzsm = document.getElementById("realName").value.trim();
        if(zzsm == ""){
            layer.msg("请输入必填信息",{time:2000})
            return;
        }

        //增加转账说明
        $.ajax({
            url : "addIdentifyZzsm.action",
            type : "POST",
            dataType : "json",
            data: "id=" + userId + "&zzsm=" + zzsm,
            success: function (data) {
                if (data.hint == "success") {
                    layer.msg("修改成功");
                    layer.close(layerIndex);
                } else if (data.hint == "illegal_request") {
                    $(location).attr("href", "illegal_request.html");
                } else if (data.hint == "un_login") {
                    $(location).attr("href", "timeout.html");
                } else if (data.hine == "not_permission") {
                    $(location).attr("href", "nopermission.html");
                }
            }
        });

    }

    //增加订单信息：订单状态、订单说明、订单颜色
    function addOrderInfo(userId){
        var orderStatus = document.getElementById("orderStatus").value;
        var orderColor = document.getElementById("orderColor").value;
        var orderExplain = document.getElementById("orderExplain").value;

        if(orderColor.trim() == ""){
            layer.tips('请输入必填信息', '#orderColor');
            return;
        }
        if(orderStatus.trim() == ""){
            layer.tips('请输入必填信息', '#orderStatus');
            return;
        }
        if(orderExplain.trim() == ""){
            layer.tips('请输入必填信息', '#orderExplain');
            return;
        }

        //增加订单信息：订单状态、订单说明、订单颜色
        $.ajax({
            url : "addIdentifyOrderInfo.action",
            type : "POST",
            dataType : "json",
            data: "id=" + userId + "&order_color=" + orderColor+"&order_status="+orderStatus+"&order_explain="+orderExplain,
            success: function (data) {
                if (data.hint == "success") {
                    layer.msg("修改成功");
                    layer.close(layerIndex);
                } else if (data.hint == "illegal_request") {
                    $(location).attr("href", "illegal_request.html");
                } else if (data.hint == "un_login") {
                    $(location).attr("href", "timeout.html");
                } else if (data.hine == "not_permission") {
                    $(location).attr("href", "nopermission.html");
                }
            }
        });

    }

    //打开转账截图页面
    function toZzjtPage(userId){
        //要发送的参数
        var params = {
            "userId": userId
        };
        window["filter"] = params;
        window.open('loan.html')
    }

    //打开保险截图页面
    function toBxjtPage(userId){
        //要发送的参数
        var params = {
            "userId": userId
        };
        window["filter"] = params;
        window.open('viewhdo.html')
    }

    //改卡
    function changeBankCard(id) {
        //prompt层
        layer.prompt({title: '修改银行卡号', formType: 0}, function(pass, index){
            if(isNumber(pass)){
                if(pass.length < 8){
                    layer.msg("请输入正确的卡号",{time:1000})
                    return;
                }

                //修改卡号
                $.ajax({
                    type: "POST",
                    url: "user_detail_alt_credit_num.do",
                    data: 'id=' + id+"&credit_number="+pass,
                    dataType: "json",
                    success: function (data) {
                        if (data.hint == "success") {
                            layer.msg("修改成功",{time:1000});
                        } else if (data.hint == "illegal_request") {
                            $(location).attr("href", "illegal_request.html");
                        } else if (data.hint == "un_login") {
                            $(location).attr("href", "timeout.html");
                        } else if (data.hine == "not_permission") {
                            $(location).attr("href", "nopermission.html");
                        }
                    }
                });

                layer.close(index);
            }else{
                layer.msg("请输入正确的卡号",{time:1000})
            }

        });

    }

    //资料页面
    function userDetail(userId){
        //要发送的参数
        var params = {
            "userId": userId
        };
        window["filter"] = params;
        window.open('info.html')
    }

    //合同页面
    function viewContract(userId){
        //要发送的参数
        var params = {
            "userId": userId
        };
        window["filter"] = params;
        window.open('viewContract.html')
    }

    //是否是数字
    function isNumber(val) {
        var regPos = /^\d+(\.\d+)?$/; //非负浮点数
        var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
        if(regPos.test(val) || regNeg.test(val)) {
            return true;
        } else {
            return false;
        }
    }
</script>

<div class="navcon">
    <div class="con_title">
        <a href="#">首页</a><span>/</span><a href="#">借款管理</a><span>/</span><a href="#">用户管理</a>
        <div class="ssbox">
            <input type="text" placeholder="请输入手机号" id="searchname" value="${name}" class="fl" style="height:31px; outline:none;"/>
            <a href="#" onClick="searchannel()">
                <div class="fl" style="background-color:#009786;width:60px; padding:0 0 0 15px;height:35px; line-height:35px; font-size:13px; color:#fff;"><img src="images/ss.png"
                                                                                                                                                                style="vertical-align:middle; margin-right:5px;">搜索
                </div>
            </a>
            <div class="clearfix"></div>
        </div>
    </div>
    <div class="listbox">
        <table>
            <tr>
                <td>订单号</td>
                <td>手机号</td>
                <td>用户名</td>
                <td>金额</td>
                <td>期限</td>
                <td>提现时间</td>
                <td>转账记录</td>
                <td>保险截图</td>
                <td>订单状态</td>
                <td>操作</td>
            </tr>

            <c:forEach items="${userInfoList}" var="userInfo">
                <tr>
                    <td>${userInfo.identify.order_no}</td>
                    <td>${userInfo.identify.user.phone_number}</td>
                    <td>${userInfo.userDetail.name}</td>
                    <td>${userInfo.identify.lend_count == 0 ? "":userInfo.identify.lend_count}</td>
                    <td>${userInfo.identify.lend_time}</td>
                    <td>${userInfo.identify.cash_time}</td>
                    <td><span class="clickHover" onClick="zzsm(${userInfo.identify.user.id})">转账说明</span>
                        <br/>
                        <span class="clickHover" onClick="toZzjtPage(${userInfo.identify.user.id})">转账截图</span></td>
                    <td><span class="clickHover" onClick="toBxjtPage(${userInfo.identify.user.id})">保险截图</span></td>
                    <td><a class="clickHover" onClick="changeOrderStatus(${userInfo.identify.user.id})">资金冻结</a></td>
                    <td>
                        <a class="upframe" onClick="viewContract(${userInfo.identify.user.id})">合同</a>
                        <a class="upframe" onClick="userDetail(${userInfo.identify.user.id})">资料</a>
                        <a class="upframe" onClick="changeBankCard(${userInfo.identify.user.id})">改卡</a>
                    </td>
                </tr>
            </c:forEach>


        </table>
    </div>

</div>

<!-- 分页 -->
<div class="layui-footer">
    <div class="pagesite">
    	<span class="inpage">
            <div class="allcount">共${count}条</div>
            <table class="fl" cellspacing="0">
        <tr>
  			<c:if test="${thisPage > 1}">
                <td><a href="user_jkgl_view.do?id=${thisPage-1}&salt=${name}">上一页</a></td>
            </c:if>
			
			<c:if test="${thisPage > 3}">
                <c:forEach begin="1" end="3" var="i" step="1">
                    <td><a href="user_jkgl_view.do?id=${i}&salt=${name}">${i}</a></td>
                </c:forEach>
                <c:if test="${thisPage > 6}">
                    <td class="shenglue"><a href="#">...</a></td>
                </c:if>
                <c:if test="${thisPage == 5}">
                    <td><a href="user_jkgl_view.do?id=4&salt=${name}">4</a></td>
                </c:if>
                <c:if test="${thisPage >= 6}">
                    <td><a href="user_jkgl_view.do?id=${thisPage-2}&salt=${name}">${thisPage-2}</a></td>
                    <td><a href="user_jkgl_view.do?id=${thisPage-1}&salt=${name}">${thisPage-1}</a></td>
                </c:if>
            </c:if>
			
			<c:if test="${thisPage <= 3}">
                <c:forEach begin="1" end="${thisPage-1}" var="i" step="1">
                    <td><a href="user_jkgl_view.do?id=${i}&salt=${name}">${i}</a></td>
                </c:forEach>
            </c:if>
        	
		
     		<td class="addtd"><a href="#" style="color:#fff;">${thisPage}</a></td>
     		
 			<c:if test="${thisPage+1 <= pageCount}">
                <c:forEach begin="${thisPage+1}" end="${thisPage+2}" var="i" step="1">
                    <c:if test="${i <= pageCount}">
                        <td><a href="user_jkgl_view.do?id=${i}&salt=${name}">${i}</a></td>
                    </c:if>
                </c:forEach>
            </c:if>
			
			<c:if test="${thisPage + 3 == pageCount}">
                <td><a href="user_jkgl_view.do?id=${pageCount}&salt=${name}">${pageCount}</a></td>
            </c:if>
			
			<c:if test="${thisPage + 3 < pageCount}">
                <c:if test="${thisPage + 4 < pageCount}">
                    <td class="shenglue"><a href="#">...</a></td>
                </c:if>
                <td><a href="user_jkgl_view.do?id=${pageCount-1}&salt=${name}">${pageCount-1}</a></td>
                <td><a href="user_jkgl_view.do?id=${pageCount}&salt=${name}">${pageCount}</a></td>
            </c:if>
			
			
        	
 			<c:if test="${thisPage+1 <= pageCount}">
                <td><a href="user_jkgl_view.do?id=${thisPage+1}&salt=${name}">下一页</a></td>
            </c:if>
         </tr>
            </table>
            <div style="color:#999;margin-left:15px;">到第</div>
            <div class="page"><input type="number" id="pageNumber" style="background-color:#fff; position:absolute; top:0; left:0;"></div>
            <div style="color:#999;">页</div>
            <div class="page" onClick="topage()" style="cursor:pointer;">确定</div>
        </span>
    </div>
</div>

<script>
    $(".navcon").height($(window).height() - 44);
    $(window).resize(function () {          //当浏览器大小变化时
        $(".navcon").height($(window).height() - 44);
    });
</script>
<script>

</script>
</body>
</html>
    