//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.tables;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(
    name = "send_log"
)
public class NewsLog {
    @Id
    @GeneratedValue(
        strategy = GenerationType.AUTO
    )
    private int id;
    private String weixin_name;
    private String app_name;
    private Date send_time;
    private int send_count;
    private int fail_count;

    public NewsLog() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeixin_name() {
        return this.weixin_name;
    }

    public void setWeixin_name(String weixin_name) {
        this.weixin_name = weixin_name;
    }

    public String getApp_name() {
        return this.app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public Date getSend_time() {
        return this.send_time;
    }

    public void setSend_time(Date send_time) {
        this.send_time = send_time;
    }

    public int getSend_count() {
        return this.send_count;
    }

    public void setSend_count(int send_count) {
        this.send_count = send_count;
    }

    public int getFail_count() {
        return this.fail_count;
    }

    public void setFail_count(int fail_count) {
        this.fail_count = fail_count;
    }
}
