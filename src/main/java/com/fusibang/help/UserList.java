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
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.fusibang.tables.WeiXin;

public class UserList {
    private static final String URL = "https://api.weixin.qq.com/cgi-bin/user/get";
    private boolean hasNext = true;
    private String nextOpenid = null;
    private String token = null;

    public UserList(WeiXin weixin) throws Exception {
        this.token = AccessToken.getToken(weixin);
    }

    private static String requestURL(String reqUrl) {
        String response = null;

        try {
            URL e = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection)e.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.connect();
            InputStream in = conn.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bf = new BufferedReader(reader);
            StringBuffer buffer = new StringBuffer();
            String line = null;

            while((line = bf.readLine()) != null) {
                buffer.append(line);
            }

            bf.close();
            reader.close();
            in.close();
            conn.disconnect();
            response = buffer.toString();
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        return response;
    }

    public List getNextUsers() throws Exception {
        List userList = null;
        if(this.hasNext) {
            String reqUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + this.token + "&next_openid=" + this.nextOpenid;
            if(this.nextOpenid == null) {
                reqUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + this.token;
            }

            String response = requestURL(reqUrl);
            Map map = (Map)JSONObject.parse(response);
            this.nextOpenid = (String)map.get("next_openid");
            this.hasNext = this.nextOpenid != null && !this.nextOpenid.equals("");
            int count = ((Integer)map.get("count")).intValue();
            if(count != 0) {
                userList = (List)((Map)map.get("data")).get("openid");
            }

            return userList;
        } else {
            throw new Exception("no more user");
        }
    }

    public boolean hasNext() {
        return this.hasNext;
    }
}
