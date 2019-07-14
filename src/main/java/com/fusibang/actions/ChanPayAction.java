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

import com.fusibang.services.ChanPayService;
import com.opensymphony.xwork2.ActionSupport;

public class ChanPayAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
    private static final long serialVersionUID = 1L;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private ChanPayService chanPayService;

    public ChanPayAction() {
    }

    public String auth() {
        try {
            this.response.getWriter().write(this.chanPayService.BankCarAuth(this.request));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return null;
    }

    public String authNotify() {
        try {
            this.response.getWriter().write(this.chanPayService.BankCarNotify(this.request));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return null;
    }

    public String authPay() {
        try {
            this.response.getWriter().write(this.chanPayService.authPay(this.request));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return null;
    }

    public String payNotify() {
        try {
            this.response.getWriter().write(this.chanPayService.payNotify(this.request));
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

    public void setChanPayService(ChanPayService chanPayService) {
        this.chanPayService = chanPayService;
    }
}
