//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.tables;

import javax.persistence.*;

@Entity
@Table(
    name = "bank_auth"
)
public class BankAuth {
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
    private String trxId;
    private String merUserId;
    private String card_begin;
    private String card_end;
    private String bank_name;
    private int status;

    public BankAuth() {
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

    public String getTrxId() {
        return this.trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    public String getMerUserId() {
        return this.merUserId;
    }

    public void setMerUserId(String merUserId) {
        this.merUserId = merUserId;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCard_begin() {
        return this.card_begin;
    }

    public void setCard_begin(String card_begin) {
        this.card_begin = card_begin;
    }

    public String getCard_end() {
        return this.card_end;
    }

    public void setCard_end(String card_end) {
        this.card_end = card_end;
    }

    public String getBank_name() {
        return this.bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }
}
