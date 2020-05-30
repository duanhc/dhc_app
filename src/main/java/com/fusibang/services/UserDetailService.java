//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.services;

import com.fusibang.tables.UserDetail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface UserDetailService {
    String addDetail(UserDetail var1, HttpSession var2);

    String altDetail(String code,UserDetail var1, HttpSession var2);

    String view(HttpServletRequest var1);

    String modifyCreditNumber(UserDetail userDetail, HttpSession session);
}
