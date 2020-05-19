//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.actions;

import com.fusibang.services.UserDetailService;
import com.fusibang.tables.UserDetail;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserDetailAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, ModelDriven<UserDetail> {
    private static final long serialVersionUID = 1L;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private UserDetail userDetail;
    private UserDetailService userDetailService;

    public UserDetailAction() {
    }

    public String view() {
        String result = this.userDetailService.view(this.request);
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

    public String addDetail() {
        HttpSession session = this.request.getSession();

        try {
            this.response.getWriter().write(this.userDetailService.addDetail(this.userDetail, session));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return null;
    }

    public String altDetail() {
        HttpSession session = this.request.getSession();

        try {
            this.response.getWriter().write(this.userDetailService.altDetail(this.userDetail, session));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return null;
    }

    /**
     * 后台-借款管理-修改卡号
     * @return
     */
    public String modifyCreditNumber() {
        HttpSession session = this.request.getSession();

        try {
            this.response.getWriter().write(this.userDetailService.modifyCreditNumber(this.userDetail, session));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return null;
    }

    public void setUserDetailService(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    public UserDetail getModel() {
        if(this.userDetail == null) {
            this.userDetail = new UserDetail();
        }

        return this.userDetail;
    }

    public void setServletResponse(HttpServletResponse arg0) {
        this.response = arg0;
    }

    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }
}
