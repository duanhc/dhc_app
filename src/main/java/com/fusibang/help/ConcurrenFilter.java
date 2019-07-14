//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.help;

import java.io.IOException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import redis.clients.jedis.Jedis;

public class ConcurrenFilter implements Filter {
    private static final Logger logger = Logger.getLogger(ConcurrenFilter.class);
    private JedisFactory jedisFactory;
    private List<String> ipWhite;
    private int frequency;
    private int timeSample;
    private boolean isdoFilter;

    public ConcurrenFilter() {
    }

    public void init(FilterConfig fileterconfig) throws ServletException {
        ServletContext context = fileterconfig.getServletContext();
        WebApplicationContext w = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        this.jedisFactory = (JedisFactory)w.getBean("jedisFactory");
        Config config = (Config)w.getBean("config");
        this.ipWhite = config.getIpWhites();
        this.frequency = config.getFrequency();
        this.timeSample = config.getTimeSample();
        this.isdoFilter = config.isDoFilter();
    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)arg0;
        HttpServletResponse response = (HttpServletResponse)arg1;
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if(this.isdoFilter && !this.ipWhite.contains(ip)) {
            Jedis jedis = this.jedisFactory.getInstance();

            try {
                String e = jedis.get(ip);
                long ttl = jedis.ttl(ip).longValue();
                if(ttl >= 0L && e != null && !e.equals("forbid")) {
                    int amount = Integer.parseInt(e);
                    if(amount < this.timeSample * this.frequency) {
                        jedis.incr(ip);
                        chain.doFilter(arg0, arg1);
                    } else {
                        String agent = request.getHeader("User-Agent");
                        String ref = request.getHeader("Referer");
                        if(ref == null && !agent.contains("Windows") && !agent.contains("iPhone") && !agent.contains("Linux") && !agent.contains("Android") && !agent.contains("Mozilla") && !agent.contains("Opera") && !agent.contains("MQQBrowser") && !agent.contains("UCWEB7") && !agent.contains("NOKIA5700")) {
                            jedis.set(ip, "forbid");
                            jedis.expire(ip, 2592000);
                            logger.info("forbid ip:" + ip + " 30 days");
                        } else {
                            long pass = (long)this.timeSample - ttl + 1L;
                            double avg = (double)((long)amount / pass);
                            if(avg < (double)(this.frequency * 2)) {
                                jedis.set(ip, "forbid");
                                jedis.expire(ip, 600);
                                logger.info("forbid ip:" + ip + " 10 mins");
                            } else if(avg < (double)(this.frequency * 10)) {
                                jedis.set(ip, "forbid");
                                jedis.expire(ip, 3600);
                                logger.info("forbid ip:" + ip + " 1 hours");
                            } else if(avg >= (double)(this.frequency * 10)) {
                                jedis.set(ip, "forbid");
                                jedis.expire(ip, 86400);
                                logger.info("forbid ip:" + ip + " 1 days");
                            }
                        }

                        response.setStatus(403);
                    }
                } else if(ttl <= 0L) {
                    jedis.set(ip, "1");
                    jedis.expireAt(ip, System.currentTimeMillis() / 1000L + (long)this.timeSample);
                    chain.doFilter(arg0, arg1);
                } else if(e != null && e.equals("forbid")) {
                    response.setStatus(403);
                }
            } catch (Exception var21) {
                var21.printStackTrace();
            } finally {
                jedis.close();
            }
        } else {
            chain.doFilter(arg0, arg1);
        }

    }

    public void destroy() {
    }
}
