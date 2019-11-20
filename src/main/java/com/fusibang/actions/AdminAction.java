//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.actions;

import com.fusibang.services.AdminService;
import com.fusibang.tables.Admin;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, ModelDriven<Admin> {
    private static final long serialVersionUID = 1L;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private Admin admin;
    private AdminService adminService;
    private String image_code;

    public AdminAction() {
    }

    public String manage() {
        return this.adminService.manage(this.request);
    }
    //test
    public String login() {
        HttpSession session = this.request.getSession();
        String result;
        if(this.image_code != null && this.image_code.equals((String)session.getAttribute("i"))) {
            result = this.adminService.loginAdmin(this.admin, session);
        } else {
            result = "{\"hint\":\"image_code_error\"}";
        }

        try {
            this.response.getWriter().write(result);
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return null;
    }

    public String logout() {
        this.request.getSession().invalidate();

        try {
            this.response.sendRedirect("core/htlogin.html");
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return null;
    }

    public String addAdmin() {
        try {
            this.response.getWriter().write(this.adminService.addAdmin(this.admin, this.request.getSession()));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return null;
    }

    public String forbidAdmin() {
        try {
            this.response.getWriter().write(this.adminService.forbidAdmin(this.admin, this.request.getSession()));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return null;
    }

    public String rtforbidView() {
        this.admin.setForbid(0);
        this.admin.setPermission("00000");
        if(this.admin.getId() == 0) {
            this.admin.setId(1);
        }

        if(this.admin.getReal_name() == null) {
            this.admin.setReal_name("");
        }

        return this.adminService.forbidView(this.admin, this.request);
    }

    public String adminAppView() {
        this.admin.setForbid(0);
        this.admin.setPermission("00010");
        if(this.admin.getId() == 0) {
            this.admin.setId(1);
        }

        if(this.admin.getReal_name() == null) {
            this.admin.setReal_name("");
        }

        return this.adminService.forbidView(this.admin, this.request);
    }

    public String allView() {
        this.admin.setForbid(1);
        if(this.admin.getId() == 0) {
            this.admin.setId(1);
        }

        if(this.admin.getReal_name() == null) {
            this.admin.setReal_name("");
        }

        return this.adminService.allView(this.admin, this.request);
    }

    public String rtforbidVediew() {
        this.admin.setForbid(1);
        this.admin.setPermission("00000");
        if(this.admin.getId() == 0) {
            this.admin.setId(1);
        }

        if(this.admin.getReal_name() == null) {
            this.admin.setReal_name("");
        }

        return this.adminService.forbidView(this.admin, this.request);
    }

    public String altView() {
        return this.adminService.altView(this.admin.getId(), this.request);
    }

    public String altAdmin() {
        try {
            this.response.getWriter().write(this.adminService.altAdminWithCreater(this.admin, this.request.getSession()));
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

    public Admin getModel() {
        if(this.admin == null) {
            this.admin = new Admin();
        }

        return this.admin;
    }

    public String getImage_code() {
        return this.image_code;
    }

    public void setImage_code(String image_code) {
        this.image_code = image_code;
    }

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }
}
