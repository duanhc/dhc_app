//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.help;

import java.util.Iterator;
import java.util.List;

import com.fusibang.dao.NewsLogDao;
import com.fusibang.tables.WeiXin;

public class TaskThread implements Runnable {
    private NewTask[] task;
    private WeiXin weixin;
    private NewsLogDao newsLogDao;
    private boolean going = true;
    private String host;
    private String webName;

    public TaskThread(NewTask[] task, WeiXin weixin, NewsLogDao newsLogDao, String host, String webName) {
        this.task = task;
        this.weixin = weixin;
        this.newsLogDao = newsLogDao;
        this.host = host;
        this.webName = webName;
    }

    public void run() {
        try {
            NewTasks.setRunTask(this.task);
            NewTasks.removeWaitTask(this.task);
            UserList e = new UserList(this.weixin);
            int i = 0;
            int taskLen = this.task.length;

            while(this.going && e.hasNext()) {
                List list = e.getNextUsers();
                if(list != null) {
                    for(Iterator it = list.iterator(); this.going && it.hasNext(); Thread.yield()) {
                        String openid = (String)it.next();
                        if(News.sendNews(AccessToken.getToken(this.weixin), openid, this.weixin.getTemplate(), this.task[i].getName(), "http://" + this.host + "/" + this.webName + "/app_redirect.do?id=" + this.task[i].getStoreId(), this.task[i].getEdu(), this.task[i].getTimeLimit())) {
                            this.task[i].hasSendIncre();
                        } else {
                            this.task[i].failSendIncre();
                        }

                        ++i;
                        if(i >= taskLen) {
                            i = 0;
                        }
                    }
                } else {
                    NewTasks.removeRunTask(this.task);
                    NewTasks.removeTask(this);
                    this.newsLogDao.add(this.task, this.weixin);
                }
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    public int[] getStatus() {
        return new int[]{this.task[0].getStoreId(), this.weixin.getId()};
    }

    public void setGoing(boolean going) {
        this.going = going;
    }
}
