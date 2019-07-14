//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.chanpay.demo.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.Map.Entry;

import org.apache.commons.httpclient.NameValuePair;

public class ChanPayUtil {
    public ChanPayUtil() {
    }

    public static void main(String[] args) {
        String rsa_private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAO/6rPCvyCC+IMalLzTy3cVBz/+wamCFNiq9qKEilEBDTttP7Rd/GAS51lsfCrsISbg5td/w25+wulDfuMbjjlW9Afh0p7Jscmbo1skqIOIUPYfVQEL687B0EmJufMlljfu52b2efVAyWZF9QBG1vx/AJz1EVyfskMaYVqPiTesZAgMBAAECgYEAtVnkk0bjoArOTg/KquLWQRlJDFrPKP3CP25wHsU4749t6kJuU5FSH1Ao81d0Dn9m5neGQCOOdRFi23cV9gdFKYMhwPE6+nTAloxI3vb8K9NNMe0zcFksva9c9bUaMGH2p40szMoOpO6TrSHO9Hx4GJ6UfsUUqkFFlN76XprwE+ECQQD9rXwfbr9GKh9QMNvnwo9xxyVl4kI88iq0X6G4qVXo1Tv6/DBDJNkX1mbXKFYL5NOW1waZzR+Z/XcKWAmUT8J9AkEA8i0WT/ieNsF3IuFvrIYG4WUadbUqObcYP4Y7Vt836zggRbu0qvYiqAv92Leruaq3ZN1khxp6gZKl/OJHXc5xzQJACqr1AU1i9cxnrLOhS8m+xoYdaH9vUajNavBqmJ1mY3g0IYXhcbFm/72gbYPgundQ/pLkUCt0HMGv89tn67i+8QJBALV6UgkVnsIbkkKCOyRGv2syT3S7kOv1J+eamGcOGSJcSdrXwZiHoArcCZrYcIhOxOWB/m47ymfE1Dw/+QjzxlUCQCmnGFUO9zN862mKYjEkjDN65n1IUB9Fmc1msHkIZAQaQknmxmCIOHC75u4W0PGRyVzq8KkxpNBq62ICl7xmsPM=";

        try {
            System.out.println(buildRequestByRSA((Map)null, rsa_private_key, "UTF-8"));
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public static String createLinkString(Map<String, String> params, boolean encode) {
        params = paraFilter(params);
        ArrayList keys = new ArrayList(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        String charset = (String)params.get("_input_charset");

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

    public static String lingString(Map<String, String> params, boolean encode) {
        params = paraFilter(params);
        ArrayList keys = new ArrayList(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        String charset = (String)params.get("_input_charset");

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

    public static String buildRequest(Map<String, String> sParaTemp, String signType, String key, String inputCharset, String gatewayUrl) throws Exception {
        Map sPara = buildRequestPara(sParaTemp, signType, key, inputCharset);
        HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();
        HttpRequest request = new HttpRequest(HttpResultType.BYTES);
        request.setCharset(inputCharset);
        request.setMethod("POST");
        request.setParameters(generatNameValuePair(sPara, inputCharset));
        request.setUrl(gatewayUrl);
        HttpResponse response = httpProtocolHandler.execute(request, (String)null, (String)null);
        if(response == null) {
            return null;
        } else {
            String strResult = response.getStringResult();
            return strResult;
        }
    }

    public static Map<String, String> paraFilter(Map<String, String> sArray) {
        HashMap result = new HashMap();
        if(sArray != null && sArray.size() > 0) {
            Iterator var3 = sArray.keySet().iterator();

            while(var3.hasNext()) {
                String key = (String)var3.next();
                String value = (String)sArray.get(key);
                if(value != null && !value.equals("") && !key.equalsIgnoreCase("sign") && !key.equalsIgnoreCase("sign_type")) {
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

        sPara.put("sign", mysign);
        sPara.put("sign_type", signType);
        return sPara;
    }

    public static String buildRequestByMD5(Map<String, String> sPara, String key, String inputCharset) throws Exception {
        String prestr = createLinkString(sPara, false);
        String mysign = MD5.sign(prestr, key, inputCharset);
        return mysign;
    }

    public static String buildRequestByRSA(Map<String, String> sPara, String privateKey, String inputCharset) throws Exception {
        String prestr = createLinkString(sPara, false);
        String mysign = RSA.sign(prestr, privateKey, inputCharset);
        System.out.println("buildRequestByRSA======加密前排序后串======" + prestr + "======加密后串======" + mysign);
        return mysign;
    }

    private static NameValuePair[] generatNameValuePair(Map<String, String> properties, String charset) throws Exception {
        NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
        int i = 0;

        Entry entry;
        for(Iterator var5 = properties.entrySet().iterator(); var5.hasNext(); nameValuePair[i++] = new NameValuePair((String)entry.getKey(), URLEncoder.encode((String)entry.getValue(), charset))) {
            entry = (Entry)var5.next();
        }

        return nameValuePair;
    }
}
