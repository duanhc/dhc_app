package com.fusibang.help;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
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
        return sendVerifyCodeRegiste(phone, code, sign);
    }

    /**
     * @Description 发送注册验证码
     * @Author Alex
     * @Date 2019/08/28 7:48
     * @Params [phone, code, sign]
     * @return boolean
     **/
    public boolean sendVerifyCodeRegiste(String phone, String code, String sign) throws Exception {
        String requestParamt = "needstatus=false&account=" + username + "&pswd=" + passwd + "&mobile=" + phone + "&msg=【" + sign + "】您正在注册为新用户，验证码是：" + code + "，感谢您的支持！";

        URL url = new URL("http://118.178.138.170/msg/HttpBatchSendSM");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("GET");

        OutputStream out = conn.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(out, "utf-8");
        writer.write(requestParamt);
        writer.close();
        out.close();

        InputStream in = conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = reader.readLine();
        in.close();
        logger.debug("send verifycode to " + phone + " result：    " + line);
        String result = line.split(",")[1];
        boolean success = result.equals("0");
        if (!success) {
            logger.info("send verifycode to " + phone + "failed  result code：" + result);
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
        return sendVerifyCodeAltPassword(phone, code, sign);
    }

    /**
     * @Description 发送修改密码验证码
     * @Author Alex
     * @Date 2019/08/28 7:50
     * @Params [phone, code, sign]
     * @return boolean
     **/
    public boolean sendVerifyCodeAltPassword(String phone, String code, String sign) throws Exception {
        String requestParamt = "needstatus=false&account=" + username + "&pswd=" + passwd + "&mobile=" + phone + "&msg=【" + sign + "】您正在修改登入密码，验证码是：" + code + "，感谢您的支持！";

        URL url = new URL("http://118.178.138.170/msg/HttpBatchSendSM");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("GET");

        OutputStream out = conn.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(out, "utf-8");
        writer.write(requestParamt);
        writer.close();
        out.close();

        InputStream in = conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = reader.readLine();
        in.close();
        logger.debug("send verifycode to " + phone + " result：    " + line);
        String result = line.split(",")[1];
        boolean success = result.equals("0");
        if (!success) {
            logger.info("send verifycode to " + phone + "failed  result code：" + result);
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
        return sendVerifyCodeLogin(phone, code, sign);
    }

    /**
     * @Description 发送登录验证码
     * @Author Alex
     * @Date 2019/08/28 7:51
     * @Params [phone, code, sign]
     * @return boolean
     **/
    public boolean sendVerifyCodeLogin(String phone, String code, String sign) throws Exception {
        String requestParamt = "needstatus=false&account=" + username + "&pswd=" + passwd + "&mobile=" + phone + "&msg=【" + sign + "】您正在登入App，验证码是：" + code + "，感谢您的支持！";

        URL url = new URL("http://118.178.138.170/msg/HttpBatchSendSM");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("GET");

        OutputStream out = conn.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(out, "utf-8");
        writer.write(requestParamt);
        writer.close();
        out.close();

        InputStream in = conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = reader.readLine();
        in.close();
        logger.debug("send verifycode to " + phone + " result：    " + line);
        String result = line.split(",")[1];
        boolean success = result.equals("0");
        if (!success) {
            logger.info("send verifycode to " + phone + "failed  result code：" + result);
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
