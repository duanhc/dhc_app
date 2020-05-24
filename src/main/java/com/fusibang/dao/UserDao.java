//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.dao;

import com.fusibang.help.MD5;
import com.fusibang.tables.IdCard;
import com.fusibang.tables.Identify;
import com.fusibang.tables.User;
import com.fusibang.tables.UserDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserDao {
    private SessionFactory sessionFactory;
    private String[] salt = new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "o", "1", "2", "3", "4", "5", "6", "7", "8", "9", "P", "Q", "R", "S", "T", "U", "V", "W"};

    public UserDao() {}

    public int getCount(String star, String end, int id) {
        String hql = "SELECT count(*) FROM User u WHERE  u.channel.id = :id AND u.registe_time > :star AND u.registe_time < :end";
        Long count = (Long)this.getSession().createQuery(hql).setInteger("id", id).setString("star", star).setString("end", end).uniqueResult();
        return count.intValue();
    }

    public Map<String, String> Stat(String star, String end, int id) {
        String hql = "FROM User u WHERE u.channel.id = :id AND u.registe_time > :star AND u.registe_time < :end ORDER BY u.id DESC";
        List users = this.getSession().createQuery(hql).setInteger("id", id).setString("star", star).setString("end", end).list();
        HashMap collect = new HashMap();
        int valid = 0;
        int ua = 0;
        int apply = 0;
        int loan = 0;
        Iterator var11 = users.iterator();

        while (var11.hasNext()) {
            User nf = (User)var11.next();
            ++ua;

            if (nf.getOpenid() != null && !nf.getOpenid().equals("")) {
                ++apply;
            }

            if (nf.getValid() == 1) {
                ++valid;
            }

            if (nf.getPay() == 1) {
                ++loan;
            }
        }

        loan /= 10;
        collect.put("valid", String.valueOf(valid));
        collect.put("ljsqrs", valid/2);
        collect.put("ua", String.valueOf(ua));
        collect.put("apply", String.valueOf(apply));
        collect.put("loan", String.valueOf(loan));
        NumberFormat var12 = NumberFormat.getNumberInstance();
        var12.setMaximumFractionDigits(4);
        var12.setRoundingMode(RoundingMode.UP);
        collect.put("applyrate", ua == 0 ? "0" : var12.format((double)apply * 100.0D / (double)ua));
        collect.put("loanrate", ua == 0 ? "0" : var12.format((double)loan * 100.0D / (double)ua));
        return collect;
    }

    public List<Map<String, String>> getAll(String star, String end, int page, int id) {
        String hql = "FROM User u WHERE u.channel.id = :id AND u.registe_time > :star AND u.registe_time < :end AND u.valid=1 ORDER BY u.id DESC";
        byte eachCount = 13;
        int offSet = eachCount * (page - 1);
        List users = this.getSession().createQuery(hql).setInteger("id", id).setString("star", star).setString("end", end).setMaxResults(eachCount).setFirstResult(offSet).list();
        ArrayList lists = new ArrayList();

        HashMap map;
        for (Iterator var11 = users.iterator(); var11.hasNext(); lists.add(map)) {
            User u = (User)var11.next();
            map = new HashMap();
            map.put("id", String.valueOf(u.getId()));
            map.put("phone", u.getPhone_number().replaceAll("(\\d{3})\\d{5}(\\d{2})", "$1*****$2"));
            map.put("time", u.getRegiste_time().toString());
            hql = "FROM IdCard ud WHERE ud.user.id = " + u.getId();
            IdCard idcard = (IdCard)this.getSession().createQuery(hql).uniqueResult();
            if (idcard != null) {
                if(StringUtils.isEmpty(idcard.getName()) || StringUtils.isEmpty(idcard.getNum())){
                    //身份证默认成功
                    hql = "FROM UserDetail ud WHERE ud.user.id = " + u.getId();
                    UserDetail userDetail = (UserDetail)this.getSession().createQuery(hql).uniqueResult();
                    map.put("name", userDetail.getName().replaceAll("(\\S)(.+)", "*$2"));
                    map.put("idcard", userDetail.getId_card().replaceAll("(\\d{5})\\d{10}(\\d{2})(\\d|x|X)", "$1**********$2$3"));
                }else{
                    map.put("name", idcard.getName().replaceAll("(\\S)(.+)", "*$2"));
                    map.put("idcard", idcard.getNum().replaceAll("(\\d{5})\\d{10}(\\d{2})(\\d|x|X)", "$1**********$2$3"));
                }
            } else {
                map.put("name", "未认证");
                map.put("idcard", "未认证");
            }
        }

        return lists;
    }

    public int getCount(String search, int admin_id, String permission) {
        String sql;
        switch (permission.hashCode()) {
            case 45806640:
                if (!permission.equals("00000")) {
                    return 0;
                }

                sql = "SELECT COUNT(*) FROM user_info WHERE valid=1 AND channel_id IN (SELECT id FROM channel WHERE viewer_id = " + admin_id + ") AND (phone_number LIKE \'%" + search
                    + "%\' OR channel_id IN (SELECT id FROM channel WHERE name LIKE \'%" + search + "%\'))";
                break;
            case 45806641:
                if (!permission.equals("00001")) {
                    return 0;
                }

                sql = "SELECT COUNT(*) FROM user_info WHERE channel_id IN (SELECT id FROM channel WHERE creater_id = " + admin_id + ") AND (phone_number LIKE \'%" + search
                    + "%\' OR channel_id IN (SELECT id FROM channel WHERE name LIKE \'%" + search + "%\'))";
                break;
            case 46760945:
                if (permission.equals("11111")) {
                    sql = "SELECT COUNT(*) FROM user_info WHERE phone_number LIKE \'%" + search + "%\' OR channel_id IN (SELECT id FROM channel WHERE name LIKE \'%" + search + "%\')";
                    break;
                }

                return 0;
            default:
                return 0;
        }

        List countList = this.getSession().createSQLQuery(sql).list();
        return ((BigInteger)countList.get(0)).intValue();
    }

    public List<User> getUsers(int page, String search, int admin_id, String permission) {
        String hql;
        switch (permission.hashCode()) {
            case 45806640:
                if (!permission.equals("00000")) {
                    return null;
                }

                hql = "FROM User u WHERE u.valid = 1 AND u.channel.viewer.id = " + admin_id + " AND (u.phone_number LIKE :phone OR u.channel.name LIKE :name) ORDER BY u.id DESC";
                break;
            case 45806641:
                if (!permission.equals("00001")) {
                    return null;
                }

                hql = "FROM User u WHERE u.channel.creater.id = " + admin_id + " AND (u.phone_number LIKE :phone OR u.channel.name LIKE :name) ORDER BY u.id DESC";
                break;
            case 46760945:
                if (permission.equals("11111")) {
                    hql = "FROM User u WHERE u.phone_number LIKE :phone OR u.channel.name LIKE :name ORDER BY u.id DESC";
                    break;
                }

                return null;
            default:
                return null;
        }

        byte eachCount = 18;
        int offSet = eachCount * (page - 1);
        List users = this.getSession().createQuery(hql).setString("phone", "%" + search + "%").setString("name", "%" + search + "%").setFirstResult(offSet).setMaxResults(eachCount).list();
        return users;
    }

    public String addUser(User user) {
        user.setRegiste_time(new Timestamp((new Date()).getTime()));
        StringBuilder bulider = new StringBuilder();
        Random random = new Random();
        int size = 5 + random.nextInt(6);

        for (int token = 0; token < size; ++token) {
            bulider.append(this.salt[random.nextInt(this.salt.length)]);
        }

        user.setSalt(bulider.toString());
        String var7 = (new MD5()).getMD5ofStr(user.getPhone_number() + user.getPassword() + user.getSalt());
        user.setPassword(var7);
        this.getSession().save(user);
        Identify identify = new Identify();
        identify.setUser(user);
        long cashPassword = System.currentTimeMillis() % 1000000;
        //设置随机六位提现密码
        identify.setCash_password(String.valueOf(cashPassword));
        this.getSession().save(identify);
        return var7;
    }

    public User getOne(int channelId) {
        User user = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String star = sdf.format(new Date());
        List users = this.getSession().createQuery("FROM User u WHERE u.valid = 0 AND u.registe_time > :star AND u.channel.id = :id ORDER BY u.id DESC").setString("star", star + " 00:00:00")
            .setInteger("id", channelId).setMaxResults(1).list();
        if (users.size() > 0) {
            user = (User)users.get(0);
        }

        return user;
    }

    public String altUser(User user) {
        User hold = this.findByPhone(user.getPhone_number());
        String token = (new MD5()).getMD5ofStr(user.getPhone_number() + user.getPassword() + hold.getSalt());
        hold.setPassword(token);
        return token;
    }

    public void delUser(User user) {
        this.getSession().delete(user);
    }

    public User findById(int id) {
        return (User)this.getSession().get(User.class, Integer.valueOf(id));
    }

    public User findByPhone(String phone) {
        User user = null;
        String hql = "FROM User u WHERE u.phone_number = :phone";
        List<User> users = this.getSession().createQuery(hql).setString("phone", phone).list();
        if (users.size() > 0) {
            user = (User)users.get(0);
        }

        return user;
    }

    public boolean existUser(String phone) {
        return this.findByPhone(phone) != null;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }
}
