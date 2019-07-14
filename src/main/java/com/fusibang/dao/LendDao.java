//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.dao;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fusibang.tables.Lend;

public class LendDao {
    private SessionFactory sessionFactory;

    public LendDao() {
    }

    public void save(Lend lend) {
        this.getSession().save(lend);
    }

    public Lend findByUserId(int userId) {
        Lend lend = (Lend)this.getSession().createQuery("FROM Lend l WHERE l.user.id = :userId").setInteger("userId", userId).uniqueResult();
        return lend;
    }

    public void verify(int userId) {
        Lend lend = this.findByUserId(userId);
        lend.setStatus(1);
        lend.setVerify_time(new Timestamp((new Date()).getTime()));
    }

    public void del(int userId) {
        this.getSession().createQuery("DELETE FROM Lend l where l.user.id = :userId").setInteger("userId", userId).executeUpdate();
    }

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
