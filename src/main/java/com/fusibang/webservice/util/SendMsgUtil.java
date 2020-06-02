package com.fusibang.webservice.util;

import com.alibaba.fastjson.JSONObject;
import com.fusibang.help.XMLHelp;
import com.fusibang.webservice.SmSWebService;
import com.fusibang.webservice.WsSendResponse;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @description: TODO
 * @author: Alex
 * @date: 2020-05-22 21:34
 */
public class SendMsgUtil {

    private static final Logger logger = Logger.getLogger(SendMsgUtil.class);

    /**
     * 发送验证码
     * @param phone
     * @param code
     * @param sign
     * @return
     */
    public static boolean send(String phone, String code, String sign){
        String content = "【"+sign+"】短信验证码:"+code+"，五分钟内输入有效";
        WsSendResponse wsSendResponse = new SmSWebService().getSmSWebServiceSoap().sendSms("175", "公爵", "gongjue", phone, content, null, null);
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

    /**
     * 发送自定义短信内容
     * @param phone
     *      手机号
     * @param content
     *      内容
     * @return
     */
    public static JSONObject send(String phone, String content) throws Exception{

        StringBuffer sb = new StringBuffer("http://39.98.237.121:8088/sms.aspx?");
        sb.append("action=send");
        sb.append("&userid="+"177");
        sb.append("&account="+"白卡分期");
        sb.append("&password="+"gongjue");
        sb.append("&mobile="+phone);
        sb.append("&content="+ content);

        URL url = new URL(sb.toString());

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("GET");

        InputStream inputStream = url.openStream();

        //1.创建DocumentBuilderFactory对象
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //2.创建DocumentBuilder对象
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document d = builder.parse(inputStream);
            NodeList sList = d.getElementsByTagName("returnsms");
            return XMLHelp.node(sList);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            inputStream.close();
        }
    }

    public static void main(String[] args) throws Exception {
//        通知
        String content = "【白卡网络】您的订单已通过，请注意查看信息！";
        content = URLEncoder.encode(content,"utf-8");
        try {
            JSONObject result = send("13163878425", content);
            System.out.println(result.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //注册
//        send("15070263720","111111","白卡分期");

    }
}