package com.fusibang.help;

import com.alibaba.fastjson.JSONObject;
import com.fusibang.webservice.util.SendMsgUtil;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

public class SendPhoneVerify {

    private static final Logger logger = Logger.getLogger(SendPhoneVerify.class);
    private String username;
    private String passwd;
    private String sign;

    private static Random random = new Random();

    public static String getRandomcode(int size) {
        String code = "";
        for (int i = 0; i < size; i++) {
            code += random.nextInt(10);
        }
        return code;
    }

    /**
     * @Description 发送注册验证码（不传签名值）
     * @Author Alex
     * @Date 2019/08/28 7:48
     * @Params [phone, code]
     * @return boolean
     **/
    public boolean sendVerifyCodeRegiste(String phone, String code) throws Exception {
        return SendMsgUtil.send(phone, code, "小花");
//        return sendVerifyCodeRegiste(phone, code, sign);
    }

    /**
     * @Description 发送注册验证码
     * @Author Alex
     * @Date 2019/08/28 7:48
     * @Params [phone, code, sign]
     * @return boolean
     **/
    public boolean sendVerifyCodeRegiste(String phone, String code, String sign) throws Exception {
        String content = "【小花】您正在注册为新用户，验证码是："+code+"，感谢您的支持！";
        StringBuffer sb = new StringBuffer("http://s.trd.ink/api/sms/send?");
        sb.append("token=d6c22b9aab547b5cfb857c7b9bb99f0f");
        sb.append("&mobile="+phone);
        sb.append("&content="+URLEncoder.encode(content,"utf-8"));

        URL url = new URL(sb.toString());

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

        String line = in.readLine();
        in.close();

        logger.debug("send verifycode to " + phone + " result：    " + line);
		JSONObject json = (JSONObject) JSONObject.parse(line);
		boolean success = json.get("code").toString().equals("0");
        if (!success) {
			logger.info("send verifycode to " + phone + " failed  result respdesc：" + json.get("msg") );
        }

        return success;
    }

    /**
     * @Description 发送修改密码验证码(不传签名)
     * @Author Alex
     * @Date 2019/08/28 7:49
     * @Params [phone, code, sign]
     * @return boolean
     **/
    public boolean sendVerifyCodeAltPassword(String phone, String code) throws Exception {
          return SendMsgUtil.send(phone, code, "小花");
//        return sendVerifyCodeAltPassword(phone, code, sign);
    }

    /**
     * @Description 发送修改密码验证码
     * @Author Alex
     * @Date 2019/08/28 7:50
     * @Params [phone, code, sign]
     * @return boolean
     **/
    public boolean sendVerifyCodeAltPassword(String phone, String code, String sign) throws Exception {
        String content = "【小花】您正在修改登入密码，验证码是："+code+"，感谢您的支持！";
        StringBuffer sb = new StringBuffer("http://s.trd.ink/api/sms/send?");
        sb.append("token=d6c22b9aab547b5cfb857c7b9bb99f0f");
        sb.append("&mobile="+phone);
        sb.append("&content="+URLEncoder.encode(content,"utf-8"));

        URL url = new URL(sb.toString());

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

        String line = in.readLine();
        in.close();

        logger.debug("send verifycode to " + phone + " result：    " + line);
        JSONObject json = (JSONObject) JSONObject.parse(line);
        boolean success = json.get("code").toString().equals("0");
        if (!success) {
            logger.info("send verifycode to " + phone + " failed  result respdesc：" + json.get("msg") );
        }

        return success;
    }

    /**
     * @Description 发送登录验证码（不传签名值）
     * @Author Alex
     * @Date 2019/08/28 7:50
     * @Params [phone, code]
     * @return boolean
     **/
    public boolean sendVerifyCodeLogin(String phone, String code) throws Exception {
            return SendMsgUtil.send(phone, code, "小花");
//        return sendVerifyCodeLogin(phone, code, sign);
    }

    /**
     * @Description 发送登录验证码
     * @Author Alex
     * @Date 2019/08/28 7:51
     * @Params [phone, code, sign]
     * @return boolean
     **/
    public boolean sendVerifyCodeLogin(String phone, String code, String sign) throws Exception {
        String content = "【小花】您正在登入App，验证码是："+code+"，感谢您的支持！";
        StringBuffer sb = new StringBuffer("http://s.trd.ink/api/sms/send?");
        sb.append("token=d6c22b9aab547b5cfb857c7b9bb99f0f");
        sb.append("&mobile="+phone);
        sb.append("&content="+URLEncoder.encode(content,"utf-8"));

        URL url = new URL(sb.toString());

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

        String line = in.readLine();
        in.close();

        logger.debug("send verifycode to " + phone + " result：    " + line);
        JSONObject json = (JSONObject) JSONObject.parse(line);
        boolean success = json.get("code").toString().equals("0");
        if (!success) {
            logger.info("send verifycode to " + phone + " failed  result respdesc：" + json.get("msg") );
        }

        return success;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
