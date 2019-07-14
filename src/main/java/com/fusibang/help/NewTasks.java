//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.help;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.fusibang.dao.NewsLogDao;
import com.fusibang.tables.WeiXin;

public class NewTasks {
    private static List<NewTask[]> waitTask = new ArrayList();
    private static NewTask[] runTask = null;
    private static List<TaskThread> tasks = new ArrayList();
    private static Executor executor = Executors.newSingleThreadExecutor();

    public NewTasks() {
    }

    public static void submitTask(NewTask[] newTask, WeiXin weixin, NewsLogDao newsLogDao, String host, String webName) {
        if(weixin.getToday_send() == 0) {
            waitTask.add(newTask);
            TaskThread task = new TaskThread(newTask, weixin, newsLogDao, host, webName);
            tasks.add(task);
            executor.execute(task);
        }

    }

    public static void cancleTask(int storeId, int weixinId) {
        Iterator var3 = tasks.iterator();

        while(var3.hasNext()) {
            TaskThread waits = (TaskThread)var3.next();
            int[] status = waits.getStatus();
            if(status[0] == storeId && status[1] == weixinId) {
                waits.setGoing(false);
            }
        }

        var3 = waitTask.iterator();

        while(var3.hasNext()) {
            NewTask[] waits1 = (NewTask[])var3.next();
            if(waits1[0].getWxId() == weixinId && waits1[0].getStoreId() == storeId) {
                removeWaitTask(waits1);
            }
        }

    }

    public static void removeTask(TaskThread task) {
        tasks.remove(task);
    }

    public static NewTask[] getRunTask() {
        return runTask;
    }

    public static List<NewTask[]> getWaitTask() {
        return waitTask;
    }

    protected static void setRunTask(NewTask[] runTask) {
        NewTasks.runTask = runTask;
    }

    protected static void removeWaitTask(NewTask[] task) {
        waitTask.remove(task);
    }

    protected static void removeRunTask(NewTask[] runFinish) {
        if(runTask == runFinish) {
            runTask = null;
        }

    }
}
