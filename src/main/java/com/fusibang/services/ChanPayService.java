//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.services;

import javax.servlet.http.HttpServletRequest;

public interface ChanPayService {
    String BankCarAuth(HttpServletRequest var1);

    String BankCarNotify(HttpServletRequest var1);

    String authPay(HttpServletRequest var1);

    String payNotify(HttpServletRequest var1);
}
