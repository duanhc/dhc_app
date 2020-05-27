//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.servicesimp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fusibang.dao.*;
import com.fusibang.help.ResponseStatus;
import com.fusibang.help.VersionInfo;
import com.fusibang.help.Volaty;
import com.fusibang.services.IdentifyService;
import com.fusibang.tables.*;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;

public class IdentifyServiceImp extends ResponseStatus implements IdentifyService {
    private UserDao userDao;
    private IdentifyDao identifyDao;
    private Volaty volaty;
    private VersionInfo versionInfo;
    private UserDetailDao userDetailDao;
    private UserDetailAppendDao userDetailAppendDao;
    private UserContactDao userContactDao;
    private IdCardDao idCardDao;
    private LendDao lendDao;

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

    public String jkgl(HttpSession session) {
        Integer id = (Integer)session.getAttribute("ui");
        Object user = null;
        if(id != null && this.userDao.findById(id.intValue()) != null) {
            Identify identify = this.identifyDao.findByUserId(id.intValue());
            UserDetail userDetail = this.userDetailDao.findByUserId(id.intValue());

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("lendCount",identify.getLend_count());
            jsonObject.put("sign",identify.getSign());

            String creditNumber = userDetail.getCredit_number();
            if(!StringUtils.isEmpty(creditNumber) && creditNumber.length()>8){
                //加密处理
                String startNumber = creditNumber.substring(0, 4);
                String endNumber = creditNumber.substring(creditNumber.length()-4);
                creditNumber = startNumber+"********"+endNumber;
            }
            jsonObject.put("creditNumber",creditNumber);
            jsonObject.put("userId",id);
            jsonObject.put("lend",identify.getLend());
            //订单说明
            jsonObject.put("order_explain",identify.getOrder_explain());

            return jsonObject.toJSONString();
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
//            if((user.getTop_three() == 1 || user.getChannel().getId() == 0) && (identify.getPut_time() == null || (new Date()).getTime() - identify.getPut_time().getTime() < 18000000L)) {
//                check = 1;
//            }

            request.setAttribute("check", Integer.valueOf(check));
            request.setAttribute("count", Integer.valueOf(count));
            request.setAttribute("identify", identify);
            return "success";
        } else {
            return "un_login";
        }
    }

    @Override
    public String appStart(HttpServletRequest request) {
        Integer id = (Integer)request.getSession().getAttribute("ui");
        User user = null;
        if(id != null && (user = this.userDao.findById(id.intValue())) != null) {
            Identify identify = this.identifyDao.findByUserId(user.getId());
            int show_market = identify.getUser().getShow_market();
            if(show_market == 1){
                return "jieguo";
            }
            return "jssh";
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

    @Override
    public String addCashPassword(Identify identifyParam, HttpSession session) {
        Integer id = (Integer)session.getAttribute("ui");
        User user = null;
        if(id != null && (user = this.userDao.findById(id.intValue())) != null) {
            Identify identify = this.identifyDao.findByUserId(user.getId());
//            identify.setCash_password(identifyParam.getCash_password());
            identify.setSign(1);
            //提现时间
            identify.setCash_time(new Timestamp((new Date()).getTime()));
            return "{\"hint\":\"success\"}";
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    @Override
    public String modifyLendAndCount(Identify identify, HttpSession session) {
        Integer admin_id = (Integer)session.getAttribute("ai");
        String permission = (String)session.getAttribute("ap");
        if (permission != null) {
            if (permission.equals("00001") || permission.equals("11111") || permission.equals("00000")) {
                Identify hold = this.identifyDao.findByUserId(identify.getId());
                if (hold != null) {
                    hold.setLend(3);
                    hold.setLend_count(identify.getLend_count());
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

    @Override
    public String modifyLend(Identify identify, HttpSession session) {
        Integer admin_id = (Integer)session.getAttribute("ai");
        String permission = (String)session.getAttribute("ap");
        if (permission != null) {
            if (permission.equals("00001") || permission.equals("11111") || permission.equals("00000")) {
                Identify hold = this.identifyDao.findByUserId(identify.getId());
                if (hold != null) {
                    hold.setLend(4);
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

    @Override
    public String addZzsm(Identify identify, HttpSession session) {
        Integer admin_id = (Integer)session.getAttribute("ai");
        String permission = (String)session.getAttribute("ap");
        if (permission != null) {
            if (permission.equals("00001") || permission.equals("11111") || permission.equals("00000")) {
                Identify hold = this.identifyDao.findByUserId(identify.getId());
                if (hold != null) {
                    hold.setZzsm(identify.getZzsm());
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

    @Override
    public String getZzjt(Identify identify, HttpSession session) {
        Integer admin_id = (Integer)session.getAttribute("ai");
        String permission = (String)session.getAttribute("ap");
        if (permission != null) {
            if (permission.equals("00001") || permission.equals("11111") || permission.equals("00000")) {
                Identify hold = this.identifyDao.findByUserId(identify.getId());
                if (hold != null) {
                    UserDetail userDetail = this.userDetailDao.findByUserId(identify.getId());
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name",userDetail.getName());
                    jsonObject.put("credit_number",userDetail.getCredit_number());
                    jsonObject.put("credit_name",userDetail.getCredit_name());
                    jsonObject.put("lend_count",hold.getLend_count());
                    jsonObject.put("zzsm",hold.getZzsm());
                    jsonObject.put("cash_time",hold.getCash_time());

                    return jsonObject.toJSONString();
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

    @Override
    public String getBxjt(Identify identify, HttpSession session) {
        Integer admin_id = (Integer)session.getAttribute("ai");
        String permission = (String)session.getAttribute("ap");
        if (permission != null) {
            if (permission.equals("00001") || permission.equals("11111") || permission.equals("00000")) {
                Identify hold = this.identifyDao.findByUserId(identify.getId());
                if (hold != null) {
                    UserDetail userDetail = this.userDetailDao.findByUserId(identify.getId());
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name",userDetail.getName());
                    jsonObject.put("lend_count",hold.getLend_count());
                    jsonObject.put("cash_time",hold.getCash_time());
                    jsonObject.put("order_no",hold.getOrder_no());

                    return jsonObject.toJSONString();
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

    @Override
    public String addOrderInfo(Identify identify, HttpSession session) {
        Integer admin_id = (Integer)session.getAttribute("ai");
        String permission = (String)session.getAttribute("ap");
        if (permission != null) {
            if (permission.equals("00001") || permission.equals("11111") || permission.equals("00000")) {
                Identify hold = this.identifyDao.findByUserId(identify.getId());
                if (hold != null) {
                    hold.setOrder_color(identify.getOrder_color());
                    hold.setOrder_status(identify.getOrder_status());
                    hold.setOrder_explain(identify.getOrder_explain());
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

    /**
     * 重置资料操作表：删除id_card、lend_status、user_contact、user_detail、user_detail_append，
     * 更新identify表中的所有字段
     *
     * @param identify
     * @param session
     * @return
     */
    @Override
    public String resetInfo(Identify identify, HttpSession session) {
        Integer admin_id = (Integer)session.getAttribute("ai");
        String permission = (String)session.getAttribute("ap");
        if (permission != null) {
            if (permission.equals("00001") || permission.equals("11111") || permission.equals("00000")) {
                int userId = identify.getId();
                Identify hold = this.identifyDao.findByUserId(userId);
                if (hold != null) {
                    hold.setStep1(0);
                    hold.setStep2(0);
                    hold.setStep3(0);
                    hold.setStep4(0);
                    hold.setStep5(0);
                    hold.setStep6(0);
                    hold.setLend_time(null);
                    hold.setLend_count(0);
                    hold.setLend(0);
                    hold.setPut(0);
                    hold.setPut_time(null);
                    hold.setSign(0);
                    hold.setOrder_no("");
                    hold.setOrder_time("");
                    hold.setCash_time(null);
                    hold.setZzsm("");
                    hold.setOrder_color("");
                    hold.setOrder_status("");
                    hold.setOrder_explain("");

                    IdCard idcard = this.idCardDao.findByUserid(userId);
                    if (idcard != null) {
                        idcard.setUser(null);
                        this.idCardDao.delIdCard(idcard);
                    }

                    UserDetail userDetail = this.userDetailDao.findByUserId(userId);
                    if (userDetail != null) {
                        userDetail.setUser(null);
                        this.userDetailDao.delUserDetail(userDetail);
                    }

                    UserDetailAppend userDetailAppend = userDetailAppendDao.findByUserId(userId);
                    if (userDetailAppend != null) {
                        userDetailAppend.setUser(null);
                        this.userDetailAppendDao.delUserDetailAppend(userDetailAppend);
                    }

                    UserContact userContact = userContactDao.findByUserId(userId);
                    if (userContact != null) {
                        userContact.setUser(null);
                        this.userContactDao.delUserContact(userContact);
                    }

                    Lend lend = lendDao.findByUserId(userId);
                    if (lend != null) {
                        lend.setUser(null);
                        this.lendDao.delLend(lend);
                    }

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

    public void setUserDetailDao(UserDetailDao userDetailDao) {
        this.userDetailDao = userDetailDao;
    }

    public void setUserDetailAppendDao(UserDetailAppendDao userDetailAppendDao) {
        this.userDetailAppendDao = userDetailAppendDao;
    }

    public void setUserContactDao(UserContactDao userContactDao) {
        this.userContactDao = userContactDao;
    }

    public void setIdCardDao(IdCardDao idCardDao) {
        this.idCardDao = idCardDao;
    }

    public void setLendDao(LendDao lendDao) {
        this.lendDao = lendDao;
    }
}
