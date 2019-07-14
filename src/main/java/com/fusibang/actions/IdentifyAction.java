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

import com.fusibang.services.IdentifyService;
import com.fusibang.tables.Identify;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class IdentifyAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, ModelDriven<Identify> {
    private static final long serialVersionUID = 1L;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private Identify identify;
    private IdentifyService identifyService;

    public IdentifyAction() {
    }

    public String faxian() {
        String result = this.identifyService.faxian(this.request);
        String url = "news.html";
        if(result.equals("success")) {
            url = "../app_market.do";
        }

        this.request.setAttribute("url", url);
        return "success";
    }

    public String showApp() {
        try {
            this.response.setContentType("application/json; charset=utf-8");
            this.response.getWriter().write(this.identifyService.showApp(this.request));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return null;
    }

    public String authCenter() {
        return this.identifyService.authCenter(this.request);
    }

    public String version() {
        try {
            this.response.setContentType("application/json; charset=utf-8");
            this.response.getWriter().write(this.identifyService.version());
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return null;
    }

    public String volaty() {
        try {
            this.response.setContentType("application/json; charset=utf-8");
            this.response.getWriter().write(this.identifyService.volaty());
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return null;
    }

    public String getIdentify() {
        this.response.setContentType("application/json; charset=utf-8");

        try {
            this.response.getWriter().write(this.identifyService.getIdentify(this.request.getSession()));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return null;
    }

    public String submit() {
        try {
            this.response.getWriter().write(this.identifyService.IdentifySubmit(this.request.getSession()));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return null;
    }

    public Identify getModel() {
        if(this.identify == null) {
            this.identify = new Identify();
        }

        return this.identify;
    }

    public void setIdentifyService(IdentifyService identifyService) {
        this.identifyService = identifyService;
    }

    public void setServletResponse(HttpServletResponse arg0) {
        this.response = arg0;
    }

    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }
}
