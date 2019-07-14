//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fusibang.tables.Channel;

public class ChannelDao {
    private SessionFactory sessionFactory;

    public ChannelDao() {
    }

    public void addChannel(Channel channel) {
        this.getSession().save(channel);
    }

    public Channel findById(int id) {
        return (Channel)this.getSession().get(Channel.class, Integer.valueOf(id));
    }

    public boolean existName(String name) {
        return this.getSession().createQuery("FROM Channel c WHERE c.name = :name").setString("name", name).list().size() > 0;
    }

    public int getCountByAdminId(int admin_id, String permission, String name) {
        String sql;
        switch(permission.hashCode()) {
        case 45806640:
            if(!permission.equals("00000")) {
                return 0;
            }

            sql = "SELECT COUNT(*) FROM channel WHERE viewer_id = " + admin_id + " AND name LIKE \'%" + name + "%\'";
            break;
        case 45806641:
            if(!permission.equals("00001")) {
                return 0;
            }

            sql = "SELECT COUNT(*) FROM channel WHERE creater_id = " + admin_id + " AND name LIKE \'%" + name + "%\'";
            break;
        case 46760945:
            if(permission.equals("11111")) {
                sql = "SELECT COUNT(*) FROM channel WHERE name LIKE \'%" + name + "%\'";
                break;
            }

            return 0;
        default:
            return 0;
        }

        List countList = this.getSession().createSQLQuery(sql).list();
        return ((BigInteger)countList.get(0)).intValue();
    }

    public List<Channel> getChannelByAdminId(int admin_id, int page, String permission, String name) {
        String hql;
        switch(permission.hashCode()) {
        case 45806640:
            if(!permission.equals("00000")) {
                return null;
            }

            hql = "FROM Channel c WHERE c.viewer.id = " + admin_id + " AND c.name LIKE :name ORDER BY c.pay2 DESC, c.pay DESC, c.pay1 DESC,c.pay3 DESC, c.id DESC";
            break;
        case 45806641:
            if(!permission.equals("00001")) {
                return null;
            }

            hql = "FROM Channel c WHERE c.creater.id = " + admin_id + " AND c.name LIKE :name ORDER BY c.pay2 DESC, c.pay DESC, c.pay1 DESC,c.pay3 DESC, c.id DESC";
            break;
        case 46760945:
            if(permission.equals("11111")) {
                hql = "FROM Channel c WHERE c.name LIKE :name ORDER BY c.pay2 DESC, c.pay DESC, c.pay1 DESC,c.pay3 DESC, c.id DESC";
                break;
            }

            return null;
        default:
            return null;
        }

        byte eachCount = 18;
        int offSet = eachCount * (page - 1);
        List channels = this.getSession().createQuery(hql).setString("name", "%" + name + "%").setFirstResult(offSet).setMaxResults(eachCount).list();
        return channels;
    }

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
