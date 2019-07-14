//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.fusibang.help.NewTask;
import com.fusibang.tables.NewsLog;
import com.fusibang.tables.WeiXin;

public class NewsLogDao {
    private SessionFactory sessionFactory;

    public NewsLogDao() {
    }

    public int getCount() {
        Long count = (Long)this.getSession().createQuery("SELECT COUNT(*) FROM NewsLog").uniqueResult();
        return count.intValue();
    }

    public List<NewsLog> getByPage(int page) {
        byte eachCount = 15;
        int offSet = (page - 1) * eachCount;
        List logs = this.getSession().createQuery("FROM NewsLog").setFirstResult(offSet).setMaxResults(eachCount).list();
        return logs;
    }

    public void add(NewTask[] tasks, WeiXin weixin) {
        Session session = this.sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        Date now = new Date((new java.util.Date()).getTime());
        NewTask[] var9 = tasks;
        int var8 = tasks.length;

        for(int var7 = 0; var7 < var8; ++var7) {
            NewTask log = var9[var7];
            NewsLog nlog = new NewsLog();
            nlog.setWeixin_name(weixin.getName());
            nlog.setApp_name(log.getName());
            nlog.setSend_time(now);
            nlog.setSend_count(log.getHasSend());
            nlog.setFail_count(log.getFailSend());
            session.save(nlog);
        }

        ts.commit();
    }

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
