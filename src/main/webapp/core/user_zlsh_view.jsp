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
    <title>资料审核</title>
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
        window.location.href = "user_zlsh_view.do?salt=" + name;
    }

    function topage() {
        var page = $("#pageNumber").val();
        var pageCount = ${pageCount};
        if (page <= pageCount) {
            window.location.href = "user_zlsh_view.do?salt=${name}&id=" + page;
        } else {
            alert("一共" + pageCount + "页");
        }
        ;

    }

    //不通过
    function disagree(id) {

        layer.confirm('确定不通过?', {
            btn: ['确定', '取消'],title:"审核结果"
        },function () {
            //审核不通过（lend=4）
            $.ajax({
                type: "POST",
                url: "identify_modify_lend.do",
                data: 'id=' + id,
                dataType: "json",
                success: function (data) {
                    if (data.hint == "success") {
                        $(location).attr("href", "user_zlsh_view.do?salt=${name}&id=${thisPage}");
                    } else if (data.hint == "illegal_request") {
                        $(location).attr("href", "illegal_request.html");
                    } else if (data.hint == "un_login") {
                        $(location).attr("href", "timeout.html");
                    } else if (data.hine == "not_permission") {
                        $(location).attr("href", "nopermission.html");
                    }
                }
            });
        });

    }

    //通过并授额
    function agree(id) {

        //prompt层
        layer.prompt({title: '授款额度', formType: 0}, function(pass, index){
            if(isNumber(pass)){
                if(parseInt(pass) < 0){
                    layer.tips('授款额度不能为负数', '.layui-layer-input');
                    return;
                }

                if(parseInt(pass) > 50000){
                    layer.tips('授款额度不能大于50000', '.layui-layer-input');
                    return;
                }

                //修改额度和审核通过（lend=3）
                $.ajax({
                    type: "POST",
                    url: "identify_modify.do",
                    data: 'id=' + id+"&lend_count="+pass,
                    dataType: "json",
                    success: function (data) {
                        if (data.hint == "success") {
                            $(location).attr("href", "user_zlsh_view.do?salt=${name}&id=${thisPage}");
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
                layer.tips('请输入数字', '.layui-layer-input');
            }

        });

    }

    //打开用户详情页面
    function userDetail(userId){
        //要发送的参数
        var params = {
            "userId": userId
        };
        window["filter"] = params;
        window.open('info.html')
    }

    //重置用户资料
    function resetUserInfo(userId){
        layer.confirm('确定重置用户所有资料?', {
            btn: ['确定', '取消'],title:"重置资料"
        },function () {
            // 重置资料操作表：删除id_card、lend_status、user_contact、user_detail、user_detail_append，
            // 更新identify表中的所有字段
            $.ajax({
                type: "POST",
                url: "identify_reset_info.do",
                data: 'id=' + userId,
                dataType: "json",
                success: function (data) {
                    if (data.hint == "success") {
                        $(location).attr("href", "user_zlsh_view.do?salt=${name}&id=${thisPage}");
                    } else if (data.hint == "illegal_request") {
                        $(location).attr("href", "illegal_request.html");
                    } else if (data.hint == "un_login") {
                        $(location).attr("href", "timeout.html");
                    } else if (data.hine == "not_permission") {
                        $(location).attr("href", "nopermission.html");
                    }
                }
            });
        });
    }

    var layerIndex;
    //发送短信弹窗
    function sendMsg(phone,cashpassword,lend) {
        //提现密码
        cashpassword = cashpassword == null ? "" : cashpassword;
        lend = lend == null ? 0 : lend;

        var sendMsgHtml = "<div class=\"navcon\">" +
            "    <div class=\"tjqdbox\">" +
            "    <table width=\"100%\">" +
            "      <tr>" +
            "            <td><input type=\"text\" placeholder=\"可自己修改\" id=\"msgContent\" name=\"msgContent\" style=\"width:480px; height:30px; outline:none;\"/></td>" +
            "      </tr>" +
            "      <tr>";

            if(lend >= 3){
                sendMsgHtml += "<td style='float: left;'><div class=\"upframe fl\" onClick=\"changeCard1()\" style=\"margin-right:10px; height:34px; line-height:34px; background-color:#f2f2f2;color: #009688;\">失败</div></td>" +
                    "           <td style='float: left;'><div class=\"upframe fl\" onClick=\"changeCard2()\" style=\"margin-right:10px; height:34px; line-height:34px; background-color:#f2f2f2;color: #009688;\">通过</div></td>" +
                    "           <td style='float: left;'><div class=\"upframe fl\" onClick=\"changeCard6()\" style=\"margin-right:10px; height:34px; line-height:34px; background-color:#f2f2f2;color: #009688;\">款项</div></td>" +
                    "           <td style='float: left;'><div class=\"upframe fl\" onClick=\"changeCard3('"+cashpassword+"')\" style=\"margin-right:10px; height:34px; line-height:34px; background-color:#f2f2f2;color: #009688;\">提现</div></td>";
            }

            if(lend >=2){
                sendMsgHtml += "<td style='float: left;'><div class=\"upframe fl\" onClick=\"changeCard5()\" style=\"margin-right:10px; height:34px; line-height:34px; background-color:#f2f2f2;color: #009688;\">订单</div></td>";
            }

            sendMsgHtml += "<td style='float: left;'><div class=\"upframe fl\" onClick=\"changeCard()\" style=\"margin-right:10px; height:34px; line-height:34px; background-color:#f2f2f2;color: #009688;\">重置</div></td>" +
            "           <td style='float: left;'><div class=\"upframe fl\" onClick=\"changeCard4()\" style=\"margin-right:10px; height:34px; line-height:34px; background-color:#f2f2f2;color: #009688;\">账号</div></td>" +
            "       </tr>" +
            "      </table>" +
            "    </div>" +
            "</div>";

        //页面层-自定义
        layerIndex = layer.open({
            type: 1,
            title: "发送短信",
            closeBtn: 1,
            shadeClose: true,
            skin: 'yourclass',
            area: ['500px', '250px'],
            btn: ['确定',"取消"],
            yes: function(index, layero){
                //按钮【按钮一】的回调
                toSend(phone);
            },
            btn2: function(index, layero){
                //按钮【按钮二】的回调
                layer.close(index);
            },
            content: sendMsgHtml
        });

    };

    function toSend(phone){
        var msgContent = document.getElementById("msgContent").value.trim();
        if(msgContent == ""){
            layer.msg("请输入必填信息",{time:2000})
            return;
        }

        //发送短信
        $.ajax({
            url : "phone_admin_send.action",
            type : "POST",
            dataType : "json",
            data: "phone=" + phone + "&content=" + msgContent,
            success: function (data) {
                if (data.hint == "success") {
                    layer.msg("发送成功");
                    layer.close(layerIndex);
                } else if (data.hint == "unknow_error") {
                    layer.msg("发送失败");
                }
            }
        });
    }


    function changeCard(){
        document.getElementById("msgContent").value="尊敬的先生/女士，您提交的资料已重置，请尽快补充、提交最新资料。";
    }
    function changeCard1(){
        document.getElementById("msgContent").value="尊敬的先生/女士，您的订单审核失败，请登录平台查看。";
    }
    function changeCard2(){
        document.getElementById("msgContent").value="尊敬的先生/女士，您提交的资料已审核通过，马上去申请吧！";
    }
    function changeCard3(cashpassword){
        var tempArg = "";
        if(arguments.length>=1){
            tempArg = cashpassword;
        }
        document.getElementById("msgContent").value="尊敬的先生/女士，您的提现密码是"+tempArg+"，请勿泄漏。";
    }
    function changeCard4(){
        document.getElementById("msgContent").value="尊敬的先生/女士，您的账号信息有误，请及时登录平台查看。";
    }
    function changeCard5(){
        document.getElementById("msgContent").value="尊敬的先生/女士，您的订单有变，请及时登录平台查看。";
    }
    function changeCard6(){
        document.getElementById("msgContent").value="尊敬的先生/女士，您的款项将于2小时内重新发放，请注意查收到账信息。";
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
        <a href="#">首页</a><span>/</span><a href="#">资料审核</a><span>/</span><a href="#">用户管理</a>
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
                <td>用户ID</td>
                <td>手机号码</td>
                <td>用户名</td>
                <td>审核结果</td>
                <td>预期金额</td>
                <td>审批金额</td>
                <td>提现状态</td>
                <td>资料</td>
                <td>操作</td>
            </tr>

            <c:forEach items="${userInfoList}" var="userInfo">
                <tr>
                    <td>${userInfo.user.id}</td>
                    <td>${userInfo.user.phone_number}</td>
                    <td>${userInfo.userDetail.name}</td>
                    <td>
                        <c:if test="${userInfo.identify.lend == 2}">
                            <span>审核中</span>
                        </c:if>
                        <c:if test="${userInfo.identify.lend == 3}">
                            <span style="color:green">审核通过</span>
                        </c:if>
                        <c:if test="${userInfo.identify.lend == 4}">
                            <span style="color:red">资料未完善</span>
                        </c:if>
                    </td>
                    <td>${userInfo.lend.lend_count}</td>
                    <td>
                        <c:if test="${userInfo.identify.lend == 3}">
                            ${userInfo.identify.lend_count == 0 ? "":userInfo.identify.lend_count}
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${userInfo.identify.lend == 3 && userInfo.identify.sign == 1}">
                            <span>提现中</span>
                        </c:if>
                    </td>
                    <td><a  onClick="userDetail(${userInfo.user.id})"><span class="clickHover">点击查看</span></a></td>
                    <td>
                        <c:if test="${userInfo.identify.lend >= 2}">
                            <a class="upframe" onClick="agree(${userInfo.user.id})">通过并授额</a>
                            <a class="upframe" onClick="disagree(${userInfo.user.id})">不通过</a>
                        </c:if>
                        <a class="upframe" onClick="resetUserInfo(${userInfo.user.id})">重置资料</a>
                        <a class="upframe" onClick="sendMsg('${userInfo.user.phone_number}','${userInfo.identify.cash_password}',${userInfo.identify.lend})">短信</a>
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
                <td><a href="user_zlsh_view.do?id=${thisPage-1}&salt=${name}">上一页</a></td>
            </c:if>
			
			<c:if test="${thisPage > 3}">
                <c:forEach begin="1" end="3" var="i" step="1">
                    <td><a href="user_zlsh_view.do?id=${i}&salt=${name}">${i}</a></td>
                </c:forEach>
                <c:if test="${thisPage > 6}">
                    <td class="shenglue"><a href="#">...</a></td>
                </c:if>
                <c:if test="${thisPage == 5}">
                    <td><a href="user_zlsh_view.do?id=4&salt=${name}">4</a></td>
                </c:if>
                <c:if test="${thisPage >= 6}">
                    <td><a href="user_zlsh_view.do?id=${thisPage-2}&salt=${name}">${thisPage-2}</a></td>
                    <td><a href="user_zlsh_view.do?id=${thisPage-1}&salt=${name}">${thisPage-1}</a></td>
                </c:if>
            </c:if>
			
			<c:if test="${thisPage <= 3}">
                <c:forEach begin="1" end="${thisPage-1}" var="i" step="1">
                    <td><a href="user_zlsh_view.do?id=${i}&salt=${name}">${i}</a></td>
                </c:forEach>
            </c:if>
        	
		
     		<td class="addtd"><a href="#" style="color:#fff;">${thisPage}</a></td>
     		
 			<c:if test="${thisPage+1 <= pageCount}">
                <c:forEach begin="${thisPage+1}" end="${thisPage+2}" var="i" step="1">
                    <c:if test="${i <= pageCount}">
                        <td><a href="user_zlsh_view.do?id=${i}&salt=${name}">${i}</a></td>
                    </c:if>
                </c:forEach>
            </c:if>
			
			<c:if test="${thisPage + 3 == pageCount}">
                <td><a href="user_zlsh_view.do?id=${pageCount}&salt=${name}">${pageCount}</a></td>
            </c:if>
			
			<c:if test="${thisPage + 3 < pageCount}">
                <c:if test="${thisPage + 4 < pageCount}">
                    <td class="shenglue"><a href="#">...</a></td>
                </c:if>
                <td><a href="user_zlsh_view.do?id=${pageCount-1}&salt=${name}">${pageCount-1}</a></td>
                <td><a href="user_zlsh_view.do?id=${pageCount}&salt=${name}">${pageCount}</a></td>
            </c:if>
			
			
        	
 			<c:if test="${thisPage+1 <= pageCount}">
                <td><a href="user_zlsh_view.do?id=${thisPage+1}&salt=${name}">下一页</a></td>
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
    