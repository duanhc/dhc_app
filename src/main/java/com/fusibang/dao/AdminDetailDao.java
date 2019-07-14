//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fusibang.tables.AdminDetail;

public class AdminDetailDao {
    private SessionFactory sessionFactory;

    public AdminDetailDao() {
    }

    public int getCountByAdminId(String permission, String name, int forbid) {
        String sql = "SELECT COUNT(*) FROM admin_detail WHERE permission = \'" + permission + "\' AND forbid = " + forbid + " AND real_name LIKE \'%" + name + "%\'";
        List countList = this.getSession().createSQLQuery(sql).list();
        return ((BigInteger)countList.get(0)).intValue();
    }

    public List<AdminDetail> getAdminsDetailByAdminId(int page, String permission, String name, int forbid) {
        String hql = "FROM AdminDetail a WHERE a.permission = :permission AND a.forbid = :forbid  AND a.real_name LIKE :name";
        byte eachCount = 18;
        int offSet = eachCount * (page - 1);
        List adminDetails = this.getSession().createQuery(hql).setString("name", "%" + name + "%").setInteger("forbid", forbid).setString("permission", permission).setFirstResult(offSet).setMaxResults(eachCount).list();
        return adminDetails;
    }

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
