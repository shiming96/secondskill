package com.shiming.secondskill.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="spring.redis")
public class RedisConfig {
	private String host;
	private int port;
	private int timeout;//秒
	private String password;
	private int jedisPoolMaxActive;
	private int jedisPoolMaxIdle;
	private int jedisPoolMaxWait;//秒

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getJedisPoolMaxActive() {
        return jedisPoolMaxActive;
    }

    public void setJedisPoolMaxActive(int jedisPoolMaxActive) {
        this.jedisPoolMaxActive = jedisPoolMaxActive;
    }

    public int getJedisPoolMaxIdle() {
        return jedisPoolMaxIdle;
    }

    public void setJedisPoolMaxIdle(int jedisPoolMaxIdle) {
        this.jedisPoolMaxIdle = jedisPoolMaxIdle;
    }

    public int getJedisPoolMaxWait() {
        return jedisPoolMaxWait;
    }

    public void setJedisPoolMaxWait(int jedisPoolMaxWait) {
        this.jedisPoolMaxWait = jedisPoolMaxWait;
    }
}
