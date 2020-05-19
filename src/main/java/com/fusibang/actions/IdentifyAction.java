//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.actions;

import com.fusibang.services.IdentifyService;
import com.fusibang.tables.Identify;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    /**
     * 修改identify，增加cash_password和签名字段
     * @return
     */
    public String addCashPassword() {
        this.response.setContentType("application/json; charset=utf-8");

        try {
            this.response.getWriter().write(this.identifyService.addCashPassword(this.identify,this.request.getSession()));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return null;
    }

    /**
     * 后台-借款管理-转账说明
     * @return
     */
    public String addZzsm() {
        this.response.setContentType("application/json; charset=utf-8");

        try {
            this.response.getWriter().write(this.identifyService.addZzsm(this.identify,this.request.getSession()));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return null;
    }

    /**
     * 后台-借款管理-订单状态
     * @return
     */
    public String addOrderInfo() {
        this.response.setContentType("application/json; charset=utf-8");

        try {
            this.response.getWriter().write(this.identifyService.addOrderInfo(this.identify,this.request.getSession()));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return null;
    }

    /**
     * 后台-借款管理-转账截图
     * @return
     */
    public String getZzjt() {
        this.response.setContentType("application/json; charset=utf-8");

        try {
            this.response.getWriter().write(this.identifyService.getZzjt(this.identify,this.request.getSession()));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return null;
    }

    /**
     * 后台-借款管理-保险截图
     * @return
     */
    public String getBxjt() {
        this.response.setContentType("application/json; charset=utf-8");

        try {
            this.response.getWriter().write(this.identifyService.getBxjt(this.identify,this.request.getSession()));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return null;
    }

    /**
     * 借款管理
     * 查询授权额度、卡号、是否签字
     *
     * @return
     */
    public String jkgl() {
        this.response.setContentType("application/json; charset=utf-8");

        try {
            this.response.getWriter().write(this.identifyService.jkgl(this.request.getSession()));
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

    public String modifyLendAndCount() {
        try {
            this.response.getWriter().write(this.identifyService.modifyLendAndCount(this.identify,this.request.getSession()));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return null;
    }

    public String modifyLend() {
        try {
            this.response.getWriter().write(this.identifyService.modifyLend(this.identify,this.request.getSession()));
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
