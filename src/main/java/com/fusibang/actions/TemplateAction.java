//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

public class TemplateAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
    private static final long serialVersionUID = 1L;
    private HttpServletResponse response;
    private HttpServletRequest request;

    public TemplateAction() {
    }

    public String execute() {
        String title = this.request.getParameter("title");
        String url = this.request.getParameter("url");
        this.request.setAttribute("title", title);
        this.request.setAttribute("url", url);
        return "success";
    }

    public void setServletResponse(HttpServletResponse arg0) {
        this.response = arg0;
    }

    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }
}
