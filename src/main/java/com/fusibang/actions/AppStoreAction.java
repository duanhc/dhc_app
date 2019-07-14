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

import com.fusibang.help.AppStoreHelp;
import com.fusibang.services.AppStoreService;
import com.fusibang.tables.AppStore;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AppStoreAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, ModelDriven<AppStore> {
    private static final long serialVersionUID = 1L;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private AppStoreHelp appStoreHelp;
    private AppStore app;
    private String type;
    private AppStoreService appStoreService;

    public AppStoreAction() {
    }

    public String marketView() {
        this.appStoreService.marketView(this.request);
        return "success";
    }

    public String jieguoView() {
        this.appStoreService.jieguotView(this.request);
        return "success";
    }

    public String altView() {
        return this.appStoreService.altView(this.app.getId(), this.request);
    }

    public String putView() {
        this.app.setPut_away(Integer.valueOf(0));
        if(this.app.getName() == null) {
            this.app.setName("");
        }

        if(this.app.getId() == 0) {
            this.app.setId(1);
        }

        return this.appStoreService.putView(this.app, this.request);
    }

    public String downView() {
        this.app.setPut_away(Integer.valueOf(1));
        if(this.app.getName() == null) {
            this.app.setName("");
        }

        if(this.app.getId() == 0) {
            this.app.setId(1);
        }

        return this.appStoreService.downView(this.app, this.request);
    }

    public String delete() {
        HttpSession session = this.request.getSession();

        try {
            this.response.getWriter().write(this.appStoreService.delete(this.app.getId(), session));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return null;
    }

    public String priority() {
        HttpSession session = this.request.getSession();

        try {
            this.response.getWriter().write(this.appStoreService.priority(this.app, session));
            this.appStoreHelp.setAppStores();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return null;
    }

    public String putApp() {
        HttpSession session = this.request.getSession();

        try {
            this.response.getWriter().write(this.appStoreService.put(this.app, session));
            this.appStoreHelp.setAppStores();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return null;
    }

    public String addApp() {
        HttpSession session = this.request.getSession();

        try {
            this.response.getWriter().write(this.appStoreService.add(this.app, session));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return null;
    }

    public String altApp() {
        HttpSession session = this.request.getSession();

        try {
            this.response.getWriter().write(this.appStoreService.alt(this.app, session));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return null;
    }

    public String getURL() {
        HttpSession session = this.request.getSession();

        try {
            this.response.getWriter().write(this.appStoreService.getUrl(this.app.getId(), session));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return null;
    }

    public String getQRCode() {
        return this.appStoreService.getQRCode(this.app.getId(), this.request, this.response);
    }

    public String sendRedirect() {
        HttpSession session = this.request.getSession();
        String url = this.appStoreService.getUrl(this.app.getId(), session);

        try {
            this.response.sendRedirect(url);
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return null;
    }

    public String getJson() {
        String json = "";
        if(this.type != null && !this.type.equals("order")) {
            if(this.type.equals("time")) {
                json = this.appStoreHelp.getJsonAsTime();
            } else if(this.type.equals("ua")) {
                json = this.appStoreHelp.getJsonAsUa();
            }
        } else {
            json = this.appStoreHelp.getJsonAsOrder();
        }

        this.response.setContentType("application/json; charset=utf-8");
        json = json == null?"":json;

        try {
            this.response.getWriter().write(json);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return null;
    }

    public void setServletResponse(HttpServletResponse arg0) {
        this.response = arg0;
    }

    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAppStoreHelp(AppStoreHelp appStoreHelp) {
        this.appStoreHelp = appStoreHelp;
    }

    public AppStore getModel() {
        if(this.app == null) {
            this.app = new AppStore();
        }

        return this.app;
    }

    public void setAppStoreService(AppStoreService appStoreService) {
        this.appStoreService = appStoreService;
    }
}
