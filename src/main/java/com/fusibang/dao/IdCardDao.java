//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.dao;

import com.fusibang.tables.IdCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class IdCardDao {
    private SessionFactory sessionFactory;

    public IdCardDao() {
    }

    public void save(IdCard idCard) {
        Session session = this.getSession();
        List idCards = session.createQuery("FROM IdCard i WHERE i.user.id = :id").setInteger("id", idCard.getUser().getId()).list();
        if(idCards.size() > 0) {
            IdCard hold = (IdCard)idCards.get(0);
            hold.setAddress(idCard.getAddress());
            hold.setGender(idCard.getGender());
            hold.setName(idCard.getName());
            hold.setNation(idCard.getNation());
            hold.setNum(idCard.getNum());
        } else {
            this.getSession().save(idCard);
        }

    }

    public IdCard findByUserid(int id) {
        IdCard idCard = (IdCard)this.getSession().createQuery("FROM IdCard i WHERE i.user.id = :id").setInteger("id", id).uniqueResult();
        return idCard;
    }

    public IdCard findByUserPhone(String phone) {
        IdCard idCard = null;
        List idCards = this.getSession().createQuery("FROM IdCard i WHERE i.user.phone_number = :phone").setString("phone", phone).list();
        idCard = idCards.size() > 0?(IdCard)idCards.get(0):null;
        return idCard;
    }

    public IdCard existNum(String num) {
        List cards = this.getSession().createQuery("FROM IdCard i WHERE i.num = :num").setString("num", num).list();
        return cards.size() > 0?(IdCard)cards.get(0):null;
    }

    public void delIdCard(IdCard idcard) {
        this.getSession().delete(idcard);
    }

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
