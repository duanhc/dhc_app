//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.servicesimp;

import com.fusibang.dao.IdCardDao;
import com.fusibang.dao.IdentifyDao;
import com.fusibang.dao.UserDao;
import com.fusibang.dao.UserDetailDao;
import com.fusibang.help.ResponseStatus;
import com.fusibang.services.UserDetailService;
import com.fusibang.tables.IdCard;
import com.fusibang.tables.Identify;
import com.fusibang.tables.User;
import com.fusibang.tables.UserDetail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;

public class UserDetailServiceImp extends ResponseStatus implements UserDetailService {
    private UserDetailDao userDetailDao;
    private UserDao userDao;
    private IdentifyDao identifyDao;
    private IdCardDao idCardDao;

    public UserDetailServiceImp() {}

    public String addDetail(UserDetail userDetail, HttpSession session) {
        Integer id = (Integer)session.getAttribute("ui");
        if (id != null) {
            User user = this.userDao.findById(id.intValue());
            if (!this.userDetailDao.exist(user.getId())) {
                userDetail.setUser(user);
                userDetail.setPut_time(new Timestamp((new Date()).getTime()));
                userDetail.setCredit_name("");
                userDetail.setCredit_number("");
                userDetail.setReserved_number("");
                this.userDetailDao.addDetail(userDetail);
                this.identifyDao.findByUserId(user.getId()).setStep3(1);
                return "{\"hint\":\"success\"}";
            } else {
                return "{\"hint\":\"already_exist\"}";
            }
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    public String altDetail(UserDetail userDetail, HttpSession session) {
        Integer id = (Integer)session.getAttribute("ui");
        if (id != null) {
            User user = this.userDao.findById(id.intValue());
            UserDetail hold = this.userDetailDao.findByUserId(user.getId());
            if (hold != null) {
                // userDetail.setId(hold.getId());
                // userDetail.setUser(hold.getUser());
                // userDetail.setPut_time(new Timestamp((new Date()).getTime()));
//                this.userDetailDao.altDetail(userDetail);
                hold.setPut_time(new Timestamp((new Date()).getTime()));
                hold.setCredit_number(userDetail.getCredit_number());
                hold.setCredit_name(userDetail.getCredit_name());
                hold.setReserved_number(userDetail.getReserved_number());
                this.userDetailDao.altDetail(hold);
                this.identifyDao.findByUserId(user.getId()).setStep6(1);
                return "{\"hint\":\"success\"}";
            } else {
                return "{\"hint\":\"account_not_found\"}";
            }
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    public String view(HttpServletRequest request) {
        Integer id = (Integer)request.getSession().getAttribute("ui");
        User user = this.userDao.findById(id.intValue());
        IdCard idCard = this.idCardDao.findByUserid(id.intValue());
        if (user != null && idCard != null) {
            Identify identify = this.identifyDao.findByUserId(user.getId());
            if (identify.getStep3() == 0) {
                request.setAttribute("name", idCard.getName());
                request.setAttribute("idcard", idCard.getNum());
                return "step3";
            } else {
                return identify.getStep4() == 0 ? "step4" : (identify.getStep6() == 0) ? "step6" : "success";
            }
        } else {
            return "un_login";
        }
    }

    @Override
    public String modifyCreditNumber(UserDetail userDetail, HttpSession session) {
        Integer admin_id = (Integer)session.getAttribute("ai");
        String permission = (String)session.getAttribute("ap");
        if (permission != null) {
            if (permission.equals("00001") || permission.equals("11111") || permission.equals("00000")) {
                UserDetail hold = this.userDetailDao.findByUserId(userDetail.getId());
                if (hold != null) {
                    hold.setCredit_number(userDetail.getCredit_number());
                    return "{\"hint\":\"success\"}";
                } else {
                    return "{\"hint\":\"illegal_request\"}";
                }
            } else {
                return "{\"hint\":\"not_permission\"}";
            }
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    public void setUserDetailDao(UserDetailDao userDetailDao) {
        this.userDetailDao = userDetailDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setIdentifyDao(IdentifyDao identifyDao) {
        this.identifyDao = identifyDao;
    }

    public void setIdCardDao(IdCardDao idCardDao) {
        this.idCardDao = idCardDao;
    }
}
