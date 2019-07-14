//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fusibang.tables.WeiXin;

public class WeiXinDao {
    private SessionFactory sessionFactory;

    public WeiXinDao() {
    }

    public WeiXin findById(int id) {
        return (WeiXin)this.getSession().get(WeiXin.class, Integer.valueOf(id));
    }

    public List<WeiXin> getUnSend() {
        List weiXins = this.getSession().createQuery("FROM WeiXin w WHERE w.today_send = 0").list();
        return weiXins;
    }

    public void setTodaySend(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        WeiXin weixin = (WeiXin)session.get(WeiXin.class, Integer.valueOf(id));
        if(weixin != null) {
            weixin.setToday_send(1);
        }

    }

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
