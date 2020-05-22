package com.fusibang.webservice.util;

import com.alibaba.fastjson.JSONObject;
import com.fusibang.webservice.SmSWebService;
import com.fusibang.webservice.WsSendResponse;

import java.io.UnsupportedEncodingException;

/**
 * @description: TODO
 * @author: Alex
 * @date: 2020-05-22 20:42
 */
public class Test {

    public static void main(String[] args) throws UnsupportedEncodingException {
//         userid 企业id,
//         account 发送用户帐号,
//         password 发送帐号密码,
//         mobile 全部被叫号码,
//         content 发送内容,短信的内容，内容需要UTF-8编码,
//         sendTime 定时发送时间,允许为空,
//         extno 允许为空
//        xiandada  xiandada     id： 155
        String content = "【小花】短信验证码:33333，五分钟内输入有效";
//        String phone = "13163878425";
//        String phone = "15070263720";
        String phone = "15070253593";
//        content = URLEncoder.encode(content, "utf-8");
        WsSendResponse wsSendResponse = new SmSWebService().getSmSWebServiceSoap().sendSms("155", "xiandada", "xiandada", phone, content, null, null);
        //任务状态,Success 成功,Faild 失败
        String returnStatus = wsSendResponse.getReturnStatus();
        //提交成功为 ok，其他为失败信息的描述
        String message = wsSendResponse.getMessage();
//        剩余短信条数
        int remainPoint = wsSendResponse.getRemainPoint();
//        成功提交的号码数
        int successCounts = wsSendResponse.getSuccessCounts();
//        任务ID
        int taskID = wsSendResponse.getTaskID();
        System.out.println(JSONObject.toJSONString(wsSendResponse));
    }

}