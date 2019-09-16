//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.servicesimp;

import com.fusibang.butterfly.Back;
import com.fusibang.butterfly.Front;
import com.fusibang.butterfly.IdCardAuth;
import com.fusibang.dao.IdCardDao;
import com.fusibang.dao.IdentifyDao;
import com.fusibang.dao.UserDao;
import com.fusibang.help.ResponseStatus;
import com.fusibang.services.IdCardService;
import com.fusibang.tables.IdCard;
import com.fusibang.tables.Identify;
import com.fusibang.tables.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class IdCardServiceImp extends ResponseStatus implements IdCardService {
    private IdCardDao idCardDao;
    private UserDao userDao;
    private IdentifyDao identifyDao;
    private IdCardAuth idCardAuth = new IdCardAuth();

    public IdCardServiceImp() {
    }

    public String saveFont(HttpSession session, IdCard idCard) {
        Integer id = (Integer)session.getAttribute("ui");
        User user = null;
        if(id != null && (user = this.userDao.findById(id.intValue())) != null) {
            IdCard hold = this.idCardDao.existNum(idCard.getNum());
            if(hold != null && hold.getUser().getId() != user.getId()) {
                return "{\"hint\":\"already_exist\"}";
            } else {
                idCard.setUser(user);
                idCard.setFont(1);
                this.identifyDao.findByUserId(user.getId()).setStep1(1);
                this.idCardDao.save(idCard);
                return "{\"hint\":\"success\"}";
            }
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    public String saveBack(HttpSession session, IdCard idCard) {
        Integer id = (Integer)session.getAttribute("ui");
        IdCard hold = null;
        if(id != null) {
            if((hold = this.idCardDao.findByUserid(id.intValue())) != null) {
                hold.setIssueAuthority(idCard.getIssueAuthority());
                hold.setSignDate(idCard.getSignDate());
                hold.setExpiryDate(idCard.getExpiryDate());
                hold.setBack(1);
                this.identifyDao.findByUserId(id.intValue()).setStep2(1);
                return "{\"hint\":\"success\"}";
            } else {
                return "{\"hint\":\"step_one_by_one\"}";
            }
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    public String authFront(HttpServletRequest request, String image) {
        HttpSession session = request.getSession();
        Integer id = (Integer)session.getAttribute("ui");
        User user = this.userDao.findById(id.intValue());
        if(user != null) {
            try {
                Front e = this.idCardAuth.authFront(image);
                if(e.getStatus().equals("success")) {
                    IdCard hold = this.idCardDao.existNum(e.getCar_id());
                    if(hold != null && hold.getUser().getId() != user.getId()) {
                        this.identifyDao.findByUserId(user.getId()).setStep1(1);
                        return "{\"hint\":\"already_exist\"}";
                    } else {
                        IdCard idCard = new IdCard();
                        idCard.setUser(user);
                        idCard.setGender(e.getGender());
                        idCard.setAddress(e.getAddress());
                        idCard.setName(e.getName());
                        idCard.setNation(e.getNation());
                        idCard.setNum(e.getCar_id());
                        idCard.setFont(1);
                        this.identifyDao.findByUserId(user.getId()).setStep1(1);
                        this.idCardDao.save(idCard);
                        return "{\"hint\":\"success\"}";
                    }
                } else {
                    IdCard idCard = new IdCard();
                    idCard.setUser(user);
                    idCard.setFont(1);
                    this.identifyDao.findByUserId(user.getId()).setStep1(1);
                    this.idCardDao.save(idCard);
                    return "{\"hint\":\"" + e.getStatus() + "\"}";
                }
            } catch (Exception var9) {
                IdCard idCard = new IdCard();
                idCard.setUser(user);
                idCard.setFont(1);
                this.identifyDao.findByUserId(user.getId()).setStep1(1);
                this.idCardDao.save(idCard);
                var9.printStackTrace();
                return "{\"hint\":\"unknow_error\"}";
            }
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    public String authBack(HttpServletRequest request, String image) {
        HttpSession session = request.getSession();
        Integer id = (Integer)session.getAttribute("ui");
        IdCard hold = null;
        if(id != null) {
            if((hold = this.idCardDao.findByUserid(id.intValue())) != null) {
                try {
                    Back back = this.idCardAuth.authBack(image);
                    if(back.getStatus().equals("success")) {
                        IdCard e = new IdCard();
                        e.setExpiryDate(back.getEnd());
                        e.setIssueAuthority(back.getAuth());
                        e.setSignDate(back.getStart());
                        hold.setIssueAuthority(e.getIssueAuthority());
                        hold.setSignDate(e.getSignDate());
                        hold.setExpiryDate(e.getExpiryDate());
                        hold.setBack(1);
                        this.identifyDao.findByUserId(id.intValue()).setStep2(1);
                        return "{\"hint\":\"success\"}";
                    } else {
                        this.identifyDao.findByUserId(id.intValue()).setStep2(1);
                        return "{\"hint\":\"" + back.getStatus() + "\"}";
                    }
                } catch (Exception var8) {
                    this.identifyDao.findByUserId(id.intValue()).setStep2(1);
                    var8.printStackTrace();
                    return "{\"hint\":\"unknow_error\"}";
                }
            } else {
                return "{\"hint\":\"step_one_by_one\"}";
            }
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    public String view(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer id = (Integer)session.getAttribute("ui");
        User user = this.userDao.findById(id.intValue());
        if(user != null) {
            Identify identify = this.identifyDao.findByUserId(user.getId());
            request.setAttribute("identify", identify);
            return "success";
        } else {
            return "un_login";
        }
    }

    public void setIdCardDao(IdCardDao idCardDao) {
        this.idCardDao = idCardDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setIdentifyDao(IdentifyDao identifyDao) {
        this.identifyDao = identifyDao;
    }
}
