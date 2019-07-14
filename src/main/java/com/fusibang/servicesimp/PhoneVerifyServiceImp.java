//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.servicesimp;

import javax.servlet.http.HttpSession;

import com.fusibang.dao.UserDao;
import com.fusibang.help.JedisFactory;
import com.fusibang.help.PhoneVerify;
import com.fusibang.help.PhoneVerifyMessage;
import com.fusibang.help.ResponseStatus;
import com.fusibang.services.PhoneVerifyService;

import redis.clients.jedis.Jedis;

public class PhoneVerifyServiceImp extends ResponseStatus implements PhoneVerifyService {
    private String model;
    private int interval;
    private UserDao userDao;
    private JedisFactory jedisFactory;
    private PhoneVerify phoneVerify;
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

                String e = this.phoneVerify.getRandomVerifyCode(5);
                if(this.phoneVerify.send2Registe(phone, e, this.model)) {
                    jedis.set(key, e);
                    jedis.expire(key, this.interval);
                    jedis.incr(phone + "i");
                    jedis.expire(phone + "i", 3600);
                    session.setAttribute(key, e);
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

                String e = this.phoneVerify.getRandomVerifyCode(5);
                if(!this.phoneVerify.send2ModifyPwd(phone, e, this.model)) {
                    return "{\"hint\":\"unknow_error\"}";
                }

                jedis.set(key, e);
                jedis.expire(key, this.interval);
                jedis.incr(phone + "i");
                jedis.expire(phone + "i", 3600);
                session.setAttribute(key, e);
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

                String e = this.phoneVerify.getRandomVerifyCode(5);
                if(this.phoneVerify.send2Login(phone, e, this.model)) {
                    jedis.incr(phone + "i");
                    jedis.expire(phone + "i", 3600);
                    jedis.set(key, e);
                    jedis.expire(key, this.interval);
                    session.setAttribute(key, e);
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

    public void setPhoneVerify(PhoneVerify phoneVerify) {
        this.phoneVerify = phoneVerify;
    }

    public void setPhoneVerifyMessage(PhoneVerifyMessage phoneVerifyMessage) {
        this.phoneVerifyMessage = phoneVerifyMessage;
    }
}
