//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.actions;

import com.alibaba.fastjson.JSONObject;
import com.fusibang.help.ResponseStatus;
import com.fusibang.services.PhoneVerifyService;
import com.fusibang.webservice.util.SendMsgUtil;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PhoneVerifyAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private static final Logger logger = Logger.getLogger(PhoneVerifyAction.class);

    private static final long serialVersionUID = 1L;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private String image_code;
    private String phone;
    private PhoneVerifyService phoneVerifyService;
    /**
     * 短信内容，用于后台发送短信
     */
    private String content;

    public PhoneVerifyAction() {
    }

    public String send2Registe() {
        HttpSession session = this.request.getSession();
        this.response.setContentType("application/json; charset=utf-8");
        String result;
        if(this.image_code != null && this.image_code.equals((String)session.getAttribute("i"))) {
            result = this.phoneVerifyService.send2Registe(this.phone, session);
        } else {
            result = "{\"hint\":\"image_code_error\"}";
        }

        try {
            this.response.getWriter().write(result);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return null;
    }

    /**
     * 后台运营人员发送短信
     * @return
     */
    public String sendFromAdmin() {
        this.response.setContentType("application/json; charset=utf-8");
        String result = "";
        try {
            JSONObject jsonObject = SendMsgUtil.send(phone,content);
            //发送失败
            if(!"Success".equals(jsonObject.get("returnstatus").toString())){
                logger.error("error, send msg from admin to " + phone + " result：" + jsonObject.toJSONString());
                result = ResponseStatus.UNKNOW_ERROR;
            }else {
                result = ResponseStatus.SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = ResponseStatus.UNKNOW_ERROR;
        }

        try {
            this.response.getWriter().write(result);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return null;
    }

    public String send2modifyPwd() {
        HttpSession session = this.request.getSession();
        this.response.setContentType("application/json; charset=utf-8");
        String result;
        if(this.image_code != null && this.image_code.equals((String)session.getAttribute("i"))) {
            result = this.phoneVerifyService.send2ChmodPwd(this.phone, session);
        } else {
            result = "{\"hint\":\"image_code_error\"}";
        }

        try {
            this.response.getWriter().write(result);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return null;
    }

    public String send2Login() {
        this.response.setContentType("application/json; charset=utf-8");

        try {
            this.response.getWriter().write(this.phoneVerifyService.send2Login(this.phone, this.request.getSession()));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return null;
    }

    public void setPhoneVerifyService(PhoneVerifyService phoneVerifyService) {
        this.phoneVerifyService = phoneVerifyService;
    }

    public void setServletResponse(HttpServletResponse arg0) {
        this.response = arg0;
    }

    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }

    public void setImage_code(String image_code) {
        this.image_code = image_code.toLowerCase();
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
