//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.actions;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.fusibang.services.PhoneVerifyService;
import com.opensymphony.xwork2.ActionSupport;

public class PhoneVerifyAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
    private static final long serialVersionUID = 1L;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private String image_code;
    private String phone;
    private PhoneVerifyService phoneVerifyService;

    public PhoneVerifyAction() {
    }

    public String send2Registe() {
        HttpSession session = this.request.getSession();
        this.response.setContentType("application/json; charset=utf-8");
        String result;
        if(this.image_code != null && this.image_code.equals((String)session.getAttribute("i"))) {
            result = this.phoneVerifyService.send2Registe(this.phone, session);
        } else {
            result = "{\"hint\":\"image_code_error\"}";
        }

        try {
            this.response.getWriter().write(result);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return null;
    }

    public String send2modifyPwd() {
        HttpSession session = this.request.getSession();
        this.response.setContentType("application/json; charset=utf-8");
        String result;
        if(this.image_code != null && this.image_code.equals((String)session.getAttribute("i"))) {
            result = this.phoneVerifyService.send2ChmodPwd(this.phone, session);
        } else {
            result = "{\"hint\":\"image_code_error\"}";
        }

        try {
            this.response.getWriter().write(result);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return null;
    }

    public String send2Login() {
        this.response.setContentType("application/json; charset=utf-8");

        try {
            this.response.getWriter().write(this.phoneVerifyService.send2Login(this.phone, this.request.getSession()));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return null;
    }

    public void setPhoneVerifyService(PhoneVerifyService phoneVerifyService) {
        this.phoneVerifyService = phoneVerifyService;
    }

    public void setServletResponse(HttpServletResponse arg0) {
        this.response = arg0;
    }

    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }

    public void setImage_code(String image_code) {
        this.image_code = image_code.toLowerCase();
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
