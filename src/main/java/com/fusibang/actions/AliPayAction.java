//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.actions;

import com.fusibang.services.AliPayService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 支付宝支付
 * @author: Alex
 * @date: 2020-03-11 21:31
 */
public class AliPayAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
    private static final long serialVersionUID = 1L;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private AliPayService aliPayService;

    public AliPayAction() {
    }

    /**
     * 支付成功跳转页面
     * @return
     */
    public String returnUrl() throws Exception{
        return this.aliPayService.returnUrl(this.request);
    }

    /**
     * 支付回调地址
     * @return
     */
    public void payNotify() throws Exception{
        try {
            this.aliPayService.resultNotify(this.request);
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public void setServletResponse(HttpServletResponse arg0) {
        this.response = arg0;
    }

    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }

    public void setAliPayService(AliPayService aliPayService) {
        this.aliPayService = aliPayService;
    }
}
