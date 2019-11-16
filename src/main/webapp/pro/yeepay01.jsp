<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date, java.text.SimpleDateFormat" %>
<%
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_ssHHssSS");

    String requestno = "first" + sdf.format(new Date());

%>

<html>

<head>
    <title>1统一首次支付请求</title>
</head>


<body>

<form action="yeepay_action_unionFirstPay.action" method="post" target="_blank">
    <table align="center" style="border:solid 1px #107929; margin-top: 40px;">
        <tr>
            <th align="center" height="30" colspan="5" bgcolor="#6BBE18">
                1统一首次支付请求
            </th>
        </tr>

        <tr height="50">
            <td width="15%" align="center">还款请求号&nbsp;:</td>
            <%--            <td width="70%" align="center"><input type="text" size="80" name="requestno" value=<%=requestno %>></td>--%>
            <td width="70%" align="center"><input type="text" size="80" name="requestno" value="<%=requestno%>"></td>
            <td width="25%">requestno<span style="color:red">&nbsp;*</span></td>
        </tr>
        <tr height="50">
            <td width="15%" align="center">用户标识&nbsp;:</td>
            <td width="70%" align="center"><input type="text" size="80" name="identityid" value="201911120810helloalex"></td>
            <td width="20%">identityid<span style="color:red">&nbsp;*</span></td>
        </tr>

        <tr height="50">
            <td width="15%" align="center">用户标识类型&nbsp;:</td>
            <td width="70%" align="center">
                <select name="identitytype">

                    <option value="MAC">MAC</option>
                    <option value="IMEI">IMEI</option>
                    <option value="ID_CARD" selected="selected">ID_CARD</option>
                    <option value="PHONE">PHONE</option>
                    <option value="EMAIL">EMAIL</option>
                    <option value="USER_ID">USER_ID</option>
                    <option value="AGREEMENT_NO">AGREEMENT_NO</option>
                </select>
            </td>
            <td width="20%">identitytype<span style="color:red">&nbsp;*</span></td>
        </tr>


        <tr height="50">
            <td width="15%" align="center">银行卡号&nbsp;:</td>
            <td width="70%" align="center"><input type="text" size="80" name="cardno"/></td>
            <td width="20%">cardno<span style="color:red">&nbsp;*</span></td>
        </tr>
        <tr height="50">
            <td width="15%" align="center">证件号&nbsp;:</td>
            <td width="70%" align="center"><input type="text" size="80" name="idcardno" value=""></td>
            <td width="20%">idcardno<span style="color:red">&nbsp;*</span></td>
        </tr>
        <tr height="50">
            <td width="15%" align="center">证件类型&nbsp;:</td>
            <td width="70%" align="center"><input type="text" size="80" name="idcardtype" value="ID" readonly></td>
            <td width="20%">idcardtype</td>
        </tr>
        <tr height="50">
            <td width="15%" align="center">用户姓名&nbsp;:</td>
            <td width="70%" align="center"><input type="text" size="80" name="username"></td>
            <td width="20%">username<span style="color:red">&nbsp;*</span></td>
        </tr>
        <tr height="50">
            <td width="15%" align="center">手机号&nbsp;:</td>
            <td width="70%" align="center"><input type="text" size="80" name="phone"></td>
            <td width="20%">phone<span style="color:red">&nbsp;*</span></td>
        </tr>
        <tr height="50">
            <td width="15%" align="center">还款金额&nbsp;:</td>
            <td width="70%" align="center"><input type="text" size="80" name="amount" value="0.01"></td>
            <td width="20%">amount<span style="color:red">&nbsp;*</span></td>
        </tr>
        <tr height="50">
            <td width="15%" align="center">鉴权类型&nbsp;:</td>
            <td width="70%" align="center">
                <select name="authtype">

                    <option value="COMMON_FOUR" selected="selected">COMMON_FOUR</option>
                    <option value="COMMON_THREE">COMMON_THREE</option>
                </select>
            </td>
            <td width="20%">authtype<span style="color:red">&nbsp;*</span></td>
        </tr>
        <tr height="50">
            <td width="15%" align="center">是否发短信&nbsp;:</td>
            <td width="70%" align="center">

                <input type="radio" name="issms" id="0" value="true"/>
                <label for="0">有短验</label>
                <input type="radio" name="issms" id="1" value="false"/>
                <label for="1">无短验</label>

            </td>
            <td width="20%">issms<span style="color:red">&nbsp;*</span></td>
        </tr>
        <tr height="50">
            <td width="15%" align="center">建议短验发送类型&nbsp;:</td>
            <td width="70%" align="center">
                <select name="advicesmstype">

                    <option value="MESSAGE" selected="selected">MESSAGE</option>
                    <option value="VOICE">VOICE</option>
                </select>
            </td>
            <td width="20%">advicesmstype</td>
        </tr>
        <tr height="50">
            <td width="15%" align="center">定制短验模板ID&nbsp;:</td>
            <td width="70%" align="center"><input type="text" size="80" name="smstemplateid"></td>
            <td width="20%">smstemplateid</td>
        </tr>
        <tr height="50">
            <td width="15%" align="center">短验模板&nbsp;:</td>
            <td width="70%" align="center"><input type="text" size="80" name="smstempldatemsg"></td>
            <td width="20%">smstempldatemsg</td>
        </tr>
        <tr height="50">
            <td width="15%" align="center">绑卡订单有效期&nbsp;:</td>
            <td width="70%" align="center"><input type="text" size="80" name="avaliabletime"></td>
            <td width="20%">avaliabletime</td>
        </tr>
        <tr height="50">
            <td width="15%" align="center">回调地址&nbsp;:</td>
            <td width="70%" align="center"><input type="text" size="80" name="callbackurl"></td>
            <td width="20%">callbackurl</td>
        </tr>
        <tr height="50">
            <td width="15%" align="center">请求时间&nbsp;:</td>
            <td width="70%" align="center"><input type="text" size="80" id="requesttime" name="requesttime"></td>
            <td width="20%">requesttime</td>
        </tr>

        <tr height="50">
            <td width="15%" align="center">商品名称&nbsp;:</td>
            <td width="70%" align="center"><input type="text" size="80" name="productname"></td>
            <td width="20%">productname</td>
        </tr>
        <!--  <tr height="50">
             <td width="15%" align="center">终端号&nbsp;:</td>
             <td width="70%" align="center"><input type="text" size="80" name="terminalno" value="SQKKSCENE10" readonly></td>
             <td width="20%">terminalno</td>
         </tr> -->
        <tr height="50">
            <td width="15%" align="center">终端标识码&nbsp;:</td>
            <td width="70%" align="center">
                <select name="terminalno">
                    <option value="SQKKSCENE10" selected="selected">代扣</option>
                    <option value="SQKKSCENEKJ010">快捷</option>
                </select>
            </td>
            <td width="20%"></td>
        </tr>
        <tr height="50">
            <td width="15%" align="center">分账结果通知地址&nbsp;:</td>
            <td width="70%" align="center"><input type="text" size="80" name="dividecallbackurl" value=""></td>
            <td width="20%">dividecallbackurl</td>
        </tr>
        <tr height="50">
            <td width="15%" align="center">分账信息&nbsp;:</td>
            <td width="70%" align="center"><input type="text" size="80" name="newdividejstr" value=""></td>
            <td width="20%">newdividejstr</td>
        </tr>
        <tr height="50">
            <td></td>
            <td style="padding-left: 250px"><input type="submit" value="提交"></td>
        </tr>
    </table>
</form>
<script language="javascript" type="text/javascript">


    var requesttime = document.getElementById("requesttime");
    requesttime.value = getNowFormatDate();


    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        var hour = date.getHours();
        var minute = date.getMinutes();
        var second = date.getSeconds();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        if (hour >= 1 && hour <= 9) {
            hour = "0" + hour;
        }
        if (minute >= 1 && minute <= 9) {
            minute = "0" + minute;
        }
        if (second >= 1 && second <= 9) {
            second = "0" + second;
        }

        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + hour + seperator2 + minute
            + seperator2 + second;
        return currentdate;
    }


</script>
</body>
</html>
