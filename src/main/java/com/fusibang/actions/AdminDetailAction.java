//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.fusibang.services.AdminDetailService;
import com.fusibang.tables.AdminDetail;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminDetailAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, ModelDriven<AdminDetail> {
    private static final long serialVersionUID = 1L;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private AdminDetail adminDetail;
    private AdminDetailService adminDetailService;

    public AdminDetailAction() {
    }

    public AdminDetail getModel() {
        if(this.adminDetail == null) {
            this.adminDetail = new AdminDetail();
        }

        return this.adminDetail;
    }

    public String detailView() {
        this.adminDetail.setPermission("00001");
        this.adminDetail.setReal_name(this.adminDetail.getName());
        if(this.adminDetail.getReal_name() == null) {
            this.adminDetail.setReal_name("");
        }

        if(this.adminDetail.getId() == 0) {
            this.adminDetail.setId(1);
        }

        return this.adminDetailService.detailView(this.adminDetail, this.request);
    }

    public void setServletResponse(HttpServletResponse arg0) {
        this.response = arg0;
    }

    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }

    public void setAdminDetailService(AdminDetailService adminDetailService) {
        this.adminDetailService = adminDetailService;
    }
}
