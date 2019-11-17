//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface IdentifyService {
    String getIdentify(HttpSession var1);

    String IdentifySubmit(HttpSession var1);

    String authCenter(HttpServletRequest var1);

    String showApp(HttpServletRequest var1);

    String volaty();

    String version();

    String faxian(HttpServletRequest var1);

    String authVerify(HttpServletRequest request);
}
