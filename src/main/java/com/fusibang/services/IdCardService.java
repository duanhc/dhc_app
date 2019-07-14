//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fusibang.tables.IdCard;

public interface IdCardService {
    String saveFont(HttpSession var1, IdCard var2);

    String saveBack(HttpSession var1, IdCard var2);

    String authFront(HttpServletRequest var1, String var2);

    String authBack(HttpServletRequest var1, String var2);

    String view(HttpServletRequest var1);
}
