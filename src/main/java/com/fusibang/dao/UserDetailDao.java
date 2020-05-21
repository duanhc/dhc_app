//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.dao;

import com.fusibang.tables.UserDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDetailDao {
    private SessionFactory sessionFactory;

    public UserDetailDao() {
    }

    public void addDetail(UserDetail userDetail) {
        this.getSession().save(userDetail);
    }

    public void altDetail(UserDetail userDetail) {
        this.getSession().merge(userDetail);
    }

    public boolean exist(int id) {
        return this.getSession().createQuery("FROM UserDetail u WHERE u.user.id = :user_id").setInteger("user_id", id).list().size() > 0;
    }

    public UserDetail findByUserId(int id) {
        UserDetail userDetail = null;
        List userDetails = this.getSession().createQuery("FROM UserDetail u WHERE u.user.id = :user_id").setInteger("user_id", id).list();
        if(userDetails.size() > 0) {
            userDetail = (UserDetail)userDetails.get(0);
        }

        return userDetail;
    }

    /**
     * 删除用户详情
     * @param userDetail
     */
    public void delUserDetail(UserDetail userDetail) {
        this.getSession().delete(userDetail);
    }

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
