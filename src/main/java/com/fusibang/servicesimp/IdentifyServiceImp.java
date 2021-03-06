//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.servicesimp;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.fusibang.dao.IdentifyDao;
import com.fusibang.dao.UserDao;
import com.fusibang.help.ResponseStatus;
import com.fusibang.help.VersionInfo;
import com.fusibang.help.Volaty;
import com.fusibang.services.IdentifyService;
import com.fusibang.tables.Identify;
import com.fusibang.tables.User;

public class IdentifyServiceImp extends ResponseStatus implements IdentifyService {
    private UserDao userDao;
    private IdentifyDao identifyDao;
    private Volaty volaty;
    private VersionInfo versionInfo;

    public IdentifyServiceImp() {
    }

    public String version() {
        return JSON.toJSONString(this.versionInfo);
    }

    public String volaty() {
        this.volaty.setDate(new Date());
        return JSON.toJSONString(this.volaty);
    }

    public String getIdentify(HttpSession session) {
        Integer id = (Integer)session.getAttribute("ui");
        Object user = null;
        if(id != null && this.userDao.findById(id.intValue()) != null) {
            Identify identify = this.identifyDao.findByUserId(id.intValue());
            identify.setNow(new Timestamp((new Date()).getTime()));
            return JSON.toJSONString(identify);
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    public String IdentifySubmit(HttpSession session) {
        Integer id = (Integer)session.getAttribute("ui");
        User user = null;
        if(id != null && (user = this.userDao.findById(id.intValue())) != null) {
            Identify identify = this.identifyDao.findByUserId(user.getId());
            if(identify.getStep6() == 1 && identify.getStep2() == 1) {
                identify.setPut(1);
                identify.setPut_time(new Timestamp((new Date()).getTime()));
                return "{\"hint\":\"success\"}";
            } else {
                return "{\"hint\":\"step_one_by_one\"}";
            }
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    public String authCenter(HttpServletRequest request) {
        Integer id = (Integer)request.getSession().getAttribute("ui");
        User user = null;
        if(id != null && (user = this.userDao.findById(id.intValue())) != null) {
            Identify identify = this.identifyDao.findByUserId(user.getId());
            identify.setNow(new Timestamp((new Date()).getTime()));
            int count = identify.getStep2() == 0?0:(identify.getStep4() == 0?1:(identify.getStep5() == 0?2:(identify.getStep6() == 0?3:4)));
            byte check = 0;
            if((user.getTop_three() == 1 || user.getChannel().getId() == 0) && (identify.getPut_time() == null || (new Date()).getTime() - identify.getPut_time().getTime() < 18000000L)) {
                check = 1;
            }

            request.setAttribute("check", Integer.valueOf(check));
            request.setAttribute("count", Integer.valueOf(count));
            request.setAttribute("identify", identify);
            return "success";
        } else {
            return "un_login";
        }
    }

    public String showApp(HttpServletRequest request) {
        Integer id = (Integer)request.getSession().getAttribute("ui");
        User user = null;
        return id != null && (user = this.userDao.findById(id.intValue())) != null?(this.identifyDao.showApp(user)?"{\"hint\":\"success\"}":"{\"hint\":\"un_login\"}"):"{\"hint\":\"un_login\"}";
    }

    public String faxian(HttpServletRequest request) {
        Integer id = (Integer)request.getSession().getAttribute("ui");
        User user = null;
        return id != null && (user = this.userDao.findById(id.intValue())) != null?(this.identifyDao.showApp(user)?"success":"faild"):"faild";
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setIdentifyDao(IdentifyDao identifyDao) {
        this.identifyDao = identifyDao;
    }

    public void setVolaty(Volaty volaty) {
        this.volaty = volaty;
    }

    public void setVersionInfo(VersionInfo versionInfo) {
        this.versionInfo = versionInfo;
    }
}
