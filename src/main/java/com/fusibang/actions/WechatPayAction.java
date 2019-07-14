//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.actions;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.fusibang.services.WechatPayService;
import com.opensymphony.xwork2.ActionSupport;

public class WechatPayAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
    private static final long serialVersionUID = 1L;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private WechatPayService wechatPayService;

    public WechatPayAction() {
    }

    public String placeOrder() {
        return this.wechatPayService.placeOrder(this.request);
    }

    public String payNotify() {
        this.response.setContentType("text/xml; charset=utf-8");

        try {
            this.response.getWriter().print(this.wechatPayService.resultNotify(this.request));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return null;
    }

    public void setServletResponse(HttpServletResponse arg0) {
        this.response = arg0;
    }

    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }

    public void setWechatPayService(WechatPayService wechatPayService) {
        this.wechatPayService = wechatPayService;
    }
}
