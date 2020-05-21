//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.dao;

import com.fusibang.tables.Identify;
import com.fusibang.tables.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigInteger;
import java.util.List;

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

    public int getCount(String search, int admin_id, String permission) {
        String sql;
        switch (permission.hashCode()) {
            case 45806640:
                if (!permission.equals("00000")) {
                    return 0;
                }
                sql = "SELECT COUNT(*) FROM user_info WHERE valid=1 AND channel_id IN (SELECT id FROM channel WHERE viewer_id = " + admin_id + ") AND (phone_number LIKE \'%" + search
                        + "%\' OR channel_id IN (SELECT id FROM channel WHERE name LIKE \'%" + search + "%\'))";
                break;
            case 45806641:
                if (!permission.equals("00001")) {
                    return 0;
                }
                sql = "SELECT COUNT(*) FROM identify i LEFT JOIN user_info u ON i.user_id=u.id WHERE i.sign = 1 AND u.channel_id IN (SELECT id FROM channel WHERE creater_id = " + admin_id + ") AND (phone_number LIKE \'%" + search
                        + "%\' OR channel_id IN (SELECT id FROM channel WHERE name LIKE \'%" + search + "%\'))";
                break;
            case 46760945:
                if (permission.equals("11111")) {
                    sql = "SELECT COUNT(*) FROM identify i LEFT JOIN user_info u ON i.user_id=u.id WHERE i.sign = 1 AND (phone_number LIKE \'%" + search
                        + "%\' OR channel_id IN (SELECT id FROM channel WHERE name LIKE \'%" + search + "%\'))";
                    break;
                }

                return 0;
            default:
                return 0;
        }

        List countList = this.getSession().createSQLQuery(sql).list();
        return ((BigInteger)countList.get(0)).intValue();
    }

    public List<Identify> getIdentifies(int page, String search, int admin_id, String permission) {
        String hql;
        switch (permission.hashCode()) {
            case 45806640:
                if (!permission.equals("00000")) {
                    return null;
                }

                hql = "FROM User u WHERE u.valid = 1 AND u.channel.viewer.id = " + admin_id + " AND (u.phone_number LIKE :phone OR u.channel.name LIKE :name) ORDER BY u.id DESC";
                break;
            case 45806641:
                if (!permission.equals("00001")) {
                    return null;
                }

                hql = "FROM Identify i WHERE i.sign = 1 AND i.user.channel.creater.id = " + admin_id + " AND (i.user.phone_number LIKE :phone OR i.user.channel.name LIKE :name) ORDER BY i.user.id DESC";
                break;
            case 46760945:
                if (permission.equals("11111")) {
                    hql = "FROM Identify i WHERE i.sign = 1 AND (i.user.phone_number LIKE :phone OR i.user.channel.name LIKE :name) ORDER BY i.user.id DESC";
                    break;
                }

                return null;
            default:
                return null;
        }

        byte eachCount = 18;
        int offSet = eachCount * (page - 1);
        List identifies = this.getSession().createQuery(hql).setString("phone", "%" + search + "%").setString("name", "%" + search + "%").setFirstResult(offSet).setMaxResults(eachCount).list();
        return identifies;
    }
}
