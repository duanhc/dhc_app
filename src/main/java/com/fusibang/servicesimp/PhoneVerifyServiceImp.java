//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.servicesimp;

import com.fusibang.dao.UserDao;
import com.fusibang.help.JedisFactory;
import com.fusibang.help.PhoneVerifyMessage;
import com.fusibang.help.ResponseStatus;
import com.fusibang.help.SendPhoneVerify;
import com.fusibang.services.PhoneVerifyService;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpSession;

public class PhoneVerifyServiceImp extends ResponseStatus implements PhoneVerifyService {
    private String model;
    private int interval;
    private UserDao userDao;
    private JedisFactory jedisFactory;
    private SendPhoneVerify phoneVerify;
    private PhoneVerifyMessage phoneVerifyMessage;

    public PhoneVerifyServiceImp() {
    }

    public String send2Registe(String phone, HttpSession session) {
        String key = "vr" + phone;
        if(!this.userDao.existUser(phone)) {
            Jedis jedis = this.jedisFactory.getInstance();

            try {
                if(jedis.exists(key).booleanValue() || jedis.exists(phone + "i").booleanValue() && Integer.parseInt(jedis.get(phone + "i")) > 10) {
                    return "{\"hint\":\"too_frequently\"}";
                }

                String code = this.phoneVerify.getRandomcode(5);
                if(this.phoneVerify.sendVerifyCodeRegiste(phone, code)) {
                    jedis.set(key, code);
                    jedis.expire(key, this.interval);
                    jedis.incr(phone + "i");
                    jedis.expire(phone + "i", 3600);
                    session.setAttribute(key,code);
                    session.removeAttribute("i");
                    return "{\"hint\":\"success\"}";
                }

                return "{\"hint\":\"unknow_error\"}";
            } catch (Exception var9) {
                var9.printStackTrace();
            } finally {
                jedis.close();
            }

            return "{\"hint\":\"unknow_error\"}";
        } else {
            session.removeAttribute("i");
            return "{\"hint\":\"already_exist\"}";
        }
    }

    public String send2ChmodPwd(String phone, HttpSession session) {
        String key = "vm" + phone;
        if(this.userDao.existUser(phone)) {
            Jedis jedis = this.jedisFactory.getInstance();

            try {
                if(jedis.exists(key).booleanValue() || jedis.exists(phone + "i").booleanValue() && Integer.parseInt(jedis.get(phone + "i")) > 10) {
                    return "{\"hint\":\"too_frequently\"}";
                }

                String code = this.phoneVerify.getRandomcode(5);
                if(!this.phoneVerify.sendVerifyCodeAltPassword(phone, code)) {
                    return "{\"hint\":\"unknow_error\"}";
                }

                jedis.set(key, code);
                jedis.expire(key, this.interval);
                jedis.incr(phone + "i");
                jedis.expire(phone + "i", 3600);
                session.setAttribute(key,code);
                session.removeAttribute("i");
                return "{\"hint\":\"success\"}";
            } catch (Exception var9) {
                var9.printStackTrace();
            } finally {
                jedis.close();
            }

            return "{\"hint\":\"unknow_error\"}";
        } else {
            session.removeAttribute("i");
            return "{\"hint\":\"account_not_found\"}";
        }
    }

    public String send2Login(String phone, HttpSession session) {
        String key = "vl" + phone;
        if(this.userDao.existUser(phone)) {
            Jedis jedis = this.jedisFactory.getInstance();

            try {
                if(jedis.exists(key).booleanValue() || jedis.exists(phone + "i").booleanValue() && Integer.parseInt(jedis.get(phone + "i")) > 10) {
                    return "{\"hint\":\"too_frequently\"}";
                }

                String code = this.phoneVerify.getRandomcode(5);
                if(this.phoneVerify.sendVerifyCodeLogin(phone, code)) {
                    jedis.incr(phone + "i");
                    jedis.expire(phone + "i", 3600);
                    jedis.set(key, code);
                    jedis.expire(key, this.interval);
                    session.setAttribute(key, code);
                    return "{\"hint\":\"success\"}";
                }
            } catch (Exception var9) {
                var9.printStackTrace();
                return "{\"hint\":\"unknow_error\"}";
            } finally {
                jedis.close();
            }

            return "{\"hint\":\"unknow_error\"}";
        } else {
            session.removeAttribute("i");
            return "{\"hint\":\"account_not_found\"}";
        }
    }

    @Override
    public String send2bankcard(String phone, HttpSession session) {
        Integer id = (Integer)session.getAttribute("ui");
        if(id != null && this.userDao.findById(id.intValue())!= null ) {
            String key = "vb" + phone;
            Jedis jedis = this.jedisFactory.getInstance();
            try {
                if(jedis.exists(key).booleanValue() || jedis.exists(phone + "i").booleanValue() && Integer.parseInt(jedis.get(phone + "i")) > 10) {
                    return "{\"hint\":\"too_frequently\"}";
                }

                String code = this.phoneVerify.getRandomcode(6);
                if(!this.phoneVerify.sendVerifyCodeAltPassword(phone, code)) {
                    return "{\"hint\":\"unknow_error\"}";
                }

                jedis.set(key, code);
                jedis.expire(key, this.interval);
                jedis.incr(phone + "i");
                jedis.expire(phone + "i", 3600);
                session.setAttribute(key,code);
                return "{\"hint\":\"success\"}";
            } catch (Exception var9) {
                var9.printStackTrace();
            } finally {
                jedis.close();
            }

        } else {
            return "{\"hint\":\"un_login\"}";
        }

        return "{\"hint\":\"unknow_error\"}";

    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setJedisFactory(JedisFactory jedisFactory) {
        this.jedisFactory = jedisFactory;
    }

    public int getInterval() {
        return this.interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void setPhoneVerify(SendPhoneVerify phoneVerify) {
        this.phoneVerify = phoneVerify;
    }

    public void setPhoneVerifyMessage(PhoneVerifyMessage phoneVerifyMessage) {
        this.phoneVerifyMessage = phoneVerifyMessage;
    }
}
