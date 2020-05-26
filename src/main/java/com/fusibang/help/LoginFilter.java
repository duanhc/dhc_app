//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.help;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    private static final Logger logger = Logger.getLogger(LoginFilter.class);

    /**
     * 生产时间(ms)
     */
    // TODO: 2020-05-26
    public static final long PRODUCTION_TIME = 1590485243000L;

    /**
     * 持续有效时间（30天）
     */
    public static final long EXPIRING_TIME = 30 * 24 * 60 * 60 * 1000L;

    /**
     * 还剩最后五天时报警
     * 报警时间（5天）
     */
    public static final long ALARM_TIME = 5 * 24 * 60 * 60 * 1000L;

    private JedisFactory jedisFactory;

    public LoginFilter() {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)arg0;
        HttpServletResponse response = (HttpServletResponse)arg1;

        if((System.currentTimeMillis() - PRODUCTION_TIME) >= EXPIRING_TIME){
            logger.error("service invalid");
            //服务过期
            response.getWriter().write("{\"hint\":\"un_login\"}");
        }else{
            if((EXPIRING_TIME - (System.currentTimeMillis() - PRODUCTION_TIME)) <= ALARM_TIME){
                //还剩5天的时候报警
                logger.error("service will invalid");
            }
        }

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
