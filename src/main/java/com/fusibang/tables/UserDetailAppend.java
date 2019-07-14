//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.tables;

import javax.persistence.*;

@Entity
@Table(
    name = "user_detail_append"
)
public class UserDetailAppend {
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
    private String profession;
    private String work_city;
    private String work_income;
    private String mirrage;
    private String house;
    private String car;

    public UserDetailAppend() {
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

    public String getProfession() {
        return this.profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getWork_city() {
        return this.work_city;
    }

    public void setWork_city(String work_city) {
        this.work_city = work_city;
    }

    public String getWork_income() {
        return this.work_income;
    }

    public void setWork_income(String work_income) {
        this.work_income = work_income;
    }

    public String getMirrage() {
        return this.mirrage;
    }

    public void setMirrage(String mirrage) {
        this.mirrage = mirrage;
    }

    public String getHouse() {
        return this.house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getCar() {
        return this.car;
    }

    public void setCar(String car) {
        this.car = car;
    }
}
