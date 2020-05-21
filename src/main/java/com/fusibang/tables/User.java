//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.tables;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(
    name = "user_info"
)
public class User {
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
        name = "channel_id"
    )
    private Channel channel;
    private String phone_number;
    private String password;
    private int valid;
    private int top_one;
    private int top_three;
    private String salt;
    private Timestamp registe_time;
    private String openid;
    private int send;
    private int pay;
    @Transient
    private int source;

    /**
     * 展示代超，0：不展示，1：展示
     */
    private int show_market;

    public User() {
    }

    public int getSend() {
        return this.send;
    }

    public void setSend(int send) {
        this.send = send;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone_number() {
        return this.phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Channel getChannel() {
        return this.channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public int getValid() {
        return this.valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public int getTop_one() {
        return this.top_one;
    }

    public void setTop_one(int top_one) {
        this.top_one = top_one;
    }

    public int getTop_three() {
        return this.top_three;
    }

    public void setTop_three(int top_three) {
        this.top_three = top_three;
    }

    public Timestamp getRegiste_time() {
        return this.registe_time;
    }

    public void setRegiste_time(Timestamp registe_time) {
        this.registe_time = registe_time;
    }

    public String getSalt() {
        return this.salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getSource() {
        return this.source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public String getOpenid() {
        return this.openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public int getPay() {
        return this.pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public int getShow_market() {
        return show_market;
    }

    public void setShow_market(int show_market) {
        this.show_market = show_market;
    }
}
