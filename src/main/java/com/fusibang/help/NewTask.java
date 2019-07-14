//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.help;

public class NewTask {
    private int storeId;
    private String name;
    private String wexinName;
    private String edu;
    private String timeLimit;
    private int hasSend;
    private int wxId;
    private int failSend;

    public NewTask(int wxId, int storeId, String name, String wexinName, String edu, String timeLimit) {
        this.wxId = wxId;
        this.storeId = storeId;
        this.name = name;
        this.wexinName = wexinName;
        this.edu = edu;
        this.timeLimit = timeLimit;
    }

    public int getStoreId() {
        return this.storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getHasSend() {
        return this.hasSend;
    }

    public void hasSendIncre() {
        ++this.hasSend;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEdu() {
        return this.edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getTimeLimit() {
        return this.timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getWxId() {
        return this.wxId;
    }

    public void setWxId(int wxId) {
        this.wxId = wxId;
    }

    public int getFailSend() {
        return this.failSend;
    }

    public void failSendIncre() {
        ++this.failSend;
    }

    public String getWexinName() {
        return this.wexinName;
    }

    public void setWexinName(String wexinName) {
        this.wexinName = wexinName;
    }
}
