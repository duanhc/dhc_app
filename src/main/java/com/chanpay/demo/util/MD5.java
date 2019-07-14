//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.chanpay.demo.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5 {
    public MD5() {
    }

    public static String sign(String text, String key, String charset) throws Exception {
        text = text + key;
        return DigestUtils.md5Hex(getContentBytes(text, charset));
    }

    public static boolean verify(String text, String sign, String key, String charset) throws Exception {
        text = text + key;
        String mysign = DigestUtils.md5Hex(getContentBytes(text, charset));
        return mysign.equals(sign);
    }

    private static byte[] getContentBytes(String content, String charset) {
        if(charset != null && !"".equals(charset)) {
            try {
                return content.getBytes(charset);
            } catch (UnsupportedEncodingException var3) {
                throw new RuntimeException("签名过程中出现错�?,指定的编码集不对,您目前指定的编码集是:" + charset);
            }
        } else {
            return content.getBytes();
        }
    }
}
