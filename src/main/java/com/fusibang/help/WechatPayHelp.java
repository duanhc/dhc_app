//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.help;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.fusibang.tables.Pay;

public class WechatPayHelp {
    private static final Logger logger = Logger.getLogger(WechatPayHelp.class);
    private String appid;
    private String key;
    private String mch_id;
    private String serverIp;
    private String hostName;
    private String webName;
    private PayHelp payHelp;

    public WechatPayHelp() {
    }

    public static String MD5(String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        byte[] var7 = array;
        int var6 = array.length;

        for(int var5 = 0; var5 < var6; ++var5) {
            byte item = var7[var5];
            sb.append(Integer.toHexString(item & 255 | 256).substring(1, 3));
        }

        return sb.toString().toUpperCase();
    }

    public static Map<String, String> xmlToMap(String xml) throws Exception {
        HashMap map = new HashMap();
        Document doc = null;

        try {
            doc = DocumentHelper.parseText(xml);
        } catch (DocumentException var6) {
            var6.printStackTrace();
        }

        if(doc == null) {
            return map;
        } else {
            Element root = doc.getRootElement();
            Iterator iterator = root.elementIterator();

            while(iterator.hasNext()) {
                Element e = (Element)iterator.next();
                map.put(e.getName(), e.getText());
            }

            return map;
        }
    }

    public static String HMACSHA256(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        byte[] var9 = array;
        int var8 = array.length;

        for(int var7 = 0; var7 < var8; ++var7) {
            byte item = var9[var7];
            sb.append(Integer.toHexString(item & 255 | 256).substring(1, 3));
        }

        return sb.toString().toUpperCase();
    }

    public static String generateSignature(Map<String, String> data, String key, String signType) throws Exception {
        Set keySet = data.keySet();
        String[] keyArray = (String[])keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        String[] var9 = keyArray;
        int var8 = keyArray.length;

        for(int var7 = 0; var7 < var8; ++var7) {
            String m = var9[var7];
            if(!m.equals("sign") && ((String)data.get(m)).trim().length() > 0) {
                sb.append(m).append("=").append(((String)data.get(m)).trim()).append("&");
            }
        }

        sb.append("key=").append(key);
        if(signType.equals("MD5")) {
            MD5 var10 = new MD5();
            return var10.getMD5ofStr(sb.toString());
        } else if(WechatPayHelp.SignType.HMACSHA256.equals(signType)) {
            return HMACSHA256(sb.toString(), key);
        } else {
            throw new Exception(String.format("Invalid sign_type: %s", new Object[]{signType}));
        }
    }

    public static String mapToXml(Map<String, String> data) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        org.w3c.dom.Document document = documentBuilder.newDocument();
        org.w3c.dom.Element root = document.createElement("xml");
        document.appendChild(root);
        Iterator transformer = data.keySet().iterator();

        while(transformer.hasNext()) {
            String tf = (String)transformer.next();
            String source = (String)data.get(tf);
            if(source == null) {
                source = "";
            }

            source = source.trim();
            org.w3c.dom.Element writer = document.createElement(tf);
            writer.appendChild(document.createTextNode(source));
            root.appendChild(writer);
        }

        TransformerFactory tf1 = TransformerFactory.newInstance();
        Transformer transformer1 = tf1.newTransformer();
        DOMSource source1 = new DOMSource(document);
        transformer1.setOutputProperty("encoding", "UTF-8");
        transformer1.setOutputProperty("indent", "yes");
        StringWriter writer1 = new StringWriter();
        StreamResult result = new StreamResult(writer1);
        transformer1.transform(source1, result);
        String output = writer1.getBuffer().toString();

        try {
            writer1.close();
        } catch (Exception var12) {
            ;
        }

        return output;
    }

    public Map<String, String> placeOrder(String openid, String totalFree, Integer userId) {
        logger.debug("placeOrder{openid=" + openid + ",totalFree=" + totalFree + ",userId=" + userId + "}");
        HashMap retMap = new HashMap();
        String out_trade_no;
        synchronized(this) {
            out_trade_no = "w" + String.valueOf(System.currentTimeMillis());
        }

        HashMap data = new HashMap();
        data.put("appid", this.appid);
        data.put("mch_id", this.mch_id);
        data.put("device_info", "WEB");
        data.put("nonce_str", "b1089cb0231011e7b7e1484520356fdc");
        data.put("out_trade_no", out_trade_no);
        data.put("body", "数据分析");
        data.put("openid", openid);
        data.put("total_fee", totalFree);
        data.put("spbill_create_ip", this.serverIp);
        data.put("notify_url", "http://" + this.hostName + "/" + this.webName + "/wechat_pay_notify.do");
        data.put("trade_type", "JSAPI");
        String sign = null;

        try {
            sign = generateSignature(data, this.key, "MD5");
        } catch (Exception var22) {
            var22.printStackTrace();
        }

        data.put("sign", sign);
        String xmlData = null;

        try {
            xmlData = mapToXml(data);
            logger.debug("order info:" + xmlData);
        } catch (Exception var21) {
            var21.printStackTrace();
        }

        try {
            StringBuffer e = new StringBuffer();
            URL url = new URL("https://api.mch.weixin.qq.com/pay/unifiedorder");
            HttpURLConnection httpUrlConn = (HttpURLConnection)url.openConnection();
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            httpUrlConn.setRequestMethod("POST");
            httpUrlConn.connect();
            OutputStream out = httpUrlConn.getOutputStream();
            PrintWriter printer = new PrintWriter(out);
            printer.print(xmlData);
            printer.flush();
            printer.close();
            out.close();
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;

            while((str = bufferedReader.readLine()) != null) {
                e.append(str);
            }

            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            String result = e.toString();
            logger.debug("order result:" + result);
            Map resultMap = xmlToMap(result);
            if(((String)resultMap.get("return_code")).equals("SUCCESS") && ((String)resultMap.get("result_code")).equals("SUCCESS")) {
                logger.debug("order " + (String)resultMap.get("prepay_id") + " success");
                retMap.put("appId", this.appid);
                retMap.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000L));
                retMap.put("nonceStr", "b1089cb0231011e7b7e1484520356fdc");
                retMap.put("package", "prepay_id=" + (String)resultMap.get("prepay_id"));
                retMap.put("signType", "MD5");
                retMap.put("paySign", generateSignature(retMap, this.key, "MD5"));
                Pay pay = new Pay();
                pay.setIndent_str(out_trade_no);
                this.payHelp.insertPay(pay, userId.intValue());
            }
        } catch (Exception var24) {
            var24.printStackTrace();
        }

        return retMap;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }

    public void setPayHelp(PayHelp payHelp) {
        this.payHelp = payHelp;
    }

    public static enum SignType {
        MD5,
        HMACSHA256;

        private SignType() {
        }
    }
}
