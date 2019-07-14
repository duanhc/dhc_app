//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.help;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import redis.clients.jedis.Jedis;

public class LoginFilter implements Filter {
    private JedisFactory jedisFactory;

    public LoginFilter() {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)arg0;
        HttpServletResponse response = (HttpServletResponse)arg1;
        Cookie[] cookies = request.getCookies();
        String phone = null;
        String token = null;
        if(cookies != null) {
            Cookie[] var12 = cookies;
            int session = cookies.length;

            for(int id_str = 0; id_str < session; ++id_str) {
                Cookie jedis = var12[id_str];
                if(jedis.getName().equals("token")) {
                    token = jedis.getValue();
                } else if(jedis.getName().equals("phone")) {
                    phone = jedis.getValue();
                }
            }
        }

        Jedis var15 = this.jedisFactory.getInstance();
        if(phone != null && token != null) {
            if(token.equals(var15.get(phone))) {
                String var13 = var15.get(phone + "id");
                HttpSession var14 = request.getSession();
                if(var13 != null) {
                    var14.setAttribute("ui", Integer.valueOf(Integer.parseInt(var13)));
                }

                var14.setAttribute("u", phone);
                chain.doFilter(arg0, arg1);
            }
        } else {
            response.getWriter().write("{\"hint\":\"un_login\"}");
        }

        var15.close();
    }

    public void init(FilterConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        WebApplicationContext w = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        this.jedisFactory = (JedisFactory)w.getBean("jedisFactory");
    }
}
