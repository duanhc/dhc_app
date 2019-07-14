//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.tables;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(
    name = "pay"
)
public class Pay {
    @Id
    @GeneratedValue(
        strategy = GenerationType.AUTO
    )
    private int id;
    @OneToOne(
        cascade = {CascadeType.ALL},
        optional = false,
        fetch = FetchType.LAZY
    )
    @JoinColumn(
        name = "user_id"
    )
    private User user;
    private String indent_str;
    private String order_str;
    private int status;
    private float amount;
    private Timestamp submit_time;
    private Timestamp pay_time;

    public Pay() {
    }

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

    public String getIndent_str() {
        return this.indent_str;
    }

    public void setIndent_str(String indent_str) {
        this.indent_str = indent_str;
    }

    public String getOrder_str() {
        return this.order_str;
    }

    public void setOrder_str(String order_str) {
        this.order_str = order_str;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getAmount() {
        return this.amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Timestamp getSubmit_time() {
        return this.submit_time;
    }

    public void setSubmit_time(Timestamp submit_time) {
        this.submit_time = submit_time;
    }

    public Timestamp getPay_time() {
        return this.pay_time;
    }

    public void setPay_time(Timestamp pay_time) {
        this.pay_time = pay_time;
    }
}
