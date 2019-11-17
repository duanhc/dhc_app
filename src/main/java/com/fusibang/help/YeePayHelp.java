//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.help;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fusibang.dao.IdentifyDao;
import com.fusibang.tables.Channel;
import com.fusibang.tables.Identify;
import com.yeepay.g3.sdk.yop.client.YopRequest;
import com.yeepay.g3.sdk.yop.client.YopResponse;
import com.yeepay.g3.sdk.yop.client.YopRsaClient;
import com.yeepay.g3.sdk.yop.encrypt.DigitalEnvelopeDTO;
import com.yeepay.g3.sdk.yop.utils.DigitalEnvelopeUtils;
import com.yeepay.g3.sdk.yop.utils.RSAKeyUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 易宝支付
 */
public class YeePayHelp {
    private static Logger logger = Logger.getLogger(YeePayHelp.class);

    /**
     * 商编
     */
    private String merchantno;

    private String appKey;

    private String publickey;

    private String privatekey;

    /**
     * 统一首次支付请求
     */
    private String unionfirstpayUri;

    /**
     * 首次支付短验确认URL
     */
    private String firstpaysmsconfUri;

    /**
     * 代扣金额
     */
    private String amount;

    /**
     * 首次支付短验重发URL
     */
    private String firstpaysmsresendUri;

    private DingDingHelp dingDingHelp;

    private String hostName;

    private String webName;

    private IdentifyDao identifyDao;

    public YeePayHelp() {}

    /**
     * 首次支付请求
     */
    public String unionFirstPay(Map<String, String> map) {
        map.put("merchantno", merchantno);
        map.put("amount", amount);
        map.put("callbackurl", "http://" + this.hostName + "/" + this.webName + "/yeepay_notify.do");

        Map<String, String> yopresponsemap = new HashMap<>();
        try {
            yopresponsemap = yeepayYOP(map, unionfirstpayUri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return yopresponsemap == null ? null : JSONObject.toJSONString(yopresponsemap);
    }

    /**
     * 首次支付短验确认
     */
    public String firstPaySmsConfirm(Map<String, String> map) {
        map.put("merchantno", merchantno);
        Map<String, String> yopresponsemap = new HashMap<>();
        try {
            yopresponsemap = yeepayYOP(map, firstpaysmsconfUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return yopresponsemap == null ? null : JSONObject.toJSONString(yopresponsemap);
    }

    //@formatter:off
    /**
     * 首次支付短验重发
     * 
     * 在首次支付过程中，如果商户没有收到短验，调用本接口重发短验
     *
     * 1.短验码格式： 6 位随机数字
     * 2.短验码有效期： 30 分钟
     * 3.短验码验证错误次数限制： 在有效期内允许试错 3 次， 3 次之后失效
     * 4.同一订单号的同一手机号限制： 在有效期内最多能发送 5 次相同的短验码
     * 5.发送最短间隔限制： 大于 50 秒
     */
    // @formatter:on
    public String firstPaySmsReSend(Map<String, String> map) {
        map.put("merchantno", merchantno);
        // map.put("requestno", "201911120810hellosuxianghua");
        // map.put("advicesmstype", advicesmstype);

        Map<String, String> yopresponsemap = new HashMap<>();
        try {
            yopresponsemap = yeepayYOP(map, firstpaysmsresendUri);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return yopresponsemap == null ? null : JSONObject.toJSONString(yopresponsemap);

    }

    /**
     * 支付回调
     * 
     * @param request
     * @return
     */
    public String payNotify(HttpServletRequest request) {
        logger.info("payNotify success");
        try {
            /*根据string格式的密钥得到密钥对象*/
            PrivateKey myPrivateKey = RSAKeyUtils.string2PrivateKey(this.privatekey);
            PublicKey yopPublicKey = RSAKeyUtils.string2PublicKey(this.publickey);
            // 此通知对应的appKey，多appKey商户可能会用到
            String appKey = request.getParameter("customerIdentification");
            String content = request.getParameter("response");

            logger.info("appKey:{" + appKey + "}, content:{" + content + "}");

            DigitalEnvelopeDTO dto = new DigitalEnvelopeDTO();
            dto.setCipherText(content);
            dto = DigitalEnvelopeUtils.decrypt(dto, myPrivateKey, yopPublicKey);
            String plainText = dto.getPlainText();
            logger.info("payNotify plain content:" + plainText);

            JSONObject jsonObject = JSONObject.parseObject(plainText);
            String status = (String)jsonObject.get("status");
            if ("PAY_SUCCESS".equals(status)) {
                String amount = (String)jsonObject.get("amount");
                String requestno = (String)jsonObject.get("requestno");
                if (requestno != null && requestno != "") {
                    Identify identify = this.identifyDao.findByRequestno(requestno);
                    Channel channel = identify.getUser().getChannel();
                    channel.setToday_income(channel.getToday_income() + new Float(amount).floatValue());
                    channel.setAll_income(channel.getAll_income() + new Float(amount).floatValue());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "SUCCESS";
    }

    /**
     * 请求易宝支付接口
     * 
     * @param map
     * @param uri
     * @return
     * @throws IOException
     */
    public Map<String, String> yeepayYOP(Map<String, String> map, String uri) throws IOException {

        YopRequest yoprequest = new YopRequest("SQKK" + merchantno, privatekey);
        Map<String, String> result = new HashMap<>();

        Set<Map.Entry<String, String>> entry = map.entrySet();
        for (Map.Entry<String, String> s : entry) {
            yoprequest.addParam(s.getKey(), s.getValue());
        }
        logger.info("yoprequest:{}" + yoprequest.getParams());

        // 向YOP发请求
        YopResponse yopresponse = YopRsaClient.post(uri, yoprequest);
        logger.info("request YOP after:{}" + yopresponse.toString());
        logger.info("request YOP after:{}" + yopresponse.getStringResult());

        // 对结果进行处理
        if ("FAILURE".equals(yopresponse.getState())) {
            if (yopresponse.getError() != null) {
                result.put("errorcode", yopresponse.getError().getCode());
            }
            result.put("errormsg", yopresponse.getError().getMessage());
            logger.info("error detail：{}" + yopresponse.getError());
            logger.info("error result：{}" + result);
            return result;
        }
        // 成功则进行相关处理
        if (yopresponse.getStringResult() != null) {
            result = parseResponse(yopresponse.getStringResult());
            logger.info("yopresponse.getStringResult: " + result);

        }

        return result;
    }

    /**
     * 将获取到的yopresponse转换成json格式
     * 
     * @param yopresponse
     * @return
     */
    public Map<String, String> parseResponse(String yopresponse) {

        Map<String, String> jsonMap = new HashMap<>();
        jsonMap = JSON.parseObject(yopresponse, new TypeReference<TreeMap<String, String>>() {});
        logger.info("yopresponse to map: " + jsonMap);
        return jsonMap;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }

    public void setMerchantno(String merchantno) {
        this.merchantno = merchantno;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public void setPublickey(String publickey) {
        this.publickey = publickey;
    }

    public void setPrivatekey(String privatekey) {
        this.privatekey = privatekey;
    }

    public void setDingDingHelp(DingDingHelp dingDingHelp) {
        this.dingDingHelp = dingDingHelp;
    }

    public void setUnionfirstpayUri(String unionfirstpayUri) {
        this.unionfirstpayUri = unionfirstpayUri;
    }

    public void setFirstpaysmsconfUri(String firstpaysmsconfUri) {
        this.firstpaysmsconfUri = firstpaysmsconfUri;
    }

    public void setFirstpaysmsresendUri(String firstpaysmsresendUri) {
        this.firstpaysmsresendUri = firstpaysmsresendUri;
    }

    public void setIdentifyDao(IdentifyDao identifyDao) {
        this.identifyDao = identifyDao;
    }
}
