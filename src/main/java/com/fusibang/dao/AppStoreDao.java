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

import com.fusibang.help.AppStoreHelp;
import com.fusibang.tables.AppStore;

public class AppStoreDao {
    private SessionFactory sessionFactory;
    private AppStoreHelp appStoreHelp;
    private int eachCount = 18;

    public AppStoreDao() {
    }

    public List<AppStore> getAllOnline() {
        List apps = this.getSession().createQuery("FROM AppStore a WHERE a.put_away = 1").list();
        return apps;
    }

    public List<AppStore> getAppsByPage(int page, int put_away, String name) {
        int first = 18 * (page - 1);
        List apps = this.getSession().createQuery("FROM AppStore a WHERE a.put_away = :put_away AND a.name LIKE :name ORDER BY a.priority").setInteger("put_away", put_away).setString("name", "%" + name + "%").setFirstResult(first).setMaxResults(this.eachCount).list();
        return apps;
    }

    public int getCount(int put_away, String name) {
        String sql = "SELECT COUNT(*) FROM app_store WHERE put_away = " + put_away + " AND name LIKE \'%" + name + "%\'";
        List countList = this.getSession().createSQLQuery(sql).list();
        return ((BigInteger)countList.get(0)).intValue();
    }

    public void putAway(AppStore app) {
        this.findById(app.getId()).setPut_away(app.getPut_away());
        this.appStoreHelp.setAppStores();
    }

    public void delete(int id) {
        this.getSession().delete(this.findById(id));
    }

    public void priority(AppStore app) {
        this.findById(app.getId()).setPriority(app.getPriority());
        this.appStoreHelp.setAppStores();
    }

    public void add(AppStore app) {
        app.setAdd_time(new Date((new java.util.Date()).getTime()));
        this.getSession().save(app);
    }

    public AppStore findById(int id) {
        return (AppStore)this.getSession().get(AppStore.class, Integer.valueOf(id));
    }

    public String getUrl(int id) {
        return this.findById(id).getApp_url();
    }

    public boolean existName(String name) {
        return this.getSession().createQuery("FROM AppStore a WHERE a.name = :name").setString("name", name).list().size() > 0;
    }

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setAppStoreHelp(AppStoreHelp appStoreHelp) {
        this.appStoreHelp = appStoreHelp;
    }
}
