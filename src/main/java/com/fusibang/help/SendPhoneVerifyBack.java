package com.fusibang.help;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class SendPhoneVerifyBack {

    private static final Logger logger = Logger.getLogger(SendPhoneVerifyBack.class);
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
        String requestParamt = "needstatus=false&username=" + username + "&passwd=" + passwd + "&phone=" + phone + "&msg=" + "您正在注册成为新用户，验证码是：" + code + "，感谢您的支持！【" + sign +"】";

		URL url = new URL("http://www.qybor.com:8500/shortMessage");
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
		JSONObject json = (JSONObject) JSONObject.parse(line);
		boolean success = json.get("respcode").equals("0");
        if (!success) {
			logger.info("send verifycode to " + phone + " failed  result respdesc：" + json.get("respdesc") );
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
        String requestParamt = "needstatus=false&username=" + username + "&passwd=" + passwd + "&phone=" + phone + "&msg=" + "您正在修改登录密码，验证码是：" + code + "，不要告诉任何人！【" + sign +"】";

		URL url = new URL("http://www.qybor.com:8500/shortMessage");
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
		JSONObject json = (JSONObject) JSONObject.parse(line);
		boolean success = json.get("respcode").equals("0");
        if (!success) {
			logger.info("send verifycode to " + phone + " failed  result respdesc：" + json.get("respdesc") );
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
        String requestParamt = "needstatus=false&username=" + username + "&passwd=" + passwd + "&phone=" + phone + "&msg=" + "您正在登入App，验证码是：" + code + "，感谢您的支持！【" + sign +"】";

        URL url = new URL("http://www.qybor.com:8500/shortMessage");
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
        JSONObject json = (JSONObject) JSONObject.parse(line);
        boolean success = json.get("respcode").equals("0");
        if (!success) {
            logger.info("send verifycode to " + phone + " failed  result respdesc：" + json.get("respdesc") );
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
