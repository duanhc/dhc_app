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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

public class News {
    private static final Logger logger = Logger.getLogger(News.class);
    private static final String URL = "https://api.weixin.qq.com/cgi-bin/message/template/send";
    private static final String METHOD = "POST";

    public News() {
    }

    private static boolean request(String json, String token) {
        boolean success = false;
        String reqUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + token;
        HttpURLConnection conn = null;

        try {
            URL e = new URL(reqUrl);
            conn = (HttpURLConnection)e.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.connect();
            OutputStream out = conn.getOutputStream();
            out.write(json.getBytes("UTF-8"));
            out.flush();
            out.close();
            InputStream in = conn.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bf = new BufferedReader(reader);
            String line = null;
            StringBuffer buffer = new StringBuffer();

            while((line = bf.readLine()) != null) {
                buffer.append(line);
            }

            String response = buffer.toString();
            logger.debug("send news result:" + response);
            Map rsMap = (Map)JSONObject.parse(response);
            success = ((Integer)rsMap.get("errcode")).intValue() == 0;
            bf.close();
            reader.close();
            in.close();
        } catch (Exception var17) {
            var17.printStackTrace();
        } finally {
            if(conn != null) {
                conn.disconnect();
            }

        }

        return success;
    }

    public static boolean sendNews(String token, String openid, String template, String name, String url, String edu, String qixian) {
        String date = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
        String remark = "筹集成功通知";
        String json = "{\"touser\":\"" + openid + "\",\"template_id\":\"" + template + "\",\"url\":\"" + url + "\",\"data\":{\"first\": {\"value\":\"零抵押零门槛秒借5000元！\",\"color\":\"#AD2D47\"},\"keyword1\":{\"value\":\"" + name + "\",\"color\":\"#BC7D6B\"},\"keyword2\": {\"value\":\"" + date + "\",\"color\":\"#173177\"},\"keyword3\": {\"value\":\"" + edu + "\",\"color\":\"#173177\"},\"keyword4\": {\"value\":\"" + qixian + "\",\"color\":\"#173177\"},\"remark\":{\"value\":\"" + remark + "\",\"color\":\"#173177\"}}}";
        logger.debug("send news json:" + json);
        return request(json, token);
    }
}
