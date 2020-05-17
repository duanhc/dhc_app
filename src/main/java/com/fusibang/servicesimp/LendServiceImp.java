//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.servicesimp;

import com.fusibang.dao.IdentifyDao;
import com.fusibang.dao.LendDao;
import com.fusibang.dao.UserDao;
import com.fusibang.help.ResponseStatus;
import com.fusibang.services.LendSercice;
import com.fusibang.tables.Identify;
import com.fusibang.tables.Lend;
import com.fusibang.tables.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

public class LendServiceImp extends ResponseStatus implements LendSercice {
    private UserDao userDao;
    private LendDao lendDao;
    private IdentifyDao identifyDao;
    private String appid;
    private String host;
    private String webName;
    private boolean guanzhu;

    public LendServiceImp() {
    }

    public String addLend(Lend lend, HttpSession session) {
        Integer id = (Integer)session.getAttribute("ui");
        User user = null;
        if(id != null && (user = this.userDao.findById(id.intValue())) != null) {
            Identify identify = this.identifyDao.findByUserId(user.getId());
            if(identify.getPut() == 1) {
                this.lendDao.del(user.getId());
                lend.setUser(user);
                this.lendDao.save(lend);
                identify.setLend(1);
                identify.setLend_count(lend.getLend_count());
                identify.setLend_time(lend.getLend_time());
                return "{\"hint\":\"success\"}";
            } else {
                return "{\"hint\":\"step_one_by_one\"}";
            }
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    public String altLendTime(Lend lend, HttpSession session) {
        Integer id = (Integer)session.getAttribute("ui");
        User user = null;
        if(id != null && (user = this.userDao.findById(id.intValue())) != null) {
            Identify identify = this.identifyDao.findByUserId(user.getId());
            if(identify.getPut() == 1) {
                identify.setLend_time(lend.getLend_time());
                return "{\"hint\":\"success\"}";
            } else {
                return "{\"hint\":\"step_one_by_one\"}";
            }
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    public String verifyLend(HttpSession session) {
        Integer id = (Integer)session.getAttribute("ui");
        User user = null;
        if(id != null && (user = this.userDao.findById(id.intValue())) != null) {
            Identify identify = this.identifyDao.findByUserId(user.getId());
            if(identify.getLend() != 0) {
                this.lendDao.verify(user.getId());
                identify.setLend(2);
                return "{\"hint\":\"success\"}";
            } else {
                return "{\"hint\":\"step_one_by_one\"}";
            }
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    public String verifyView(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer id = (Integer)session.getAttribute("ui");
        User user = null;
        if(id != null && (user = this.userDao.findById(id.intValue())) != null) {
            Lend lend = this.lendDao.findByUserId(user.getId());
            if(lend != null) {
                request.setAttribute("lend", lend);
                double lixi = (double)lend.getLend_count() * 0.07D * 0.01D;
                request.setAttribute("lixi", String.format("%.2f", new Object[]{Double.valueOf(lixi)}));
                return "success";
            } else {
                return "illegal_request";
            }
        } else {
            return "un_login";
        }
    }

    public String collectView(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Integer id = (Integer)session.getAttribute("ui");
        User user = null;
        if(id != null && (user = this.userDao.findById(id.intValue())) != null) {
            user.setOpenid("guanzhu");
            if(!this.guanzhu || user.getOpenid() != null && !user.getOpenid().equals("guanzhu")) {
                Lend e = this.lendDao.findByUserId(user.getId());
                if(e != null && e.getStatus() == 1) {
                    Identify identify = this.identifyDao.findByUserId(user.getId());
                    long time = ((new Date()).getTime() - e.getVerify_time().getTime()) / 1000L;
                    if(identify.getPay() == 1) {
                        time = ((new Date()).getTime() - identify.getPay_time().getTime()) / 1000L;
                    }

                    int radio = 0;
                    boolean success = false;
                    if(time > 201600L) {
                        radio = 100;
                        success = true;
                    } else if(time > 3600L && time <= 201600L) {
                        radio = (int)(23L + 7L * (time - 3600L) / 18000L);
                        if(identify.getPay() == 1 || radio >= 100) {
                            radio = 100;
                            success = true;
                        }
                    } else if(time < 3600L) {
                        radio = (int)(time / 180L + 3L);
                        if(identify.getPay() == 1) {
                            radio = (int)(time / 40L + 10L);
                            if(radio >= 100) {
                                radio = 100;
                                success = true;
                            }
                        }
                    }

                    request.setAttribute("identify", identify);
                    request.setAttribute("lend", e);
                    request.setAttribute("radio", Integer.valueOf(radio));
                    request.setAttribute("success", Boolean.valueOf(success));
                    return "success";
                } else {
                    return "illegal_request";
                }
            } else {
                try {
                    response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + this.appid + "&redirect_uri=http%3a%2f%2f" + this.host + "%2f" + this.webName + "%2fwechat_pay.action&response_type=code&scope=snsapi_userinfo&state=auth#wechat_redirect");
                } catch (IOException var12) {
                    var12.printStackTrace();
                }

                return null;
            }
        } else {
            return "un_login";
        }
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setLendDao(LendDao lendDao) {
        this.lendDao = lendDao;
    }

    public void setIdentifyDao(IdentifyDao identifyDao) {
        this.identifyDao = identifyDao;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }

    public void setGuanzhu(boolean guanzhu) {
        this.guanzhu = guanzhu;
    }
}
