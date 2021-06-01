//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.services;

import com.fusibang.tables.AppStore;
import com.fusibang.tables.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface UserService {
    String loginUser(String var1, String var2, HttpSession var3);

    String loginByPhone(String var1, String var2, HttpSession var3);

    String addUser(User var1, HttpSession var2);

    String logout(User var1, HttpSession var2);

    String altUser(User var1, HttpSession var2);

    String usersView(User var1, HttpServletRequest var2);

    String setValid(User var1, HttpSession var2);

    String getAll(HttpServletRequest var1);

    String getAllRt(HttpServletRequest var1);

    String delUser(User var1, HttpSession var2);

    boolean existUser(String phone_number);

    User getById(Integer userId);

    AppStore findApp(Integer appId);
}
