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

import com.fusibang.services.LendSercice;
import com.fusibang.tables.Lend;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LendAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, ModelDriven<Lend> {
    private static final long serialVersionUID = 1L;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private Lend lend;
    private LendSercice lendSercice;

    public LendAction() {
    }

    public String addLend() {
        this.response.setContentType("application/json; charset=utf-8");

        try {
            this.response.getWriter().write(this.lendSercice.addLend(this.lend, this.request.getSession()));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return null;
    }

    public String verifyLend() {
        this.response.setContentType("application/json; charset=utf-8");

        try {
            this.response.getWriter().write(this.lendSercice.verifyLend(this.request.getSession()));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return null;
    }

    public String verifyView() {
        return this.lendSercice.verifyView(this.request);
    }

    public String collectView() {
        return this.lendSercice.collectView(this.request, this.response);
    }

    public void setServletResponse(HttpServletResponse arg0) {
        this.response = arg0;
    }

    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }

    public Lend getModel() {
        if(this.lend == null) {
            this.lend = new Lend();
        }

        return this.lend;
    }

    public void setLendSercice(LendSercice lendSercice) {
        this.lendSercice = lendSercice;
    }
}
