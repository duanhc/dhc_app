//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.help;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fusibang.tables.*;

public class InitialListenter implements ServletContextListener {
    private static final Logger logger = Logger.getLogger(InitialListenter.class);
    private static boolean runing = true;

    public InitialListenter() {
    }

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        WebApplicationContext w = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        final SessionFactory sessionFactory = (SessionFactory)w.getBean("sessionFactory");
        final Integer weixin = Integer.valueOf(Integer.parseInt(context.getInitParameter("weixin")));
        final String app = context.getInitParameter("app");
        final String host = context.getInitParameter("host");
        final String webName = context.getInitParameter("webName");
        runing = context.getInitParameter("send").equals("true");
        (new Thread(new Runnable() {
            public void run() {
                while(InitialListenter.runing) {
                    Session session = sessionFactory.openSession();

                    try {
                        Transaction e = session.beginTransaction();
                        WeiXin weiXin = (WeiXin)session.get(WeiXin.class, weixin);
                        if(weixin != null && app != null) {
                            List users = session.createQuery("FROM User u WHERE u.send != 1 AND u.send < 8 AND u.openid is not null").list();
                            String token = AccessToken.getToken(weiXin);

                            for(Iterator var7 = users.iterator(); var7.hasNext(); Thread.yield()) {
                                User user = (User)var7.next();
                                Date now = new Date();
                                Identify identify = (Identify)session.createQuery("FROM Identify i WHERE i.user.id = :id").setInteger("id", user.getId()).uniqueResult();
                                Lend lend = (Lend)session.createQuery("FROM Lend l WHERE l.user.id = :id").setInteger("id", user.getId()).uniqueResult();
                                if(lend != null && lend.getStatus() == 1) {
                                    long time = (now.getTime() - lend.getVerify_time().getTime()) / 1000L;
                                    if(identify.getPay() == 1) {
                                        time = (now.getTime() - identify.getPay_time().getTime()) / 1000L;
                                    }

                                    boolean radio = false;
                                    boolean success = false;
                                    if(time > 201600L) {
                                        radio = true;
                                        success = true;
                                    } else {
                                        int radio1;
                                        if(time > 3600L && time <= 201600L) {
                                            radio1 = (int)(23L + 7L * (time - 3600L) / 18000L);
                                            if(identify.getPay() == 1 || radio1 >= 100) {
                                                radio = true;
                                                success = true;
                                            }
                                        } else if(time < 3600L) {
                                            radio1 = (int)(time / 180L + 3L);
                                            if(identify.getPay() == 1) {
                                                radio1 = (int)(time / 40L + 10L);
                                                if(radio1 >= 100) {
                                                    radio = true;
                                                    success = true;
                                                }
                                            }
                                        }
                                    }

                                    if(success) {
                                        if(News.sendNews(token, user.getOpenid(), weiXin.getTemplate(), app, "http://" + host + "/" + webName + "/app_jieguo.do", lend.getLend_count() + "元", lend.getLend_time())) {
                                            user.setSend(1);
                                            NewsLog log = new NewsLog();
                                            log.setApp_name(app);
                                            log.setWeixin_name(weiXin.getName() + ":" + user.getPhone_number());
                                            log.setSend_count(1);
                                            log.setSend_time(new java.sql.Date(now.getTime()));
                                            session.save(log);
                                        } else {
                                            InitialListenter.logger.info("listenter send news faild, user:" + user.getPhone_number());
                                            user.setSend(user.getSend() + 2);
                                        }
                                    }
                                }
                            }
                        } else {
                            InitialListenter.logger.info("initialListenter weixin is null:" + (weiXin == null) + ",appStore is null:" + (app == null));
                        }

                        e.commit();
                    } catch (Exception var21) {
                        var21.printStackTrace();
                    } finally {
                        session.close();
                    }

                    try {
                        Thread.sleep(300000L);
                    } catch (InterruptedException var20) {
                        var20.printStackTrace();
                    }
                }

            }
        })).start();
    }

    public void contextDestroyed(ServletContextEvent sce) {
        runing = false;
    }

    public synchronized void switchNews(boolean run) {
        runing = run;
    }
}
