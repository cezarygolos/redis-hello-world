package com.netguru.service;

import com.netguru.model.GoldRate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class GoldService {

    public GoldRate[] fetchRates() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json, charset=utf-8");
        headers.add("cache-control", "no-cache, no-store, must-revalidate");
        headers.add("expires", "0");
        headers.add("pragma", "no-cache");

        ResponseEntity<GoldRate[]> response = restTemplate.getForEntity("http://api.nbp.pl/api/cenyzlota/last/30", GoldRate[].class, null, headers);

        return response.getBody();
    }

    public String ratesAsString() {
        StringBuilder rates = new StringBuilder();
        for (GoldRate line : fetchRates()) {
            rates.append(line.toString()+"\n");
        }
        return rates.toString();
    }
}
