package com.fusibang.webservice.util;

import com.fusibang.webservice.SmSWebService;
import com.fusibang.webservice.WsSendResponse;
import org.apache.log4j.Logger;

/**
 * @description: TODO
 * @author: Alex
 * @date: 2020-05-22 21:34
 */
public class SendMsgUtil {

    private static final Logger logger = Logger.getLogger(SendMsgUtil.class);

    public static boolean send(String phone, String code, String sign){
        String content = "【"+sign+"】短信验证码:"+code+"，五分钟内输入有效";
        WsSendResponse wsSendResponse = new SmSWebService().getSmSWebServiceSoap().sendSms("155", "xiandada", "xiandada", phone, content, null, null);
//        {"message":"ok","remainPoint":207,"returnStatus":"Success","successCounts":1,"taskID":213143}
        //任务状态,Success 成功,Faild 失败
        String returnStatus = wsSendResponse.getReturnStatus();
        if("Success".equals(returnStatus)){
            return true;
        }
        //提交成功为 ok，其他为失败信息的描述
        String message = wsSendResponse.getMessage();
        logger.debug("send verifycode to " + phone + " result：    " + message);
        return false;
    }

}