//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.services;

import com.fusibang.tables.Lend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface LendSercice {
    String addLend(Lend var1, HttpSession var2);

    String altLendTime(Lend var1, HttpSession var2);

    String verifyLend(HttpSession var1);

    String verifyView(HttpServletRequest var1);

    String collectView(HttpServletRequest var1, HttpServletResponse var2);
}
