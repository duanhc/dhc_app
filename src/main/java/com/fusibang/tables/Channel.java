//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.tables;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(
    name = "channel"
)
public class Channel {
    @Id
    @GeneratedValue(
        strategy = GenerationType.AUTO
    )
    private int id;
    @ManyToOne(
        fetch = FetchType.EAGER,
        cascade = {CascadeType.PERSIST},
        optional = true
    )
    @JoinColumn(
        name = "creater_id"
    )
    private Admin creater;
    @ManyToOne(
        fetch = FetchType.EAGER,
        cascade = {CascadeType.PERSIST},
        optional = true
    )
    @JoinColumn(
        name = "viewer_id"
    )
    private Admin viewer;
    private String name;
    private int price;
    private int today_click;
    private int yester_click;
    private int all_click;
    private int today_ua;
    private int yester_ua;
    private int all_ua;
    private int pay1;
    private int pay2;
    private int pay3;
    private int pay4;
    private int pay5;
    private int pay6;
    private int pay7;
    private int pay;
    private int month1;
    private int month2;
    private float today_income;
    private float yester_income;
    private float all_income;
    private Date add_time;
    private int online;

    public Channel() {
    }

    public Channel(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Admin getCreater() {
        return this.creater;
    }

    public void setCreater(Admin creater) {
        this.creater = creater;
    }

    public Admin getViewer() {
        return this.viewer;
    }

    public void setViewer(Admin viewer) {
        this.viewer = viewer;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getToday_click() {
        return this.today_click;
    }

    public void setToday_click(int today_click) {
        this.today_click = today_click;
    }

    public int getYester_click() {
        return this.yester_click;
    }

    public void setYester_click(int yester_click) {
        this.yester_click = yester_click;
    }

    public int getAll_click() {
        return this.all_click;
    }

    public void setAll_click(int all_click) {
        this.all_click = all_click;
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

    public int getPay1() {
        return this.pay1;
    }

    public void setPay1(int pay1) {
        this.pay1 = pay1;
    }

    public int getPay2() {
        return this.pay2;
    }

    public void setPay2(int pay2) {
        this.pay2 = pay2;
    }

    public int getPay3() {
        return this.pay3;
    }

    public void setPay3(int pay3) {
        this.pay3 = pay3;
    }

    public int getPay4() {
        return this.pay4;
    }

    public void setPay4(int pay4) {
        this.pay4 = pay4;
    }

    public int getPay5() {
        return this.pay5;
    }

    public void setPay5(int pay5) {
        this.pay5 = pay5;
    }

    public int getPay6() {
        return this.pay6;
    }

    public void setPay6(int pay6) {
        this.pay6 = pay6;
    }

    public int getPay7() {
        return this.pay7;
    }

    public void setPay7(int pay7) {
        this.pay7 = pay7;
    }

    public int getMonth1() {
        return this.month1;
    }

    public void setMonth1(int month1) {
        this.month1 = month1;
    }

    public int getMonth2() {
        return this.month2;
    }

    public void setMonth2(int month2) {
        this.month2 = month2;
    }

    public float getToday_income() {
        return this.today_income;
    }

    public void setToday_income(float today_income) {
        this.today_income = today_income;
    }

    public float getYester_income() {
        return this.yester_income;
    }

    public void setYester_income(float yester_income) {
        this.yester_income = yester_income;
    }

    public float getAll_income() {
        return this.all_income;
    }

    public void setAll_income(float all_income) {
        this.all_income = all_income;
    }

    public Date getAdd_time() {
        return this.add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    public int getOnline() {
        return this.online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getPay() {
        return this.pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }
}
