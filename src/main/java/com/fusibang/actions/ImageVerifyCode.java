//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.actions;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.fusibang.help.GenerateVerifyCodeImg;
import com.opensymphony.xwork2.ActionSupport;

public class ImageVerifyCode extends ActionSupport implements ServletRequestAware, ServletResponseAware {
    private static final long serialVersionUID = 1L;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public ImageVerifyCode() {
    }

    public String execute() {
        ServletOutputStream out = null;

        try {
            String e = GenerateVerifyCodeImg.generateVerifyCode(4, "0123456789");
            HttpSession session = this.request.getSession();
            session.setAttribute("i", e.toLowerCase());
            out = this.response.getOutputStream();
            GenerateVerifyCodeImg.outputImage(121, 40, out, e);
        } catch (Exception var12) {
            var12.printStackTrace();
        } finally {
            try {
                if(out != null) {
                    out.close();
                }
            } catch (IOException var11) {
                var11.printStackTrace();
            }

        }

        return null;
    }

    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }

    public void setServletResponse(HttpServletResponse arg0) {
        this.response = arg0;
    }
}
