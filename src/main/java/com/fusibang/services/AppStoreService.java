//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fusibang.tables.AppStore;

public interface AppStoreService {
    String add(AppStore var1, HttpSession var2);

    String alt(AppStore var1, HttpSession var2);

    String put(AppStore var1, HttpSession var2);

    String priority(AppStore var1, HttpSession var2);

    String delete(int var1, HttpSession var2);

    String getUrl(int var1, HttpSession var2);

    String getQRCode(int var1, HttpServletRequest var2, HttpServletResponse var3);

    String altView(int var1, HttpServletRequest var2);

    String putView(AppStore var1, HttpServletRequest var2);

    String downView(AppStore var1, HttpServletRequest var2);

    String marketView(HttpServletRequest var1);

    String jieguotView(HttpServletRequest var1);
}
