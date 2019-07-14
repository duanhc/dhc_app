//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.servicesimp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fusibang.dao.BankAuthDao;
import com.fusibang.dao.IdentifyDao;
import com.fusibang.dao.UserDao;
import com.fusibang.help.ChanPayHelp;
import com.fusibang.help.PayHelp;
import com.fusibang.help.ResponseStatus;
import com.fusibang.services.ChanPayService;
import com.fusibang.tables.BankAuth;
import com.fusibang.tables.Pay;
import com.fusibang.tables.User;

public class ChanPayServiceImp extends ResponseStatus implements ChanPayService {
    private ChanPayHelp chanPayHelp;
    private UserDao userDao;
    private BankAuthDao bankAuthDao;
    private IdentifyDao identifyDao;
    private PayHelp payHelp;

    public ChanPayServiceImp() {
    }

    public String BankCarAuth(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer id = (Integer)session.getAttribute("ui");
        User user;
        if(id != null && (user = this.userDao.findById(id.intValue())) != null) {
            String MerUserId = user.getPhone_number() + "a" + user.getId();
            String source = request.getParameter("source");
            String trxId;
            if(source != null && source.equals("ios")) {
                synchronized(this) {
                    trxId = "x" + Long.toString(System.currentTimeMillis());
                }
            } else {
                synchronized(this) {
                    trxId = "y" + Long.toString(System.currentTimeMillis());
                }
            }

            String url = this.chanPayHelp.auth(MerUserId, trxId);
            if(url.contains("http")) {
                BankAuth bank = new BankAuth();
                bank.setUser(user);
                bank.setMerUserId(MerUserId);
                bank.setTrxId(trxId);
                this.bankAuthDao.save(bank);
            }

            return url;
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    public String authPay(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer id = (Integer)session.getAttribute("ui");
        User user;
        if(id != null && (user = this.userDao.findById(id.intValue())) != null) {
            BankAuth bank = this.bankAuthDao.findByUserId(user.getId());
            if(bank == null) {
                return "{\"hint\":\"account_not_found\"}";
            } else {
                String source = request.getParameter("source");
                String trxId;
                if(source != null && source.equals("ios")) {
                    synchronized(this) {
                        trxId = "x" + Long.toString(System.currentTimeMillis());
                    }
                } else {
                    synchronized(this) {
                        trxId = "y" + Long.toString(System.currentTimeMillis());
                    }
                }

                String url = this.chanPayHelp.authPay(bank, trxId);
                Pay pay = new Pay();
                pay.setIndent_str(trxId);
                this.payHelp.insertPay(pay, user.getId());
                return url;
            }
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    public String payNotify(HttpServletRequest request) {
        String outer_trade_no = request.getParameter("outer_trade_no");
        String inner_trade_no = request.getParameter("inner_trade_no");
        String trade_amount = request.getParameter("trade_amount");
        String trade_status = request.getParameter("trade_status");
        return trade_status.equals("TRADE_SUCCESS")?this.payHelp.updatePay2(outer_trade_no, inner_trade_no, (new Float(trade_amount)).floatValue()):"faild";
    }

    public String BankCarNotify(HttpServletRequest request) {
        String status = request.getParameter("auth_status");
        String merUserId = request.getParameter("mer_user_id");
        String bank_name = request.getParameter("bank_name");
        String card_begin = request.getParameter("card_begin");
        String card_end = request.getParameter("card_end");
        if("S".equals(status)) {
            BankAuth bank = this.bankAuthDao.findByMerUserId(merUserId);
            if(bank != null) {
                bank.setStatus(1);
                bank.setBank_name(bank_name);
                bank.setCard_begin(card_begin);
                bank.setCard_end(card_end);
                this.identifyDao.findByUserId(bank.getUser().getId()).setStep6(1);
                return "success";
            }
        }

        return "faild";
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setChanPayHelp(ChanPayHelp chanPayHelp) {
        this.chanPayHelp = chanPayHelp;
    }

    public void setBankAuthDao(BankAuthDao bankAuthDao) {
        this.bankAuthDao = bankAuthDao;
    }

    public void setIdentifyDao(IdentifyDao identifyDao) {
        this.identifyDao = identifyDao;
    }

    public void setPayHelp(PayHelp payHelp) {
        this.payHelp = payHelp;
    }
}
