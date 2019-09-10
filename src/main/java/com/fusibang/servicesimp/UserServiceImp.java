//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.servicesimp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fusibang.dao.AdminDao;
import com.fusibang.dao.ChannelDao;
import com.fusibang.dao.UserDao;
import com.fusibang.help.*;
import com.fusibang.services.UserService;
import com.fusibang.tables.Admin;
import com.fusibang.tables.Channel;
import com.fusibang.tables.User;

import redis.clients.jedis.Jedis;

public class UserServiceImp extends ResponseStatus implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImp.class);
    private UserDao userDao;
    private ChannelDao channelDao;
    private AdminDao adminDao;
    private JedisFactory jedisFactory;
    private PayHelp payHelp;
    private String host;
    private String webName;

    public UserServiceImp() {}

    public String loginUser(String phone, String pwd, HttpSession session) {
        User user = this.userDao.findByPhone(phone);
        if (user != null) {
            String token = (new MD5()).getMD5ofStr(phone + pwd + user.getSalt());
            if (token.equals(user.getPassword())) {
                session.setAttribute("ui", Integer.valueOf(user.getId()));
                Jedis jedis = this.jedisFactory.getInstance();
                jedis.set(phone, token);
                jedis.set(phone + "id", String.valueOf(user.getId()));
                jedis.expire(phone + "id", Config.tokenTime);
                jedis.expire(phone, Config.tokenTime);
                jedis.close();
                return token;
            } else {
                return "{\"hint\":\"password_error\"}";
            }
        } else {
            return "{\"hint\":\"account_not_found\"}";
        }
    }

    public String loginByPhone(String phone, String code, HttpSession session) {
        User user = this.userDao.findByPhone(phone);
        if (user != null) {
            if (code.equals(session.getAttribute("vl" + phone))) {
                session.setAttribute("ui", Integer.valueOf(user.getId()));
                String token = user.getPassword();
                Jedis jedis = this.jedisFactory.getInstance();
                jedis.set(phone, token);
                jedis.set(phone + "id", String.valueOf(user.getId()));
                jedis.expire(phone + "id", Config.tokenTime);
                jedis.expire(phone, Config.tokenTime);
                jedis.close();
                return token;
            } else {
                return "{\"hint\":\"phone_code_error\"}";
            }
        } else {
            return "{\"hint\":\"account_not_found\"}";
        }
    }

    public String logout(User user, HttpSession session) {
        String phone = (String)session.getAttribute("u");
        if (phone != null) {
            Jedis jedis = this.jedisFactory.getInstance();
            jedis.del(phone);
            jedis.close();
        }

        session.invalidate();
        return "{\"hint\":\"success\"}";
    }

    public String altUser(User user, HttpSession session) {
        String token = this.userDao.altUser(user);
        session.removeAttribute("vm" + user.getPhone_number());
        Jedis jedis = this.jedisFactory.getInstance();
        jedis.set(user.getPhone_number(), token);
        jedis.expire(user.getPhone_number(), Config.tokenTime);
        jedis.close();
        return token;
    }

    public String addUser(User user, HttpSession session) {
        Channel channel = this.channelDao.findById(user.getSource());
        if (channel == null) {
            channel = this.channelDao.findById(0);
        }

        user.setChannel(channel);
        if (this.payHelp.updateChannel(channel)) {
            user.setValid(1);
            user.setTop_three(1);
        }

        String token = this.userDao.addUser(user);
        session.removeAttribute("vr" + user.getPhone_number());
        Jedis jedis = this.jedisFactory.getInstance();
        jedis.set(user.getPhone_number(), token);
        jedis.expire(user.getPhone_number(), Config.tokenTime);
        jedis.close();
        return token;
    }

    public String usersView(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer admin_id = (Integer)session.getAttribute("ai");
        String permission = (String)session.getAttribute("ap");
        if (permission != null) {
            if (!permission.equals("00001") && !permission.equals("11111") && !permission.equals("00000")) {
                return "not_permission";
            } else {
                int count = this.userDao.getCount(user.getSalt(), admin_id.intValue(), permission);
                List users = this.userDao.getUsers(user.getId(), user.getSalt(), admin_id.intValue(), permission);
                int pageCount = (int)Math.ceil((double)count / 18.0D);
                request.setAttribute("users", users);
                request.setAttribute("count", Integer.valueOf(count));
                request.setAttribute("pageCount", Integer.valueOf(pageCount));
                request.setAttribute("thisPage", Integer.valueOf(user.getId()));
                request.setAttribute("name", user.getSalt());
                return "success";
            }
        } else {
            return "un_login";
        }
    }

    public String setValid(User user, HttpSession session) {
        Integer admin_id = (Integer)session.getAttribute("ai");
        String permission = (String)session.getAttribute("ap");
        if (permission != null) {
            if (permission.equals("11111")) {
                User hold = this.userDao.findById(user.getId());
                if (hold != null && hold.getValid() == 0) {
                    hold.setValid(1);
                    Channel channel = hold.getChannel();
                    channel.setPay(channel.getPay() + 1);
                    channel.setPay1(channel.getPay1() + 1);
                    channel.setMonth1(channel.getMonth1() + 1);
                    logger.info("admin:" + admin_id + " set user:" + hold.getId() + " valid");
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

    public String getAll(HttpServletRequest request) {
        String id = request.getParameter("channelId");
        String pageStr = request.getParameter("page");
        String star = request.getParameter("star");
        // String end = request.getParameter("end");
        String end = null;
        int channelId = id == null ? 0 : Integer.parseInt(id);
        int page = pageStr == null ? 1 : Integer.parseInt(pageStr);
        Channel channel = this.channelDao.findById(channelId);
        if (channel != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String str = sdf.format(new Date());
            star = star == null ? str : star;
            end = end == null ? star : end;
            request.setAttribute("star", star);
            request.setAttribute("end", end);
            star = star + " 00:00:00";
            end = end + " 23:59:59";
            List users = this.userDao.getAll(star, end, page, channelId);
            int count = this.userDao.getCount(star, end, channelId);
            int pageCount = (int)Math.ceil((double)count / 13.0D);
            Map collect = this.userDao.Stat(star, end, channelId);
            request.setAttribute("collect", collect);
            request.setAttribute("users", users);
            request.setAttribute("count", Integer.valueOf(count));
            request.setAttribute("pageCount", Integer.valueOf(pageCount));
            request.setAttribute("thisPage", Integer.valueOf(page));
            request.setAttribute("channelId", Integer.valueOf(channelId));
            request.setAttribute("name", channel.getName());
            return "success";
        } else {
            return "illegal_request";
        }
    }

    public String getAllRt(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String permission = (String)session.getAttribute("ap");
        if (permission != null && permission.equals("00000")) {
            int admin_id = ((Integer)session.getAttribute("ai")).intValue();
            List channels = this.adminDao.findChannlByViewer(admin_id);
            Admin admin = this.adminDao.findById(admin_id);
            if (channels != null && channels.size() > 0) {
                Channel channel = (Channel)channels.get(0);
                String id = request.getParameter("channelId");
                id = id == null ? String.valueOf(channel.getId()) : id;
                String pageStr = request.getParameter("page");
                String star = request.getParameter("star");
                String end = request.getParameter("end");
                int channelId = id == null ? 0 : Integer.parseInt(id);
                int page = pageStr == null ? 1 : Integer.parseInt(pageStr);
                channel = this.channelDao.findById(channelId);
                if (channel != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String str = sdf.format(new Date());
                    star = star == null ? str : star;
                    end = end == null ? str : end;
                    request.setAttribute("star", star);
                    request.setAttribute("end", end);
                    star = star + " 00:00:00";
                    end = end + " 23:59:59";
                    List users = this.userDao.getAll(star, end, page, channelId);
                    int count = this.userDao.getCount(star, end, channelId);
                    int pageCount = (int)Math.ceil((double)count / 13.0D);
                    request.setAttribute("channels", channels);
                    request.setAttribute("users", users);
                    request.setAttribute("count", Integer.valueOf(count));
                    request.setAttribute("pageCount", Integer.valueOf(pageCount));
                    request.setAttribute("thisPage", Integer.valueOf(page));
                    request.setAttribute("channelId", Integer.valueOf(channelId));
                    request.setAttribute("name", channel.getName());
                    request.setAttribute("admin", admin);
                    request.setAttribute("host", this.host);
                    request.setAttribute("webName", this.webName);
                    return "success";
                } else {
                    return "illegal_request";
                }
            } else {
                return "illegal_request";
            }
        } else {
            return "un_login";
        }
    }

    public String delUser(User user, HttpSession session) {
        Integer admin_id = (Integer)session.getAttribute("ai");
        String permission = (String)session.getAttribute("ap");
        if (permission != null) {
            if (permission.equals("11111")) {
                User hold = this.userDao.findById(user.getId());
                if (hold != null) {
                    logger.info("admin:" + admin_id + " del user:" + hold.getId() + " valid");
                    this.userDao.delUser(hold);
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

    public void setJedisFactory(JedisFactory jedisFactory) {
        this.jedisFactory = jedisFactory;
    }

    public void setChannelDao(ChannelDao channelDao) {
        this.channelDao = channelDao;
    }

    public void setPayHelp(PayHelp payHelp) {
        this.payHelp = payHelp;
    }

    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }
}
