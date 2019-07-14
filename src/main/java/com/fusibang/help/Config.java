//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.help;

import java.util.List;

public class Config {
    public static int tokenTime = 2592000;
    private String projectHost;
    private String projectName;
    private String redisHost;
    private String redisAuth;
    private int redisPort;
    private List<String> ipWhites;
    private int frequency;
    private int timeSample;
    private boolean doFilter;

    public Config() {
    }

    public int getTimeSample() {
        return this.timeSample;
    }

    public void setTimeSample(int timeSample) {
        this.timeSample = timeSample;
    }

    public boolean isDoFilter() {
        return this.doFilter;
    }

    public void setDoFilter(boolean doFilter) {
        this.doFilter = doFilter;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getRedisHost() {
        return this.redisHost;
    }

    public void setRedisHost(String redisHost) {
        this.redisHost = redisHost;
    }

    public String getRedisAuth() {
        return this.redisAuth;
    }

    public void setRedisAuth(String redisAuth) {
        this.redisAuth = redisAuth;
    }

    public String getProjectHost() {
        return this.projectHost;
    }

    public void setProjectHost(String projectHost) {
        this.projectHost = projectHost;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<String> getIpWhites() {
        return this.ipWhites;
    }

    public void setIpWhites(List<String> ipWhites) {
        this.ipWhites = ipWhites;
    }

    public void setRedisPort(int redisPort) {
        this.redisPort = redisPort;
    }

    public int getRedisPort() {
        return this.redisPort;
    }
}
