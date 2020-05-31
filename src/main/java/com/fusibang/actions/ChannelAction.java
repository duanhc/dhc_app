//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.actions;

import com.fusibang.services.ChannelService;
import com.fusibang.tables.Channel;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChannelAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, ModelDriven<Channel> {
    private static final long serialVersionUID = 1L;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private Channel channel;
    private ChannelService channelService;

    public ChannelAction() {
    }

    /**
     * 渠道视图
     * @return
     */
    public String channelView() {
        System.out.println(this.channel.getName());
        if(this.channel.getId() == 0) {
            this.channel.setId(1);
        }

        if(this.channel.getName() == null) {
            this.channel.setName("");
        }

        return this.channelService.channelView(this.channel, this.request);
    }

    /**
     * 注册页面-统计uv
     * @return
     */
    public String channelViewCountUv() {
        return this.channelService.channelViewCountUv(this.channel, this.request);
    }

    public String addChannel() {
        this.channel.setName(this.channel.getName().trim());
        HttpSession session = this.request.getSession();

        try {
            this.response.getWriter().write(this.channelService.addChannel(this.channel, session));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return null;
    }

    public String altChannel() {
        this.channel.setName(this.channel.getName().trim());
        HttpSession session = this.request.getSession();

        try {
            this.response.getWriter().write(this.channelService.altChannel(this.channel, session));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return null;
    }

    public String addView() {
        return this.channelService.addView(this.request);
    }

    public String altView() {
        return this.channelService.altView(this.channel.getId(), this.request);
    }

    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
    }

    public Channel getModel() {
        if(this.channel == null) {
            this.channel = new Channel();
        }

        return this.channel;
    }

    public void setServletResponse(HttpServletResponse arg0) {
        this.response = arg0;
    }

    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }
}
