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
import org.apache.commons.lang3.StringUtils;

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

    public String altDetail(String code,UserDetail userDetail, HttpSession session) {
        Integer id = (Integer)session.getAttribute("ui");
        if (id != null) {
            User user = this.userDao.findById(id.intValue());
            String phone_number = user.getPhone_number();
            if(code != null && code.equals(session.getAttribute("vb" + phone_number))) {
                if (!this.userDetailDao.exist(user.getId())) {
                    IdCard idCard = this.idCardDao.findByUserid(id.intValue());
                    userDetail.setId_card(idCard.getNum());
                    userDetail.setName(idCard.getName());
                    userDetail.setCity(idCard.getAddress());
                    userDetail.setUser(user);
                    userDetail.setPut_time(new Timestamp((new Date()).getTime()));
                    this.userDetailDao.addDetail(userDetail);
                    Identify identify = this.identifyDao.findByUserId(user.getId());
                    identify.setStep6(1);
                    identify.setPut(1);
                    session.removeAttribute("vm" + phone_number);
                    return "{\"hint\":\"success\"}";
                } else {
                    return "{\"hint\":\"already_exist\"}";
                }
            }else {
                return  "{\"hint\":\"phone_code_error\"}";
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
            String name = idCard.getName();
            name = (name == null ? "" : name);
            if(name.length() > 1){
                String lastName = name.substring(0, 1);
                String starName = "";
                for(int i=1;i<name.length();i++){
                    starName += "*";
                }
                name = lastName+starName;
            }
            String num = idCard.getNum();
            num = (num == null ? "" : num);
            if(num.length() > 10){
                StringBuilder sb = new StringBuilder(num);
                sb.replace(6, 14, StringUtils.repeat("*", 8));
                num = sb.toString();
            }
            request.setAttribute("name", name);
            request.setAttribute("idcard", num);
            return "step6";
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
