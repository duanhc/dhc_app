//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.help;

import com.fusibang.dao.IdentifyDao;
import com.fusibang.dao.PayDao;
import com.fusibang.dao.UserDao;
import com.fusibang.tables.Channel;
import com.fusibang.tables.Identify;
import com.fusibang.tables.Pay;
import com.fusibang.tables.User;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class PayHelp {
    private static final Logger logger = Logger.getLogger(PayHelp.class);
    private IdentifyDao identifyDao;
    private PayDao payDao;
    private UserDao userDao;
    private boolean cut;
    private int cutRate;

    public PayHelp() {
    }

    public boolean updateChannel(Channel channel) {
        channel.setToday_ua(channel.getToday_ua() + 1);
        channel.setAll_ua(channel.getAll_ua() + 1);
//        if(channel.getAll_ua() > 5 && channel.getId() != 0 && channel.getId() != 2) {
//            return false;
//        } else {
//            channel.setPay1(channel.getPay1() + 1);
//            channel.setPay(channel.getPay() + 1);
//            channel.setMonth1(channel.getMonth1() + 1);
//            return true;
//        }

        channel.setPay1(channel.getPay1() + 1);
        channel.setPay(channel.getPay() + 1);
        channel.setMonth1(channel.getMonth1() + 1);
        return true;
    }

    public String updatePay(String outer_trade_no, String inner_trade_no, float amount) {
        Pay pay = this.payDao.findByIndent(outer_trade_no);
        if(pay != null) {
            Timestamp now = new Timestamp((new Date()).getTime());
            pay.setAmount(amount);
            pay.setOrder_str(inner_trade_no);
            pay.setStatus(1);
            pay.setPay_time(now);
            User user = pay.getUser();
            Channel channel = user.getChannel();
            channel.setToday_income(channel.getToday_income() + amount);
            channel.setAll_income(channel.getAll_income() + amount);
            Identify identify = this.identifyDao.findByUserId(user.getId());
            identify.setPay(1);
            identify.setPay_time(now);
            if(user.getValid() == 0) {
                pay.getUser().setValid(1);
                channel.setMonth1(channel.getMonth1() + 1);
                channel.setPay(channel.getPay() + 1);
                channel.setPay1(channel.getPay1() + 1);
            }

            return "success";
        } else {
            return "faild";
        }
    }

    public String updatePay2(String outer_trade_no, String inner_trade_no, float amount) {
        Pay pay = this.payDao.findByIndent(outer_trade_no);
        if(pay == null) {
            return "faild";
        } else {
            Timestamp now = new Timestamp((new Date()).getTime());
            pay.setAmount(amount);
            pay.setOrder_str(inner_trade_no);
            pay.setStatus(1);
            pay.setPay_time(now);
            User user = pay.getUser();
            Identify identify = this.identifyDao.findByUserId(user.getId());
            identify.setPay(1);
            identify.setPay_time(now);
            if(this.cut && (new Random()).nextInt(100) <= this.cutRate) {
                logger.info("cut.............2");
            } else {
                Channel channel = user.getChannel();
                channel.setToday_income(channel.getToday_income() + amount);
                channel.setAll_income(channel.getAll_income() + amount);
                user.setPay(1);
                if(user.getValid() == 0) {
                    user.setValid(1);
                    Date regTime = new Date(user.getRegiste_time().getTime());
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new Date());
                    int n_day = calendar.get(6);
                    calendar.setTime(regTime);
                    int r_day = calendar.get(6);
                    if(n_day != r_day) {
                        user.setRegiste_time(now);
                    }

                    User append = this.userDao.getOne(channel.getId());
                    if(append != null) {
                        append.setValid(1);
                        channel.setMonth1(channel.getMonth1() + 2);
                        channel.setPay(channel.getPay() + 2);
                        channel.setPay1(channel.getPay1() + 2);
                    } else {
                        channel.setMonth1(channel.getMonth1() + 1);
                        channel.setPay(channel.getPay() + 1);
                        channel.setPay1(channel.getPay1() + 1);
                    }
                }
            }

            return "success";
        }
    }

    public void insertPay(Pay pay, int userId) {
        User user = this.userDao.findById(userId);
        pay.setUser(user);
        pay.setSubmit_time(new Timestamp((new Date()).getTime()));
        this.payDao.save(pay);
    }

    public void setIdentifyDao(IdentifyDao identifyDao) {
        this.identifyDao = identifyDao;
    }

    public void setPayDao(PayDao payDao) {
        this.payDao = payDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setCut(boolean cut) {
        this.cut = cut;
    }

    public void setCutRate(int cutRate) {
        this.cutRate = cutRate;
    }
}
