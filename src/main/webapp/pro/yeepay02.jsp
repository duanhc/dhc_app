<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date, java.text.SimpleDateFormat" %>


<html>

<head>
    <title>2首次支付短验确认</title>
</head>


<body>

<form action="yeepay_action_firstPaySmsConfirm.action" method="post" target="_blank">
    <table align="center" style="border:solid 1px #107929; margin-top: 40px;">
        <tr>
            <th align="center" height="30" colspan="5" bgcolor="#6BBE18">
                2首次支付短验确认
            </th>
        </tr>


        <tr height="50">
            <td width="15%" align="center">首次还款请求号&nbsp;:</td>
            <td width="70%" align="center"><input type="text" size="80" name="requestno" value=""></td>
            <td width="25%">requestno<span style="color:red">&nbsp;*</span></td>
        </tr>
        <tr height="50">
            <td width="15%" align="center">短验码&nbsp;:</td>
            <td width="70%" align="center"><input type="text" size="80" name="validatecode" value=""></td>
            <td width="20%">validatecode<span style="color:red">&nbsp;*</span></td>
        </tr>


        <tr height="50">
            <td></td>
            <td style="padding-left: 250px"><input type="submit" value="提交"></td>
        </tr>
    </table>
</form>

</body>
</html>
