package com.fusibang.help;

import java.util.Arrays;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;

/**
 * @description: 钉钉消息推送
 * @author: Alex
 * @date: 2019-09-09 00:47
 */
public class DingDingHelp {

    private String appid;
    private String key;
    private String mch_id;
    private String totalFree;
    private String serverIp;
    private String hostName;
    private String webName;

    public void send(String phone) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=d9daedec39c9c884e209480032ea09188d9eb39c2065d10383ac1ac09b387394");
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent(phone + " pay success! totalFree:" + totalFree + ",appid:" + appid + ",mchId:" + mch_id + ",hostName:" + hostName + ",serverIP:" + serverIp + ",webName:" + webName);
        request.setText(text);

        try {
            OapiRobotSendResponse response = client.execute(request);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    public static void test() {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=d9daedec39c9c884e209480032ea09188d9eb39c2065d10383ac1ac09b387394");
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent("测试文本消息");
        request.setText(text);
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setAtMobiles(Arrays.asList("13261303345"));
        request.setAt(at);

        request.setMsgtype("link");
        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
        link.setMessageUrl("https://www.dingtalk.com/");
        link.setPicUrl("");
        link.setTitle("时代的火车向前开");
        link.setText("这个即将发布的新版本，创始人陈航（花名“无招”）称它为“红树林”。\n" + "而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是“红树林");
        request.setLink(link);

        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("杭州天气");
        markdown.setText("#### 杭州天气 @156xxxx8827\n" + "> 9度，西北风1级，空气良89，相对温度73%\n\n" + "> ![screenshot](https://gw.alicdn.com/tfs/TB1ut3xxbsrBKNjSZFpXXcXhFXa-846-786.png)\n"
            + "> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n");
        request.setMarkdown(markdown);
        try {
            OapiRobotSendResponse response = client.execute(request);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        test();
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getTotalFree() {
        return totalFree;
    }

    public void setTotalFree(String totalFree) {
        this.totalFree = totalFree;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }
}