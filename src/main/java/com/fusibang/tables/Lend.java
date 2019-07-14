//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.tables;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(
    name = "lend_status"
)
public class Lend {
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
    private int lend_count;
    private String lend_time;
    private int status;
    private Timestamp verify_time;

    public Lend() {
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

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getVerify_time() {
        return this.verify_time;
    }

    public void setVerify_time(Timestamp verify_time) {
        this.verify_time = verify_time;
    }
}
