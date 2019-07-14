//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.tables;

import javax.persistence.*;

@Entity
@Table(
    name = "admin_detail"
)
public class AdminDetail {
    @Id
    @GeneratedValue(
        strategy = GenerationType.AUTO
    )
    private int id;
    private String real_name;
    private String permission;
    private String name;
    private int forbid;
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

    public AdminDetail() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReal_name() {
        return this.real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getPermission() {
        return this.permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getForbid() {
        return this.forbid;
    }

    public void setForbid(int forbid) {
        this.forbid = forbid;
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

    public int getPay() {
        return this.pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
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
}
