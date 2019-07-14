//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fusibang.tables.Channel;

public interface ChannelService {
    String addChannel(Channel var1, HttpSession var2);

    String addView(HttpServletRequest var1);

    String channelView(Channel var1, HttpServletRequest var2);

    String altChannel(Channel var1, HttpSession var2);

    String altView(int var1, HttpServletRequest var2);
}
