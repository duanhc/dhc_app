//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fusibang.help;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisFactory {
    private String host;
    private String auth;
    private JedisPool pool;

    public JedisFactory() {
    }

    public Jedis getInstance() {
        Jedis jedis = this.pool.getResource();
        jedis.auth(this.auth);
        jedis.select(1);
        return jedis;
    }

    public void setConfig(Config config) {
        this.host = config.getRedisHost();
        this.auth = config.getRedisAuth();
        this.pool = new JedisPool(this.host, config.getRedisPort());
    }
}
