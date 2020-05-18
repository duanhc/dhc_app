package com.fusibang.help;

import com.fusibang.tables.Identify;
import com.fusibang.tables.Lend;
import com.fusibang.tables.User;
import com.fusibang.tables.UserDetail;

/**
* 用户信息类，用来存放资料审核的相关信息
*/
public class UserInfo{
    public User user;
    public UserDetail userDetail;
    public Identify identify;
    public Lend lend;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public Identify getIdentify() {
        return identify;
    }

    public void setIdentify(Identify identify) {
        this.identify = identify;
    }

    public Lend getLend() {
        return lend;
    }

    public void setLend(Lend lend) {
        this.lend = lend;
    }
}