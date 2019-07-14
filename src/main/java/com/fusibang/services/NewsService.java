//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fusibang.help.NewTask;

public interface NewsService {
    String addTask(HttpSession var1, NewTask[] var2);

    String cancalTask(HttpSession var1, NewTask var2);

    String sendView(HttpServletRequest var1);

    String runningView(HttpServletRequest var1);

    String historyView(HttpServletRequest var1, int var2);
}
