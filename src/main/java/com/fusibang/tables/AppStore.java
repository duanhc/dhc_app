//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.tables;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(
    name = "app_store"
)
@DynamicUpdate(true)
@DynamicInsert(true)
public class AppStore {
    @Id
    @GeneratedValue(
        strategy = GenerationType.AUTO
    )
    private int id;
    @Column(
        name = "name",
        unique = true,
        nullable = false
    )
    private String name;
    private String logo;
    private String descript;
    private int min_limit;
    private int max_limit;
    private float feilv;
    private String timelimit;
    private int rate;
    private String app_url;
    private String need_condition;
    private String price;
    private Integer priority;
    private Integer put_away;
    private int today_ua;
    private int yester_ua;
    private int all_ua;
    /**
     * 产品下架ua
     */
    @Column(nullable = true)
    private int put_away_ua;
    /**
     * 产品下架时间
     */
    @Column(nullable = true)
    private Timestamp put_away_time;
    private Date add_time;

    public AppStore() {
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

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescript() {
        return this.descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public int getMin_limit() {
        return this.min_limit;
    }

    public void setMin_limit(int min_limit) {
        this.min_limit = min_limit;
    }

    public int getMax_limit() {
        return this.max_limit;
    }

    public void setMax_limit(int max_limit) {
        this.max_limit = max_limit;
    }

    public float getFeilv() {
        return this.feilv;
    }

    public void setFeilv(float feilv) {
        this.feilv = feilv;
    }

    public String getTimelimit() {
        return this.timelimit;
    }

    public void setTimelimit(String timelimit) {
        this.timelimit = timelimit;
    }

    public int getRate() {
        return this.rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getApp_url() {
        return this.app_url;
    }

    public void setApp_url(String app_url) {
        this.app_url = app_url;
    }

    public String getNeed_condition() {
        return this.need_condition;
    }

    public void setNeed_condition(String need_condition) {
        this.need_condition = need_condition;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getPut_away() {
        return this.put_away;
    }

    public void setPut_away(Integer put_away) {
        this.put_away = put_away;
    }

    public int getToday_ua() {
        return this.today_ua;
    }

    public void setToday_ua(int today_ua) {
        this.today_ua = today_ua;
    }

    public int getYester_ua() {
        return this.yester_ua;
    }

    public void setYester_ua(int yester_ua) {
        this.yester_ua = yester_ua;
    }

    public int getAll_ua() {
        return this.all_ua;
    }

    public void setAll_ua(int all_ua) {
        this.all_ua = all_ua;
    }

    public Date getAdd_time() {
        return this.add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getPut_away_ua() {
        return put_away_ua;
    }

    public void setPut_away_ua(int put_away_ua) {
        this.put_away_ua = put_away_ua;
    }

    public Timestamp getPut_away_time() {
        return put_away_time;
    }

    public void setPut_away_time(Timestamp put_away_time) {
        this.put_away_time = put_away_time;
    }
}
