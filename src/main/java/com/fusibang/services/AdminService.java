//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fusibang.tables.Admin;

public interface AdminService {
    String loginAdmin(Admin var1, HttpSession var2);

    String addAdmin(Admin var1, HttpSession var2);

    String forbidAdmin(Admin var1, HttpSession var2);

    String forbidView(Admin var1, HttpServletRequest var2);

    String altAdminWithCreater(Admin var1, HttpSession var2);

    String altView(int var1, HttpServletRequest var2);

    String allView(Admin var1, HttpServletRequest var2);

    String manage(HttpServletRequest var1);
}
