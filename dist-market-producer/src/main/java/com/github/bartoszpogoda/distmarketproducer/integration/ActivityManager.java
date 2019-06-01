package com.github.bartoszpogoda.distmarketproducer.integration;

import com.github.bartoszpogoda.distmarketproducer.dto.UpdateSupplierActiveDto;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ActivityManager implements InitializingBean, DisposableBean {

    @Value("${marketplace.api.updateDataUrl}")
    private String updateDataUrl;

    @Value("${marketplace.api.key}")
    private String apiKey;


    private final RestTemplate restTemplate;

    public ActivityManager(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("ActivityManager - init");
        restTemplate.put(updateDataUrl, buildHttpEntity(true));
    }

    @Override
    public void destroy() throws Exception {
        restTemplate.put(updateDataUrl, buildHttpEntity(false));
    }

    private HttpEntity<UpdateSupplierActiveDto> buildHttpEntity(boolean active) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("API-KEY", apiKey);
        UpdateSupplierActiveDto dto = UpdateSupplierActiveDto.builder().active(active).build();

        return new HttpEntity<>(dto, headers);
    }
}
