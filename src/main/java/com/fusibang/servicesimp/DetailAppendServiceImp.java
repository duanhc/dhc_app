//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.servicesimp;

import javax.servlet.http.HttpSession;

import com.fusibang.dao.IdentifyDao;
import com.fusibang.dao.UserDao;
import com.fusibang.dao.UserDetailAppendDao;
import com.fusibang.help.ResponseStatus;
import com.fusibang.services.DetailAppendService;
import com.fusibang.tables.Identify;
import com.fusibang.tables.User;
import com.fusibang.tables.UserDetailAppend;

public class DetailAppendServiceImp extends ResponseStatus implements DetailAppendService {
    private UserDetailAppendDao userDetailAppendDao;
    private IdentifyDao identifyDao;
    private UserDao userDao;

    public DetailAppendServiceImp() {
    }

    public String appendDetail(HttpSession session, UserDetailAppend userDetailAppend) {
        Integer id = (Integer)session.getAttribute("ui");
        User user = null;
        if(id != null && (user = this.userDao.findById(id.intValue())) != null) {
            Identify identify = this.identifyDao.findByUserId(user.getId());
            if(identify.getStep3() == 1) {
                userDetailAppend.setUser(user);
                this.userDetailAppendDao.save(userDetailAppend);
                identify.setStep4(1);
                return "{\"hint\":\"success\"}";
            } else {
                return "{\"hint\":\"step_one_by_one\"}";
            }
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    public String altDetail(HttpSession session, UserDetailAppend userDetailAppend) {
        Integer id = (Integer)session.getAttribute("ui");
        User user = null;
        if(id != null && (user = this.userDao.findById(id.intValue())) != null) {
            UserDetailAppend hold = this.userDetailAppendDao.findByUserId(user.getId());
            if(hold != null) {
                userDetailAppend.setId(hold.getId());
                userDetailAppend.setUser(hold.getUser());
                this.userDetailAppendDao.alt(userDetailAppend);
                return "{\"hint\":\"success\"}";
            } else {
                return "{\"hint\":\"illegal_request\"}";
            }
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    public void setIdentifyDao(IdentifyDao identifyDao) {
        this.identifyDao = identifyDao;
    }

    public void setUserDetailAppendDao(UserDetailAppendDao userDetailAppendDao) {
        this.userDetailAppendDao = userDetailAppendDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
