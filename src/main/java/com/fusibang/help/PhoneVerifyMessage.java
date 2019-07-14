//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.help;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import org.apache.log4j.Logger;

public class PhoneVerifyMessage {
    private static final Logger logger = Logger.getLogger(PhoneVerifyMessage.class);
    private String userid;
    private String account;
    private String password;
    private String model;

    public PhoneVerifyMessage() {
    }

    public String getRandomVerifyCode(int size) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();

        for(int i = 0; i < size; ++i) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }

    public boolean send2Registe(String phone, String code) {
        String context = "【" + this.model + "】你正在注册为新用户，验证码:" + code + ",不要告诉任何人!";
        boolean success = false;

        try {
            success = this.sendVerifyCode(phone, context);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return success;
    }

    public boolean send2ModifyPwd(String phone, String code) {
        String context = "【" + this.model + "】你正在修改登录密码，验证码:" + code + ",不要告诉任何人!";
        boolean success = false;

        try {
            success = this.sendVerifyCode(phone, context);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return success;
    }

    public boolean send2Login(String phone, String code) {
        String context = "【" + this.model + "】你正在登录，验证码:" + code + ",不要告诉任何人!";
        boolean success = false;

        try {
            success = this.sendVerifyCode(phone, context);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return success;
    }

    public boolean sendVerifyCode(String phoneNumber, String context) throws Exception {
        HashMap map = new HashMap();
        map.put("userid", this.userid);
        map.put("account", this.account);
        map.put("password", this.password);
        map.put("mobile", phoneNumber);
        map.put("content", context);
        map.put("action", "send");
        map.put("checkcontent", "0");
        map.put("taskName", "test_task");
        map.put("countnumber", "1");
        map.put("mobilenumber", "1");
        map.put("telephonenumber", "1");
        StringBuilder par = new StringBuilder();
        Iterator url = map.keySet().iterator();

        String parStr;
        while(url.hasNext()) {
            parStr = (String)url.next();
            par.append(parStr + "=" + (String)map.get(parStr) + "&");
        }

        par.deleteCharAt(par.length() - 1);
        parStr = par.toString();
        URL url1 = new URL("http://211.149.178.15:5555/sms.aspx");
        HttpURLConnection conn = (HttpURLConnection)url1.openConnection();
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(2000);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        conn.connect();
        OutputStream out = conn.getOutputStream();
        out.write(parStr.getBytes("utf-8"));
        out.close();
        InputStream in = conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
        StringBuilder builder = new StringBuilder();

        String line;
        while((line = reader.readLine()) != null) {
            builder.append(line);
        }

        reader.close();
        in.close();
        boolean success = ((String)WechatPayHelp.xmlToMap(builder.toString()).get("message")).equals("ok");
        if(!success) {
            logger.info("send verifycode faild,phone:" + phoneNumber + ",context:" + context);
            logger.info(builder.toString());
        }

        return success;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
