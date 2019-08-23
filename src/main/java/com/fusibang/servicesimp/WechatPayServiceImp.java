//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.servicesimp;

import com.alibaba.fastjson.JSONObject;
import com.fusibang.dao.UserDao;
import com.fusibang.help.PayHelp;
import com.fusibang.help.WechatPayHelp;
import com.fusibang.services.WechatPayService;
import com.fusibang.tables.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class WechatPayServiceImp implements WechatPayService {
    private static final Logger logger = Logger.getLogger(WechatPayServiceImp.class);
    private String appid;
    private String secret;
    private String mch_id;
    private String totalFree;
    private UserDao userDao;
    private WechatPayHelp wechatPayHelp;
    private PayHelp payHelp;
    private String appid2;
    private String secret2;
    private String hostName;
    private String webName;

    public WechatPayServiceImp() {}

    public String placeOrder(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer id = (Integer)session.getAttribute("ui");
        User user = null;
        if (id != null && (user = this.userDao.findById(id.intValue())) != null) {
            String state = request.getParameter("state");
            String code = request.getParameter("code");

            if (null == code || "".equals(code)) {
                logger.debug("------------- getCode ----------------");
                String addr = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + this.appid + "&redirect_uri=http%3a%2f%2f" + this.hostName + "%2f" + this.webName
                    + "%2fwechat_pay.action&response_type=code&scope=snsapi_base&state=zhifu#wechat_redirect";
                try {
                    URL e = new URL(addr);
                    HttpURLConnection httpUrlConn = (HttpURLConnection)e.openConnection();
                    httpUrlConn.setDoOutput(true);
                    httpUrlConn.setDoInput(true);
                    httpUrlConn.setUseCaches(false);
                    httpUrlConn.setRequestMethod("GET");
                    httpUrlConn.connect();
                    InputStream inputStream = httpUrlConn.getInputStream();
                    inputStream.close();
                    httpUrlConn.disconnect();
                    return "success2";
                } catch (Exception var19) {
                    var19.printStackTrace();
                    return "error";
                }
            } else {
                logger.debug("getOpenid request state=" + state + "&code=" + code);
                StringBuffer buffer = new StringBuffer();
                String addr = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + this.appid + "&secret=" + this.secret + "&code=" + code + "&grant_type=authorization_code";
                if ("auth".equals(state)) {
                    addr = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + this.appid2 + "&secret=" + this.secret2 + "&code=" + code + "&grant_type=authorization_code";
                }

                try {
                    URL e = new URL(addr);
                    HttpURLConnection httpUrlConn = (HttpURLConnection)e.openConnection();
                    httpUrlConn.setDoOutput(true);
                    httpUrlConn.setDoInput(true);
                    httpUrlConn.setUseCaches(false);
                    httpUrlConn.setRequestMethod("GET");
                    httpUrlConn.connect();
                    InputStream inputStream = httpUrlConn.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String str = null;

                    while ((str = bufferedReader.readLine()) != null) {
                        buffer.append(str);
                    }

                    bufferedReader.close();
                    inputStreamReader.close();
                    inputStream.close();
                    inputStream = null;
                    httpUrlConn.disconnect();
                    String result = buffer.toString();
                    logger.debug("getopenid result:" + result);
                    JSONObject json = JSONObject.parseObject(result);
                    String openid = json.getString("openid");
                    if ("zhifu".equals(state)) {
                        Map order = this.wechatPayHelp.placeOrder(openid, this.totalFree, Integer.valueOf(user.getId()));
                        request.setAttribute("order", order);
                        return "success";
                    } else if ("auth".equals(state)) {
                        user.setOpenid(openid);
                        return "success2";
                    } else {
                        return "illegal_request";
                    }
                } catch (Exception var19) {
                    var19.printStackTrace();
                    return "error";
                }
            }

        } else {
            return "un_login";
        }
    }

    public String resultNotify(HttpServletRequest request) {
        logger.debug("recept wechat pay notification");
        Map map = null;

        try {
            ServletInputStream outer_trade_no = request.getInputStream();
            short amount_str = 1024;
            if (outer_trade_no != null) {
                ByteArrayOutputStream inner_trade_no = new ByteArrayOutputStream();
                byte[] amount = new byte[amount_str];
                boolean count = true;

                int count1;
                while ((count1 = outer_trade_no.read(amount, 0, amount_str)) != -1) {
                    inner_trade_no.write(amount, 0, count1);
                }

                amount = (byte[])null;
                inner_trade_no.flush();
                String result = new String(inner_trade_no.toByteArray(), "UTF-8");
                logger.debug("wechat pay notification content:" + result);
                map = WechatPayHelp.xmlToMap(result);
            }
        } catch (Exception var9) {
            var9.printStackTrace();
            return "error";
        }

        if (((String)map.get("return_code")).equals("SUCCESS") && ((String)map.get("appid")).equals(this.appid) && ((String)map.get("mch_id")).equals(this.mch_id)) {
            logger.debug("wechatpay notification " + (String)map.get("out_trade_no") + " signature success");
            String outer_trade_no1 = (String)map.get("out_trade_no");
            String amount_str1 = (String)map.get("total_fee");
            String inner_trade_no1 = (String)map.get("transaction_id");
            Float amount1 = Float.valueOf((new Float(amount_str1)).floatValue() / 100.0F);
            if (this.payHelp.updatePay(outer_trade_no1, inner_trade_no1, amount1.floatValue()).equals("success")) {
                return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
            }
        }

        return "error";
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setWechatPayHelp(WechatPayHelp wechatPayHelp) {
        this.wechatPayHelp = wechatPayHelp;
    }

    public void setTotalFree(String totalFree) {
        this.totalFree = totalFree;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public void setPayHelp(PayHelp payHelp) {
        this.payHelp = payHelp;
    }

    public void setAppid2(String appid2) {
        this.appid2 = appid2;
    }

    public void setSecret2(String secret2) {
        this.secret2 = secret2;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }
}
