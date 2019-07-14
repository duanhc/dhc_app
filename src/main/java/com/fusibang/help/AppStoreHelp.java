//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.help;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fusibang.tables.AppStore;

public class AppStoreHelp {
    private SessionFactory sessionFactory;
    private List<AppStore> appStoresAsOrder;
    private List<AppStore> appStoresAsTime;
    private List<AppStore> appStoresAsUa;
    private List<AppStore> appStoresHidden;
    private String jsonAsOrder;
    private String jsonAsTime;
    private String jsonAsUa;

    public AppStoreHelp() {
    }

    public synchronized void setAppStores() {
        Session session = this.sessionFactory.openSession();

        try {
            Transaction e = session.beginTransaction();
            this.appStoresHidden = session.createQuery("FROM AppStore a WHERE a.priority < 0 AND a.put_away = 1 ORDER BY a.priority,a.id").list();
            this.appStoresAsOrder = session.createQuery("FROM AppStore a WHERE a.priority > 0 AND a.put_away = 1 ORDER BY a.priority,a.id").list();
            this.appStoresAsTime = session.createQuery("FROM AppStore a WHERE a.priority > 0 AND a.put_away = 1 ORDER BY a.id DESC").list();
            this.appStoresAsUa = session.createQuery("FROM AppStore a WHERE a.priority > 0 AND a.put_away = 1 ORDER BY a.all_ua DESC,a.id ASC").list();
            this.jsonAsOrder = JSONArray.parseArray(JSON.toJSONString(this.appStoresAsOrder)).toString();
            this.jsonAsTime = JSONArray.parseArray(JSON.toJSONString(this.appStoresAsTime)).toString();
            this.jsonAsUa = JSONArray.parseArray(JSON.toJSONString(this.appStoresAsUa)).toString();
            e.commit();
        } catch (Exception var6) {
            var6.printStackTrace();
        } finally {
            session.close();
        }

    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.setAppStores();
    }

    public List<AppStore> getAppStoresHidden() {
        return this.appStoresHidden;
    }

    public List<AppStore> getAppStoresAsOrder() {
        return this.appStoresAsOrder;
    }

    public void setAppStoresAsOrder(List<AppStore> appStoresAsOrder) {
        this.appStoresAsOrder = appStoresAsOrder;
    }

    public List<AppStore> getAppStoresAsTime() {
        return this.appStoresAsTime;
    }

    public void setAppStoresAsTime(List<AppStore> appStoresAsTime) {
        this.appStoresAsTime = appStoresAsTime;
    }

    public List<AppStore> getAppStoresAsUa() {
        return this.appStoresAsUa;
    }

    public void setAppStoresAsUa(List<AppStore> appStoresAsUa) {
        this.appStoresAsUa = appStoresAsUa;
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public String getJsonAsOrder() {
        return this.jsonAsOrder;
    }

    public String getJsonAsTime() {
        return this.jsonAsTime;
    }

    public String getJsonAsUa() {
        return this.jsonAsUa;
    }
}
