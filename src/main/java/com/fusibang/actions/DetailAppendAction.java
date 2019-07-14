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

import com.fusibang.services.DetailAppendService;
import com.fusibang.tables.UserDetailAppend;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class DetailAppendAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, ModelDriven<UserDetailAppend> {
    private static final long serialVersionUID = 1L;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private UserDetailAppend userDetailAppend;
    private DetailAppendService detailAppendService;

    public DetailAppendAction() {
    }

    public String appendDetail() {
        try {
            this.response.getWriter().write(this.detailAppendService.appendDetail(this.request.getSession(), this.userDetailAppend));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return null;
    }

    public String altDetail() {
        try {
            this.response.getWriter().write(this.detailAppendService.altDetail(this.request.getSession(), this.userDetailAppend));
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

    public void setDetailAppendService(DetailAppendService detailAppendService) {
        this.detailAppendService = detailAppendService;
    }

    public UserDetailAppend getModel() {
        if(this.userDetailAppend == null) {
            this.userDetailAppend = new UserDetailAppend();
        }

        return this.userDetailAppend;
    }
}
