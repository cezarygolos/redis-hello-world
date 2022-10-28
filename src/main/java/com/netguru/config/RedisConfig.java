package com.netguru.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Configuration
public class RedisConfig {

    @Bean
    public Jedis redisConnectionPool() {
        JedisPool pool = new JedisPool("localhost", 55409);
        return pool.getResource();
    }
}
