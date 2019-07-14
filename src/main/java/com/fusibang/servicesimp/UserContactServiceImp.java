//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.servicesimp;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fusibang.dao.IdentifyDao;
import com.fusibang.dao.UserContactDao;
import com.fusibang.dao.UserDao;
import com.fusibang.help.ResponseStatus;
import com.fusibang.services.UserContactService;
import com.fusibang.tables.Identify;
import com.fusibang.tables.User;
import com.fusibang.tables.UserContact;

public class UserContactServiceImp extends ResponseStatus implements UserContactService {
    private UserContactDao userContactDao;
    private UserDao userDao;
    private IdentifyDao identifyDao;

    public UserContactServiceImp() {
    }

    public String addContact(HttpSession session, UserContact userContact) {
        Integer id = (Integer)session.getAttribute("ui");
        User user = null;
        if(id != null && (user = this.userDao.findById(id.intValue())) != null) {
            Identify identify = this.identifyDao.findByUserId(user.getId());
            if(identify.getStep4() == 1) {
                userContact.setUser(user);
                userContact.setTime(new Timestamp((new Date()).getTime()));
                this.userContactDao.save(userContact);
                identify.setStep5(1);
                return "{\"hint\":\"success\"}";
            } else {
                return "{\"hint\":\"step_one_by_one\"}";
            }
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    public String altContact(HttpSession session, UserContact userContact) {
        Integer id = (Integer)session.getAttribute("ui");
        User user = null;
        if(id != null && (user = this.userDao.findById(id.intValue())) != null) {
            UserContact hold = this.userContactDao.findByUserId(user.getId());
            if(hold != null) {
                userContact.setId(hold.getId());
                userContact.setUser(user);
                userContact.setTime(new Timestamp((new Date()).getTime()));
                this.userContactDao.alt(userContact);
                return "{\"hint\":\"success\"}";
            } else {
                return "{\"hint\":\"illegal_request\"}";
            }
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    public String view(HttpServletRequest request) {
        Integer id = (Integer)request.getSession().getAttribute("ui");
        User user = this.userDao.findById(id.intValue());
        if(user != null) {
            Identify identify = this.identifyDao.findByUserId(user.getId());
            return identify.getStep5() == 0?"step5":"success";
        } else {
            return "un_login";
        }
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setUserContactDao(UserContactDao userContactDao) {
        this.userContactDao = userContactDao;
    }

    public void setIdentifyDao(IdentifyDao identifyDao) {
        this.identifyDao = identifyDao;
    }
}
