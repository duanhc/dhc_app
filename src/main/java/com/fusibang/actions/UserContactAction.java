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

import com.fusibang.services.UserContactService;
import com.fusibang.tables.UserContact;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserContactAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, ModelDriven<UserContact> {
    private static final long serialVersionUID = 1L;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private UserContact userContact;
    private UserContactService userContactService;

    public UserContactAction() {
    }

    public String view() {
        String result = this.userContactService.view(this.request);
        if(result.equals("success")) {
            try {
                this.response.sendRedirect("auth_center.action");
            } catch (IOException var3) {
                var3.printStackTrace();
            }

            return null;
        } else {
            return result;
        }
    }

    public String addContact() {
        try {
            this.response.getWriter().write(this.userContactService.addContact(this.request.getSession(), this.userContact));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return null;
    }

    public String altContact() {
        try {
            this.response.getWriter().write(this.userContactService.altContact(this.request.getSession(), this.userContact));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return null;
    }

    public UserContact getModel() {
        if(this.userContact == null) {
            this.userContact = new UserContact();
        }

        return this.userContact;
    }

    public void setServletResponse(HttpServletResponse arg0) {
        this.response = arg0;
    }

    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }

    public void setUserContactService(UserContactService userContactService) {
        this.userContactService = userContactService;
    }
}
