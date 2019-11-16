//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.servicesimp;

import com.alibaba.fastjson.JSONObject;
import com.fusibang.dao.IdCardDao;
import com.fusibang.dao.IdentifyDao;
import com.fusibang.dao.UserDao;
import com.fusibang.dao.UserDetailDao;
import com.fusibang.help.JedisFactory;
import com.fusibang.help.ResponseStatus;
import com.fusibang.help.YeePayHelp;
import com.fusibang.services.UserDetailService;
import com.fusibang.tables.Channel;
import com.fusibang.tables.Identify;
import com.fusibang.tables.User;
import com.fusibang.tables.UserDetail;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserDetailServiceImp extends ResponseStatus implements UserDetailService {

    private static Logger logger = Logger.getLogger(YeePayHelp.class);

    private UserDetailDao userDetailDao;
    private UserDao userDao;
    private IdentifyDao identifyDao;
    private IdCardDao idCardDao;

    /**
     * 易宝支付
     */
    private YeePayHelp yeePayHelp;

    private JedisFactory jedisFactory;

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
        logger.info("altDetail-----------------:" + userDetail.getReserved_number());
        Integer id = (Integer)session.getAttribute("ui");
        if (id != null) {
            User user = this.userDao.findById(id.intValue());
            UserDetail hold = this.userDetailDao.findByUserId(user.getId());
            if (hold != null) {
                Map<String, String> map = getFirstParamMap(hold, userDetail);
                // 首次支付
                String result = yeePayHelp.unionFirstPay(map);
                JSONObject jsonObject = JSONObject.parseObject(result);
                String errormsg = (String)jsonObject.get("errormsg");

                if (errormsg == null) {
                    hold.setPut_time(new Timestamp((new Date()).getTime()));
                    hold.setCredit_number(userDetail.getCredit_number());
                    hold.setCredit_name(userDetail.getCredit_name());
                    hold.setReserved_number(userDetail.getReserved_number());
                    hold.setName(userDetail.getName());
                    hold.setId_card(userDetail.getId_card());

                    this.userDetailDao.altDetail(hold);

                    Identify identify = this.identifyDao.findByUserId(user.getId());
                    identify.setLend(1);
                    // 保存订单号
                    identify.setRequestno(map.get("requestno"));

                    // 控制重发发送验证码的次数
                    Jedis jedis = this.jedisFactory.getInstance();
                    try {
                        String key = "rs" + user.getPhone_number();
                        jedis.set(key, map.get("requestno"));
                        jedis.expire(key, 60);
                        jedis.incr(key + "i");
                        jedis.expire(key + "i", 1800);
                    } catch (Exception e) {
                        e.printStackTrace();;
                    } finally {
                        jedis.close();
                    }

                    return "{\"hint\":\"unionFirstPay_success\"}";
                } else {
                    try {
                        errormsg = errormsg.replaceAll("参数错误:", "").replaceAll("idcardno", "身份证号码").replaceAll("cardno", "银行卡号").replaceAll("username", "姓名").replaceAll("phone", "手机号");
                        errormsg = URLEncoder.encode(errormsg, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    return "{\"hint\":\"" + errormsg + "\"}";
                }
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
        UserDetail hold = this.userDetailDao.findByUserId(user.getId());
        // if (user != null && idCard != null) {
        if (user != null) {
            Identify identify = this.identifyDao.findByUserId(user.getId());
            if (hold != null) {
                request.setAttribute("name", null == hold.getName() ? "" : hold.getName());
                request.setAttribute("idcard", null == hold.getId_card() ? "" : hold.getId_card());
                request.setAttribute("reserved_number", null == user.getPhone_number() ? "" : user.getPhone_number());
                request.setAttribute("credit_number", null == hold.getCredit_number() ? "" : hold.getCredit_number());
                request.setAttribute("credit_name", null == hold.getCredit_name() ? "" : hold.getCredit_name());
                request.setAttribute("lend", identify.getLend());
            }

            if (identify.getStep3() == 0) {
                return "step3";
            } else {
                return identify.getStep4() == 0 ? "step4" : (identify.getStep6() == 0) ? "step6" : "success";
            }
        } else {
            return "un_login";
        }
    }

    @Override
    public String smsConfirm(UserDetail userDetail, HttpSession session) {
        logger.info("smsConfirm-----------------:" + userDetail.getValidatecode());
        Integer id = (Integer)session.getAttribute("ui");
        if (id != null) {
            User user = this.userDao.findById(id.intValue());
            UserDetail hold = this.userDetailDao.findByUserId(user.getId());
            if (hold != null) {
                Identify identify = this.identifyDao.findByUserId(user.getId());
                String validatecode = userDetail.getValidatecode();
                Map<String, String> map = new HashMap<>();
                map.put("requestno", identify.getRequestno());
                map.put("validatecode", validatecode);
                // 首次支付
                String result = yeePayHelp.firstPaySmsConfirm(map);
                JSONObject jsonObject = JSONObject.parseObject(result);
                String errormsg = (String)jsonObject.get("errormsg");

                if (errormsg == null) {
                    this.identifyDao.findByUserId(user.getId()).setStep6(1);
                    // 设置有效量
                    user.setValid(1);
                    // 设置注册时间
                    Date regTime = new Date(user.getRegiste_time().getTime());
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new Date());
                    int n_day = calendar.get(6);
                    calendar.setTime(regTime);
                    int r_day = calendar.get(6);
                    if (n_day != r_day) {
                        Timestamp now = new Timestamp((new Date()).getTime());
                        user.setRegiste_time(now);
                    }
                    // 设置渠道
                    Channel channel = user.getChannel();
                    channel.setMonth1(channel.getMonth1() + 1);
                    channel.setPay(channel.getPay() + 1);
                    channel.setPay1(channel.getPay1() + 1);

                    return "{\"hint\":\"success\"}";
                } else {
                    try {
                        errormsg = URLEncoder.encode(errormsg, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    return "{\"hint\":\"" + errormsg + "\"}";
                }
            } else {
                return "{\"hint\":\"account_not_found\"}";
            }
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    @Override
    public String smsResend(UserDetail userDetail, HttpSession session) {
        logger.info("smsResend-----------------");
        Integer id = (Integer)session.getAttribute("ui");
        if (id != null) {
            User user = this.userDao.findById(id.intValue());
            UserDetail hold = this.userDetailDao.findByUserId(user.getId());
            if (hold != null) {
                Identify identify = this.identifyDao.findByUserId(user.getId());
                Map<String, String> map = new HashMap<>();
                map.put("requestno", identify.getRequestno());
                // 重发验证码
                String result = yeePayHelp.firstPaySmsReSend(map);
                JSONObject jsonObject = JSONObject.parseObject(result);
                String errormsg = (String)jsonObject.get("errormsg");

                if (errormsg == null) {

                    // 控制重发发送验证码的次数
                    Jedis jedis = this.jedisFactory.getInstance();
                    try {
                        String key = "rs" + user.getPhone_number();
                        if (jedis.exists(key).booleanValue() || jedis.exists(key + "i").booleanValue() && Integer.parseInt(jedis.get(key + "i")) > 5) {
                            return "{\"hint\":\"too_frequently\"}";
                        } else if (!(jedis.exists(key + "i").booleanValue())) {
                            // 订单过期，重试
                            return "{\"hint\":\"re_try\"}";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();;
                    } finally {
                        jedis.close();
                    }

                    return "{\"hint\":\"firstPaySmsResend_success\"}";
                } else {
                    try {
                        errormsg = URLEncoder.encode(errormsg, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    return "{\"hint\":\"" + errormsg + "\"}";
                }
            } else {
                return "{\"hint\":\"account_not_found\"}";
            }
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    /**
     * 首次支付参数
     * 
     *
     * @param userDetail
     * @param hold
     * @return
     */
    private Map<String, String> getFirstParamMap(UserDetail hold, UserDetail userDetail) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("requestno", "u" + hold.getId() + "t" + System.currentTimeMillis());
        map.put("identityid", userDetail.getId_card());
        map.put("identitytype", "ID_CARD");
        map.put("cardno", userDetail.getCredit_number());
        map.put("idcardno", userDetail.getId_card());
        map.put("idcardtype", "ID");
        map.put("terminalno", "SQKKSCENE10");
        map.put("username", hold.getName());
        map.put("phone", hold.getUser().getPhone_number());
        map.put("authtype", "COMMON_FOUR");
        map.put("issms", "true");

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String requesttime = df.format(new Date()).toString();
        map.put("requesttime", requesttime);

        return map;
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

    public void setJedisFactory(JedisFactory jedisFactory) {
        this.jedisFactory = jedisFactory;
    }

    public void setYeePayHelp(YeePayHelp yeePayHelp) {
        this.yeePayHelp = yeePayHelp;
    }
}
