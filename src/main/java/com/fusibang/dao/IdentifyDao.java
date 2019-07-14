//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fusibang.tables.Identify;
import com.fusibang.tables.User;

public class IdentifyDao {
    private SessionFactory sessionFactory;

    public IdentifyDao() {
    }

    public Identify findByUserId(int id) {
        Identify identify = (Identify)this.getSession().createQuery("FROM Identify i WHERE i.user.id = :id").setInteger("id", id).uniqueResult();
        return identify;
    }

    public Identify findByUserIdNoSession(int id) {
        Session session = this.sessionFactory.openSession();
        Identify identify = (Identify)session.createQuery("FROM Identify i WHERE i.user.id = :id").setInteger("id", id).uniqueResult();
        session.flush();
        return identify;
    }

    public boolean showApp(User user) {
        if(user.getChannel().getId() != 0 && user.getTop_three() != 1) {
            int suc = this.getSession().createQuery("FROM Identify i WHERE i.user.channel.id = :id AND i.step1 = 1").setInteger("id", user.getChannel().getId()).setMaxResults(5).list().size();
            Identify identify = this.findByUserId(user.getId());
            return identify.getStep1() == 1 && suc > 2;
        } else {
            return false;
        }
    }

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
