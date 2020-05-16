//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.services;

import javax.servlet.http.HttpServletRequest;

public interface AliPayService {
    String returnUrl(HttpServletRequest var1) throws Exception;

    void resultNotify(HttpServletRequest var1) throws Exception;
}
