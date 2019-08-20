//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.tables;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(
    name = "user_detail"
)
public class UserDetail {
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
    private String name;
    private String id_card;
    private String edu_degree;
    private String city;
    private int credit_sorce;
//    private String credit_number;
//    private String credit_name;
//    private String reserved_number;
    private Timestamp put_time;

    public UserDetail() {
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

    public String getId_card() {
        return this.id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getEdu_degree() {
        return this.edu_degree;
    }

    public void setEdu_degree(String edu_degree) {
        this.edu_degree = edu_degree;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCredit_sorce() {
        return this.credit_sorce;
    }

    public void setCredit_sorce(int credit_sorce) {
        this.credit_sorce = credit_sorce;
    }

    /*public String getCredit_number() {
        return this.credit_number;
    }

    public void setCredit_number(String credit_number) {
        this.credit_number = credit_number;
    }

    public String getCredit_name() {
        return this.credit_name;
    }

    public void setCredit_name(String credit_name) {
        this.credit_name = credit_name;
    }

    public String getReserved_number() {
        return this.reserved_number;
    }

    public void setReserved_number(String reserved_number) {
        this.reserved_number = reserved_number;
    }*/

    public Timestamp getPut_time() {
        return this.put_time;
    }

    public void setPut_time(Timestamp put_time) {
        this.put_time = put_time;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
