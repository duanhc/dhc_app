//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fusibang.tables.Pay;

public class PayDao {
    private SessionFactory sessionFactory;

    public PayDao() {
    }

    public void save(Pay pay) {
        this.getSession().save(pay);
    }

    public Pay findByIndent(String indent) {
        Pay pay = null;
        String hql = "FROM Pay p WHERE p.indent_str = :indent_str AND p.status = 0";
        List pays = this.getSession().createQuery(hql).setString("indent_str", indent).list();
        if(pays.size() > 0) {
            pay = (Pay)pays.get(0);
        }

        return pay;
    }

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
