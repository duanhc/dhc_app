//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.dao;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fusibang.help.MD5;
import com.fusibang.tables.Admin;
import com.fusibang.tables.Channel;

public class AdminDao {
    private SessionFactory sessionFactory;

    public AdminDao() {
    }

    public int getCountByAdminId(int admin_id, String target_permission, String name, int forbid) {
        String permission = this.findById(admin_id).getPermission();
        String sql;
        switch(permission.hashCode()) {
        case 45806641:
            if(!permission.equals("00001")) {
                return 0;
            }

            sql = "SELECT COUNT(*) FROM admin_info WHERE permission = \'" + target_permission + "\' AND forbid = " + forbid + " AND creater_id = " + admin_id + " AND real_name LIKE \'%" + name + "%\'";
            break;
        case 46760945:
            if(permission.equals("11111")) {
                sql = "SELECT COUNT(*) FROM admin_info WHERE permission = \'" + target_permission + "\'AND forbid = " + forbid + " AND real_name LIKE \'%" + name + "%\'";
                break;
            }

            return 0;
        default:
            return 0;
        }

        List countList = this.getSession().createSQLQuery(sql).list();
        return ((BigInteger)countList.get(0)).intValue();
    }

    public int getCountAll(String name, int forbid) {
        String sql = "SELECT COUNT(*) FROM admin_info WHERE (permission = \'00001\' OR permission = \'00010\') AND forbid = " + forbid + " AND real_name LIKE \'%" + name + "%\'";
        List countList = this.getSession().createSQLQuery(sql).list();
        return ((BigInteger)countList.get(0)).intValue();
    }

    public List<Admin> getAdminsByAll(int page, String name, int forbid) {
        String hql = "FROM Admin a WHERE (a.permission = :permission1 OR a.permission = :permission2) AND a.forbid = :forbid  AND a.real_name LIKE :name";
        byte eachCount = 18;
        int offSet = eachCount * (page - 1);
        List admins = this.getSession().createQuery(hql).setString("name", "%" + name + "%").setInteger("forbid", forbid).setString("permission1", "00001").setString("permission2", "00010").setFirstResult(offSet).setMaxResults(eachCount).list();
        return admins;
    }

    public List<Admin> getAdminsByAdminId(int admin_id, int page, String target_permission, String name, int forbid) {
        String permission = this.findById(admin_id).getPermission();
        String hql;
        switch(permission.hashCode()) {
        case 45806641:
            if(!permission.equals("00001")) {
                return null;
            }

            hql = "FROM Admin a WHERE a.permission = :permission AND a.forbid = :forbid AND a.creater.id = " + admin_id + " AND a.real_name LIKE :name";
            break;
        case 46760945:
            if(permission.equals("11111")) {
                hql = "FROM Admin a WHERE a.permission = :permission AND a.forbid = :forbid AND a.real_name LIKE :name";
                break;
            }

            return null;
        default:
            return null;
        }

        byte eachCount = 18;
        int offSet = eachCount * (page - 1);
        List admins = this.getSession().createQuery(hql).setString("name", "%" + name + "%").setInteger("forbid", forbid).setString("permission", target_permission).setFirstResult(offSet).setMaxResults(eachCount).list();
        return admins;
    }

    public Admin findByName(String name) {
        Admin admin = null;
        String hql = "FROM Admin a WHERE a.name = :name";
        List admins = this.getSession().createQuery(hql).setString("name", name).list();
        if(admins.size() > 0) {
            admin = (Admin)admins.get(0);
        }

        return admin;
    }

    public List<Admin> findAdminsByPermission(int id, String permission) {
        List admins = this.getSession().createQuery("FROM Admin a WHERE a.permission = :permission AND a.creater.id = :id").setString("permission", permission).setInteger("id", id).list();
        return admins;
    }

    public Admin findById(int id) {
        return (Admin)this.getSession().get(Admin.class, Integer.valueOf(id));
    }

    public List<Channel> findChannlByViewer(int id) {
        return this.getSession().createQuery("FROM Channel c WHERE c.viewer.id = :id").setInteger("id", id).list();
    }

    public void addAdmin(Admin admin) {
        Session session = this.getSession();
        admin.setPassword((new MD5()).getMD5ofStr(admin.getPassword()));
        session.save(admin);
        if(admin.getPermission().equals("00001")) {
            Admin rt = new Admin();
            rt.setPermission("00000");
            rt.setReal_name(admin.getReal_name() + "自动生成");
            rt.setName((new MD5()).getMD5ofStr(admin.getName() + admin.getId()));
            rt.setPassword((new MD5()).getMD5ofStr(rt.getReal_name()));
            rt.setCreater(admin);
            session.save(rt);
            Channel channel = new Channel();
            channel.setCreater(admin);
            channel.setAdd_time(new Date((new java.util.Date()).getTime()));
            channel.setViewer(rt);
            channel.setPrice(0);
            channel.setName(admin.getReal_name() + "自动生成" + admin.getId());
            session.save(channel);
        }

    }

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
