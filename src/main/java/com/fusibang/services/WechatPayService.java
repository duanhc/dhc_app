//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.services;

import javax.servlet.http.HttpServletRequest;

public interface WechatPayService {
    String placeOrder(HttpServletRequest var1);

    String resultNotify(HttpServletRequest var1);
}
