//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.dao;

import com.fusibang.tables.UserDetailAppend;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDetailAppendDao {
    private SessionFactory sessionFactory;

    public UserDetailAppendDao() {
    }

    public void save(UserDetailAppend userDetailAppend) {
        this.getSession().save(userDetailAppend);
    }

    public UserDetailAppend findByUserId(int id) {
        UserDetailAppend userDetailAppend = null;
        List userDetailAppends = this.getSession().createQuery("FROM UserDetailAppend u WHERE u.user.id = :id").setInteger("id", id).list();
        if(userDetailAppends.size() > 0) {
            userDetailAppend = (UserDetailAppend)userDetailAppends.get(0);
        }

        return userDetailAppend;
    }

    public void delUserDetailAppend(UserDetailAppend userDetailAppend) {
        this.getSession().delete(userDetailAppend);
    }

    public void alt(UserDetailAppend userDetailAppend) {
        this.getSession().merge(userDetailAppend);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }
}
