//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.tables;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(
    name = "user_contact"
)
public class UserContact {
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
    private String relation1;
    private String name1;
    private String phone1;
    private String relation2;
    private String name2;
    private String phone2;
    private Timestamp time;

    public UserContact() {
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

    public String getRelation1() {
        return this.relation1;
    }

    public void setRelation1(String relation1) {
        this.relation1 = relation1;
    }

    public String getName1() {
        return this.name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getPhone1() {
        return this.phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getRelation2() {
        return this.relation2;
    }

    public void setRelation2(String relation2) {
        this.relation2 = relation2;
    }

    public String getName2() {
        return this.name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getPhone2() {
        return this.phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public Timestamp getTime() {
        return this.time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
