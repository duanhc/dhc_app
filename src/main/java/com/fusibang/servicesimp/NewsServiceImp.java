//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.servicesimp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fusibang.dao.AppStoreDao;
import com.fusibang.dao.NewsLogDao;
import com.fusibang.dao.WeiXinDao;
import com.fusibang.help.NewTask;
import com.fusibang.help.NewTasks;
import com.fusibang.help.ResponseStatus;
import com.fusibang.services.NewsService;
import com.fusibang.tables.WeiXin;

public class NewsServiceImp extends ResponseStatus implements NewsService {
    private WeiXinDao weiXinDao;
    private NewsLogDao newsLogDao;
    private AppStoreDao appStoreDao;
    private String host;
    private String webName;

    public NewsServiceImp() {
    }

    public String addTask(HttpSession session, NewTask[] newTasks) {
        Integer adminId = (Integer)session.getAttribute("ai");
        String permission = (String)session.getAttribute("ap");
        if(adminId != null) {
            if(!permission.equals("11111") && !permission.equals("00010")) {
                return "{\"hint\":\"not_permission\"}";
            } else {
                WeiXin weixin = this.weiXinDao.findById(newTasks[0].getWxId());
                if(weixin != null) {
                    NewTasks.submitTask(newTasks, weixin, this.newsLogDao, this.host, this.webName);
                    weixin.setToday_send(1);
                    return "{\"hint\":\"success\"}";
                } else {
                    return "{\"hint\":\"illegal_request\"}";
                }
            }
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    public String cancalTask(HttpSession session, NewTask newTask) {
        Integer adminId = (Integer)session.getAttribute("ai");
        String permission = (String)session.getAttribute("ap");
        if(adminId != null) {
            if(!permission.equals("11111") && !permission.equals("00010")) {
                return "{\"hint\":\"not_permission\"}";
            } else {
                NewTasks.cancleTask(newTask.getStoreId(), newTask.getWxId());
                return "{\"hint\":\"success\"}";
            }
        } else {
            return "{\"hint\":\"un_login\"}";
        }
    }

    public String sendView(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer adminId = (Integer)session.getAttribute("ai");
        String permission = (String)session.getAttribute("ap");
        if(adminId != null) {
            if(!permission.equals("11111") && !permission.equals("00010")) {
                return "not_permission";
            } else {
                List weixins = this.weiXinDao.getUnSend();
                List apps = this.appStoreDao.getAllOnline();
                request.setAttribute("weixins", weixins);
                request.setAttribute("apps", apps);
                return "success";
            }
        } else {
            return "un_login";
        }
    }

    public String runningView(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer adminId = (Integer)session.getAttribute("ai");
        String permission = (String)session.getAttribute("ap");
        if(adminId != null) {
            if(!permission.equals("11111") && !permission.equals("00010")) {
                return "not_permission";
            } else {
                NewTask[] runs = NewTasks.getRunTask();
                List waits = NewTasks.getWaitTask();
                request.setAttribute("runs", runs);
                request.setAttribute("waits", waits);
                return "success";
            }
        } else {
            return "un_login";
        }
    }

    public String historyView(HttpServletRequest request, int page) {
        HttpSession session = request.getSession();
        Integer adminId = (Integer)session.getAttribute("ai");
        String permission = (String)session.getAttribute("ap");
        if(adminId != null) {
            if(!permission.equals("11111") && !permission.equals("00010")) {
                return "not_permission";
            } else {
                List logs = this.newsLogDao.getByPage(page);
                int count = this.newsLogDao.getCount();
                int pageCount = (int)Math.ceil((double)count / 15.0D);
                request.setAttribute("logs", logs);
                request.setAttribute("thisPage", Integer.valueOf(page));
                request.setAttribute("pageCount", Integer.valueOf(pageCount));
                return "success";
            }
        } else {
            return "un_login";
        }
    }

    public void setWeiXinDao(WeiXinDao weiXinDao) {
        this.weiXinDao = weiXinDao;
    }

    public void setNewsLogDao(NewsLogDao newsLogDao) {
        this.newsLogDao = newsLogDao;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }

    public void setAppStoreDao(AppStoreDao appStoreDao) {
        this.appStoreDao = appStoreDao;
    }
}
