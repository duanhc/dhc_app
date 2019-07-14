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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.fusibang.tables.WeiXin;

public class AccessToken {
    private String token = null;
    private Date tokenDate = null;
    private int expires = 7200;
    private String addr;
    private static Map<String, AccessToken> map = new HashMap();

    public static String getToken(WeiXin weixin) {
        AccessToken accessToken = (AccessToken)map.get(weixin.getAppid());
        if(accessToken == null) {
            accessToken = new AccessToken(weixin);
            map.put(weixin.getAppid(), accessToken);
        }

        accessToken.flushToken();
        return accessToken.getToken();
    }

    protected AccessToken(WeiXin weixin) {
        this.addr = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + weixin.getAppid() + "&secret=" + weixin.getAppset();
    }

    private void flushToken() {
        Date now = new Date();
        if(this.token == null || this.tokenDate == null || now.getTime() - this.tokenDate.getTime() > (long)(this.expires * 1000)) {
            this.requestToken();
        }

    }

    private void requestToken() {
        try {
            URL e = new URL(this.addr);
            HttpURLConnection conn = (HttpURLConnection)e.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.connect();
            InputStream in = conn.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bf = new BufferedReader(reader);
            String str = null;
            StringBuffer buffer = new StringBuffer();

            while((str = bf.readLine()) != null) {
                buffer.append(str);
            }

            bf.close();
            reader.close();
            in.close();
            conn.disconnect();
            Map response = (Map)JSONObject.parse(buffer.toString());
            this.tokenDate = new Date();
            this.token = (String)response.get("access_token");
            this.expires = ((Integer)response.get("expires_in")).intValue();
        } catch (Exception var9) {
            var9.printStackTrace();
        }

    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTokenDate() {
        return this.tokenDate;
    }

    public void setTokenDate(Date tokenDate) {
        this.tokenDate = tokenDate;
    }

    public int getExpires() {
        return this.expires;
    }

    public void setExpires(int expires) {
        this.expires = expires;
    }
}
