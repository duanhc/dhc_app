//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.tables;

import javax.persistence.*;

@Entity
@Table(
    name = "admin_info"
)
public class Admin {
    @Id
    @GeneratedValue(
        strategy = GenerationType.AUTO
    )
    private int id;
    private String name;
    private String password;
    private String real_name;
    private int forbid;
    private String permission;
    @ManyToOne(
        cascade = {CascadeType.ALL},
        optional = false,
        fetch = FetchType.LAZY
    )
    @JoinColumn(
        name = "creater_id"
    )
    private Admin creater;

    public Admin() {
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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReal_name() {
        return this.real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public int getForbid() {
        return this.forbid;
    }

    public void setForbid(int forbid) {
        this.forbid = forbid;
    }

    public String getPermission() {
        return this.permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Admin getCreater() {
        return this.creater;
    }

    public void setCreater(Admin creater) {
        this.creater = creater;
    }
}
