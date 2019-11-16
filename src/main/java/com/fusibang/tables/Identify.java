//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.tables;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "identify")
public class Identify {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne(cascade = {CascadeType.ALL}, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private int step1;
    private int step2;
    private int step3;
    private int step4;
    private int step5;
    private int step6;
    private int put;
    private int lend;
    private int lend_count;
    private String lend_time;
    private int pay;
    private Timestamp put_time;
    private Timestamp pay_time;
    @Transient
    private Timestamp now;

    /**
     * 订单号
     */
    private String requestno;

    public Identify() {}

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getStep1() {
        return this.step1;
    }

    public void setStep1(int step1) {
        this.step1 = step1;
    }

    public int getStep2() {
        return this.step2;
    }

    public void setStep2(int step2) {
        this.step2 = step2;
    }

    public int getStep3() {
        return this.step3;
    }

    public void setStep3(int step3) {
        this.step3 = step3;
    }

    public int getStep4() {
        return this.step4;
    }

    public void setStep4(int step4) {
        this.step4 = step4;
    }

    public int getStep5() {
        return this.step5;
    }

    public void setStep5(int step5) {
        this.step5 = step5;
    }

    public int getPut() {
        return this.put;
    }

    public void setPut(int put) {
        this.put = put;
    }

    public int getPay() {
        return this.pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public Timestamp getPut_time() {
        return this.put_time;
    }

    public void setPut_time(Timestamp put_time) {
        this.put_time = put_time;
    }

    public Timestamp getPay_time() {
        return this.pay_time;
    }

    public void setPay_time(Timestamp pay_time) {
        this.pay_time = pay_time;
    }

    public int getStep6() {
        return this.step6;
    }

    public void setStep6(int step6) {
        this.step6 = step6;
    }

    public Timestamp getNow() {
        return this.now;
    }

    public void setNow(Timestamp now) {
        this.now = now;
    }

    public int getLend() {
        return this.lend;
    }

    public void setLend(int lend) {
        this.lend = lend;
    }

    public int getLend_count() {
        return this.lend_count;
    }

    public void setLend_count(int lend_count) {
        this.lend_count = lend_count;
    }

    public String getLend_time() {
        return this.lend_time;
    }

    public void setLend_time(String lend_time) {
        this.lend_time = lend_time;
    }

    public String getRequestno() {
        return requestno;
    }

    public void setRequestno(String requestno) {
        this.requestno = requestno;
    }
}
