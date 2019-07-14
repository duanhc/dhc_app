//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.help;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.log4j.Logger;

import com.chanpay.demo.util.*;
import com.chanpay.demo.util.MD5;
import com.fusibang.tables.BankAuth;

public class ChanPayHelp {
    private static final Logger logger = Logger.getLogger(ChanPayHelp.class);
    private String charset = "UTF-8";
    private String PartnerId;
    private String SellerId;
    private String host;
    private String webName;
    private String TrxAmt;
    private static String MERCHANT_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDPq3oXX5aFeBQGf3Ag/86zNu0VICXmkof85r+DDL46w3vHcTnkEWVbp9DaDurcF7DMctzJngO0u9OG1cb4mn+Pn/uNC1fp7S4JH4xtwST6jFgHtXcTG9uewWFYWKw/8b3zf4fXyRuI/2ekeLSstftqnMQdenVP7XCxMuEnnmM1RwIDAQAB";
    private static String MERCHANT_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALMwFrHP3JbdA4+kW0CHMVOkZl/YIZcTgYIRwex+ALNWlJWkIO/ZtU1Sr9lAJVTam9t9XwhvYKXnLzGwrp9bUjf1w0e0DGDQ8Jtd2GkRoh6KW+3sd8UPmRG14KdScQq6Szwi7OpjfmPMhhURg6/Siopah62tmEr4xHW6WCLtADTtAgMBAAECgYAj7TxfomGEDgQsxLNXGMQXFRtBlEAH3m1U+uJ63DifvHhGGIZSEMB9JFsFoArcHIirVVO8iThDizq3J8mbQeAV6i7uT2Yk6wu8D/95GxvOTpMtTiLuEaf4gQkamKadg376DAhF2v6mK5rB1BiuC6fJxK04zr+5dntOD1iOStMegQJBAOQAU4SuP7hHA6LIxPQPpOY39EeVVurw2X3xVXlfcNjv0TfkkF6AQPixUtGSTXihbLKmLaFBLBwnZUc3m2uHMUkCQQDJMTcU3NZWmqXDaJ2Y1vKyrz0HJk2ijV6eTbM56YC+5wnCOuQ0IWpmtgbFxxq6HlB1AyMUC4Y7XIcV3zEr4MqFAkAxb6isQiPqzusI5sNdr+6vRD6hLs5Tzrk5x5Qi5wDO6ODSX6q18tUjlz8/1ZsaptWsvr9yEXGBAUHCeVonrH6RAkBVzDMdJoARNqnyBy8N+rxNDwYLgn4hEACFqcUAsV9CCezTc6HVyVpCquWAekQfksXk4I2ZAsEfl3nUDfY+LAKxAkEAkw3XJhsG6vCPeI7QXHf4YNGkCJ9UmkdV2HcUTCegBBX2nFDspKPALLRjlm4joWcGpZw2q8auZSc2LH1Z7BvCXA==";

    public ChanPayHelp() {
    }

    public String auth(String MerUserId, String trxId) {
        HashMap origMap = new HashMap();
        Map origMap1 = this.setCommonMap(origMap);
        origMap1.put("Service", "nmg_page_api_auth_req");
        origMap1.put("TrxId", trxId);
        origMap1.put("ExpiredTime", "40m");
        origMap1.put("MerUserId", MerUserId);
        origMap1.put("PayVersion", "0");
        origMap1.put("NotifyUrl", "http://" + this.host + "/" + this.webName + "/bank_auth_notify.do");
        if(trxId.contains("x")) {
            logger.debug("auth from ios");
            origMap1.put("ReturnUrl", "http://" + this.host + "/" + this.webName + "/pro/test.html");
        } else {
            origMap1.put("ReturnUrl", "http://" + this.host + "/" + this.webName + "/static/html/complete.html");
        }

        String url = this.gatewayPost(origMap1, this.charset, MERCHANT_PRIVATE_KEY);
        logger.debug("auth,TrxId:" + trxId + ",MerUserId:" + MerUserId);
        if(trxId.contains("x")) {
            url = URLEncoder.encode(url);
        }

        return url;
    }

    public String authPay(BankAuth bank, String trxId) {
        HashMap origMap = new HashMap();
        Map origMap1 = this.setCommonMap(origMap);
        origMap1.put("Service", "nmg_nquick_onekeypay");
        origMap1.put("TrxId", trxId);
        origMap1.put("OrdrName", "数据分析");
        origMap1.put("OrdrDesc", "[{\'商品型号\':\'D007\',\'商品性能\':\'Test\'}]");
        origMap1.put("MerUserId", bank.getMerUserId());
        origMap1.put("PayVersion", "0");
        origMap1.put("SellerId", this.SellerId);
        origMap1.put("CardBegin", bank.getCard_begin());
        origMap1.put("CardEnd", bank.getCard_end());
        origMap1.put("ExpiredTime", "40m");
        origMap1.put("TradeType", "11");
        origMap1.put("TrxAmt", this.TrxAmt);
        origMap1.put("AccessChannel", "wap");
        origMap1.put("NotifyUrl", "http://" + this.host + "/" + this.webName + "/chan_pay_notify.do");
        if(trxId.contains("x")) {
            logger.debug("pay from ios");
            origMap1.put("ReturnUrl", "http://" + this.host + "/" + this.webName + "/pro/user_collect_view.do");
        } else {
            origMap1.put("ReturnUrl", "http://" + this.host + "/" + this.webName + "/static/html/complete.html");
        }

        origMap1.put("Extension", "");
        String url = this.gatewayPost(origMap1, this.charset, MERCHANT_PRIVATE_KEY);
        if(trxId.contains("x")) {
            url = URLEncoder.encode(url);
        }

        return url;
    }

    public static String createLinkString(Map<String, String> params, boolean encode) {
        params = paraFilter(params);
        ArrayList keys = new ArrayList(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        String charset = (String)params.get("InputCharset");

        for(int i = 0; i < keys.size(); ++i) {
            String key = (String)keys.get(i);
            String value = (String)params.get(key);
            if(encode) {
                try {
                    value = URLEncoder.encode(value, charset);
                } catch (UnsupportedEncodingException var9) {
                    var9.printStackTrace();
                }
            }

            if(i == keys.size() - 1) {
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

    public static String buildRequestByRSA(Map<String, String> sPara, String privateKey, String inputCharset) throws Exception {
        String prestr = createLinkString(sPara, false);
        String mysign = "";
        mysign = RSA.sign(prestr, privateKey, inputCharset);
        return mysign;
    }

    public static String buildRequestByMD5(Map<String, String> sPara, String key, String inputCharset) throws Exception {
        String prestr = createLinkString(sPara, false);
        String mysign = "";
        mysign = MD5.sign(prestr, key, inputCharset);
        return mysign;
    }

    private String encrypt(String src, String publicKey, String charset) {
        try {
            byte[] e = RSA.encryptByPublicKey(src.getBytes(charset), publicKey);
            return Base64.encodeBase64String(e);
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public Map<String, String> setCommonMap(Map<String, String> origMap) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String dateStr = sdf.format(new Date());
        origMap.put("Version", "1.0");
        origMap.put("PartnerId", this.PartnerId);
        origMap.put("InputCharset", this.charset);
        origMap.put("TradeDate", dateStr.split("-")[0]);
        origMap.put("TradeTime", dateStr.split("-")[1]);
        origMap.put("Memo", (String) null);
        return origMap;
    }

    public static Map<String, String> createLinkRequestParas(Map<String, String> params) {
        HashMap encodeParamsValueMap = new HashMap();
        ArrayList keys = new ArrayList(params.keySet());
        String charset = (String)params.get("InputCharset");

        for(int i = 0; i < keys.size(); ++i) {
            String key = (String)keys.get(i);

            try {
                String value = URLEncoder.encode((String)params.get(key), charset);
                encodeParamsValueMap.put(key, value);
            } catch (UnsupportedEncodingException var8) {
                var8.printStackTrace();
            }
        }

        return encodeParamsValueMap;
    }

    public static Map<String, String> paraFilter(Map<String, String> sArray) {
        HashMap result = new HashMap();
        if(sArray != null && sArray.size() > 0) {
            Iterator var3 = sArray.keySet().iterator();

            while(var3.hasNext()) {
                String key = (String)var3.next();
                String value = (String)sArray.get(key);
                if(value != null && !value.equals("") && !key.equalsIgnoreCase("Sign") && !key.equalsIgnoreCase("SignType") && !key.equalsIgnoreCase("sign_type")) {
                    result.put(key, value);
                }
            }

            return result;
        } else {
            return result;
        }
    }

    public static Map<String, String> buildRequestPara(Map<String, String> sParaTemp, String signType, String key, String inputCharset) throws Exception {
        Map sPara = paraFilter(sParaTemp);
        String mysign = "";
        if("MD5".equalsIgnoreCase(signType)) {
            mysign = buildRequestByMD5(sPara, key, inputCharset);
        } else if("RSA".equalsIgnoreCase(signType)) {
            mysign = buildRequestByRSA(sPara, key, inputCharset);
        }

        sPara.put("Sign", mysign);
        sPara.put("SignType", signType);
        return sPara;
    }

    public static String buildRequest(Map<String, String> sParaTemp, String signType, String key, String inputCharset, String gatewayUrl) throws Exception {
        Map sPara = buildRequestPara(sParaTemp, signType, key, inputCharset);
        HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();
        HttpRequest request = new HttpRequest(HttpResultType.BYTES);
        request.setCharset(inputCharset);
        request.setMethod("POST");
        request.setParameters(generatNameValuePair(createLinkRequestParas(sPara), inputCharset));
        request.setUrl(gatewayUrl);
        String strResult = gatewayUrl + httpProtocolHandler.toString(generatNameValuePair(createLinkRequestParas(sPara), inputCharset));
        return strResult;
    }

    private static NameValuePair[] generatNameValuePair(Map<String, String> properties, String charset) throws Exception {
        NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
        int i = 0;

        Entry entry;
        for(Iterator var5 = properties.entrySet().iterator(); var5.hasNext(); nameValuePair[i++] = new NameValuePair((String)entry.getKey(), (String)entry.getValue())) {
            entry = (Entry)var5.next();
        }

        return nameValuePair;
    }

    public String gatewayPost(Map<String, String> origMap, String charset, String MERCHANT_PRIVATE_KEY) {
        String result = "";

        try {
            String e = "https://pay.chanpay.com/mag-unify/gateway/receiveOrder.do?";
            buildRequestPara(origMap, "RSA", MERCHANT_PRIVATE_KEY, charset);
            result = buildRequest(origMap, "RSA", MERCHANT_PRIVATE_KEY, charset, e);
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return result;
    }

    public void setPartnerId(String partnerId) {
        this.PartnerId = partnerId;
    }

    public void setSellerId(String sellerId) {
        this.SellerId = sellerId;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }

    public void setTrxAmt(String trxAmt) {
        this.TrxAmt = trxAmt;
    }

    public static void setMERCHANT_PRIVATE_KEY(String mERCHANT_PRIVATE_KEY) {
    }

    public static void setMERCHANT_PUBLIC_KEY(String mERCHANT_PUBLIC_KEY) {
    }
}
