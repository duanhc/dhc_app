//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.tables;

import javax.persistence.*;

@Entity
@Table(
    name = "id_card"
)
public class IdCard {
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
    private String gender;
    private String nation;
    private String num;
    private String address;
    private String issueAuthority;
    private String signDate;
    private String expiryDate;
    private int font;
    private int back;

    public IdCard() {
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNation() {
        return this.nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getNum() {
        return this.num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIssueAuthority() {
        return this.issueAuthority;
    }

    public void setIssueAuthority(String issueAuthority) {
        this.issueAuthority = issueAuthority;
    }

    public String getSignDate() {
        return this.signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getFont() {
        return this.font;
    }

    public void setFont(int font) {
        this.font = font;
    }

    public int getBack() {
        return this.back;
    }

    public void setBack(int back) {
        this.back = back;
    }
}
