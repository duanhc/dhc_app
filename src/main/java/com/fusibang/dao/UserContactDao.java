//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fusibang.tables.UserContact;

public class UserContactDao {
    private SessionFactory sessionFactory;

    public UserContactDao() {
    }

    public void save(UserContact userContact) {
        this.getSession().save(userContact);
    }

    public UserContact findByUserId(int id) {
        UserContact userContact = null;
        List userContacts = this.getSession().createQuery("FROM UserContact u WHERE u.user.id = :id").setInteger("id", id).list();
        if(userContacts.size() > 0) {
            userContact = (UserContact)userContacts.get(0);
        }

        return userContact;
    }

    public void alt(UserContact userContact) {
        this.getSession().merge(userContact);
    }

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
