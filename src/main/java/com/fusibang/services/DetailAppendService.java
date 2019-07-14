//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.services;

import javax.servlet.http.HttpSession;

import com.fusibang.tables.UserDetailAppend;

public interface DetailAppendService {
    String appendDetail(HttpSession var1, UserDetailAppend var2);

    String altDetail(HttpSession var1, UserDetailAppend var2);
}
