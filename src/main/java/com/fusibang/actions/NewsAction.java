//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.actions;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.fusibang.help.NewTask;
import com.fusibang.services.NewsService;
import com.opensymphony.xwork2.ActionSupport;

public class NewsAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
    private static final long serialVersionUID = 1L;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private String edu;
    private String timeLimit;
    private String wxinfo;
    private int wxId;
    private NewsService newsService;

    public NewsAction() {
    }

    public String addTask() {
        this.response.setContentType("application/json; charset=utf-8");
        String[] idArray = this.request.getParameterValues("storeId");
        HttpSession session = this.request.getSession();
        String result;
        if(this.wxinfo == null) {
            result = "{\"hint\":\"weixin_select\"}";
        } else if(idArray.length == 0) {
            result = "{\"hint\":\"app_select\"}";
        } else {
            this.edu = this.edu != null && !this.edu.equals("")?this.edu:"1000-8000元";
            this.timeLimit = this.timeLimit != null && !this.timeLimit.equals("")?this.timeLimit:"7-30天";
            String e = this.wxinfo.split(",")[0];
            String weixinName = this.wxinfo.split(",")[1];
            ArrayList list = new ArrayList();
            String[] var10 = idArray;
            int var9 = idArray.length;

            for(int var8 = 0; var8 < var9; ++var8) {
                String newTasks = var10[var8];
                String id = newTasks.split(",")[0];
                String name = newTasks.split(",")[1];
                list.add(new NewTask(Integer.parseInt(e), Integer.parseInt(id), name, weixinName, this.edu, this.timeLimit));
            }

            NewTask[] var14 = new NewTask[list.size()];
            list.toArray(var14);
            result = this.newsService.addTask(session, var14);
        }

        try {
            this.response.getWriter().print(result);
        } catch (IOException var13) {
            var13.printStackTrace();
        }

        return null;
    }

    public String cancleTask() {
        this.response.setContentType("application/json; charset=utf-8");
        String storeId = this.request.getParameter("storeId");
        String result = "{\"hint\":\"error\"}";
        if(storeId != null && this.wxId != 0) {
            NewTask e = new NewTask(this.wxId, Integer.parseInt(storeId), (String)null, (String)null, (String)null, (String)null);
            result = this.newsService.cancalTask(this.request.getSession(), e);
        }

        try {
            this.response.getWriter().print(result);
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return null;
    }

    public String taskAddView() {
        return this.newsService.sendView(this.request);
    }

    public String runView() {
        return this.newsService.runningView(this.request);
    }

    public String hostoryView() {
        this.wxId = this.wxId == 0?1:this.wxId;
        return this.newsService.historyView(this.request, this.wxId);
    }

    public void setServletResponse(HttpServletResponse arg0) {
        this.response = arg0;
    }

    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public void setWxinfo(String wxinfo) {
        this.wxinfo = wxinfo;
    }

    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    public void setWxId(int wxId) {
        this.wxId = wxId;
    }
}
