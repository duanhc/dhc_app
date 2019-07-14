//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fusibang.tables.BankAuth;

public class BankAuthDao {
    private SessionFactory sessionFactory;

    public BankAuthDao() {
    }

    public void save(BankAuth bankAuth) {
        this.getSession().save(bankAuth);
    }

    public BankAuth findByMerUserId(String merUserId) {
        BankAuth bank = null;
        String hql = "FROM BankAuth b WHERE b.merUserId = :merUserId";
        List banks = this.getSession().createQuery(hql).setString("merUserId", merUserId).list();
        if(banks.size() > 0) {
            bank = (BankAuth)banks.get(0);
        }

        return bank;
    }

    public BankAuth findByUserId(int userId) {
        BankAuth bank = null;
        String hql = "FROM BankAuth b WHERE b.user.id = :userId AND b.status = 1";
        List banks = this.getSession().createQuery(hql).setInteger("userId", userId).list();
        if(banks.size() > 0) {
            bank = (BankAuth)banks.get(0);
        }

        return bank;
    }

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
