//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.services;

import javax.servlet.http.HttpSession;

public interface PhoneVerifyService {
    String send2Registe(String var1, HttpSession var2);

    String send2ChmodPwd(String var1, HttpSession var2);

    String send2Login(String var1, HttpSession var2);

    String send2bankcard(String phone, HttpSession session);
}
