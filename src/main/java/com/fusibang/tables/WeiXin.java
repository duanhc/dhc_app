//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.tables;

import javax.persistence.*;

@Entity
@Table(
    name = "weixin"
)
public class WeiXin {
    @Id
    @GeneratedValue(
        strategy = GenerationType.AUTO
    )
    private int id;
    private String name;
    private String appid;
    private String appset;
    private String template;
    private int today_send;

    public WeiXin() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppid() {
        return this.appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppset() {
        return this.appset;
    }

    public void setAppset(String appset) {
        this.appset = appset;
    }

    public String getTemplate() {
        return this.template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public int getToday_send() {
        return this.today_send;
    }

    public void setToday_send(int today_send) {
        this.today_send = today_send;
    }
}
