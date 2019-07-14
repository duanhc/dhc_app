//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fusibang.tables.UserContact;

public interface UserContactService {
    String addContact(HttpSession var1, UserContact var2);

    String altContact(HttpSession var1, UserContact var2);

    String view(HttpServletRequest var1);
}
