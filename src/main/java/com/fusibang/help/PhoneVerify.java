//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.help;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

public class PhoneVerify {
    private static final Logger logger = Logger.getLogger(PhoneVerify.class);
    private String accesskey;
    private String secret;
    private String sign;
    private String templateParamRegiste;
    private String templateParamModifyPwd;
    private String templateParamLogin;

    public PhoneVerify() {
    }

    public String getRandomVerifyCode(int size) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();

        for(int i = 0; i < size; ++i) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }

    public boolean send2Registe(String phone, String code, String model) {
        return this.sendVerifyCode(phone, code, this.templateParamRegiste);
    }

    public boolean send2ModifyPwd(String phone, String code, String model) {
        return this.sendVerifyCode(phone, code, this.templateParamModifyPwd);
    }

    public boolean send2Login(String phone, String code, String model) {
        return this.sendVerifyCode(phone, code, this.templateParamLogin);
    }

    public boolean sendVerifyCode(String phoneNumber, String code, String templateParam) {
        try {
            URL e = new URL("http://api.1cloudsp.com/api/v2/single_send");
            HttpURLConnection conn = (HttpURLConnection)e.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();
            String para = "accesskey=" + this.accesskey + "&secret=" + this.secret + "&sign=" + this.sign + "&templateId=" + templateParam + "&mobile=" + phoneNumber + "&content=" + code;
            conn.getOutputStream().write(para.getBytes("UTF-8"));
            InputStream in = conn.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bf = new BufferedReader(reader);
            String result = bf.readLine();
            JSONObject json = JSONObject.parseObject(result);
            String resultCode = json.getString("code");
            conn.disconnect();
            if(!resultCode.equals("0")) {
                logger.info("send verify code fail,phone:" + phoneNumber + ",code:" + code);
            }

            return resultCode.equals("0");
        } catch (Exception var13) {
            var13.printStackTrace();
            return false;
        }
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setTemplateParamRegiste(String templateParamRegiste) {
        this.templateParamRegiste = templateParamRegiste;
    }

    public void setTemplateParamModifyPwd(String templateParamModifyPwd) {
        this.templateParamModifyPwd = templateParamModifyPwd;
    }

    public void setTemplateParamLogin(String templateParamLogin) {
        this.templateParamLogin = templateParamLogin;
    }
}
