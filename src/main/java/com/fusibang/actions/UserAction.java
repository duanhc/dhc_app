//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.actions;

import com.fusibang.help.Config;
import com.fusibang.services.UserService;
import com.fusibang.tables.AppStore;
import com.fusibang.tables.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, ModelDriven<User> {
    private static final long serialVersionUID = 1L;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private UserService userService;
    private User user;
    private String phone_verify;
    private Config config;

    public UserAction() {
    }

    public String getAll() {
        return this.userService.getAll(this.request);
    }

    public String getAllRt() {
        return this.userService.getAllRt(this.request);
    }

    public String loginByPhone() {
        String result = this.userService.loginByPhone(this.user.getPhone_number(), this.phone_verify, this.request.getSession());
        if(!result.contains("hint")) {
            Cookie e = new Cookie("token", result);
            e.setMaxAge(2592000);
            Cookie cookie2 = new Cookie("phone", this.user.getPhone_number());
            cookie2.setMaxAge(2592000);
            this.response.addCookie(e);
            this.response.addCookie(cookie2);
        }

        try {
            this.response.getWriter().write(result);
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return null;
    }

    public String loginByPwd() {
        String result = this.userService.loginUser(this.user.getPhone_number(), this.user.getPassword(), this.request.getSession());

        try {
            if(!result.contains("hint")) {
                Cookie e = new Cookie("token", result);
                e.setMaxAge(2592000);
                Cookie cookie2 = new Cookie("phone", this.user.getPhone_number());
                cookie2.setMaxAge(2592000);
                this.response.addCookie(e);
                this.response.addCookie(cookie2);
            }

            this.response.getWriter().write(result);
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return null;
    }

    public String logout() {
        try {
            this.response.getWriter().write(this.userService.logout(this.user, this.request.getSession()));
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        Cookie[] allCookies = this.request.getCookies();
        if(allCookies != null) {
            Cookie[] var5 = allCookies;
            int var4 = allCookies.length;

            for(int var3 = 0; var3 < var4; ++var3) {
                Cookie cookie = var5[var3];
                cookie.setMaxAge(0);
                cookie.setPath("/"+config.getProjectName());
                this.response.addCookie(cookie);
            }
        }

        return null;
    }

    public String add() {
        HttpSession session = this.request.getSession();
        String result;
//        if(this.phone_verify != null && this.phone_verify.equals(session.getAttribute("vr" + this.user.getPhone_number()))) {
        //判断是否已注册
        if(!this.userService.existUser(this.user.getPhone_number())) {
            //注册用户
            String e = this.userService.addUser(this.user, session);
            Cookie cookie = new Cookie("token", e);
            cookie.setMaxAge(2592000);
            this.response.addCookie(cookie);
            Cookie cookie2 = new Cookie("phone", this.user.getPhone_number());
            cookie2.setMaxAge(2592000);
            this.response.addCookie(cookie);
            this.response.addCookie(cookie2);
            result = "{\"hint\":\"success\"}";
        }else {
            result = "{\"hint\":\"already_exist\"}";
        }

        try {
            this.response.getWriter().write(result);
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        return null;
    }

    public String qnq() {
        HttpSession session = this.request.getSession();
        String appId = this.request.getParameter("appId");
        Integer aid = Integer.parseInt(appId);
        session.setAttribute("tem_app_id",aid);
        Integer userId = (Integer)session.getAttribute("ui");
        String result;
        if (userId == null) {
            result = "{\"hint\":\"un_login\"}";
        } else {
            AppStore appStore = userService.findApp(aid);
            User user = this.userService.getById(userId);
            if (user == null) {
                result = "{\"hint\":\"un_login\"}";
            } else if(appStore == null ){
                result = "{\"hint\":\"no_data\"}";
            }else if(appStore.getPut_away() == 0 ){
                result = "{\"hint\":\"put_away\"}";
            }else if (user.getSend() == 0) {
                result = "{\"hint\":\"un_send\"}";
            } else {
                if(session.getAttribute("app_uv" + appId) == null) {
                    userService.altApp(appStore,user.getPhone_number());
                    session.setAttribute("app_uv" + appId, "");
                }
                result = "{\"hint\":\"send\"}";
            }
        }

        try {
            this.response.getWriter().write(result);
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        return null;
    }

    public String alt() {
        HttpSession session = this.request.getSession();
        String result;
        if(this.phone_verify != null && this.phone_verify.equals(session.getAttribute("vm" + this.user.getPhone_number()))) {
            String e = this.userService.altUser(this.user, session);
            Cookie cookie = new Cookie("token", e);
            cookie.setMaxAge(2592000);
            this.response.addCookie(cookie);
            Cookie cookie2 = new Cookie("phone", this.user.getPhone_number());
            cookie2.setMaxAge(2592000);
            this.response.addCookie(cookie);
            this.response.addCookie(cookie2);
            result = "{\"hint\":\"success\"}";
        } else {
            result = "{\"hint\":\"phone_code_error\"}";
        }

        try {
            this.response.getWriter().write(result);
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        return null;
    }

    public String logined() throws IOException {
//        Integer userId = (Integer)this.request.getSession().getAttribute("ui");
//        String result = "";
//        if(userId != null) {
//            result = "{\"hint\":\"logined,userId:"+userId+"\"}";
//            this.response.getWriter().write(result);
//        } else {
//            result = "{\"hint\":\"un_login\"}";
//            this.response.getWriter().write(result);
//        }

        return null;
    }

    public String getSessionId() {
        String sessionId = this.request.getSession().getId();

        try {
            this.response.getWriter().write(sessionId);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return null;
    }

    public String usersView() {
        if(this.user.getId() == 0) {
            this.user.setId(1);
        }

        if(this.user.getSalt() == null) {
            this.user.setSalt("");
        }

        return this.userService.usersView(this.user, this.request);
    }

    public String setValid() {
        try {
            this.response.getWriter().write(this.userService.setValid(this.user, this.request.getSession()));
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        return null;
    }

    public String delUser() {
        try {
            this.response.getWriter().write(this.userService.delUser(this.user, this.request.getSession()));
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

    public User getModel() {
        if(this.user == null) {
            this.user = new User();
        }

        return this.user;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setPhone_verify(String phone_verify) {
        this.phone_verify = phone_verify;
    }

    public void setConfig(Config config) {
        this.config = config;
    }
}
