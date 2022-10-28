package com.netguru.web;

import com.netguru.config.RedisConfig;
import com.netguru.model.GoldRate;
import com.netguru.service.GoldService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GoldController {

    private GoldService service;
    private RedisConfig config;
    private Jedis redis;

    public GoldController(GoldService service, RedisConfig config) {
        this.service = service;
        this.config = config;
        this.redis = config.redisConnectionPool();
    }

    @GetMapping("/api/nbp")
    public ResponseEntity<String> getGoldRatesFromNbpApi() {
        return ResponseEntity.status(HttpStatus.OK).body(service.ratesAsString());
    }

    @GetMapping("/api/redis")
    public ResponseEntity<String> getGoldRatesFromRedis() {
        List<String> redisGoldRates = redis.lrange("gold_rates", 0, -1);
        redisGoldRates = redisGoldRates.stream().map(element -> element+"\n").collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(redisGoldRates.toString());
    }
}
