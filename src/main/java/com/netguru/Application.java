package com.netguru;

import com.netguru.model.GoldRate;
import com.netguru.service.GoldService;
import org.springframework.boot.SpringApplication;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class Application {

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
        System.out.println("Hello world!");

        GoldService service = new GoldService();
        List<GoldRate> goldRates = Arrays.asList(service.fetchRates());
//        System.out.println("NPB API gold rates:");
//        Stream.of(service.fetchRates()).forEach(System.out::println);
        JedisPool pool = new JedisPool("localhost", 55409);

        try (Jedis jedis = pool.getResource()) {
            String list = "gold_rates";
            for (GoldRate rate : goldRates) {
                jedis.lpush(list, rate.toString());
            }
            List<String> redisGoldRates = jedis.lrange(list, 0, -1);
//            System.out.println("Redis gold rates:");
//            redisGoldRates.forEach(System.out::println);
        }
    }
}
